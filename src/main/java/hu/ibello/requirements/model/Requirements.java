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
 * Model interface for an object which contains requirements.
 * The requirements containing this object should have some common reason / theme.
 * 
 * @author Kornél Simon
 */
public interface Requirements {

	/**
	 * The path to the requirements file, relative to the ibello requirements directory. 
	 * @return the relative path to the file
	 */
	public String getRelativePath();
	
	/**
	 * Title of the requirement list. This describes the main purpose of the containing requirements.
	 * @return title of the requirement list
	 */
	public String getTitle();
	
	/**
	 * Return <code>true</code> if this object has non-empty a title.
	 * @return <code>true</code> if the oobject has a title
	 */
	public default boolean hasTitle() {
		String title = getTitle();
		return title != null && !title.trim().isEmpty();
	}
	
	/**
	 * Description of the requirement list. This describes the purpose of the containing requirements.
	 * @return description of the requirement list
	 */
	public String getDescription();
	
	/**
	 * The id of all requirements in this list should start with this text.
	 * @return prefix of the requirement ids
	 */
	public String getBaseId();
	
	/**
	 * If the requirements are related to a functionality of the system, the this method returns identifier of that functionality. 
	 * @return the related functionality identifier
	 */
	public String getFunctionalityId();
	
	/**
	 * List of links (eg. identifiers of related tasks from a task management system).
	 * These links are related to each requirement in this object.
	 * If there are no links then this methos returns an empty list.
	 * @return list of links
	 */
	public List<String> getLinks();
	
	/**
	 * List of individual requirements.
	 * @return requirement list
	 */
	public List<? extends Requirement> getRequirements();
	
	/**
	 * How many active requirements are in this requirement list?
	 * @return number of active requirements
	 */
	public default int getActiveRequirementCount() {
		int result = 0;
		if (getRequirements() != null) {
			for (Requirement req : getRequirements()) {
				if (!req.isArchived()) {
					result++;
				}
			}
		}
		return result;
	}
}
