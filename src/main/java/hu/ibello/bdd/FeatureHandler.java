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

import hu.ibello.bdd.model.Feature;

/**
 * With the method of this class we can load Cucumber feature files.
 * The files will be loaded from the current ibello project.
 * <p>
 * The return values are in ibello's own format. The {@link Feature} class describes a Cucumber feature
 * with scenarios and steps. Child steps are also loaded from step definition files.
 * </p>
 * @author Kornél Simon
 */
public interface FeatureHandler {

	/**
	 * Loads a single Cucumber feature file.
	 * @param relativePath the path to the file, relative to the "features" directory of the project
	 * @return the loaded Cucumber feature data
	 */
	Feature loadFeature(String relativePath);
	
	/**
	 * Loads multiple Cucumber features files.
	 * The method uses a regular expression to find the files.
	 * If a file's relative path matches the regular expression, it will be loaded.
	 * @param relativePath regular expression for the path to the file (which is relative to the "features" directory of the project)
	 * @return the list of the loaded Cucumber features
	 */
	List<Feature> loadFeatures(Pattern relativePath);
	
	/**
	 * Loads multiple Cucumber features files.
	 * This method loads features by tags.
	 * If a feature has one of the specified tags, it will be loaded.
	 * @param tags the tags of the loaded features
	 * @return the list of the loaded Cucumber features
	 */
	List<Feature> loadFeaturesWithTags(String ... tags);
}
