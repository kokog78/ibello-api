package hu.ibello.expect;

/**
 * Instance of this interface contains methods to create and execute expectations
 * about the browser.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface BrowserBeExpectations {

	/**
	 * Runs an expectation which successes when the current page is loaded.
	 * The expectation waits until all dynamic changes are made in the DOM.
	 */
	void loaded();
	
}
