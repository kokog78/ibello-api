package hu.ibello.actions;

/**
 * Enumeration storing event names.
 * Constant values can be used in {@link ActionBuilder#fireEvent(Events)} method.
 * 
 * @author Kornél Simon
 *
 */
public enum Events {

	MOUSE_ENTER			("mouseenter",			EventType.MOUSE),
	MOUSE_OVER			("mouseover",			EventType.MOUSE),
	MOUSE_MOVE			("mousemove",			EventType.MOUSE),
	MOUSE_DOWN			("mousedown",			EventType.MOUSE),
	MOUSE_UP			("mouseup",				EventType.MOUSE),
	AUX_CLICK			("auxclick",			EventType.MOUSE),
	CLICK				("click",				EventType.MOUSE),
	DBLCLICK			("dblclick",			EventType.MOUSE),
	CONTEXT_MENU		("contextmenu",			EventType.MOUSE),
	WHEEL				("wheel",				EventType.MOUSE),
	MOUSE_LEAVE			("mouseleave",			EventType.MOUSE),
	MOUSE_OUT			("mouseout",			EventType.MOUSE),
	SELECT				("select",				EventType.MOUSE),
	POINTER_LOCK_CHANGE	("pointerlockchange",	EventType.MOUSE),
	POINTER_LOCK_ERROR	("pointerlockerror",	EventType.MOUSE);
	
	/**
	 * Name of the event.
	 */
	public final String eventName;
	
	/**
	 * Type of the event.
	 */
	public final EventType type;
	
	private Events(String eventName, EventType type) {
		this.eventName = eventName;
		this.type = type;
	}
	
}
