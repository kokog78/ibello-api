package hu.ibello.search;

/**
 * This enum symbolizes an element search algorithm.
 * During element search on web-pages, a search algorithm and a search pattern is used
 * to find the desired elements. The meaning of the pattern is specified by the search algorithm
 * (an enum constant).
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 *
 */
public enum By {

	/**
	 * Search by CSS selector. The search pattern can be any valid CSS selector.
	 */
	CSS_SELECTOR,
	
	/**
	 * Search by ID. The search pattern is an ID of an element.
	 */
	ID,
	
	/**
	 * Search by name. The search pattern if the value of the <code>name</code> attribute
	 * of the desired element(s).
	 */
	NAME,
	
	/**
	 * Search by tag name. The search pattern is the tag name of the desired element(s).
	 */
	TAG_NAME,
	
	/**
	 * Search by CSS class name. The search pattern is a CSS cass name which is assigned to
	 * the desired element(s).
	 */
	CLASS_NAME,
	
	/**
	 * Search by text of a button. The search pattern is the case-sensitive caption of the desired element.
	 * The element itself can be:
	 * <ul>
	 * <li>a <code>button</code> with the given text,</li>
	 * <li>an <code>input</code> field for which the <code>value</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>button</code>, <code>submit</code> or <code>reset</code>,</li>
	 * <li>an <code>input</code> field for which the <code>alt</code> attribute is the given text,
	 * and the <code>type</code> attribute is <code>image</code></li>
	 * <li>an <code>a</code> (link) element with the given text.</li>
	 * </ul>
	 * During the search, the first two categories are preferred. It means that if the search results a positive
	 * outcome for the first category, then the other categories are not searched.
	 */
	BUTTON_TEXT,
	
	/**
	 * Search by the text of an element. The search pattern is the case-sensitive content of the desired element.
	 */
	TEXT,
	
	/**
	 * Search by label. The search pattern is the case-sensitive content of a <code>label</code> element.
	 * The <code>label</code> should belong to the desired element. It means that it's <code>for</code> attribute
	 * matches the <code>id</code> attribute of the desired element.
	 */
	LABEL;
}
