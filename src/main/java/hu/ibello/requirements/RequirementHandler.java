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
 * With the methods of this class we can load and save Cucumber feature files.
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
	
}