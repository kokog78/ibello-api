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
 * Contains the coverage of multiple elements.
 * 
 * @param <E> type of coverage elements
 */
class ElementsCoverage<E extends ElementCoverage> {

	private int totalPercent;
	
	private List<E> list;
	
	/**
	 * Returns the total coverage of the elements, in percents.
	 * @return total coverage of the elements
	 */
	public int getTotalPercent() {
		return totalPercent;
	}
	
	/**
	 * Sets the total coverage of the elements, in percents.
	 * @param totalPercent total coverage of the elements
	 */
	public void setTotalPercent(int totalPercent) {
		this.totalPercent = totalPercent;
	}
	
	/**
	 * Returns the list of coverage elements.
	 * @return list of coverage elements
	 */
	public List<E> getList() {
		return list;
	}
	
	/**
	 * Sets the list of coverage elements.
	 * @param list list of coverage elements
	 */
	public void setList(List<E> list) {
		this.list = list;
	}
}
