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
/**
 * This package contains interfaces, annotations and enums used in element search.
 * <h1>Ibello Element Search Engine</h1>
 * <p>
 * During element search we are trying to find some elements on the current page by
 * specifying search conditions. These conditions are:
 * </p>
 * <ul>
 * <li>Search algorithm: the algorithm how the search is processed. The available search algorithms
 * are available as enum constants in {@link hu.ibello.search.By}.</li>
 * <li>Search pattern: a single string. It's meaning depends on the search algorithm. For example,
 * if search algorithm is {@link hu.ibello.search.By#CSS_SELECTOR}, then the search pattern
 * is a valid CSS selector, and if search algorithm is {@link hu.ibello.search.By#TAG_NAME},
 * the the pattern is a valid HTML tag name.</li>
 * <li>Search parameters: the search pattern may contain parameter substitution markers.
 * A parameter substitution marker is a 0-based index surrounded by <code>${</code> and <code>}</code>.
 * Parameters are transformed to string and substituted into those markers by index.
 * For example, if <code>pattern</code> is <code>"${0}-${1}"</code>,
 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
 * result will be <code>"a-1"</code>.</li>
 * <li>Search modifiers: each search may have one or more modifiers. These are just another conditions
 * for the search. Some are conditions about the position of the desired elements, another ones
 * specify the relation of the desired elements to another elements on the page.</li>
 * </ul>
 * <p>
 * The result of an element search is a single {@link hu.ibello.elements.WebElement} instance,
 * or a collection of elements: {@link hu.ibello.elements.WebElements}.
 * </p>
 * <p>
 * An elements search can be <em>static</em> or <em>dynamic</em>.
 * </p>
 * <p>
 * During a static element search the search conditions are specified with annotations added to
 * fields of a page object (see {@link hu.ibello.pages.PageObject}). The search is performed
 * automatically when the page object is initialized, and the results are stored in the
 * field's value. These annotations are:
 * </p>
 * <ul>
 * <li>{@link hu.ibello.search.Find}</li>
 * <li>{@link hu.ibello.search.Position}</li>
 * <li>{@link hu.ibello.search.Relation}</li>
 * </ul>
 * <p>
 * Examples:
 * </p>
 * <pre>
 * {@literal @}Find(selector="button")
 * {@literal @}Position(type=PositionType.LEFT_FROM, selector="#ok-button")
 * private WebElement cancelButton;
 * 
 * {@literal @}Find(by=By.TAG_NAME, selector="button")
 * {@literal @}Relation(type=RelationType.DESCENDANT_OF, selector="#modal-window")
 * private WebElement modalButton;
 * </pre>
 * <p>
 * It is possible to use different search rules for different browsers. Example:
 * </p>
 * <pre>
 * {@literal @}Find(in=BrowserKind.ANDROID, by=By.ID, selector="android-resource-id")
 * {@literal @}Find(in=BrowserKind.IOS, by=By.NAME, selector="ios-element-name")
 * private WebElement cancelButton;
 * </pre>
 * <p>
 * During a dynamic element search java methods are called directly. This is possible with the use of
 * {@link hu.ibello.search.SearchTool} interface: it has some useful methods where the search
 * conditions can be specified. Instances of this interface can be obtained by
 * {@link hu.ibello.elements.WebElement#find()} and {@link hu.ibello.pages.PageObject#find()}.
 * Examples (in a page object):
 * </p>
 * <pre>
 * WebElement okButton = find().using("#ok-button").first();
 * WebElement cancelButton = find().using("button").leftFrom(okButton).first();
 * WebElement modalButton = find().using("#modal-window").first().find().using(By.TAG_NAME, "button").first();
 * </pre>
 * In some cases the desired elements are in a HTML <code>iframe</code> element. To find these elements,
 * the page object, which contains those element references, should have a {@link hu.ibello.search.Frame}
 * annotation with the search properties of the <code>iframe</code>. In this case every element searches
 * performed by that page object will be executed inside of that specific <code>iframe</code>.
 * @author Kornél Simon
 */
package hu.ibello.search;