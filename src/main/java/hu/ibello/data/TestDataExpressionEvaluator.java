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

import hu.ibello.core.Value;

/**
 * An interface which can be used to evaluate a test data expression.
 * 
 * @author Kornél Simon
 *
 */
public interface TestDataExpressionEvaluator {

	/**
	 * With this method we can define a reference which can be used in the expression.
	 * @param name the name of the referenced
	 * @param value the value of the reference
	 * @return the {@link TestDataExpressionEvaluator} instance
	 */
	public TestDataExpressionEvaluator withReference(String name, Object value);
	
	/**
	 * Evaluates the expression with the previously defined references.
	 * @return the result of the evaluation
	 */
	public Value evaluate();
	
}
