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
package hu.ibello.requirements.model;

/**
 * Type of a requirement.
 * 
 * @author Kornél Simon
 */
public enum RequirementKind {

	/**
	 * Business level requirements are higher ones. In many cases they are containing lower-level (technical) requirements.
	 */
	BUSINESS,
	
	/**
	 * Technical requirement is the lowest level requirement. It should not contain any child requirements.
	 */
	TECHNICAL;
}
