package hu.ibello.expect;

import hu.ibello.elements.WebElement;

/**
 * Interface for building an expectation about a collection of {@link WebElement} objects.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementsExpectationBuilder {

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotHave()}.
	 * @return {@link WebElementsHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementsHaveExpectations toHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toHave()}.
	 * @return {@link WebElementsHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementsHaveExpectations toNotHave();
	
}
