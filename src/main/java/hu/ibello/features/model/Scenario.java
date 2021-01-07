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
package hu.ibello.features.model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

	private String keyword;
	
	private String name;
	
    private String description;
    
    private List<String> tags;
    
    private List<Step> steps;
    
    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean hasTag() {
		return tags != null && !tags.isEmpty();
	}
	
	public List<String> getTags() {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		return tags;
	}
	
	public boolean hasStep() {
		return steps != null && !steps.isEmpty();
	}
	
	public List<Step> getSteps() {
    	if (steps == null) {
    		steps = new ArrayList<>();
    	}
		return steps;
	}
}
