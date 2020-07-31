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

import java.io.File;
import java.net.URL;
import java.util.regex.Pattern;

import hu.ibello.model.Screenshot;

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
 * <li>{@link Browser#reload()}</li>
 * </ul>
 * <p>
 * To set the size of the browser window, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#maximize()}</li>
 * <li>{@link Browser#resize(int, int)}</li>
 * </ul>
 * <p>
 * To manage storages, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#sessionStorage()}</li>
 * <li>{@link Browser#localStorage()}</li>
 * <li>{@link Browser#cookies()}</li>
 * </ul>
 * <p>
 * To handle downloaded files, use these methods:
 * </p>
 * <ul>
 * <li>{@link #getLatestDownloadedFile()}</li>
 * <li>{@link #findDownloadedFile(String)}</li>
 * <li>{@link #findDownloadedFile(Pattern)}</li>
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
	 * Reloads the current URL in the browser.
	 */
	public void reload();
	
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
	
	/**
	 * Save screenshot about the active window or tab.
	 * The screenshot will be added to the result report too.
	 * @return screenshot data with the file and URL
	 */
	public Screenshot saveScreenshot();
	
	/**
	 * Returns a {@link Storage} instance which can be used to manipulate the session storage of the browser.
	 * @return the session storage object
	 */
	public Storage sessionStorage();
	
	/**
	 * Returns a {@link Storage} instance which can be used to manipulate the local storage of the browser.
	 * @return the local storage object
	 */
	public Storage localStorage();
	
	/**
	 * Returns a {@link Storage} instance which can be used to manipulate the cookies of the browser.
	 * @return the storage of cookies
	 */
	public Storage cookies();
	
	/**
	 * Returns a file which was downloaded with the browser earlier.
	 * If the file with the given name does not exists then this method returns <code>null</code>.
	 * <p>
	 * The method tries to find the file in the download directory of the browser.
	 * In some cases browsers rename files because of name collision. The <code>fileName</code>
	 * parameter of this method should be the original name of the file. The method will try to find out the new name.
	 * </p>
	 * @param fileName the name of the downloaded file
	 * @return the file or <code>null</code>
	 */
	public File findDownloadedFile(String fileName);
	
	/**
	 * Returns a file which was downloaded with the browser earlier.
	 * If the file with the given pattern does not exists then this method returns <code>null</code>.
	 * <p>
	 * The method tries to find the file in the download directory of the browser.
	 * In some cases browsers rename files because of name collision. The <code>pattern</code>
	 * parameter of this method should target the original name of the file. The method will try to find out the new name.
	 * </p>
	 * @param pattern regular expression pattern for the name of the downloaded file
	 * @return the file or <code>null</code>
	 */
	public File findDownloadedFile(Pattern pattern);
	
	/**
	 * Returns the latest file from the browsers download directory.
	 * If the directory is empty then this method returns <code>null</code>.
	 * @return the file or <code>null</code>
	 */
	public File getLatestDownloadedFile();
}
