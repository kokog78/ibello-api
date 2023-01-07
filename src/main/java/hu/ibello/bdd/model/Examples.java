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
package hu.ibello.bdd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a Cucumber example file from the ibello project.
 * @author Kornél Simon
 */
public class Examples extends BDDDocument {
	
	private List<String> tags;

	private List<Example> examples;

	/**
	 * Are there any tags in the example feature?
	 * @return <code>true</code> if the feature has tags
	 */
	public boolean hasTag() {
		return tags != null && !tags.isEmpty();
	}
	
	/**
	 * Returns the tags of the example feature.
	 * If the feature does not have tags, it returns an empty list.
	 * @return the tags of the example feature
	 */
	public List<String> getTags() {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		return tags;
	}
	
	/**
	 * Are there any example scenarios in the item?
	 * @return <code>true</code> if item has examples
	 */
	public boolean hasExample() {
		return examples != null && !examples.isEmpty();
	}
	
	/**
	 * Returns the example scenarios of the item.
	 * If the item does not have examples, it returns an empty list.
	 * @return the example scenarios of the item
	 */
	public List<Example> getExamples() {
		if (examples == null) {
			examples = new ArrayList<>();
		}
		return examples;
	}
}
