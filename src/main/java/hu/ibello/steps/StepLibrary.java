/*
 * Ark-Sys Kft. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hu.ibello.steps;

import hu.ibello.apitest.HttpClient;
import hu.ibello.apitest.RestClient;
import hu.ibello.core.Name;
import hu.ibello.core.Value;
import hu.ibello.core.WindowRelated;
import hu.ibello.data.TestDataBuilder;
import hu.ibello.inject.Inject;
import hu.ibello.inject.Injectable;
import hu.ibello.inject.Scope;
import hu.ibello.output.OutputHandler;
import hu.ibello.pages.PageObject;
import hu.ibello.search.Window;

/**
 * <p>
 * Superclass for step libraries. Each class sub-classing it will be automatically injected by the injector in
 * session-scope - there is no need for {@link Inject} annotation.
 * </p>
 * <p>
 * A step library defines business-level operations of a workflow. It does not describes technical functionality
 * - all technical-level functions should be specified in page object (see {@link PageObject}). A step library
 * <em>uses</em> page objects to construct higher-level steps. For example, to login into an application, you
 * should enter the user name and password into some input fields on the login page, the click the "login" button.
 * From business perspective, this is just one step, so the step library defining this step can be similar to this:
 * </p>
 * <pre>
 * public class LoginSteps extends StepLibrary {
 * 
 *     // page object will be automatically injected by the injector
 *     private LoginPage loginPage;
 *     
 *     public void login(String userName, String password) {
 *         loginPage.open();
 *         loginPage.setUserName(userName);
 *         loginPage.setPassword(password);
 *         loginPage.clickLoginButton();
 *     }
 * }
 * </pre>
 * <p>
 * A step library can use page objects and another step libraries. The injector automatically injects
 * (and initializes) all fields if the type of the field inherits the {@link PageObject} or {@link StepLibrary}
 * class. These fields can be referenced from all methods of the step library (but not from the constructor).
 * </p>
 * <p>
 * By default, the injected page objects are controlling the main browser window. With the {@link Window} annotation
 * we can bound a page object field to a different browser window. The annotation receives a string identifier.
 * If the window with that identifier already exists, the page object will be connected to that window,
 * otherwise a new window with that identifier will be created. In the example below the <code>editor</code>
 * page object instance will control a new browser window, which has the "editor" identifier.
 * </p>
 * <pre>
 * public class EditorSteps extends StepLibrary {
 * 
 *     {@literal @}Window("editor")
 *     private EditorPage editor;
 *     
 *     private PresenterPage presenter;
 * 
 * }
 * </pre>
 * <p>
 * Also step library fields may have {@link Window} annotation. The browser window can be inherited from the container class.
 * If a {@link PageObject} field does not have {@link Window} annotation, but the containing step library instance was injected
 * into another step library (or test) with a {@link Window} annotation, then the page object will use the browser window with
 * the injected identifier.
 * </p>
 * <p>
 * All public methods of a step library are considered as test steps and therefore automatically logged when called.
 * The log will contain the descriptive name of the methods. If the method has a {@link Name} annotation, then
 * the descriptive name will be the one specified by that annotation. Otherwise the descriptive name will be transformed
 * from the name of the method; all underscore character will be replaced by a space, and all camel-case substring
 * will be separated into multiple words. Method parameters are also included in the descriptive name. '$' signs in
 * method name are placeholders for parameters.
 * </p>
 * <p>
 * Examples:
 * </p>
 * <pre>
 * public void sendErrorMessage() {
 *     // descriptive name is "Send Error Message"
 * }
 * 
 * public void list_all_elements() {
 *     // descriptive name is "List All Elements"
 * }
 * 
 * {@literal @}Name("Logout From Application")
 * public void logout() {
 *     // descriptive name is "Logout From Application"
 * }
 * 
 * {@literal @}Name("Remove Message with Index ${0}")
 * public void removeMessage(int index) {
 *     // the name may contain parameter substitution marker
 *     // eg. removeMessage(5) will have descriptive name: "Remove Message with Index 5"
 * }
 * 
 * public void open_details_for_$s_job(String personName) {
 *     // the '$' character will be replaced with the person name
 *     // eg. open_details_for_$s_job("John") will have descriptive name: "Open Details for Johns Job"
 * }
 * </pre>
 * <p>
 * A step library should have a public default constructor.
 * </p>
 * @author Kornél Simon
 */
@Injectable(Scope.STEPS)
public abstract class StepLibrary extends WindowRelated {

	@Inject
	private StepLibraryTool tool;
	
	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	protected Value getConfigurationValue(String name) {
		return tool.getConfigurationValue(name);
	}
	
	/**
	 * Starts a new test data loading. The result is a {@link TestDataBuilder} instance which can be used
	 * to configure and perform the test loading operation.
	 * @return a test data loader instance
	 * @see TestDataBuilder
	 */
	protected TestDataBuilder testData() {
		return tool.testData();
	}
	
	/**
	 * Returns the output handler tool. With that we can add custom actions and expectations to the test report.
	 * For example, a custom logging can be performed as:
	 * <pre>
	 * output().recordCustomAction("Username is " + username);
	 * </pre>
	 * We can do custom expectation with the following code (using the junit assertion library):
	 * <pre>
	 * org.junit.Assert.assertEquals("Username is wrong", "Martin", username);
	 * output().recordCustomExpectation("Username is the expected " + username);
	 * </pre>
	 * This code will throw an {@link AssertionError}, if the username is not the expected one. (All {@link AssertionError} type
	 * exceptions are handled by the ibello automatically.) If the username is correct, then the second row will add a
	 * message to the test report.
	 * @return the output handler instance
	 * @see OutputHandler
	 */
	protected OutputHandler output() {
		return tool.output();
	}
	
	/**
	 * Returns a REST client. With that we can perform a REST operation - send a JSON REST call and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the REST client instance
	 * @see RestClient
	 */
	protected RestClient restClient() {
		return tool.restClient();
	}
	
	/**
	 * Returns a HTTP client. With that we can perform a HTTP call - send a request and receive it's response.
	 * The request and response data will be logged automatically.
	 * @return the HTTP client instance
	 * @see HttpClient
	 */
	protected HttpClient httpClient() {
		return tool.httpClient();
	}
	
}
