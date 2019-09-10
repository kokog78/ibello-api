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
import hu.ibello.core.Name;
import hu.ibello.core.Value;
import hu.ibello.core.WindowRelated;
import hu.ibello.data.TestDataBuilder;
import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;
import hu.ibello.expect.ExpectationBuilder;
import hu.ibello.inject.Inject;
import hu.ibello.inject.Injectable;
import hu.ibello.inject.Scope;
import hu.ibello.search.Find;
import hu.ibello.search.Frame;
import hu.ibello.search.Position;
import hu.ibello.search.Relation;
import hu.ibello.search.SearchTool;

/**
 * <p>
 * Superclass for page objects. Each class sub-classing it will be automatically injected by the injector in
 * session-scope - there is no need for {@link Inject} annotation.
 * </p>
 * <p>
 * During the dependency injection the injector automatically initializes all fields in a page object, when the
 * field has one {@link Find} annotation and it's type is one of the followings:
 * </p>
 * <ul>
 * <li>{@link WebElement} - the field will represent a single element on the page,</li>
 * <li>{@link WebElements} - the field will represent multiple elements on the page.</li>
 * </ul>
 * <p>
 * The fields even can be private. Examples:
 * </p>
 * <pre>
 * {@literal @}Find(selector="#ok-button")
 * private WebElement okButton;
 * 
 * {@literal @}Find(selector=".main-table tr")
 * private WebElements tableRows; 
 * </pre>
 * <p>
 * After the {@link Find} annotation some other (modifier) annotations can be added to a field.
 * With the {@link Position} annotation the desired element's relative position can be specified to an anchor element.
 * With the {@link Relation} annotation the structural relation between the desired and anchor elements can be
 * specified. Examples:
 * </p>
 * <pre>
 * {@literal @}Find(selector="button")
 * {@literal @}Position(type=PositionType.LEFT_FROM, selector="#ok-button")
 * private WebElement cancelButton;
 * 
 * {@literal @}Find(selector="button")
 * {@literal @}Relation(type=RelationType.DESCENDANT_OF, selector="#modal-window")
 * private WebElement modalButton;
 * </pre>
 * <p>
 * When the content of a page is included by an <code>iframe</code> parent, then a single {@link Find} annotation will
 * not find the desired elements. In this case, a {@link Frame} annotation should be added to the page object class,
 * which specifies the search properties of the <code>iframe</code> element. Every element search inside of this page object
 * will be automatically performed inside of that <code>iframe</code>.
 * </p>
 * <p>
 * A page object encapsulates the technical functionality of a well-defined part of the application under test.
 * For example, it can represent a single web-page, a navigation bar, a frame, ... Every
 * necessary action available on that object is specified in the page object as public method. Every assertion
 * that can be verified on that object is represented by a public method too.
 * </p>
 * <p>
 * The abstract {@link PageObject} class contains some useful protected methods. These are for:
 * </p>
 * <ul>
 * <li>doing something with elements, see {@link PageObject#doWith(WebElement)},</li>
 * <li>accessing browser interface which can be used to open an URL, and to other things,
 * see {@link PageObject#browser()},</li>
 * <li>verifying some expectations, see {@link PageObject#expectations()},</li>
 * <li>reading configuration values, see {@link PageObject#getConfigurationValue(String)}.</li>
 * </ul>
 * <p>
 * Examples:
 * </p>
 * <pre>
 * public void clickButton() {
 *     // clicking a button
 *     doWith(okButton).click();
 * }
 * 
 * private WebElement findButton() {
 *     // finding a specific element on page
 *     return browser().find("#ok-button");
 * }
 * 
 * public void openPage() {
 *     // opening a specific URL
 *     browser().openURL("http://localhost/page");
 * }
 * 
 * public void expectButtonIsVisible() {
 *     // verifying that button is visible
 *     expectations().expect(okButton).toBe().visible();
 * }
 * 
 * public void expectPageIsOpened() {
 *     // verifying that a specific URL is opened
 *     expectations().expect(browser()).toHave().url("http://localhost/page");
 * }
 * 
 * private Long getDefaultTimeout() {
 *     // returning configuration value as number
 *     return getConfigurationValue("ibello.timeout.default").toLong();
 * }
 * </pre>
 * <p>
 * All public methods of a page object are considered as test steps and therefore automatically logged when called.
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
 * public void clickBigButton() {
 *     // descriptive name is "Click Big Button"
 * }
 * 
 * public void open_page() {
 *     // descriptive name is "Open Page"
 * }
 * 
 * {@literal @}Name("Close Modal Dialog")
 * public void doSomething() {
 *     // descriptive name is "Close Modal Dialog"
 * }
 * 
 * {@literal @}Name("Click Button in Row ${0}")
 * public void clickButton(int index) {
 *     // the name may contain parameter substitution marker
 *     // eg. clickButton(5) will have descriptive name: "Click Button in Row 5"
 * }
 * 
 * public void click_$_button(String title) {
 *     // the '$' character will be replaced with the title
 *     // eg. click_$_button("Open") will have descriptive name: "Click Open Button"
 * }
 * </pre>
 * <p>
 * A page object should have a public default constructor.
 * </p>
 * @author Kornél Simon
 * @see Find
 * @see Position
 * @see Relation
 */
