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
package hu.ibello.actions;

import hu.ibello.core.TimeoutRelated;

/**
 * <p>
 * Executes a single task multiple times. The task itself is specified with the {@link Task} interface.
 * </p><p>
 * The methods of this interface are paying respect to the ibello timeout settings. With the {@link #withTimeout(Enum)}
 * or {@link #withTimeout(String)} methods we can specify a concrete timeout value. If we don't do that then the default timeout will be used.
 * </p>
 * <p>
 * With the {@link #untilSucceeds()} and {@link #untilFails()} methods we can start the repeat process - with a specific exit condition.
 * </p>
 * @author Kornél Simon
 *
 */
public interface TaskRepeater extends TimeoutRelated<TaskRepeater> {

	/**
	 * Repeats the task until it succeeds or the time ends.
	 * If the task fails during the timeout, it will be executed again.
	 */
	public void untilSucceeds();
	
	/**
	 * Repeats the task until it fails or the time ends.
	 * If the task succeeds during the timeout, it will be executed again.
	 */
	public void untilFails();
	
}
