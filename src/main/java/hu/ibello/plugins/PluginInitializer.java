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
package hu.ibello.plugins;

import hu.ibello.core.Value;

/**
 * A simple interface, which is used in ibello plugins.
 * It can be used for logging: all log messages received by the {@link #info(String)} or {@link #error(String)}
 * methods will be added to the ibello logs automatically. Another use case of this interface is to get
 * configuration parameters with the {@link #getConfigurationValue(String)} method.
 * @author Kornél Simon
 *
 */
public interface PluginInitializer {

	/**
	 * Adds an information-level message to the ibello logs.
	 * @param message the log message
	 */
	public void info(String message);
	
	/**
	 * Adds an error-level message to the ibello logs.
	 * @param message the log message
	 */
	public void error(String message);
	
	/**
	 * Returns a configuration property as a {@link Value}. The returned value offers some public methods to
	 * transform the configuration property into different java types.
	 * This method always has a non-null result, even if the configuration value does not exist - in this case,
	 * the wrapped value will be <code>null</code>.
	 * @param name name of the configuration parameter
	 * @return value of the configuration parameter wrapped into a {@link Value} instance
	 */
	public Value getConfigurationValue(String name);
	
}
