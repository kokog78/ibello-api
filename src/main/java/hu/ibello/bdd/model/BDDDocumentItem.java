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
 * Describes an item of a Cucumber document.
 * It can be {@link Scenario} or {@link Example}.
 * @param <STEP> the concrete class of the steps specified by this item
 * @author Kornél Simon
 */
public abstract class BDDDocumentItem<STEP extends Step> {

	private String keyword;
	
	private String name;
	
    private String description;
    
    private List<STEP> steps;
    
    /**
	 * Returns the keyword of the item.
	 * It depends on the language of the document.
	 * For english language, it is "Scenario".
	 * @return the keyword of the item
	 */
    public String getKeyword() {
		return keyword;
	}

    /**
	 * Sets the keyword of the item.
	 * It depends on the language of the document.
	 * For english language, it should be "Scenario".
	 * @param keyword the keyword of the item
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
	/**
	 * Returns the name of the item.
	 * @return the name of the item
	 */
    public String getName() {
		return name;
	}

    /**
	 * Sets the name of the item.
	 * @param name the name of the item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the item.
	 * @return the description of the item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the item.
	 * @param description the description of the item
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Are there any steps in the item?
	 * @return <code>true</code> if item has steps
	 */
	public boolean hasStep() {
		return steps != null && !steps.isEmpty();
	}
	
	/**
	 * Returns the steps of the item.
	 * If the item does not have steps, it returns an empty list.
	 * @return the steps of the item
	 */
	public List<STEP> getSteps() {
    	if (steps == null) {
    		steps = new ArrayList<>();
    	}
		return steps;
	}
	
	/**
	 * Sets the steps of the item.
	 * If the method is called with <code>null</code>, the result of the {@link #getSteps()} method will be an empty list.
	 * @param steps the steps of the item
	 */
	public void setSteps(List<STEP> steps) {
		this.steps = steps;
	}
	
	@Override
	public String toString() {
		String key = keyword == null || keyword.isEmpty() ? "Scenario" : keyword;
		return String.format("%s: %s", key, name);
	}
}
