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

import java.util.regex.Pattern;

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
public interface WebElementHaveExpectations {

	/**
	 * An <em>expectation</em> which comes true when the element's <code>id</code> attribute
	 * is the given one. If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the <code>id</code> is the given one.
	 * </p>
	 * @param id a value to check
	 */
	void id(String id);
	
	/**
	 * An <em>expectation</em> which comes true when the element's <code>value</code> attribute
	 * is the given one. If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the <code>value</code> is the given one.
	 * </p>
	 * @param value a value to check
	 */
	void value(String value);
	
	/**
	 * An <em>expectation</em> which comes true when the element's <code>value</code> attribute
	 * matches the given pattern. If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the <code>value</code> matches
	 * the regular expression.
	 * </p>
	 * @param pattern a regular expression
	 */
	void value(Pattern pattern);
	
	/**
	 * An <em>expectation</em> which comes true when the element is a file input and it's value
	 * is a file with the given name. If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * Only the file name is checked, and not the full path.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the file name is the given one.
	 * </p>
	 * @param value the file name to check
	 */
	void fileName(String value);
	
	/**
	 * An <em>expectation</em> which comes true when the element is a file input and it's value
	 * is a file with name matching the given regular expression. If this not occurs during the timeout,
	 * an exception will be thrown, all information will be logged and the current test will fail.
	 * Only the file name is checked, and not the full path.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the file name matches
	 * the regular expression.
	 * </p>
	 * @param pattern a regular expression
	 */
	void fileName(Pattern pattern);
	
	/**
	 * An <em>expectation</em> which comes true when the element has attribute with the given name.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the attribute exists.
	 * </p>
	 * @param name name of attribute
	 */
	void attribute(String name);
	
	/**
	 * An <em>expectation</em> which comes true when the element has attribute with the given name and value.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the attribute's value is the given one.
	 * </p>
	 * @param name name of attribute
	 * @param value value of attribute
	 */
	void attributeWithValue(String name, String value);
	
	/**
	 * An <em>expectation</em> which comes true when the element has attribute with the given name and
	 * it's value matches the given regular expression.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the attribute's value
	 * matches the regular expression.
	 * </p>
	 * @param name name of attribute
	 * @param pattern a regular expression about the attribute's value
	 */
	void attributeWithValue(String name, Pattern pattern);
	
	/**
	 * An <em>expectation</em> which comes true when the element's tag name is the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the tag name is the given one.
	 * </p>
	 * @param tagName tag name to check
	 */
	void tagName(String tagName);
	
	/**
	 * An <em>expectation</em> which comes true when the element has a CSS class with the given name.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the CSS class is present
	 * on the element.
	 * </p>
	 * @param className CSS class name
	 */
	void cssClassName(String className);
	
	/**
	 * An <em>expectation</em> which comes true when the element's CSS property has the given value.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the CSS value is the given one.
	 * </p>
	 * @param propertyName CSS property name
	 * @param value CSS property value
	 */
	void cssValue(String propertyName, String value);
	
	/**
	 * An <em>expectation</em> which comes true when the element's CSS property matches the given
	 * regular expression.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the CSS value matches
	 * the regular expression.
	 * </p>
	 * @param propertyName CSS property name
	 * @param pattern a regular expression for CSS property value
	 */
	void cssValue(String propertyName, Pattern pattern);
	
	/**
	 * An <em>expectation</em> which comes true when the element's text is the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the text is the given one.
	 * </p>
	 * @param value text value to check
	 */
	void text(String value);
	
	/**
	 * An <em>expectation</em> which comes true when the element's text matches the given
	 * regular expression.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the text matches
	 * the regular expression.
	 * </p>
	 * @param pattern a regular expression
	 */
	void text(Pattern pattern);

	/**
	 * An <em>expectation</em> which comes true when the element is a dropdown list and the text of the
	 * currently selected option is the given one.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the text is the given one.
	 * </p>
	 * @param text text value to check
	 */
	void selectedOption(String text);
	
	/**
	 * An <em>expectation</em> which comes true when the element is a dropdown list and the text of the
	 * currently selected option matches the given regular expression.
	 * If this not occurs during the timeout, an exception will be thrown,
	 * all information will be logged and the current test will fail.
	 * <p>
	 * If the method was called after {@link WebElementExpectationBuilder#toNotHave()},
	 * then the outcome of the expectation is the opposite: it will fail if the text matches
	 * the regular expression.
	 * </p>
	 * @param pattern a regular expression
	 */
	void selectedOption(Pattern pattern);
	
}
