package hu.ibello.core;

/**
 * Base class for window-related objects, such as page objects and step libraries.
 * It has a window identifier string which identifies the browser window.
 * The {@link #getWindowId()} returns this identifier.
 * @author Kornél Simon
 *
 */
public abstract class WindowRelated {

	private String windowId;
	
	/**
	 * Identifier of the related browser window.
	 * This object is connected to that browser window.
	 * @return window identifier
	 */
	protected String getWindowId() {
		return windowId;
	}
}
