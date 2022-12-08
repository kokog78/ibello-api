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

import hu.ibello.requirements.model.Requirements;

/**
 * With the methods of this class we can load requirements.
 * The files will be loaded from the current ibello project.
 * <p>
 * The return values are in ibello's own format. The {@link Requirements} class describes a requirement list.
 * </p>
 * @author Kornél Simon
 */
public interface RequirementHandler {

	/**
	 * Loads a single requirement file.
	 * @param relativePath the path to the file, relative to the requirements directory of the project
	 * @return the loaded requirement list
	 * @throws RequirementException if there was an error during loading the file
	 */
	Requirements loadRequirements(String relativePath) throws RequirementException;
	
	/**
	 * Loads all requirement files and put them into a list.
	 * @return the list of the loaded requirements
	 * @throws RequirementException if there was an error during loading a file
	 */
	List<Requirements> loadRequirements() throws RequirementException;
	
	/**
	 * Loads selected requirement files and put them into a list.
	 * @param relativePath regular expression for the path to the file (which is relative to the requirements directory of the project)
	 * @return the loaded requirement list
	 * @throws RequirementException if there was an error during loading the file
	 */
	List<Requirements> loadRequirements(Pattern relativePath) throws RequirementException;
	
	/**
	 * Checks if a requirement file exists.
	 * @param relativePath the path to the file, relative to the requirements directory of the project
	 * @return <code>true</code> if the given file exists, <code>false</code> otherwise
	 */
	boolean existsRequirementsFile(String relativePath);
	
	/**
	 * Calculate the test coverage of the requirements defined in the specified file.
	 * The test coverage is calculated from the Cucumber feature files related to the requirements.
	 * The relation is made by the requirement ID: if a scenario has a tag with the ID of a requirement, then the requirement is covered by the test.
	 * @param relativePath the path to the file, relative to the requirements directory of the project
	 * @return the test coverage of the requirements
	 * @throws RequirementException if there was an error during loading the file or calculating the coverage
	 */
	RequirementsCoverage calculateTestCoverage(String relativePath) throws RequirementException;
	
	/**
	 * Calculate the example coverage of the requirements defined in the specified file.
	 * The example coverage is calculated from the Cucumber example files related to the requirements.
	 * The relation is made by the requirement ID: if a scenario has a tag with the ID of a requirement, then the requirement is covered by the example.
	 * @param relativePath the path to the file, relative to the requirements directory of the project
	 * @return the example coverage of the requirements
	 * @throws RequirementException if there was an error during loading the file or calculating the coverage
	 */
	RequirementsCoverage calculateExampleCoverage(String relativePath) throws RequirementException;
	
	
}
