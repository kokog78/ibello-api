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
 * Describes a scenario from a Cucumber feature file.
 * @author Kornél Simon
 */
public class Scenario extends BDDDocumentItem<ParentStep> {

    private List<String> tags;
    
    /**
	 * Are there any tags in the scenario?
	 * @return <code>true</code> if the scenario has tags
	 */
	public boolean hasTag() {
		return tags != null && !tags.isEmpty();
	}
	
	/**
	 * Returns the tags of the scenario.
	 * If the scenario does not have tags, it returns an empty list.
	 * @return the tags of the scenario
	 */
	public List<String> getTags() {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		return tags;
	}
	
    /**
     * Returns the list of the descendant steps, collected into a flat list.
     * If a child step has children, then instead of the original child it's children will be included in the result.
     * This method avoids infinite loops caused by recursive step references.
     * @return all the descendant steps in a flat list
     */
	public List<Step> getFlattenSteps() {
		return getFlattenSteps(false);
	}
	
	/**
	 * Returns the list of the descendant steps, collected into a flat list.
     * If a child step has children, then it can be included in the result,
     * if the <code>includeParentSteps</code> parameter is <code>true</code>.
     * Otherwise, only its children will be included.
     * This method avoids infinite loops caused by recursive step references.
	 * @param includeParentSteps should the parent steps be included in the result?
	 * @return all the descendant steps in a flat list
	 */
	public List<Step> getFlattenSteps(boolean includeParentSteps) {
		return StepUtils.getFlattenSteps(getSteps(), includeParentSteps);
	}
}
