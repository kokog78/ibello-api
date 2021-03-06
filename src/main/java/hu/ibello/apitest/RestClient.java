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
package hu.ibello.apitest;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * This interface describes a REST client which sends a single request and receives a response.
 * <p>
 * A new instance of this interface can be configured with it's fluent methods.
 * These methods return the same {@link RestClient} instance so the configuration can be performed
 * with method call chain. At the end of this chain the {@link #sendAndReceive(Class)} method should be called
 * which executes the call and returns with the response.
 * </p>
 * <p>
 * The client instance has some default configuration.
 * </p>
 * <ul>
 * <li>Default HTTP method is GET. This can be changed with the {@link #method(HttpMethod)} method.</li>
 * <li>Default request charset is UTF-8. This can be changed with {@link #charset(Charset)} method.</li>
 * <li>Default request MIME type is "application/json". This can be changed with {@link #mimeType(String)} method.</li>
 * <li>By default the "Accept" header is added to the request with "application/json" value. This can be changed with the {@link #accept(String)} method.</li>
 * </ul>
 * <p>
 * Example REST API call:
 * </p>
 * <pre>
 * MyRequest data = ...;
 * HttpResponse&lt;MyResponse&gt; response = restClient()
 *     .url("http://myservice.xy/service")
 *     .body(data)
 *     .basicAuthorization("user", "pwd")
 *     .sendAndReceive(MyResponse.class);
 * </pre>
 * @author Kornél Simon
 *
 */
public interface RestClient {
	
	/**
	 * Sets the URL of the REST call. The argument can be:
	 * <ul>
	 * <li>a full absolute URL with protocol and hostname, eg. <code>http://localhost:8080/page</code>,</li>
	 * <li>a relative URL without host, eg. <code>/page</code> or <code>service?param=x</code>.</li>
	 * </ul>
	 * <p>
	 * If the URL is absolute, then it will be used by the REST client.
	 * </p>
	 * <p>
	 * If the URL is relative, then it will be appended to the value of the <code>ibello.url.base</code> configuration property,
	 * and the result URL will be used.
	 * </p>
	 * @param url relative or absolute URL
	 * @return this {@link RestClient} instance
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public RestClient url(String url);
	
	/**
	 * Sets the URL of the HTTP request.
	 * @param url the URL
	 * @return this {@link RestClient} instance
	 * @throws NullPointerException when the argument is <code>null</code>
	 */
	public RestClient url(URL url);

	/**
	 * Sets the HTTP method of the REST call.
	 * @param method the HTTP method
	 * @return this {@link RestClient} instance
	 */
	public RestClient method(HttpMethod method);
	
	/**
	 * Sets the MIME type of the REST call's content.
	 * @param mimeType a valid MIME type
	 * @return this {@link RestClient} instance
	 */
	public RestClient mimeType(String mimeType);
	
	/**
	 * Sets the charset of the REST call's content.
	 * @param charset a java {@link Charset} instance
	 * @return this {@link RestClient} instance
	 */
	public RestClient charset(Charset charset);
	
	/**
	 * Sets the charset of the REST call's content.
	 * @param charsetName the name of the charset
	 * @return this {@link RestClient} instance
	 */
	public RestClient charset(String charsetName);
	
	/**
	 * Sets the body of the REST call.
	 * If the given object is a string, then it will be considered as a JSON value.
	 * Otherwise it will be transformed to JSON.
	 * @param object the object (or JSON string) to be sent
	 * @param <T> the type of the body object
	 * @return this {@link RestClient} instance
	 */
	public <T> RestClient body(T object);
	
	/**
	 * Adds a HTTP header to the REST call.
	 * @param name the name of the header
	 * @param value the value of the header
	 * @return this {@link RestClient} instance
	 */
	public RestClient header(String name, String value);
	
	/**
	 * Sets the "Accept" header of the REST call.
	 * @param mimeType a valid MIME type
	 * @return this {@link RestClient} instance
	 */
	public RestClient accept(String mimeType);
	
	/**
	 * Sets the "Authorization" header of the REST call.
	 * @param authorization the value of the Authorization header
	 * @return this {@link RestClient} instance
	 */
	public RestClient authorization(String authorization);
	
	/**
	 * Sets the "Authorization" header of the REST call. It will be a "Basic" authorization value with the
	 * given username and password.
	 * @param username the authorization username
	 * @param password the authorization password
	 * @return this {@link RestClient} instance
	 */
	public RestClient basicAuthorization(String username, String password);
	
	/**
	 * Sets the "Authorization" header of the REST call. It will be a "Bearer" authorization value with the
	 * given token.
	 * @param token the authorization token
	 * @return this {@link RestClient} instance
	 */
	public RestClient bearerAuthorization(String token);
	
	/**
	 * Executes the REST call: sends the request and receives the response.
	 * It automatically transforms the response JSON to a java object.
	 * If the <code>responseType</code> argument is <code>String.class</code>,
	 * then the original JSON response will be returned in the result (without any transformation).
	 * @param responseType class of the response
	 * @param <T> type of the response
	 * @return the response as java object
	 * @throws IOException if the communication with the REST server failed
	 * @throws IllegalArgumentException if the response of the REST server was invalid
	 */
	public <T> HttpResponse<T> sendAndReceive(Class<T> responseType) throws IOException, IllegalArgumentException;
	
}
