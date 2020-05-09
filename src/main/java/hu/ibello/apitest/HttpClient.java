package hu.ibello.apitest;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * This interface describes a HTTP client which sends a single request and receives a response.
 * <p>
 * A new instance of this interface can be configured with it's fluent methods.
 * These methods return the same {@link HttpClient} instance so the configuration can be performed
 * with method call chain. At the end of this chain the {@link #sendAndReceive()} method should be called
 * which executes the call and returns with the response.
 * </p>
 * <p>
 * The client instance has some default configuration.
 * </p>
 * <ul>
 * <li>Default HTTP method is GET. This can be changed with the {@link #method(HttpMethod)} method.</li>
 * <li>Default request charset is UTF-8. This can be changed with {@link #charset(Charset)} method.</li>
 * <li>Default request MIME type is "text/plain". This can be changed with {@link #mimeType(String)} method.</li>
 * <li>By default the "Accept" header is added to the request with "text/html" value. This can be changed with the {@link #accept(String)} method.</li>
 * </ul>
 * <p>
 * Example HTTP API call:
 * </p>
 * <pre>
 * String data = ...;
 * HttpResponse&lt;String&gt; response = httpClient()
 *     .url("http://myservice.xy/service")
 *     .body(data)
 *     .basicAuthorization("user", "pwd")
 *     .sendAndReceive();
 * </pre>
 * @author Kornél Simon
 *
 */
public interface HttpClient {

	/**
	 * Sets the URL of the HTTP request. The argument can be:
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
	 * @return this {@link HttpClient} instance
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public HttpClient url(String url);
	
	/**
	 * Sets the HTTP method of the request.
	 * @param method the HTTP method
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient method(HttpMethod method);
	
	/**
	 * Sets the MIME type of the HTTP request's content.
	 * @param mimeType a valid MIME type
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient mimeType(String mimeType);
	
	/**
	 * Sets the charset of the HTTP request's content.
	 * @param charset a java {@link Charset} instance
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient charset(Charset charset);
	
	/**
	 * Sets the charset of the HTTP request's content.
	 * @param charsetName the name of the charset
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient charset(String charsetName);
	
	/**
	 * Sets the body of the HTTP request.
	 * The parameter's value will be transformed to a {@link String}.
	 * @param object the object to be sent
	 * @return this {@link HttpClient} instance
	 */
	public <T> HttpClient body(T object);
	
	/**
	 * Adds a HTTP header to the request.
	 * @param name the name of the header
	 * @param value the value of the header
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient header(String name, String value);
	
	/**
	 * Sets the "Accept" header of the HTTP request.
	 * @param mimeType a valid MIME type
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient accept(String mimeType);
	
	/**
	 * Sets the "Authorization" header of the HTTP request.
	 * @param authorization the value of the Authorization header
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient authorization(String authorization);
	
	/**
	 * Sets the "Authorization" header of the HTTP request. It will be a "Basic" authorization value with the
	 * given username and password.
	 * @param username the authorization username
	 * @param password the authorization password
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient basicAuthorization(String username, String password);
	
	/**
	 * Sets the "Authorization" header of the HTTP request. It will be a "Bearer" authorization value with the
	 * given token.
	 * @param token the authorization token
	 * @return this {@link HttpClient} instance
	 */
	public HttpClient bearerAuthorization(String token);
	
	/**
	 * Executes the HTTP request: sends the request and receives the response.
	 * It automatically transforms the response to a java string.
	 * @return the response as java string
	 * @throws IOException if the communication with the REST server failed
	 * @throws IllegalArgumentException if the response of the REST server was invalid
	 */
	public HttpResponse<String> sendAndReceive() throws IOException, IllegalArgumentException;
}
