/**
 * This package contains interfaces used in different expectations.
 * <h1>Ibello Expectation Engine</h1>
 * <p>
 * During an expectation the program waits until a specific property of the examined
 * entity changes or the time runs out. If the change occurs, the expectation will success
 * and the test execution continues. If the time runs out, an exception will be thrown,
 * every important information will be logged and the current test will fail. It means that
 * the test execution will stop and the next test will be started.
 * </p>
 * <p>
 * The examined entity can be an element on the page, a collection of elements, or the browser itself.
 * </p>
 * <p>
 * The timeout of an expectation cannot be specified directly with a numeric value. All timeouts should
 * be defined in the <em>ibello</em> configuration, with <code>"ibello.timeout."</code> prefix. The unit
 * should be seconds. During the expectation, only the key of the timeout can be specified - it is the other half
 * of the configuration property, after the <code>"ibello.timeout."</code> prefix. With this rule we can
 * manage our timeout values in a more secure and general way, and can change them in different test execution
 * environment easily.
 * </p>
 * <p>
 * The center interface of the expectation engine is the {@link hu.ibello.expect.ExpectationBuilder}.
 * It contains fluent methods to construct and run an expectation. This interface is available only in page objects
 * - see {@link hu.ibello.pages.PageObject#expectations()} method.
 * </p>
 * <p>
 * Examples (from a page object):
 * </p>
 * <pre>
 * WebElement button = ...;
 * WebElements inputFields = ...;
 * 
 * expectations().expect(button).toBe().clickable();
 * expectations().expect(inputFields).toHave().size(3);
 * expectations().expect(browser()).toHave().url("ibello.com");
 * expectations().expectAny(() -&gt; {
 *     expectations().expect(button).toBe().clickable();
 *     expectations().expect(inputFields).toHave().size(3);
 *     expectations().expect(browser()).toHave().url("ibello.com");
 * });
 * </pre>
 * @author Kornél Simon
 */
package hu.ibello.expect;