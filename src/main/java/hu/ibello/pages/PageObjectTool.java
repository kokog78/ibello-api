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
package hu.ibello.pages;

import hu.ibello.actions.BrowserActionBuilder;
import hu.ibello.actions.KeyHelper;
import hu.ibello.actions.Task;
import hu.ibello.actions.TaskRepeater;
import hu.ibello.actions.WebElementActionBuilder;
import hu.ibello.check.WebElementChecker;
import hu.ibello.check.WebElementGetter;
import hu.ibello.core.Browser;
import hu.ibello.core.ConfigurationTool;
import hu.ibello.core.Value;
import hu.ibello.data.TestDataBuilder;
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
	 * @param windowId identifier of the browser window
	 * @return an object used for element search on the page
	 */
	SearchTool find(String windowId);

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @deprecated Use <code>configuration().getValue(name)</code> method.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	@Deprecated
	default Value getConfigurationValue(String name) {
		return configuration().getValue(name);
	}
	
	/**
	 * Returns an object which has some methods to access ibello configuration.
	 * @see ConfigurationTool
	 * @return the configuration object
	 */
	ConfigurationTool configuration();
	
	/**
	 * Returns an URL which is merged with the <code>ibello.url.base</code> configuration property.
	 * <p>
	 * If the given URL is an absolute one with protocol, then it will be returned (without any changes).
	 * </p>
	 * <p>
	 * If the URL is relative, then it will be concatenated to the configuration property, and the result will be returned.
	 * </p>
	 * @param url the absolute or relative URL
	 * @return the merged URL
	 */
	String getMergedURL(String url);
	
	/**
	 * Returns a {@link Browser} instance which can be used for different browser-specific actions,
	 * including element search and opening URL.
	 * @param windowId identifier of the browser window
	 * @return an interface which offers browser-specific actions
	 */
	Browser browser(String windowId);
	
	/**
	 * Returns an {@link WebElementActionBuilder} instance which can be used to perform an action on the web element.
	 * If the action fails then the test execution stops.
	 * @param element we want to perform an action with this elements
	 * @return an interface configured for doing actions with the element
	 */
	WebElementActionBuilder doWith(WebElement element);
	
	/**
	 * Returns an {@link WebElementActionBuilder} instance which can be used to perform an action on the web element.
	 * If the action fails then the test execution continues.
	 * @param element we want to perform an action with this elements
	 * @return an interface configured for doing actions with the element
	 */
	WebElementActionBuilder tryWith(WebElement element);
	
	/**
	 * Returns an {@link BrowserActionBuilder} instance which can be used to manager browser window related actions.
	 * If the action fails then test execution stops.
	 * @param browser the browser instance, can be obtained with the {@link PageObject#browser()} method
	 * @return an interface configured for doing actions with the browser
	 */
	BrowserActionBuilder doWith(Browser browser);
	
	/**
	 * Returns an {@link BrowserActionBuilder} instance which can be used to manager browser window related actions.
	 * If the action fails then test execution continues.
	 * @param browser the browser instance, can be obtained with the {@link PageObject#browser()} method
	 * @return an interface configured for doing actions with the browser
	 */
	BrowserActionBuilder tryWith(Browser browser);
	
	/**
	 * Returns a {@link TaskRepeater} instance which will run the given task multiple times.
	 * The methods of this instance are customizing the exit condition of this repeat process.
	 * @param task the task we want to repeat
	 * @return an interface configured for repeating the task
	 */
	TaskRepeater repeat(Task task);
	
	/**
	 * Returns a {@link WebElementChecker} instance which can be used to inspect a {@link WebElement}'s state and have
	 * some boolean result about it. The investigation holds until the element is accessible (or the time runs out).
	 * @param element the element we need to investigate
	 * @return an interface configured for running investigation on the element
	 */
	WebElementChecker checkThat(WebElement element);
	
	/**
	 * Returns a {@link WebElementGetter} instance which can be used to get some property of the given {@link WebElement}.
	 * The operation waits until the element is accessible (or the time runs out).
	 * @param element the element
	 * @return an interface configured for querying properties of the element
	 */
	WebElementGetter get(WebElement element);
	
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
	 * @param windowId identifier of the browser window
	 * @return an {@link ExpectationBuilder} instance which is configured to run expectations
	 */
	ExpectationBuilder expectations(String windowId);
	
	/**
	 * Returns a {@link KeyHelper} instance, which offers special keys and key modifiers.
	 * Those can be used in {@link WebElementActionBuilder#sendKeys(CharSequence...)} and
	 * {@link WebElementActionBuilder#sendKeys(hu.ibello.actions.KeyModifier, CharSequence...)} methods.
	 * @return a {@link KeyHelper} instance
	 */
	KeyHelper keys();
	
	/**
	 * Starts a new test data loading. The result is a {@link TestDataBuilder} instance which can be used
	 * to configure and perform the test loading operation.
	 * @return a test data loader instance
	 * @see TestDataBuilder
	 */
	TestDataBuilder testData();
	
	/**
	 * Returns a page object with the given type.
	 * @param <P> type of the page object
	 * @param type page object class
	 * @param windowId the window identifier - if available
	 * @return the instantiated page object with the given type
	 * @throws IllegalArgumentException if the given class cannot be instantiated as page object
	 */
	<P extends PageObject> P pageObject(Class<P> type, String windowId) throws IllegalArgumentException;
}
