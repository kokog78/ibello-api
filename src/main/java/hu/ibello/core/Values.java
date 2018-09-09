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
package hu.ibello.core;

import java.util.Collection;

/**
 * An interface which collects multiple {@link Value} instances.
 * Each instance is identified by a name.
 * @author Kornél Simon
 *
 */
public interface Values {

	/**
	 * Returns a {@link Value} instance with the specified name.
	 * @param name the name of the instance
	 * @return the named instance
	 */
	public Value getValue(String name);
	
	/**
	 * Returns the number of {@link Value} instances stored by this object.
	 * @return number of stored instances
	 */
	public int size();
	
	/**
	 * Returns the names of {@link Value} instances stored by this object.
	 * @return names of stored instances (as a {@link Collection})
	 */
	public Collection<String> getNames();
	
}
