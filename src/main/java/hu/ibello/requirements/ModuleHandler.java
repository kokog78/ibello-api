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
package hu.ibello.requirements;

import java.util.List;
import java.util.regex.Pattern;

import hu.ibello.requirements.model.Module;

/**
 * With the methods of this class we can load module trees.
 * The files will be loaded from the current ibello project.
 * <p>
 * The return values are in ibello's own format. The {@link Module} class describes a module.
 * </p>
 * @author Kornél Simon
 */
public interface ModuleHandler {
	
	/**
	 * Loads a single module file.
	 * @param relativePath the path to the file, relative to the modules directory of the project
	 * @return the loaded module
	 * @throws ModuleException if there was an error during loading the file
	 */
	public Module loadModule(String relativePath) throws ModuleException;
	
	/**
	 * Loads all module files and put them into a list.
	 * @return the list of the loaded modules
	 * @throws ModuleException if there was an error during loading a file
	 */
	public List<Module> loadModules() throws ModuleException;
	
	/**
	 * Loads selected module files and put them into a list.
	 * @param relativePath regular expression for the path to the file (which is relative to the modules directory of the project)
	 * @return the loaded module list
	 * @throws ModuleException if there was an error during loading the file
	 */
	public List<Module> loadModules(Pattern relativePath) throws ModuleException;
	
	/**
	 * Checks if a module file exists.
	 * @param relativePath the path to the file, relative to the modules directory of the project
	 * @return <code>true</code> if the given file exists, <code>false</code> otherwise
	 */
	public boolean existsModuleFile(String relativePath);
	
}
