package hu.ibello.search;

/**
 * <p>
 * Constants of this enum specifies the relative position of an element from an anchor element.
 * </p>
 * <p>
 * The values are used in {@link Position#type()} annotation property.
 * </p>
 * <p>About <em>ibello</em> search engine see {@link hu.ibello.search}.</p>
 * @author Kornél Simon
 * @see Position
 */
public enum PositionType {

	/**
	 * The desired element is in the same row as the anchor element.
	 * It means that the horizontal center line of the anchor should fit into the boundaries of the desired
	 * element.
	 */
	ROW,
	
	/**
	 * The desired element is in the same column as the anchor element.
	 * It means that the vertical center line of the anchor should fit into the boundaries of the desired
	 * element.
	 */
	COLUMN,
	
	/**
	 * The desired element is left from the anchor element.
	 * It means that the x coordinate of the desired element's right edge if less than or equal to the x coordinate
	 * of the anchor element's left edge.
	 */
	LEFT_FROM,
	
	/**
	 * The desired element is right from the anchor element.
	 * It means that the x coordinate of the desired element's left edge if greater than or equal to the x
	 * coordinate of the anchor element's right edge.
	 */
	RIGHT_FROM,
	
	/**
	 * The desired element is above the anchor element.
	 * It means that the bottom y coordinate of the desired element is less than or equal to the top y coordinate
	 * of the anchor element.
	 */
	ABOVE,
	
	/**
	 * The desired element is below the anchor element.
	 * It means that the top y coordinate of the desired element is greater than or equal to the bottom y coordinate
	 * of the anchor element.
	 */
	BELOW;
	
}
