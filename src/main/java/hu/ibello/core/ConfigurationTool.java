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
 * With this interface we can handle ibello configuration in the test code.
 * Important methods are:
 * <ul>
 * <li>{@link #getValue(String)}</li>
 * <li>{@link #setValue(String, Object)}</li>
 * </ul>
 */
public interface ConfigurationTool {

	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	Value getValue(String name);
	
	/**
	 * Sets a configuration property to the given value.
	 * The type of the value can be any type which is known by the {@link Value} interface.
	 * @param name name of the configuration parameter
	 * @param value value of the configuration parameter
	 */
	void setValue(String name, Object value);
}
