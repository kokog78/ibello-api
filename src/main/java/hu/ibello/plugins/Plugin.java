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

/**
 * Base interface of ibello plugins. If a class implements this interface and it is registered as ibello plugin, then
 * the ibello system will call it's {@link #initialize(PluginLogger)} method before any tests.
 * @author Kornél Simon
 * @see hu.ibello.plugins
 */
public interface Plugin {

	/**
	 * Initializes the plugin. Receives a {@link PluginLogger} instance which has some methods to generate log lines.
	 * These lines will be added to the ibello logs.
	 * @param logger az instance which can be used to add lines to the ibello logs
	 * @throws PluginException if a fatal error occurs, this exception will prevent any tests to be executed
	 */
	public void initialize(PluginLogger logger) throws PluginException;
	
}
