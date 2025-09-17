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

import hu.ibello.core.Name;

/**
 * <p>
 * Task runner is something which runs named tasks. It is possible to run a task by it's name from command line.
 * The task runner receives the name in the {@link #runTask(String)} method and tries to perform the task.
 * If it is possible - because the runner recognizes the name - then the method returns <code>true</code>.
 * </p>
 * <p>
 * A task runner may have a readable name. For that, multiple options are available.
 * </p>
 * <ol>
 * <li>The task runner can have a descriptor in JSON format. The file should be available on the classpath,
 * in the same package where the class is. The descriptor can have a "title" property which contains the readable
 * name.</li>
 * <li>If there is no descriptor file or it does not contain a non-empty title then it is possible to set
 * the readable name with the {@link Name} annotation which should be placed on the class.</li>
 * </ol>
 * <p>
 * It is possible to interact with the ibello infrastructure in the executed task. For this, the parameter of the
 * {@link #initialize(PluginInitializer)} method should be used. The {@link PluginInitializer#getConfigurationValue(String)}
 * is useful to access configuration parameters and command line options. With {@link PluginInitializer#testData()} method
 * an ibello test data can be loaded.
 * </p>
 * @author Kornél Simon
 *
 */
public interface IbelloTaskRunner extends Plugin {

	/**
	 * <p>
	 * Performs a named task. If the task can be processed by this instance then the result value should be <code>true</code>
	 * - otherwise <code>false</code>.
	 * </p>
	 * <p>
	 * Multiple task runners can be registered in an ibello execution. If a named task should be performed, then the ibello calls
	 * the {@link #runTask(String)} method of these runners. If a runner returns <code>true</code>, then the task will be considered as
	 * performed and the remaining runners will not be called.
	 * </p>
	 * @param name the name of the task
	 * @return was the task performed?
	 * @throws PluginException in case of any error
	 */
	public boolean runTask(String name) throws PluginException;
	
}
