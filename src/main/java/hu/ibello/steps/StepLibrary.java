package hu.ibello.steps;

import hu.ibello.core.Name;
import hu.ibello.core.Value;
import hu.ibello.inject.Inject;
import hu.ibello.inject.Injectable;
import hu.ibello.inject.Scope;
import hu.ibello.pages.PageObject;

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
 * All public methods of a step library are considered as test steps and therefore automatically logged when called.
 * The log will contain the descriptive name of the methods. If the method has a {@link Name} annotation, then
 * the descriptive name will be the one specified by that annotation. Otherwise the descriptive name will be transformed
 * from the name of the method; all underscore character will be replaced by a space, and all camel-case substring
 * will be separated into multiple words. Method parameters are also included in the descriptive name.
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
 * </pre>
 * <p>
 * A step library should have a public default constructor.
 * </p>
 * @author Kornél Simon
 */
@Injectable(Scope.STEPS)
public abstract class StepLibrary {

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
	
}
