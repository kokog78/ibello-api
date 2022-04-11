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

/**
 * Contains the coverage of multiple requirements.
 * 
 * @author Kornél Simon
 * @see RequirementHandler#calculateTestCoverage(String)
 */
public class RequirementsCoverage {

	private int totalPercent;
	private List<RequirementCoverage> list;
	
	/**
	 * Returns the total coverage of the requirements, in percents.
	 * @return total coverage of the requirements
	 */
	public int getTotalPercent() {
		return totalPercent;
	}
	
	/**
	 * Sets the total coverage of the requirements, in percents.
	 * @param totalPercent total coverage of the requirements
	 */
	public void setTotalPercent(int totalPercent) {
		this.totalPercent = totalPercent;
	}
	
	/**
	 * Returns the list of coverage items calculated for the individual requirements.
	 * @return list of coverage items
	 */
	public List<RequirementCoverage> getList() {
		return list;
	}
	
	/**
	 * Sets the list of coverage items calculated for the individual requirements.
	 * @param list list of coverage items
	 */
	public void setList(List<RequirementCoverage> list) {
		this.list = list;
	}
}
