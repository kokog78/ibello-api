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
 * The methods of this interface are returning properties of a single {@link WebElement} instance.
 * </p>
 * <p>
 * Each getter method blocks the current thread until the element is accessible.
 * This waiting has a timeout which can be specified with the {@link TimeoutRelated#withTimeout(Enum)} or {@link TimeoutRelated#withTimeout(String)}
 * method. If no timeout is specified then the default timeout will be used.
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementGetter extends TimeoutRelated<WebElementGetter> {

	/**
	 * Returns the \"id\" attribute of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the \"id\" attribute of the element
	 */
	public String id();
	
	/**
	 * Returns the value of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the value of the element
	 */
	public String value();
	
	/**
	 * Returns the file name of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the file name of the element
	 */
	public String fileName();
	
	/**
	 * Returns the named attribute of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @param name the name of the desired attribute
	 * @return the attribute of the element
	 */
	public String attribute(String name);
	
	/**
	 * Returns the tag name of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the tag name of the element
	 */
	public String tagName();
	
	/**
	 * Returns the CSS class names of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the CSS class names of the element
	 */
	public String[] cssClassNames();
	
	/**
	 * Returns the CSS property value of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @param propertyName the name of the desired CSS property
	 * @return the CSS property value of the element
	 */
	public String cssValue(String propertyName);
	
	/**
	 * Returns the text of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the text of the element
	 */
	public String text();
	
	/**
	 * Returns the text content of all the selected options of the element.
	 * <p>
	 * This methods blocks the thread until the element is accessible or the time runs out.
	 * </p>
	 * @return the selected options of the element
	 */
	public String[] selectedOptions();
	
}
