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
package hu.ibello.output;

/**
 * This interface contains methods which are useful if we want to add custom actions and failure messages
 * to the test report.
 * @author Kornél Simon
 */
public interface OutputHandler {

	/**
	 * Adds a custom message to the test report. With the message, an action will be generated with the given name.
	 * The action will be successful.
	 * @param name the name (text) of the action
	 */
	void recordCustomAction(String name);
	
	/**
	 * Adds a custom expectation to the test report. With the message, an expectation will be generated with the given name.
	 * The expectation will be successful.
	 * @param name the name (text) of the expectation
	 */
	void recordCustomExpectation(String name);
}
