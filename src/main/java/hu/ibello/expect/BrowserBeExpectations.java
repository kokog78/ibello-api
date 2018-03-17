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
package hu.ibello.expect;

import hu.ibello.core.Browser;
import hu.ibello.pages.PageObject;

/**
 * Instance of this interface contains methods to create and execute expectations
 * about the browser.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface BrowserBeExpectations {

	/**
	 * Runs an expectation which successes when the current page is loaded.
	 * The expectation waits until all dynamic changes are made in the DOM.
	 */
	void loaded();
	
	/**
	 * Runs an expectation which successes when the tab (associated to the {@link Browser} object)
	 * is open.
	 * <p>
	 * If there is no tab associated to the {@link Browser} object, but there is a "free"
	 * tab which was opened by the application and not associated to any objects, then this
	 * expectation will capture that tab and binds it to the {@link Browser}.
	 * With this method we can capture tabs opened by the tested application and assign
	 * {@link Browser} (or: {@link PageObject}) instances to them.
	 * </p>
	 */
	void open();
	
}
