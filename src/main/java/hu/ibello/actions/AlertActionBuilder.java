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

import hu.ibello.core.Alert;
import hu.ibello.pages.PageObject;

/**
 * Interface for building an <em>action</em> with an alert window.
 * It contains fluent methods to build and execute interactive operations on the alert window.
 * <p>
 * During an action, the <em>ibello</em> tries to interact with the alert window.
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
 * <p>
 * {@link AlertActionBuilder} instance can be obtained on page objects, with {@link PageObject#doWith(Alert)}
 * method.
 * </p>
 * <p>
 * Example:
 * </p>
 * <pre>
 * doWith(browser().alert()).accept();
 * </pre>
 * @author Kornél Simon
 *
 */
public interface AlertActionBuilder extends ActionBuilder<AlertActionBuilder> {

	/**
	 * Closes the alert window without accepting it's content.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void dismiss();
	
	/**
	 * Closes the alert window with accepting it's content.
	 * <p>
	 * During the timeout, this method tries to execute the action until it is successfully
	 * performed. If the time runs out and the action was not performed, an exception will
	 * be thrown, the error will be logged and the current test will be interrupted and
	 * marked as "error".
	 * </p>
	 */
	void accept();
	
}
