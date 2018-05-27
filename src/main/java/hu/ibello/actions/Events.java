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
	
	public static Events of(String eventName) {
		try {
			return Events.valueOf(eventName);
		} catch (Exception ex) {
			for (Events e : Events.values()) {
				if (e.eventName.equalsIgnoreCase(eventName)) {
					return e;
				}
			}
			return null;
		}
	}
	
}
