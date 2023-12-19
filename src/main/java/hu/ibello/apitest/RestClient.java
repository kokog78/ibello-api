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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
	 * Sets the first part of the URL used in the HTTP request.
	 * This method replaces the usage of <code>ibello.url.base</code> configuration property in the URL.
	 * It means that if the {@link #url(String)} method is called with relative value, then
	 * the final URL will be constructed from the base URL and the relative one.
	 * @param baseUrl the base URL of the request
	 * @return this {@link RestClient} instance
	 */
	public RestClient urlBase(String baseUrl);

	/**
	 * Sets the HTTP method of the REST call.
	 * @param method the HTTP method
	 * @return this {@link RestClient} instance
	 */
	public RestClient method(HttpMethod method);

	/**
	 * Sets the HTTP method of the request to GET.
	 * @return this {@link RestClient} instance
	 */
	public RestClient get();

	/**
	 * Sets the HTTP method of the request to POST.
	 * @return this {@link RestClient} instance
	 */
	public RestClient post();

	/**
	 * Sets the HTTP method of the request to PUT.
	 * @return this {@link RestClient} instance
	 */
	public RestClient put();

	/**
	 * Sets the HTTP method of the request to DELETE.
	 * @return this {@link RestClient} instance
	 */
	public RestClient delete();

	/**
	 * Sets the HTTP method of the request to PATCH.
	 * @return this {@link RestClient} instance
	 */
	public RestClient patch();
	
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
	 * The parameter can be:
	 * <ul>
	 * <li>a {@link File} instance, in this case the content of the file will be the request body,</li>
	 * <li>an {@link InputStream} instance, in this case the content of the stream will be the request body,</li>
	 * <li>a byte array, in this case the bytes will be the request body,</li>
	 * <li>a {@link String} - it will be considered as a JSON value,</li>
	 * <li>a {@link Map} - it will be transformed to JSON,</li>
	 * <li>or anything else - which will be transformed to JSON.</li>
	 * </ul>
	 * @param object the object (or JSON string) to be sent
	 * @param <T> the type of the body object
	 * @return this {@link RestClient} instance
	 */
	public <T> RestClient body(T object);
	
	/**
	 * Adds a multipart body to the HTTP request. If specified, the MIME type of request will be multipart/form-data,
	 * and the specified body will be appended to the parts. The body can be:
	 * <ul>
	 * <li>a {@link File} instance,</li>
	 * <li>an {@link InputStream} instance,</li>
	 * <li>a byte array,</li>
	 * <li>a {@link String},</li>
	 * <li>a {@link Map} - it will be transformed to JSON,</li>
	 * <li>or anything else - which will be transformed to JSON.</li>
	 * </ul>
	 * @param mimeType a valid MIME type
	 * @param name name of the multipart body
	 * @param body the object to be sent
	 * @param <T> the type of the body object
	 * @return this {@link RestClient} instance
	 */
	public <T> RestClient multipart(String mimeType, String name, T body);
	
	/**
	 * Adds a multipart body to the HTTP request. If specified, the MIME type of request will be multipart/form-data,
	 * and the specified body will be appended to the parts. The body can be:
	 * <ul>
	 * <li>a {@link File} instance,</li>
	 * <li>an {@link InputStream} instance,</li>
	 * <li>a byte array,</li>
	 * <li>a {@link String},</li>
	 * <li>a {@link Map} - it will be transformed to JSON,</li>
	 * <li>or anything else - which will be transformed to JSON.</li>
	 * </ul>
	 * The MIME type of the multipart will not be specified.
	 * @param name name of the multipart body
	 * @param body the object to be sent
	 * @param <T> the type of the body object
	 * @return this {@link RestClient} instance
	 */
	public <T> RestClient multipart(String name, T body);
	
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
	 * Turns off SSL certificate validation.
	 * This is useful if the SSL certificate of the host is not valid from any reason but we need to communicate with it with HTTPS protocol.
	 * @return this {@link RestClient} instance
	 */
	public RestClient withoutCertificateValidation();
	
	/**
	 * Specifies a body consumer instance which will receive the body content before the request is sent.
	 * With this method we can inject a body-related logic into the behavior of the client.
	 * Special cases:
	 * <ul>
	 * <li>If the request body is empty (for example, for a GET or DELETE request), then the consumer will receive a <code>null</code> value.</li>
	 * <li>If the request is a multipart request, then the consumer will be called for every body parts.</li>
	 * <li>If the content of the body is a file or an input stream, then the byte content of that file or stream will be input parameter of the consumer.</li>
	 * <li>If the content of the body is a string, then the charset which is used to transform the string to bytes will be read out from the content type.</li>
	 * <li>If the charset is not specified in the content type, the it will be UTF-8.</li>
	 * </ul>
	 * @param consumer body consumer instance
	 * @return this {@link RestClient} instance
	 */
	public RestClient bodyConsumer(Consumer<byte[]> consumer);

	/**
	 * Executes the REST call: sends the request and receives the response.
	 * It automatically transforms the response JSON to a java object.
	 * <ul>
	 * <li>If the <code>responseType</code> argument is <code>String.class</code>,
	 * then the original JSON response will be returned in the result (without any transformation).</li>
	 * <li>If the <code>responseType</code> argument is <code>byte[].class</code>,
	 * then the original response will be returned as byte array (without any transformation).</li> 
	 * <li>If the <code>responseType</code> argument is <code>InputStream.class</code>,
	 * then the original stream response will be returned.</li> 
	 * </ul>
	 * @param responseType class of the response
	 * @param <T> type of the response
	 * @return the response as java object
	 * @throws IOException if the communication with the REST server failed
	 * @throws IllegalArgumentException if the response of the REST server was invalid
	 */
	public <T> HttpResponse<T> sendAndReceive(Class<T> responseType) throws IOException, IllegalArgumentException;
	
	/**
	 * Executes the REST call: sends the request and receives the response.
	 * It automatically transforms the response JSON to a java list.
	 * @param itemType class of the items in the response list
	 * @param <T> type of the items in the response list
	 * @return the response as java list
	 * @throws IOException if the communication with the REST server failed
	 * @throws IllegalArgumentException if the response of the REST server was invalid
	 */
	public <T> HttpResponse<List<T>> sendAndReceiveList(Class<T> itemType) throws IOException, IllegalArgumentException;
}
