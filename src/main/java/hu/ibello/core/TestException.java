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

/**
 * A special exception class which can be used to mark test errors during test execution.
 * If a test step throws this exception, the test will fail and the automatic test failure classification
 * algorithm will mark it as test error.
 * 
 * @author Kornél Simon
 *
 */
public class TestException extends RuntimeException {

	private static final long serialVersionUID = 1012225247033207358L;

	/**
	 * Constructs a new test exception with the specified detail message and cause.
	 * 
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
	 */
	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new test exception with the specified detail message.
	 * 
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
	 */
	public TestException(String message) {
		super(message);
	}

	
}
