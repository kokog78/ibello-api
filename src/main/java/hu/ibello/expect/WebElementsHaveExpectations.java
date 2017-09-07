package hu.ibello.expect;

import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;

/**
 * Instance of this interface contains methods to create and execute expectations
 * about a collection of {@link WebElement} (or an instance of {@link WebElements}.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementsHaveExpectations {

	/**
	 * An <em>expectation</em> which comes true when the element collection's size is the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementsExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the size equals to the given one.
	 * </p>
	 * @param size expected size of element collection
	 */
	void size(int size);
	
	/**
	 * An <em>expectation</em> which comes true when the element collection's size is greater than the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementsExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the size is greater than the limit.
	 * </p>
	 * @param lowerLimit lower limit of element collection's size
	 */
	void sizeGreaterThan(int lowerLimit);
	
	/**
	 * An <em>expectation</em> which comes true when the element collection's size is less than the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementsExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the size is less than the limit.
	 * </p>
	 * @param upperLimit upper limit of element collection's size
	 */
	void sizeLessThan(int upperLimit);
	
	/**
	 * An <em>expectation</em> which comes true when the element collection's size is between the given values.
	 * It means that the size must be greater than or equal to the <code>minSize</code>, and it must be
	 * less that or equal to the <code>maxSize</code>.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementsExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the size is between the given values.
	 * </p>
	 * @param minSize lower limit of element collection's size
	 * @param maxSize upper limit of element collection's size
	 */
	void sizeBetween(int minSize, int maxSize);
	
}
