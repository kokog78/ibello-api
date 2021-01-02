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
package hu.ibello.expect;

import java.util.List;

import hu.ibello.core.Browser;
import hu.ibello.core.TimeoutRelated;
import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;
import hu.ibello.pages.PageObject;

/**
 * An instance of this interface builds and executes <em>expectations</em>.
 * The interface offers fluent methods (through another interfaces).
 * <ul>
 * <li>Methods to set timeout: see {@link #withTimeout(Enum)} and {@link #withTimeout(String)}.</li>
 * <li>Methods to build a simple "hard" expectation: see {@link #expect(Browser)}, {@link #expect(WebElement)},
 * {@link #expect(List)}.</li>
 * <li>Methods to build a simple "soft" expectation: see {@link #assume(Browser)}, {@link #assume(WebElement)},
 * {@link #assume(List)}.</li>
 * <li>Methods to build complex expectations: see {@link #all(Runnable)} and {@link #any(Runnable)}.</li>
 * </ul>
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface ExpectationBuilder extends TimeoutRelated<ExpectationBuilder> {

	/**
	 * Starts to build a simple expectation about the given element.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "hard" one. If it fails, the execution of the
	 * current test will be terminated. This is the difference between {@link #assume(WebElement)}
	 * and {@link #expect(WebElement)}.
	 * </p>
	 * @param element a {@link WebElement}
	 * @return {@link WebElementExpectationBuilder} instance to continue the expectation
	 */
	WebElementExpectationBuilder expect(WebElement element);
	
	/**
	 * Starts to build a simple expectation about the given elements.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "hard" one. If it fails, the execution of the
	 * current test will be terminated. This is the difference between {@link #assume(List)}
	 * and {@link #expect(List)}.
	 * </p>
	 * @param elements collection of {@link WebElement} objects - it can be {@link WebElements}
	 * @return {@link WebElementsExpectationBuilder} instance to continue the expectation
	 */
	WebElementsExpectationBuilder expect(List<WebElement> elements);
	
	/**
	 * Starts to build a simple expectation about the browser.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "hard" one. If it fails, the execution of the
	 * current test will be terminated. This is the difference between {@link #assume(Browser)}
	 * and {@link #expect(Browser)}.
	 * </p>
	 * @param browser the browser object, {@link PageObject#browser()}
	 * @return {@link BrowserExpectationBuilder} instance to continue the expectation
	 */
	BrowserExpectationBuilder expect(Browser browser);
	
	/**
	 * Starts to build a simple expectation about the given element.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "soft" one. If it fails, the execution of the
	 * test code will not be stopped. This is the difference between {@link #expect(WebElement)}
	 * and {@link #assume(WebElement)}.
	 * </p>
	 * @param element a {@link WebElement}
	 * @return {@link WebElementExpectationBuilder} instance to continue the expectation
	 */
	WebElementExpectationBuilder assume(WebElement element);
	
	/**
	 * Starts to build a simple expectation about the given elements.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "soft" one. If it fails, the execution of the
	 * test code will not be stopped. This is the difference between {@link #expect(List)}
	 * and {@link #assume(List)}.
	 * </p>
	 * @param elements collection of {@link WebElement} objects - it can be {@link WebElements}
	 * @return {@link WebElementsExpectationBuilder} instance to continue the expectation
	 */
	WebElementsExpectationBuilder assume(List<WebElement> elements);
	
	/**
	 * Starts to build a simple expectation about the browser.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * <p>
	 * The expectation provided by this method is a "soft" one. If it fails, the execution of the
	 * test code will not be stopped. This is the difference between {@link #expect(Browser)}
	 * and {@link #assume(Browser)}.
	 * </p>
	 * @param browser the browser object, {@link PageObject#browser()}
	 * @return {@link BrowserExpectationBuilder} instance to continue the expectation
	 */
	BrowserExpectationBuilder assume(Browser browser);
	
	/**
	 * Deprecated method, use {@link #all(Runnable)}.
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	@Deprecated
	default void expectAll(Runnable expectations) {
		all(expectations);
	}

	/**
	 * Executes a complex expectation. It receives a {@link Runnable} instance, in which we can call
	 * multiple expectations (even another complex ones). As result, all expectations will be executed
	 * in arbitrary order. The complex expectation will fail if any of the child expectations fails.
	 * (So all of them should be successful.)
	 * <p>Example (in a page object):</p>
	 * <pre>
	 * expectations().all(() -&gt; {
	 *     expectations().expect(browser()).toHave().url("https://ibello.eu");
	 *     expectations().expect(registerButton).toBe().clickable();
	 * });
	 * </pre>
	 * <p>
	 * In the complex expectation we can use "hard" and "soft" child expectations. If the complex expectation
	 * fails because a "hard" child failed, then the execution of the current test will be terminated instantly.
	 * If the reason of the failure was a "soft" expectation, then the test will continue with the next step.
	 * In both cases, at the end the test will be marked as failed.
	 * </p>
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	void all(Runnable expectations);
	
	/**
	 * Deprecated method, use {@link #any(Runnable)}.
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	@Deprecated
	default void expectAny(Runnable expectations) {
		any(expectations);
	}
	
	/**
	 * Executes a complex expectation. It receives a {@link Runnable} instance, in which we can call
	 * multiple expectations (even another complex ones). As result, all expectations will be executed
	 * in arbitrary order. The complex expectation will fail if all of the child expectations fail.
	 * (So at least one should be successful.) After the first successful child expectation the
	 * execution of the children will stop.
	 * <p>Example (in a page object):</p>
	 * <pre>
	 * expectations().any(() -&gt; {
	 *     expectations().expect(loginButton).toBe().clickable();
	 *     expectations().expect(registerButton).toBe().clickable();
	 * });
	 * </pre>
	 * <p>
	 * In the complex expectation we can use "hard" and "soft" child expectations. If we use at least one
	 * "hard" expectation, and the complex expectation fails, then the test run will be instantly terminated
	 * (because that "hard" one failed). If the complex expectation contains only "soft" children, then the
	 * test execution will continue even if the expectation fails. In both cases, at the end the test will be
	 * marked as failed.
	 * </p>
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	void any(Runnable expectations);
	
}
