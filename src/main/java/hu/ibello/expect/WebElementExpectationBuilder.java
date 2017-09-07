package hu.ibello.expect;

import hu.ibello.elements.WebElement;

/**
 * Interface for building an expectation about a {@link WebElement}.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementExpectationBuilder {

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotHave()}.
	 * @return {@link WebElementHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementHaveExpectations toHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toHave()}.
	 * @return {@link WebElementHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementHaveExpectations toNotHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotBe()}.
	 * @return {@link WebElementBeExpectations} instance to finish and execute the expectation
	 */
	WebElementBeExpectations toBe();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toBe()}.
	 * @return {@link WebElementBeExpectations} instance to finish and execute the expectation
	 */
	WebElementBeExpectations toNotBe();
	
}
