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

import java.util.List;

/**
 * Model interface for a requirement.
 * 
 * @author Kornél Simon
 */
public interface Requirement {

	/**
	 * Type of the requirement.
	 * @return requirement kind
	 */
	public RequirementKind getKind();
	
	/**
	 * Generated identifier of the requirement.
	 * Can be used as scenario tag.
	 * @return requirement ID
	 */
	public String getId();
	
	/**
	 * Human readable title / name of the requirement.
	 * @return requirement title
	 */
	public String getTitle();
	
	/**
	 * Detailed description of the requirement.
	 * @return requirement description
	 */
	public String getDescription();
	
	/**
	 * The level/depth of the requirement.
	 * The value for root requirements is 1, for child requirements of the root ones is 2, etc.
	 * @return level/depth of the requirement
	 */
	public int getLevel();
	
	/**
	 * List of links (eg. identifiers of related tasks from a task management system).
	 * These links are related to this requirement and to each child requirement.
	 * If there are no links then this method returns an empty list.
	 * @return list of links
	 */
	public List<String> getLinks();
	
	/**
	 * List of child requirements.
	 * If this requirement may have child requirements, then the returned value is a {@link List}.
	 * Otherwise, <code>null</code> is returned.
	 * @return the list of child requirements or <code>null</code>
	 */
	public List<? extends Requirement> getRequirements();
	
	/**
	 * Is the requirement archived?
	 * Archived requirements should not be used for any purpose.
	 * @return <code>true</code> if requirement is archived
	 */
	public boolean isArchived();
	
	/**
	 * Is the requirement active?
	 * A requirement is active if it is not archived.
	 * @return <code>true</code> if requirement is active
	 */
	public default boolean isActive() {
		return !isArchived();
	}
	
	/**
	 * It is possible to assign a color to a requirement.
	 * This method returns the HTML code of that color.
	 * It may return <code>null</code> or empty string.
	 * @return the color of the requirement
	 */
	public String getColor();
}
