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
 * A simple logger interface, which is used in ibello plugins.
 * All log messages received by the {@link #info(String)} or {@link #error(String)}
 * methods will be added to the ibello logs automatically.
 * @author Kornél Simon
 *
 */
public interface PluginLogger {

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
	
}
