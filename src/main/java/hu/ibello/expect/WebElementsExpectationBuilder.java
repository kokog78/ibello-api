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
package hu.ibello.expect;

import hu.ibello.elements.WebElement;

/**
 * Interface for building an expectation about a collection of {@link WebElement} objects.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface WebElementsExpectationBuilder {

	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toNotHave()}.
	 * @return {@link WebElementsHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementsHaveExpectations toHave();
	
	/**
	 * Expectation builder method. It returns a new interface which has another methods
	 * to finish and execute the expectation. The result will the opposite of {@link #toHave()}.
	 * @return {@link WebElementsHaveExpectations} instance to finish and execute the expectation
	 */
	WebElementsHaveExpectations toNotHave();
	
}
