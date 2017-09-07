package hu.ibello.expect;

import java.util.List;

import hu.ibello.core.Browser;
import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;
import hu.ibello.pages.PageObject;

/**
 * An instance of this interface builds and executes <em>expectations</em>.
 * The interface offers fluent methods (through another interfaces).
 * <ul>
 * <li>Methods to set timeout: see {@link #withTimeout(Enum)} and {@link #withTimeout(String)}.</li>
 * <li>Methods to build a simple expectation: see {@link #expect(Browser)}, {@link #expect(WebElement)},
 * {@link #expect(List)}.</li>
 * <li>Methods to build complex expectations: see {@link #expectAll(Runnable)} and {@link #expectAny(Runnable)}.</li>
 * </ul>
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface ExpectationBuilder {

	/**
	 * Sets the timeout of the expectation by it's string identifier.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout("LONG")</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current {@link ExpectationBuilder} instance
	 */
	ExpectationBuilder withTimeout(String key);

	/**
	 * Sets the timeout of the expectation by it's enum identifier. The enum can be
	 * any valid java enum, it's not part of the <em>ibello</em> system. The given enum
	 * constant will be transformed to string.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout(Timeouts.LONG)</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current {@link ExpectationBuilder} instance
	 */
	ExpectationBuilder withTimeout(Enum<?> key);
	
	/**
	 * Starts to build a simple expectation about the given element.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * @param element a {@link WebElement}
	 * @return {@link WebElementExpectationBuilder} instance to continue the expectation
	 */
	WebElementExpectationBuilder expect(WebElement element);
	
	/**
	 * Starts to build a simple expectation about the given elements.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * @param elements collection of {@link WebElement} objects - it can be {@link WebElements}
	 * @return {@link WebElementsExpectationBuilder} instance to continue the expectation
	 */
	WebElementsExpectationBuilder expect(List<WebElement> elements);
	
	/**
	 * Starts to build a simple expectation about the browser.
	 * The expectations will use the timeout specified in this ExpectationBuilder instance.
	 * The result is also an interface instance which has methods to build the expectation further.
	 * @param browser the browser object, {@link PageObject#browser()}
	 * @return {@link BrowserExpectationBuilder} instance to continue the expectation
	 */
	BrowserExpectationBuilder expect(Browser browser);
	
	/**
	 * Executes a complex expectation. It receives a {@link Runnable} instance, in which we can call
	 * multiple expectations (even another complex ones). As result, all expectations will be executed
	 * in arbitrary order. The complex expectation will fail if any of the child expectations fails.
	 * (So all of them should be successful.)
	 * <p>Example (from a page object):</p>
	 * <pre>
	 * expectations().expectAll(() -&gt; {
	 *     expectations().expect(browser()).toHave().url("www.ibello.com");
	 *     expectations().expect(registerButton).toBe().clickable();
	 * });
	 * </pre>
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	void expectAll(Runnable expectations);
	
	/**
	 * Executes a complex expectation. It receives a {@link Runnable} instance, in which we can call
	 * multiple expectations (even another complex ones). As result, all expectations will be executed
	 * in arbitrary order. The complex expectation will fail if all of the child expectations fail.
	 * (So at least one should be successful.) After the first successful child expectation the
	 * execution of the children will stop.
	 * <p>Example (from a page object):</p>
	 * <pre>
	 * expectations().expectAny(() -&gt; {
	 *     expectations().expect(loginButton).toBe().clickable();
	 *     expectations().expect(registerButton).toBe().clickable();
	 * });
	 * </pre>
	 * @param expectations a {@link Runnable} instance which calls the child expectations
	 */
	void expectAny(Runnable expectations);
	
}
