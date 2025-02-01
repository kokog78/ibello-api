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

import hu.ibello.requirements.model.Functionality;

/**
 * With the methods of this class we can load functionality trees.
 * The files will be loaded from the current ibello project.
 * <p>
 * The return values are in ibello's own format. The {@link Functionality} class describes a functionality element.
 * </p>
 * @author Kornél Simon
 */
public interface FunctionalityHandler {
	
	/**
	 * Loads a single functionality file.
	 * @param relativePath the path to the file, relative to the functionalities directory of the project
	 * @return the loaded functionality
	 * @throws FunctionalityException if there was an error during loading the file
	 */
	public Functionality loadFunctionality(String relativePath) throws FunctionalityException;
	
	/**
	 * Loads all functionality files and put them into a list.
	 * @return the list of the loaded functionalities
	 * @throws FunctionalityException if there was an error during loading a file
	 */
	public List<Functionality> loadFunctionalities() throws FunctionalityException;
	
	/**
	 * Loads selected functionality files and put them into a list.
	 * @param relativePath regular expression for the path to the file (which is relative to the functionalities directory of the project)
	 * @return the loaded functionality list
	 * @throws FunctionalityException if there was an error during loading the file
	 */
	public List<Functionality> loadFunctionalities(Pattern relativePath) throws FunctionalityException;
	
	/**
	 * Checks if a functionality file exists.
	 * @param relativePath the path to the file, relative to the functionalities directory of the project
	 * @return <code>true</code> if the given file exists, <code>false</code> otherwise
	 */
	public boolean existsFunctionalityFile(String relativePath);
	
	/**
	 * Calculate the test coverage of the functionalities defined in the specified file.
	 * The test coverage is calculated from the Cucumber feature files related to the functionalities.
	 * The relation is made by the functionality ID: if a scenario has a tag with the ID of a functionality, then the functionality is covered by the test.
	 * @param relativePath regular expression for the path to the file (which is relative to the functionalities directory of the project)
	 * @return the test coverage of the functionalities
	 * @throws FunctionalityException if there was an error during loading the file or calculating the coverage
	 */
	public FunctionalitiesCoverage calculateTestCoverage(Pattern relativePath) throws FunctionalityException;
	
	/**
	 * Calculate the example coverage of the functionalities defined in the specified file.
	 * The example coverage is calculated from the Cucumber example files related to the functionalities.
	 * The relation is made by the functionality ID: if a scenario has a tag with the ID of a functionality, then the functionality is covered by the example.
	 * @param relativePath regular expression for the path to the file (which is relative to the functionalities directory of the project)
	 * @return the example coverage of the functionalities
	 * @throws FunctionalityException if there was an error during loading the file or calculating the coverage
	 */
	public FunctionalitiesCoverage calculateExampleCoverage(Pattern relativePath) throws FunctionalityException;
	
}
