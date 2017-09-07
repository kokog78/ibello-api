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
 * is a valid CSS selector, but if search algorithm is {@link hu.ibello.search.By#TAG_NAME},
 * the the pattern is a valid HTML tag name.</li>
 * <li>Search parameters: the search pattern may contain parameter substitution markers.
 * A parameter substitution marker is a 0-based index surrounded by <code>${</code> and <code>}</code>.
 * Parameters are transformed to string and substituted into those markers by index.
 * For example, if <code>pattern</code> is <code>"${0}-${1}"</code>,
 * then the method will replace <code>${0}</code> with the first parameter, and <code>${1}</code>
 * with the second parameter. If the parameters are <code>"a"</code> and <code>1</code>, then the
 * result pattern will be <code>"a-1"</code>.</li>
 * <li>Search modifiers: each search may have one or more modifiers. These are just another conditions
 * for the search. Some are conditions for the position of the desired elements, another ones
 * specify the relation of the desired elements to another elements on the page.</li>
 * </ul>
 * <p>
 * The result of an element search is a single {@link hu.ibello.elements.WebElement} instance,
 * or a collection of elements: {@link hu.ibello.elements.WebElements}, list of WebElement instances
 * or array of WebElement instances.
 * </p>
 * <p>
 * An elements search can be <em>static</em> or <em>dynamic</em>.
 * </p>
 * <p>
 * During a static element search the search conditions are specified with annotations added to
 * fields in a page object (see {@link hu.ibello.pages.PageObject}). The search is performed
 * automatically when the page object is initialized, and the result elements are stored in the
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
 * During a dynamic element search java methods are called directly. This is possible with the use of
 * {@link hu.ibello.search.SearchTool} interface: it has some useful methods where the search
 * conditions can be specified. Instances of this interface can be obtained by
 * {@link hu.ibello.elements.WebElement#find()} and {@link hu.ibello.pages.PageObject#find()}.
 * Examples:
 * </p>
 * <pre>
 * WebElement okButton = find().using("#ok-button").first();
 * WebElement cancelButton = find().using("button").leftFrom(okButton).first();
 * WebElement modalButton = find().using("#modal-window").first().using(By.TAG_NAME, "button").first();
 * </pre>
 * @author Kornél Simon
 */
package hu.ibello.search;