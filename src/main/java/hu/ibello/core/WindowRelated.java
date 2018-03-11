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
