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
/**
 * The <em>ibello</em> Plugin Architecture.
 * <p>
 * Plugins are pluggable components of ibello. Everybody can write a plugin and include it in ibello tests.
 * For that, 2 things should be done.
 * </p>
 * <ol>
 * <li>
 * There should be a class which implements the {@link hu.ibello.plugins.Plugin} interface. It should have an
 * {@link hu.ibello.plugins.Plugin#initialize(PluginLogger)} method which will be called by the ibello system before
 * any test step. This class should be accessible on the classpath during the test execution.
 * </li>
 * <li>
 * The plugin class should be registered in ibello configuration, with the <code>ibello.plugin</code> parameter.
 * The value of this parameter should be the full class name of the plugin. Multiple plugin classes can be registered,
 * in this case the class names should be separated by comma.
 * </li>
 * </ol>
 * <p>
 * A plugin class which implements the {@link hu.ibello.plugins.IbelloReporter} interface is a reporter plugin. It will
 * receive information about test execution steps and may do something with it. (For example, generate a test report with a
 * custom format.)
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.plugins;