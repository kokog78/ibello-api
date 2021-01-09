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
 * Contains the different parameter kinds from a test step.
 * @author Kornél Simon
 */
public enum ParameterKind {

	/**
	 * String parameter type.
	 */
	STRING,
	
	/**
	 * Integer number parameter type.
	 */
	INTEGER,
	
	/**
	 * Decimal number parameter type.
	 */
	DECIMAL,
	
	/**
	 * Date/time parameter type.
	 */
	DATETIME,
	
	/**
	 * Boolean parameter type.
	 */
	BOOLEAN;
}
