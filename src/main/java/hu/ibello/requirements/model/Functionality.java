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
 * Represents a functionality element (module) in the tested system.
 * 
 * @author Kornél Simon
 *
 */
public interface Functionality extends Comparable<Functionality> {

	/**
	 * The identifier of the functionality.
	 * @return the functionality identifier
	 */
	public String getId();
	
	/**
	 * The short name/title of the functionality.
	 * @return functionality title
	 */
	public String getTitle();
	
	/**
	 * Description of the functionality.
	 * @return functionality description
	 */
	public String getDescription();
	
	/**
	 * If the functionality can be splitted into multiple ones, then this list returns the sub-functions.
	 * @return list of sub-functions
	 */
	public List<Functionality> getChildren();
	
	@Override
	public default int compareTo(Functionality o) {
		return getId().compareTo(o.getId());
	}
	
}
