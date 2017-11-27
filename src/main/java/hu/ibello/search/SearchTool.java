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
package hu.ibello.search;

import hu.ibello.elements.WebElement;
import hu.ibello.elements.WebElements;

/**
 * <p>
 * Interface for searching elements on a page.
 * It offers a fluent interface for element search. Examples:
 * </p>
 * <p>Search a single element:</p>
 * <pre>
 * SearchTool find = ...;
 * WebElement element = find.using(By.LABEL, "Field #${0}:", 1).first();
 * </pre>
 * <p>Search multiple elements (below a specific one):</p>
 * <pre>
 * WebElement currentRow = ...;
 * SearchTool find = ...;
 * WebElements element = find.using(By.TAG_NAME, "tr").below(currentRow).all();
 * </pre>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 */
public interface SearchTool {

	/**
	 * <p>
	 * Sets the main search conditions for the element search.
	 * </p>
	 * <p>
	 * The <code>by</code> argument specifies the main search algorithm. It is an enum constant of {@link By}.
	 * </p>
	 * <p>
	 * The meaning of <code>pattern</code> argument depends on the value of <code>by</code>. For more information,
	 * see enum constants of {@link By}.
	 * </p>
	 * <p>
	 * The <code>parameters</code> is a vararg. It specifies parameters in the <code>pattern</code> argument.
	 * If <code>pattern</code> contain parameter substitution markers then the method will substitute
	 * the parameters into those places. A parameter substitution marker is a 0-based index surrounded by
	 * <code>${</code> and <code>}</code>. For example, if <code>pattern</code> is <code>"${0}-${1}"</code>,
	 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
	 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
	 * result pattern will be <code>"a-1"</code>.
	 * </p>
	 * @param by search algorithm
	 * @param pattern search pattern
	 * @param parameters substitute parameters of <code>pattern</code>
	 * @return this {@link SearchTool} instance
	 * @see Find
	 */
	public SearchTool using(By by, String pattern, Object ... parameters);
	
	/**
	 * <p>
	 * Sets the main search conditions for the element search.
	 * </p>
	 * <p>
	 * The search algorithm of the anchor is {@link By#CSS_SELECTOR}. The <code>pattern</code> argument
	 * should be a CSS selector.
	 * </p>
	 * <p>
	 * The <code>parameters</code> is a vararg. It specifies parameters in the <code>pattern</code> argument.
	 * If <code>pattern</code> contain parameter substitution markers then the method will substitute
	 * the parameters into those places. A parameter substitution marker is a 0-based index surrounded by
	 * <code>${</code> and <code>}</code>. For example, if <code>pattern</code> is <code>"#${0}-${1}"</code>,
	 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
	 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
	 * result pattern will be <code>"#a-1"</code>.
	 * </p>
	 * @param pattern search pattern
	 * @param parameters substitute parameters of <code>pattern</code>
	 * @return this {@link SearchTool} instance
	 * @see SearchTool#using(By, String, Object...)
	 * @see Find
	 */
	public default SearchTool using(String pattern, Object ... parameters) {
		return using(By.CSS_SELECTOR, pattern, parameters);
	}
	
