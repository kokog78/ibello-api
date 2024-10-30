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

import hu.ibello.model.BrowserKind;
import hu.ibello.model.Screenshot;
import hu.ibello.script.JavascriptExecutor;
import hu.ibello.search.Window;

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
 * To handle the browser window, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#maximize()}</li>
 * <li>{@link Browser#resize(int, int)}</li>
 * <li>{@link Browser#close()}</li>
 * </ul>
 * <p>
 * To get some information about the current window or tab, see these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#getKind()}</li>
 * <li>{@link Browser#getVersion()}</li>
 * <li>{@link Browser#getCompositeId()}</li>
 * <li>{@link Browser#getPageSource()}</li>
 * <li>{@link Browser#saveScreenshot()}</li>
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
 * To execute JavaScript code int the current window or tab, see this method:
 * </p>
 * <ul>
 * <li>{@link Browser#javascript()}</li>
 * </ul>
 * <p>
 * To handle downloaded files, use these methods:
 * </p>
 * <ul>
 * <li>{@link Browser#getLatestDownloadedFile()}</li>
 * <li>{@link Browser#findDownloadedFile(String)}</li>
 * <li>{@link Browser#findDownloadedFile(Pattern)}</li>
 * </ul>
 * @author Kornél Simon
 *
 */
public interface Browser {
	
	/**
	 * Returns the current URL in the browser.
	 * @return the current URL
	 */
	public String getURL();
	
	/**
	 * Opens the given URL in the browser. The argument can be:
	 * <ul>
	 * <li>a full absolute URL with protocol and hostname, eg. <code>http://localhost:8080/page</code>,</li>
	 * <li>a relative URL, eg. <code>/page</code> or <code>index.html</code>.</li>
	 * </ul>
	 * <p>
	 * If the URL is absolute, then it will be loaded in the browser.
	 * </p>
	 * <p>
	 * If the URL is relative, then it will be appended to the value of the <code>ibello.url.base</code> configuration property,
	 * and the result URL will be loaded into the browser.
	 * </p>
	 * @param url relative or absolute URL
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public void openURL(String url);
	
	/**
	 * <p>
	 * Opens the given URL in the browser.
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
	 * Closes the browser window or tab.
	 */
	public void close();
	
	/**
	 * Returns the HTML code of the current page.
	 * This contains every elements of the current state of DOM.
	 * @return the source HTML code of the current page
	 */
	public String getPageSource();
	
	/**
	 * Save screenshot about the active window or tab.
	 * The screenshot will be added to the result report too.
	 * @return screenshot data with the file and URL
	 */
	public Screenshot saveScreenshot();
	
	/**
	 * Returns an enumeration value which reflects the kind of the browser.
	 * @return the browser's kind
	 */
	public BrowserKind getKind();
	
	/**
	 * Returns the version of the browser.
	 * @return the browser's version
	 */
	public String getVersion();
	
	/**
	 * Returns the composite ID of the current browser and tab.
	 * This is the ID which was specified with a {@link Window} annotation.
	 * <p>
	 * If the ID is empty then the browser is the default one.
	 * </p><p>
	 * If the ID starts with a colon then it represents a new tab in the default window.
	 * </p><p>
	 * If the ID contains a colon (but not starts with it), then it represents a tab in an additional
	 * browser window. The characters before the colon are assigned to the window.
	 * </p><p>
	 * If the ID does not contain a colon and it is not empty, then it represents the first tab of an additional window.
	 * </p>
	 * @return the composite ID of the browser
	 */
	public String getCompositeId();
	
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
	 * Returns an object which is capable to run JavaScript code in the browser.
	 * @return JavaScript executor instance
	 */
	public JavascriptExecutor javascript();
	
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
