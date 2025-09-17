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

/**
 * Base class for coverage calculation.
 * Contains coverage information for one coverage element.
 * Elements are collected into tree structure where each element has a level -
 * see {@link #getLevel()}. Each of them has a unique identifier - see {@link #getId()}.
 */
class ElementCoverage {
	
	private String id;
	
	private int level;
	
	private int featureCount;
	
	private int scenarioCount;
	
	private int percentage;
	
	/**
	 * Identifier of the element.
	 * @return ID of element
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the identifier of the element.
	 * @param id ID of element
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * The level/depth of the element.
	 * The value for root elements is 1, for child elements of the root ones is 2, etc.
	 * @return level/depth of the element
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the level/depth of the element.
	 * The value for root elements is 1, for child elements of the root ones is 2, etc.
	 * @param level/depth of the element
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Returns the number of feature files assigned to this element.
	 * @return number of feature files
	 */
	public int getFeatureCount() {
		return featureCount;
	}
	
	/**
	 * Sets the number of feature files assigned to this element.
	 * @param featureCount number of feature files
	 */
	public void setFeatureCount(int featureCount) {
		this.featureCount = featureCount;
	}
	
	/**
	 * Increases the number of feature files assigned to this element.
	 * @param plus this number will be added to the number of feature files
	 */
	public void incFeatureCount(int plus) {
		featureCount += plus;
	}
	
	/**
	 * Returns the number of scenarios assigned to this element.
	 * @return number of scenarios
	 */
	public int getScenarioCount() {
		return scenarioCount;
	}
	
	/**
	 * Sets the number of scenarios assigned to this element.
	 * @param scenarioCount number of scenarios
	 */
	public void setScenarioCount(int scenarioCount) {
		this.scenarioCount = scenarioCount;
	}
	
	/**
	 * Increases the number of scenarios assigned to this element.
	 * @param plus this number will be added to the number of scenarios
	 */
	public void incScenarioCount(int plus) {
		scenarioCount += plus;
	}
	
	/**
	 * Returns the coverage value, in percents.
	 * @return the coverage value
	 */
	public int getPercentage() {
		return percentage;
	}
	
	/**
	 * Sets the coverage value, in percents.
	 * @param percentage the coverage value
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public int hashCode() {
		if (id == null) {
			return 0;
		}
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (id != null) {
			if (obj instanceof ElementCoverage) {
				ElementCoverage req = (ElementCoverage)obj;
				return id.equals(req.id);
			}
		}
		return super.equals(obj);
	}
}
