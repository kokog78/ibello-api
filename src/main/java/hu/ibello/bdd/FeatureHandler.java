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

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import hu.ibello.bdd.model.Feature;

public interface FeatureHandler {

	Feature loadFeature(String relativePath) throws IOException;
	
	List<Feature> loadFeatures(Pattern relativePath) throws IOException;
	
	List<Feature> loadFeaturesWithTags(String ... tags) throws IOException;
}
