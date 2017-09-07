package hu.ibello.search;

/**
 * <p>
 * Constants of this enum specifies the relation of an element and an anchor element.
 * </p>
 * <p>
 * The values are used in {@link Relation#type()} annotation property.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Relation
 */
public enum RelationType {

	/**
	 * The desired element is the descendant of the anchor element.
	 */
	DESCENDANT_OF,
	
	/**
	 * The desired element is the ancestor of the anchor element.
	 */
	ANCESTOR_OF;
	
}
