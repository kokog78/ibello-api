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
package hu.ibello.check;

import hu.ibello.core.TimeoutRelated;
import hu.ibello.elements.WebElement;

/**
 * <p>
 * The methods of this interface are checking a single {@link WebElement} instance. Each checker method returns with a boolean value.
 * The result is <code>true</code>, if the element fulfills some condition, and <code>false</code> if not.
 * </p>
 * <p>
 * Each checker method blocks the current thread until the element is accessible. (The only exception is the {@link #isPresent()} method.)
 * This waiting has a timeout which can be specified with the {@link TimeoutRelated#withTimeout(Enum)} or {@link TimeoutRelated#withTimeout(String)}
 * method. If no timeout is specified then the default timeout will be used.
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementChecker extends TimeoutRelated<WebElementChecker> {

	/**
	 * <p>
	 * Is the element displayed on the page?
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element is displayed
	 */
	public boolean isDisplayed();
	
	/**
	 * <p>
	 * Is the element enabled?
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element is enabled
	 */
	public boolean isEnabled();
	
	/**
	 * <p>
	 * Is the element read only? Disabled elements are counted as read-only.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element is read only
	 */
	public boolean isReadonly();
	
	/**
	 * <p>
	 * Is the element clickable? It means that the element should be displayed and enabled.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element is clickable
	 */
	public boolean isClickable();
	
	/**
	 * <p>
	 * Is the element selected? Only checkboxes, options in a <code>select</code> tag and radio buttons can be selected.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element is selected
	 */
	public boolean isSelected();
	
	/**
	 * <p>
	 * Is the element attached to the DOM structure of the page?
	 * </p>
	 * @return <code>true</code> if the element is present in the DOM
	 */
	public boolean isPresent();
	
	/**
	 * <p>
	 * Does the element have a non-empty "id" attribute?
	 * If the element's id contains only whitespaces, this method returns <code>false</code>.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element has an id
	 */
	public boolean hasId();
	
	/**
	 * <p>
	 * Does the element have text?
	 * If the element's text is empty or contains only whitespaces, this method returns <code>false</code>.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element has text
	 */
	public boolean hasText();
	
	/**
	 * <p>
	 * Does the element have value?
	 * If the element's value is empty or contains only whitespaces, this method returns <code>false</code>.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element has value
	 */
	public boolean hasValue();
	
	/**
	 * <p>
	 * Does the element have a non-empty attribute with the given name?
	 * If the element's attribute contains only whitespaces, this method returns <code>false</code>.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @param name the name of the attribute
	 * @return <code>true</code> if the element has the attribute
	 */
	public boolean hasAttribute(String name);
	
	/**
	 * <p>
	 * Does the element have any class name?
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return <code>true</code> if the element has class name
	 */
	public boolean hasClassName();
	
	/**
	 * <p>
	 * Does the element have the given class name?
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @param className the class name
	 * @return <code>true</code> if the element has the class name
	 */
	public boolean hasClassName(String className);
	
	/**
	 * <p>
	 * Does the element have CSS value with the given name?
	 * If the CSS value is empty or contains only whitespaces, this method returns <code>false</code>.
	 * </p>
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @param propertyName the name of the CSS property
	 * @return <code>true</code> if the element has the CSS property
	 */
	public boolean hasCssValue(String propertyName);
	
}
