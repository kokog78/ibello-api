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
package hu.ibello.inject;

/**
 * <p>
 * This interface marks an initializable object. The {@link Initializable#initialize()} method will be called
 * automatically when the injector cerates a new instance for dependency injection.
 * </p>
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * 
 * @author Kornél Simon
 *
 */
public interface Initializable {

	/**
	 * Intializes the injected instance.
	 * This method is called automatically after every fields in this instance are initialized by the injector.
	 * If {@link #isInitialized()} returns <code>true</code>, then this method will NOT be called.
	 */
	void initialize();
	
	/**
	 * Was the instance already initialized? Should return <code>true</code> if it was.
	 * Otherwise an initialization with the {@link #initialize()} method will be performed automatically.
	 * @return <code>true</code> if instance was initialized
	 */
	default boolean isInitialized() {
		return false;
	}
}
