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
package com.ocs.dynamo.exception;

/**
 * An exception used to indicate that something went wrong during an import
 * 
 * @author bas.rutten
 */
public class OCSImportException extends OCSRuntimeException {

    private static final long serialVersionUID = -8031113801985156724L;

    public OCSImportException() {
    	// default constructor
    }

    public OCSImportException(String message) {
        super(message);
    }

    public OCSImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
