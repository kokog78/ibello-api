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

/**
 * This interface is designed for complex task execution.
 * It has only a {@link #run()} method which does something - similarly to java {@link Runnable} interface.
 * The method is allowed to throw an exception. If it does, the task will be considered as failed.
 * @author Kornél Simon
 * @see TaskRunner
 */
@FunctionalInterface
public interface Task {

	/**
	 * Executes something, which can throw an exception.
	 * @throws Exception the method is allowed to throw an exception
	 */
	public void run() throws Exception;
}