@Injectable(Scope.PAGE)
public abstract class PageObject extends WindowRelated {
	
	@Inject
	private PageObjectTool tool;
	
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
	protected SearchTool find() {
		return tool.find(getWindowId());
	}

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
	 * Returns a {@link Browser} instance which can be used for different browser-specific actions,
	 * including element search and opening URL.
	 * @return an interface which offers browser-specific actions
	 */
	protected Browser browser() {
		return tool.browser(getWindowId());
	}
	
	/**
	 * Returns an {@link WebElementActionBuilder} instance which can be used to perform different actions on the web element.
	 * @param element we want to perform an action with this elements
	 * @return an interface configured for doing actions with the element
	 */
	protected WebElementActionBuilder doWith(WebElement element) {
		return tool.doWith(element);
	}
	
	/**
	 * Returns an {@link BrowserActionBuilder} instance which can be used to manager browser window related actions.
	 * @param browser the browser instance, can be obtained with the {@link PageObject#browser()} method
	 * @return an interface configured for doing actions with the browser
	 */
	protected BrowserActionBuilder doWith(Browser browser) {
		return tool.doWith(browser);
	}
	
	/**
	 * Returns a {@link TaskRepeater} instance which will run the given task multiple times.
	 * The methods of this instance are customizing the exit condition of this repeat process.
	 * @param task the task we want to repeat
	 * @return an interface configured for repeating the task
	 */
	protected TaskRepeater repeat(Task task) {
		return tool.repeat(task);
	}
	
	/**
	 * Returns a {@link WebElementChecker} instance which can be used to inspect a {@link WebElement}'s state and have
	 * some boolean result about it. The investigation holds until the element is accessible (or the time runs out).
	 * @param element the element we need to investigate
	 * @return an interface configured for running investigation on the element
	 */
	protected WebElementChecker checkThat(WebElement element) {
		return tool.checkThat(element);
	}
	
	/**
	 * Returns a {@link WebElementGetter} instance which can be used to get some property of the given {@link WebElement}.
	 * The operation waits until the element is accessible (or the time runs out).
	 * @param element the element
	 * @return an interface configured for querying properties of the element
	 */
	protected WebElementGetter get(WebElement element) {
		return tool.get(element);
	}
	
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
	 * expectations().assume(okButton).toHave().text("OK");
	 * expectations().expect(okButton).toBe().visible();
	 * expectations().expect(buttons).toHave().size(5);
	 * expectations().expect(browser()).toHave().url("http://localhost/page");
	 * </pre>
	 * @return an {@link ExpectationBuilder} instance which is configured to run expectations
	 */
	protected ExpectationBuilder expectations() {
		return tool.expectations(getWindowId());
	}
	
	/**
	 * Returns a {@link KeyHelper} instance, which offers special keys and key modifiers.
	 * Those can be used in {@link WebElementActionBuilder#sendKeys(CharSequence...)} and
	 * {@link WebElementActionBuilder#sendKeys(hu.ibello.actions.KeyModifier, CharSequence...)} methods.
	 * @return a {@link KeyHelper} instance
	 */
	protected KeyHelper keys() {
		return tool.keys();
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
	
}
