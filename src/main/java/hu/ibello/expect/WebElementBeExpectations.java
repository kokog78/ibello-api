package hu.ibello.expect;

import hu.ibello.elements.WebElement;

/**
 * Instance of this interface contains methods to create and execute expectations
 * about a {@link WebElement}.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementBeExpectations {

	/**
	 * An <em>expectation</em> which comes true when the element is displayed on the page.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotBe()},
	 * then the outcome of the expectation is the opposite: it will fail if the element is displayed.
	 * </p>
	 */
	void displayed();
	
	/**
	 * An <em>expectation</em> which comes true when the element is enabled.
	 * The method fails only if the element is a disabled input field.
	 * In this case an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotBe()},
	 * then the outcome of the expectation is the opposite: it will fail if the element is enabled.
	 * </p>
	 */
	void enabled();
	
	/**
	 * An <em>expectation</em> which comes true when the element can be clicked.
	 * It means that the elements should be displayed and enabled.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotBe()},
	 * then the outcome of the expectation is the opposite: it will fail if the element is clickable.
	 * </p>
	 */
	void clickable();
	
	/**
	 * An <em>expectation</em> which comes true when the element is selected.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * Only checkboxes, options in a select and radio buttons can be selected.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotBe()},
	 * then the outcome of the expectation is the opposite: it will fail if the element is selected.
	 * </p>
	 */
	void selected();
	
	/**
	 * An <em>expectation</em> which comes true when the element is attached to the DOM structure of
	 * the page.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotBe()},
	 * then the outcome of the expectation is the opposite: it will fail if the element is present in the DOM.
	 * </p>
	 */
	void present();
}
