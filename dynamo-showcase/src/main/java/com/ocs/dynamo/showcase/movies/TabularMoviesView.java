/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.ocs.dynamo.showcase.movies;

import java.util.List;

import javax.inject.Inject;

import com.ocs.dynamo.domain.model.AttributeModel;
import com.ocs.dynamo.domain.model.EntityModel;
import com.ocs.dynamo.service.BaseService;
import com.ocs.dynamo.showcase.Views;
import com.ocs.dynamo.ui.component.DefaultVerticalLayout;
import com.ocs.dynamo.ui.component.EntityComboBox;
import com.ocs.dynamo.ui.composite.form.FormOptions;
import com.ocs.dynamo.ui.composite.layout.TabularEditLayout;
import com.ocs.dynamo.ui.view.BaseView;
import com.vaadin.data.sort.SortOrder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Field;
import com.vaadin.ui.VerticalLayout;

/**
 * A tabular display of the Movies table
 * 
 * @author bas.rutten
 *
 */
@SpringView(name = Views.TABULAR_MOVIES_VIEW)
@UIScope
@SuppressWarnings("serial")
public class TabularMoviesView extends BaseView {

	/** The Movies View is using the MovieService for data access. */
	@Inject
	private MovieService movieService;

	@Inject
	private BaseService<Integer, Country> countryService;

	private List<Country> allCountries;

	@Override
	public void enter(ViewChangeEvent event) {

		// Apply Vaadin Layout.
		VerticalLayout mainLayout = new DefaultVerticalLayout(true, true);

		// Set form options by convention.
		FormOptions fo = new FormOptions().setOpenInViewMode(true).setShowRemoveButton(true).setShowEditButton(true);

		// retrieve the country list just once
		allCountries = countryService.findAll();

		// custom entity model for this screen. See the "entitymodel.properties" file for specifics
		EntityModel<Movie> em = getModelFactory().getModel("MoviesTable", Movie.class);

		// A SplitLayout is a component that displays a search screen and an edit form
		TabularEditLayout<Integer, Movie> movieLayout = new TabularEditLayout<Integer, Movie>(movieService, em, fo,
		        new SortOrder("title", SortDirection.ASCENDING)) {

			@Override
			protected Field<?> constructCustomField(EntityModel<Movie> entityModel, AttributeModel attributeModel,
			        boolean viewMode, boolean searchMode) {
				if ("country".equals(attributeModel.getName())) {
					return new EntityComboBox<>(getEntityModelFactory().getModel(Country.class), attributeModel,
					        allCountries);
				}
				return null;
			}
		};

		movieLayout.setPageLength(25);

		mainLayout.addComponent(movieLayout);
		setCompositionRoot(mainLayout);
	}
}
