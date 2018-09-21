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
 * Basic interface for timeout-related operations. It has methods to set the timeout.
 * @author Kornél Simon
 *
 * @param <R> type if the class which contains the methods
 */
public interface TimeoutRelated<R extends TimeoutRelated<?>> {

	/**
	 * Sets the timeout of the operation by it's string identifier.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout("LONG")</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current operation instance
	 */
	public R withTimeout(String key);

	/**
	 * Sets the timeout of the operation by it's enum identifier. The enum can be
	 * any valid java enum, it's not part of the <em>ibello</em> system. The given enum
	 * constant will be transformed to string.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout(Timeouts.LONG)</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current operation instance
	 */
	public R withTimeout(Enum<?> key);

}
