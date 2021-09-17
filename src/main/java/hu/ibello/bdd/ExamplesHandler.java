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
package hu.ibello.bdd;

import java.util.List;
import java.util.regex.Pattern;

import hu.ibello.bdd.model.Examples;

/**
 * With the methods of this class we can load and save Cucumber example files.
 * The files will be loaded from the current ibello project.
 * <p>
 * The return values are in ibello's own format. The {@link Examples} class describes a Cucumber example
 * with scenarios and steps.
 * </p>
 * @author Kornél Simon
 */
public interface ExamplesHandler {

	/**
	 * Loads a single Cucumber example file.
	 * @param relativePath the path to the file, relative to the "examples" directory of the project
	 * @return the loaded Cucumber example data
	 * @throws BDDException if there was an error during loading the file
	 */
	Examples loadExamples(String relativePath) throws BDDException;
	
	/**
	 * Loads multiple Cucumber example files.
	 * The method uses a regular expression to find the files.
	 * If a file's relative path matches the regular expression, it will be loaded.
	 * @param relativePath regular expression for the path to the file (which is relative to the "examples" directory of the project)
	 * @return the list of the loaded Cucumber examples
	 * @throws BDDException if there was an error during loading a file
	 */
	List<Examples> loadExamples(Pattern relativePath) throws BDDException;
	
	/**
	 * Checks if an examples file exists.
	 * @param relativePath the path to the file, relative to the "examples" directory of the project
	 * @return <code>true</code> if the given file exists, <code>false</code> otherwise
	 */
	boolean existsExamplesFile(String relativePath);
	
	/**
	 * Saves the given examples into a gherkin feature file.
	 * It overwrites the existing file.
	 * The file path should be specified with the {@link Examples#setFullPath(String)} or {@link Examples#setRelativePath(String)}
	 * method.
	 * @param examples the examples we want to save
	 * @throws BDDException if there was an error during saving the file or the file path is not specified
	 */
	void saveExamples(Examples examples) throws BDDException;
}
