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

import java.net.URL;

/**
 * <p>
 * The object which implements this interface represents the browser.
 * It's public methods can be used for different purposes.
 * </p>
 * <p>
 * To open an URL in the browser, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#openURL(String)}</li>
 * <li>{@link Browser#openURL(java.net.URL)}</li>
 * </ul>
 * <p>
 * To set the size of the browser window, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#maximize()}</li>
 * <li>{@link Browser#resize(int, int)}</li>
 * </ul>
 * @author Kornél Simon
 *
 */
public interface Browser {
	
	/**
	 * Opens the given URL in the browser. The argument can be:
	 * <ul>
	 * <li>a full absolute URL with protocol and hostname, eg. <code>http://localhost:8080/page</code>,</li>
	 * <li>an absolute URL without protocol, eg. <code>localhost:8080/page</code>,</li>
	 * <li>a relative URL without host, eg. <code>/page</code>.</li>
	 * </ul>
	 * <p>
	 * The result depends on the value of the <code>ibello.url.base</code> configuration property.
	 * </p>
	 * <p>
	 * If it is not specified and an absolute URL is given without protocol, then the <code>http</code>
	 * protocol will be used. Relative URL without the configuration property results a runtime exception.
	 * </p>
	 * <p>
	 * If the configuration property is specified, then the method merges the argument with the property.
	 * If the argument is an absolute URL (with or without protocol), then only it's path will be used,
	 * the protocol, host and port will come from the configuration. If the URL is relative, then it will
	 * concatenated to the configuration property.
	 * </p>
	 * @param url relative or absolute URL
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public void openURL(String url);
	
	/**
	 * <p>
	 * Opens the given URL in the browser.
	 * </p>
	 * <p>
	 * If the <code>ibello.url.base</code> configuration property is specified, then the method merges the argument
	 * with the property. Protocol, host and port of the result URL will come from the configuration, the other
	 * parts will come from the argument.
	 * </p>
	 * @param url the URL to set
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public default void openURL(URL url) {
		openURL(url.toExternalForm());
	}
	
	/**
	 * <p>
	 * Maximizes the size of the browser window to fit the borders of the current display.
	 * </p>
	 */
	public void maximize();
	
	/**
	 * Sets the size of the browser window.
	 * @param width the new width of the browser window, in pixels
	 * @param height the new height of the browser window, in pixels
	 */
	public void resize(int width, int height);
	
}
