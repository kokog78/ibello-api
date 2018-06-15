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
 * Interface for building an <em>action</em> with a target.
 * <p>
 * During an action, the <em>ibello</em> tries to interact with the action's target.
 * If the interaction fails, it will try it again and again, until it successes or the time
 * runs out. By default, an action uses the <em>ibello</em>
 * default timeout, but it can be changed with the {@link #withTimeout(Enum)} or
 * {@link #withTimeout(String)} method.
 * </p>
 * <p>
 * In some cases it is useful to wait a few seconds after an interaction occurred on the page.
 * The action may change the page structure dynamically, or can navigate to another page.
 * With the {@link #withPageRefreshWait()} method, this wait can be performed right after
 * the action finishes.
 * </p>
 * <p>
 * During test execution, every action is logged with it's additional information. If an action
 * fails, then the test will be marked with "error" flag, and not with "failed". This is a
 * difference between actions and expectations - see {@link hu.ibello.expect}.
 * </p>
 * @author Kornél Simon
 *
 */
interface ActionBuilder<B extends ActionBuilder<?>> {

	/**
	 * Sets the timeout of the action by it's string identifier.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout("LONG")</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current action builder instance
	 */
	public B withTimeout(String key);

	/**
	 * Sets the timeout of the action by it's enum identifier. The enum can be
	 * any valid java enum, it's not part of the <em>ibello</em> system. The given enum
	 * constant will be transformed to string.
	 * The value of the timeout should be specified in the <em>ibello</em>
	 * configuration, with <code>"ibello.timeout."</code> prefix. For example,
	 * <code>withTimeout(Timeouts.LONG)</code> will set the timeout specified in the
	 * <code>ibello.timeout.LONG</code> configuration property. The timeout is
	 * specified in seconds.
	 * @param key identifier of the desired timeout
	 * @return the current action builder instance
	 */
	public B withTimeout(Enum<?> key);
	
	/**
	 * If this method is called on the action builder instance, and then an action
	 * is executed successfully, the program will wait until the page is refreshed and
	 * all dynamic changes in the DOM performed.
	 * @return the current action builder instance
	 */
	public B withPageRefreshWait();
}
