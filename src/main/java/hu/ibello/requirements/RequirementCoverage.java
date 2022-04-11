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

import hu.ibello.requirements.model.RequirementKind;

/**
 * Contains the coverage of a single requirement.
 * 
 * @author Kornél Simon
 * @see RequirementHandler#calculateTestCoverage(String)
 */
public class RequirementCoverage {

	private RequirementKind kind;
	private String id;
	private int featureCount;
	private int scenarioCount;
	private int percentage;
	
	public RequirementCoverage() {
		this(null, null);
	}
	
	public RequirementCoverage(RequirementKind kind, String id) {
		setKind(kind);
		setId(id);
	}
	
	/**
	 * Returns the type of the requirement.
	 * @return type of the requirement
	 */
	public RequirementKind getKind() {
		return kind;
	}
	
	/**
	 * Sets the type of the requirement.
	 * @param kind type of the requirement
	 */
	public void setKind(RequirementKind kind) {
		this.kind = kind;
	}
	
	/**
	 * Identifier of the requirement.
	 * @return ID of requirement
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the identifier of the requirement.
	 * @param id ID of requirement
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Returns the number of feature files assigned to this requirement.
	 * @return number of feature files
	 */
	public int getFeatureCount() {
		return featureCount;
	}
	
	/**
	 * Sets the number of feature files assigned to this requirement.
	 * @param featureCount number of feature files
	 */
	public void setFeatureCount(int featureCount) {
		this.featureCount = featureCount;
	}
	
	/**
	 * Increases the number of feature files assigned to this requirement.
	 * @param plus this number will be added to the number of feature files
	 */
	public void incFeatureCount(int plus) {
		featureCount += plus;
	}
	
	/**
	 * Returns the number of scenarios assigned to this requirement.
	 * @return number of scenarios
	 */
	public int getScenarioCount() {
		return scenarioCount;
	}
	
	/**
	 * Sets the number of scenarios assigned to this requirement.
	 * @param scenarioCount number of scenarios
	 */
	public void setScenarioCount(int scenarioCount) {
		this.scenarioCount = scenarioCount;
	}
	
	/**
	 * Increases the number of scenarios assigned to this requirement.
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
			if (obj instanceof RequirementCoverage) {
				RequirementCoverage req = (RequirementCoverage)obj;
				return id.equals(req.id);
			}
		}
		return super.equals(obj);
	}
}
