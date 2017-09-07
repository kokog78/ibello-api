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
	
}
