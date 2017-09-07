package hu.ibello.expect;

/**
 * Interface for building an expectation about the browser.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface BrowserExpectationBuilder {

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotHave()}.
	 * @return {@link BrowserHaveExpectations} instance to finish and execute the expectation
	 */
	BrowserHaveExpectations toHave();

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toHave()}.
	 * @return {@link BrowserHaveExpectations} instance to finish and execute the expectation
	 */
	BrowserHaveExpectations toNotHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation.
	 * @return {@link BrowserBeExpectations} instance to finish and execute the expectation
	 */
	BrowserBeExpectations toBe();
}