	/**
	 * Modifies the element search so the desired element should be in the same row as the anchor element.
	 * It means that the horizontal center line of the anchor should fit into the boundaries of the desired
	 * element.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#ROW
	 */
	public SearchTool inRowOf(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be in the same column as the anchor element.
	 * It means that the vertical center line of the anchor should fit into the boundaries of the desired
	 * element.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#COLUMN
	 */
	public SearchTool inColumnOf(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be left from the anchor element.
	 * It means that the x coordinate of the desired element's right edge if less than or equal to the x coordinate
	 * of the anchor element's left edge.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#LEFT_FROM
	 */
	public SearchTool leftFrom(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be right from the anchor element.
	 * It means that the x coordinate of the desired element's left edge if greater than or equal to the x
	 * coordinate of the anchor element's right edge.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#RIGHT_FROM
	 */
	public SearchTool rightFrom(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be above the anchor element.
	 * It means that the bottom y coordinate of the desired element is less than or equal to the top y coordinate
	 * of the anchor element.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#ABOVE
	 */
	public SearchTool above(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be below the anchor element.
	 * It means that the top y coordinate of the desired element is greater than or equal to the bottom y coordinate
	 * of the anchor element.
	 * @param anchor another element on the page
	 * @return this {@link SearchTool} instance
	 * @see Position
	 * @see PositionType#BELOW
	 */
	public SearchTool below(WebElement anchor);
	
	/**
	 * Modifies the element search so the desired element should be the ancestor of the anchor element.
	 * The anchor element is specified by it's search conditions.
	 * <p>
	 * The <code>by</code> argument specifies the search algorithm. It is an enum constant of {@link By}.
	 * </p>
	 * <p>
	 * The meaning of <code>pattern</code> argument depends on the value of <code>by</code>. For more information,
	 * see enum constants of {@link By}.
	 * </p>
	 * <p>
	 * The <code>parameters</code> is a vararg. It specifies parameters in the <code>pattern</code> argument.
	 * If <code>pattern</code> contain parameter substitution markers then the method will substitute
	 * the parameters into those places. A parameter substitution marker is a 0-based index surrounded by
	 * <code>${</code> and <code>}</code>. For example, if <code>pattern</code> is <code>"${0}-${1}"</code>,
	 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
	 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
	 * result pattern will be <code>"a-1"</code>.
	 * </p>
	 * @param by search algorithm of anchor element
	 * @param pattern search pattern of anchor element
	 * @param parameters search parameters of anchor element
	 * @return this {@link SearchTool} instance
	 * @see Relation
	 * @see RelationType#ANCESTOR_OF
	 */
	public SearchTool asAncestorOf(By by, String pattern, Object ... parameters);
	
	/**
	 * Modifies the element search so the desired element should be the ancestor of the anchor element.
	 * The anchor element is specified by it's search conditions.
	 * <p>
	 * The search algorithm of the anchor is {@link By#CSS_SELECTOR}. The <code>pattern</code> argument
	 * should be a CSS selector.
	 * </p>
	 * <p>
	 * The <code>parameters</code> is a vararg. It specifies parameters in the <code>pattern</code> argument.
	 * If <code>pattern</code> contain parameter substitution markers then the method will substitute
	 * the parameters into those places. A parameter substitution marker is a 0-based index surrounded by
	 * <code>${</code> and <code>}</code>. For example, if <code>pattern</code> is <code>"#${0}-${1}"</code>,
	 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
	 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
	 * result pattern will be <code>"#a-1"</code>.
	 * </p>
	 * @param pattern search pattern of anchor element
	 * @param parameters search parameters of anchor element
	 * @return this {@link SearchTool} instance
	 * @see Relation
	 * @see RelationType#ANCESTOR_OF
	 */
	public default SearchTool asAncestorOf(String pattern, Object ... parameters) {
		return asAncestorOf(By.CSS_SELECTOR, pattern, parameters);
	}
	
	/**
	 * <p>
	 * Get only the first element on the page which matches the conditions.
	 * </p>
	 * <p>
	 * This method always returns a non-null object, even if the element is not present on the
	 * page at the time of the search. The returned {@link WebElement} instance can be used for
	 * different actions and assertions later - there is no need to search with the same arguments
	 * again.
	 * </p>
	 * @return the first element which matches the conditions
	 */
	public WebElement first();
	
	/**
	 * <p>
	 * Get the nth element on the page which matches the conditions. The index of the element should
	 * be specified with the parameter. It is 0-based, so the index of the first element is 0, the second is 1,
	 * and so on.
	 * </p>
	 * <p>
	 * This method always returns a non-null object, even if the element is not present on the
	 * page at the time of the search. The returned {@link WebElement} instance can be used for
	 * different actions and assertions later - there is no need to search with the same arguments
	 * again.
	 * </p>
	 * @param index 0-based index of the element
	 * @return the element with the given index
	 */
	public default WebElement nth(int index) {
		return all().get(index);
	}
	
	/**
	 * <p>
	 * Get all elements on the page that match the conditions.
	 * </p>
	 * <p>
	 * This method always returns a non-null object, even if the elements are not present on the
	 * page at the time of the search. The returned {@link WebElements} instance can be used for
	 * different actions and assertions later - there is no need to search with the same arguments
	 * again.
	 * </p>
	 * @return all elements that match the conditions
	 */
	public WebElements all();
	
}
