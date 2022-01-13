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

/**
 * Implementation of interface represents a HTTP response with it's attributes.
 * 
 * @author Kornél Simon
 *
 * @param <T> type of the response object
 */
public interface HttpResponse<T> {

	/**
	 * Returns the response object.
	 * @return the response object
	 */
	public T getObject();
	
	/**
	 * Returns the response HTTP code.
	 * @return the response code.
	 */
	public int getCode();
	
	/**
	 * Returns the HTTP header from the response, or <code>null</code> if it does not exist.
	 * If the header contains multiple values then the first one will be returned.
	 * @param name the name of the header we are looking for
	 * @return the value of the header or <code>null</code>
	 */
	public String getHeader(String name);
}
