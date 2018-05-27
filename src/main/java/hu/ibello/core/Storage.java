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
 * Provides an API for a storage structure, like local storage or session storage in the browser.
 * An implementation of this interface can be obtained with {@link Browser#localStorage()} and
 * {@link Browser#sessionStorage()} methods.
 * @author Kornél Simon
 *
 */
public interface Storage {

	/**
	 * Clear the entire storage in the browser, removing all elements from it.
	 */
	void clear();
	
	/**
	 * Remove a single item from the storage.
	 * @param key the name of the item we want to remove
	 */
	void removeItem(String key);
	
	/**
	 * Sets the value of an item.
	 * @param key the name of the item
	 * @param value the new value of the item
	 */
	void setItem(String key, String value);
	
	/**
	 * Returns the value of the named item from the storage, or <code>null</code> if it does not exist.
	 * @param key the name of the item
	 * @return the value of the item, or <code>null</code>
	 */
	String getItem(String key);
	
}
