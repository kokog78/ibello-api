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

import java.util.List;

import hu.ibello.pages.PageObject;
import hu.ibello.search.SearchTool;

/**
 * <p>
 * Represents a collection of elements on a page.
 * The existence of a WebElements instance does not state that the underlying elements exists on the page.
 * It just stores the search criteria and another information about the elements. Later, when the
 * WebElement instances are going to be used, will be verified that the underlying elements exist or not.
 * Therefore it is safe to store a WebElements instance and use it later.
 * </p>
 * <p>
 * A WebElements instance does not have direct methods to manipulate the element itself or
 * do some assertions with it - these are available from page objects. (See {@link PageObject}.)
 * During element manipulation and verification the <em>ibello</em> reruns the search again
 * if necessary. A typical example is when an element on a page is recreated by javascript code
 * after the search. In this case, the original element is not present anymore so it can't be clicked.
 * If someone tries to call a click action on it, then <em>ibello</em> recognizes the change and executes
 * the search again. Only if the second search does not finds anything will <em>ibello</em> throw an
 * exception.
 * </p>
 * <p>
 * The WebElements interface extends the {@link List} interface (with {@link WebElement} items).
 * Unlike "normal" lists, a WebElements instance never throws exception when someone reads a non-existing item
 * - see {@link List#get(int)}. Instead of it, a new WebElement instance is created and returned (which
 * points to a non-existing element). Therefore it is safe to call {@link List#get(int)} on a WebElements
 * instance then check the existence of the element returned.
 * </p>
 * <p>
 * If a WebElements instance was obtained by a search which received parameters, then there is an option to
 * change these parameters later, and rerun the search - see {@link WebElements#applyParameters(Object...)}.
 * </p>
 * 
 * @author Kornél Simon
 * @see WebElement
 */
public interface WebElements extends List<WebElement> {

	/**
	 * <p>
	 * Changes the search parameters of this WebElements instance.
	 * </p>
	 * <p>
	 * A WebElements instance can be created with an <em>element search</em> - see {@link SearchTool} and
	 * {@link PageObject}. During the search
	 * some parameters can be specified, which are substituted into the search pattern. The WebElements instance
	 * stores these parameters, and we can change them to indicate a new search later, when the instance is going
	 * to be used.
	 * </p>
	 * <p>
	 * It is possible to <em>not</em> define the parameters at the time of the creation of a WebElements instance,
	 * and set them later, before the instance is used. Example (from a page object):
	 * </p>
	 * <pre>
	 * {@literal //} search elements without specifying the parameter
	 * {@literal @}Find("#row-${0} .cell")
	 * WebElements cells;
	 * 
	 * public expectCellIsVisible(int rowIndex, int columnIndex) {
	 *     {@literal //} set the parameter and therefore rerun the search
	 *     {@literal //} before we perform assertion
	 *     expect().that(cells.applyParameters(rowIndex).get(columnIndex)).isVisible();
	 * }
	 * </pre>
	 * @param parameters the new search parameters
	 * @return the current WebElements instance
	 */
	public WebElements applyParameters(Object ... parameters);
}
