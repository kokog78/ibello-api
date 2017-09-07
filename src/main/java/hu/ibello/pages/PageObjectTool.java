package hu.ibello.pages;

import hu.ibello.actions.ActionBuilder;
import hu.ibello.actions.KeyHelper;
import hu.ibello.core.Browser;
import hu.ibello.core.Value;
import hu.ibello.elements.WebElement;
import hu.ibello.expect.ExpectationBuilder;
import hu.ibello.search.SearchTool;

/**
 * Helper interface for page objects.
 * <p>
 * There is no need to use this interface directly.
 * The <em>ibello</em> dependency injection system automatically instantiates and injects
 * an instance into page objects.
 * </p>
 * @see PageObject
 * @author Kornél Simon
 */
public interface PageObjectTool {

	/**
	 * <p>
	 * Returns a {@link SearchTool} instance which is used to search elements on the page.
	 * </p>
	 * <p>
	 * The returned instance offers a fluent interface for element search. Example:
	 * </p>
	 * <pre>
	 * WebElement image = ...;
	 * WebElement child = find().using(By.TAG_NAME, "span").leftFrom(image).first();
	 * </pre>
	 * @return an object used for element search on the page
	 */
	SearchTool find();

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	Value getConfigurationValue(String name);
	
	/**
	 * Returns a {@link Browser} instance which can be used for different browser-specific actions,
	 * including element search and opening URL.
	 * @return an interface which offers browser-specific actions
	 */
	Browser browser();
	
	/**
	 * Returns an {@link ActionBuilder} instance which can be used to perform different actions on the web element.
	 * @param element we want to perform an action with this elements
	 * @return an interface configured for doing actions with the element
	 */
	ActionBuilder doWith(WebElement element);
	
	/**
	 * <p>
	 * Returns an {@link ExpectationBuilder} instance which can be used to build and execute an expectation.
	 * </p>
	 * <p>
	 * The returned instance offers a fluent interface for element search. Example:
	 * </p>
	 * <pre>
	 * WebElement okButton = ...;
	 * WebElements buttons = ...;
	 * expectations().expect(okButton).toBe().visible();
	 * expectations().expect(buttons).toHave().size(5);
	 * expectations().expect(browser()).toHave().url("http://localhost/page");
	 * </pre>
	 * @return an {@link ExpectationBuilder} instance which is configured to run expectations
	 */
	ExpectationBuilder expectations();
	
	/**
	 * Returns a {@link KeyHelper} instance, which offers special keys and key modifiers.
	 * Those can be used in {@link ActionBuilder#sendKeys(CharSequence...)} and
	 * {@link ActionBuilder#sendKeys(hu.ibello.actions.KeyModifier, CharSequence...)} methods.
	 * @return a {@link KeyHelper} instance
	 */
	KeyHelper keys();
	
}
