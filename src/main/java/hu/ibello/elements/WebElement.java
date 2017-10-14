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
package hu.ibello.elements;

import hu.ibello.pages.PageObject;
import hu.ibello.search.SearchTool;

/**
 * <p>
 * An instance of this interface represents an element on a web-page.
 * The existence of a WebElement instance does not state that the underlying element exists on the page.
 * It just stores the search criteria and another information about the element. Later, when the
 * WebElement instance is used, will be verified that the underlying element exists or not.
 * Therefore it is safe to store a WebElement instance and use it later.
 * </p>
 * <p>
 * A WebElement instance does not have direct methods to manipulate the element itself or
 * do some assertions with it - these are available from page objects. (See {@link PageObject}.)
 * During element manipulation and verification the <em>ibello</em> reruns the search again
 * if necessary. A typical example is when an element on a page is recreated by javascript code
 * after the search. In this case, the original element is not present anymore so it can't be clicked.
 * If someone tries to call a click action on it, then <em>ibello</em> recognizes the change and executes
 * the search again. Only if the second search does not finds anything will <em>ibello</em> throw an
 * exception.
 * </p>
 * <p>
 * The WebElement supports element search between it's descendants. For more information, see
 * {@link WebElement#find()}.
 * </p>
 * <p>
 * If a WebElement instance was obtained by a search which received parameters, then there is an option to
 * change these parameters later, and rerun the search - see {@link WebElement#applyParameters(Object...)}.
 * </p>
 * @author Kornél Simon
 * @see WebElements
 */
public interface WebElement {

	/**
	 * <p>
	 * Changes the search parameters of this WebElement instance.
	 * </p>
	 * <p>
	 * A WebElement instance can be created with an <em>element search</em> - see {@link SearchTool} and
	 * {@link PageObject}. During the
	 * search some parameters can be specified, which are substituted into the search pattern. The WebElement
	 * instance stores these parameters, and we can change them to indicate a new search later, when the instance
	 * is going to be used.
	 * </p>
	 * <p>
	 * It is possible to <em>not</em> define the parameters at the time of the creation of a WebElement instance,
	 * and set them later, before the instance is used. Example (from a page object):
	 * </p>
	 * <pre>
	 * {@literal //} search an element without specifying the parameter
	 * {@literal @}Find("#button-${0}")
	 * WebElement button;
	 * 
	 * public clickButton(int index) {
	 *     {@literal //} set the parameter and therefore rerun the search then click the element
	 *     doWith(button.applyParameters(index)).click();
	 * }
	 * </pre>
	 * @param parameters the new search parameters
	 * @return the current WebElement instance
	 */
	public WebElement applyParameters(Object ... parameters);
	
	/**
	 * <p>
	 * Returns a {@link SearchTool} instance which is used to search elements inside this element.
	 * </p>
	 * <p>
	 * The returned instance offers a fluent interface for element search. Example:
	 * </p>
	 * <pre>
	 * WebElement image = ...;
	 * WebElement child = find().using(By.TAG_NAME, "span").leftFrom(image).first();
	 * </pre>
	 * @return an object used for element search inside this element
	 */
	public SearchTool find();
}
