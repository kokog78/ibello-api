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
package hu.ibello.data;

/**
 * For editable model fields - marked with {@link Editable} annotation - we can specify the type of the editor,
 * where the value of the can be selected.
 * @author Kornél Simon
 *
 */
public enum Editor {

	/**
	 * The edited value is the identifier of a functionality.
	 */
	FUNCTIONALITY,
	
	/**
	 * The edited value is the identified of a requirement.
	 */
	REQUIREMENT,
	
	/**
	 * The edited value is the identified of a risk.
	 */
	RISK,
	
	/**
	 * The edited value is the identified of a test suite.
	 */
	TEST_SUITE,
	
	/**
	 * The edited value is the full name of a model class.
	 */
	MODEL_CLASS,
	
	/**
	 * The edited value is the directory of a test result.
	 */
	TEST_RESULT;
}
