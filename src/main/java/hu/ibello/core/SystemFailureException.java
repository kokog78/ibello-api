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
 * A special exception which marks a system failure.
 * If a test step throws this exception then the test will fail and the automatic test failure classification
 * algorithm will mark it as system error.
 * 
 * @author Kornél Simon
 *
 */
public class SystemFailureException extends AssertionError {

	private static final long serialVersionUID = 1667978367845610220L;

	private final boolean soft;
	
	/**
	 * Constructs a new system failure exception with the specified
     * detail message and cause. With this constructor it is possible to mark the failure as a soft one.
     * Soft failures does not stops test execution.
     * 
	 * @param message the detail message, may be {@code null}
	 * @param soft is the system failure a soft one?
	 * @param cause the cause, may be {@code null}
	 */
	public SystemFailureException(String message, boolean soft, Throwable cause) {
		super(message, cause);
		this.soft = soft;
	}

	/**
	 * Constructs a new system failure exception with the specified
     * detail message. With this constructor it is possible to mark the failure as a soft one.
     * Soft failures does not stops test execution.
     * 
	 * @param message the detail message, may be {@code null}
	 * @param soft is the system failure a soft one?
	 */
	public SystemFailureException(String message, boolean soft) {
		this(message, soft, null);
	}
	
	/**
	 * Constructs a new system failure exception with the specified
     * detail message and cause. The failure will be a hard one -
     * which means it will stop the test execution as well.
     * 
	 * @param message the detail message, may be {@code null}
	 * @param cause the cause, may be {@code null}
	 */
	public SystemFailureException(String message, Throwable cause) {
		this(message, false, cause);
	}

	/**
	 * Constructs a new system failure exception with the specified
     * detail message.The failure will be a hard one -
     * which means it will stop the test execution as well.
     * 
	 * @param message the detail message, may be {@code null}
	 */
	public SystemFailureException(String message) {
		this(message, false, null);
	}
	
	/**
	 * Is this system failure a soft one?
	 * Soft failures does not stops test execution.
	 * @return <code>true</code> if the system failure is a soft one
	 */
	public boolean isSoft() {
		return soft;
	}
	
}
