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

/**
 * Contains the keyword type of a BDD test step.
 * @author Kornél Simon
 */
public enum StepKind {
	
	/**
	 * Unknown step type.
	 */
	UNKNOWN,
	
	/**
	 * A given.
	 */
	GIVEN,
	
	/**
	 * A when.
	 */
	WHEN,
	
	/**
	 * A then.
	 */
	THEN;

	/**
	 * Return the gherkin representation of the keyword, in english.
	 * @return gherkin representation of the keyword
	 */
	public String toGherkin() {
		if (this == UNKNOWN) {
			return "*";
		}
		String n = name();
		return n.substring(0, 1) + n.substring(1).toLowerCase();
	}
}
