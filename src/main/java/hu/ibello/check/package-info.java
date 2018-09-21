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
/**
 * This package contains interfaces what are capable to query information about {@link WebElement} instances.
 * <p>
 * During each query the program waits until a the element is accessible in the DOM.
 * If this does not happen until a timeout runs out, an exception will be thrown.
 * </p>
 * <p>
 * The timeout cannot be specified directly with a numeric value. All timeouts should
 * be defined in the <em>ibello</em> configuration, with <code>"ibello.timeout."</code> prefix. The unit
 * should be seconds. During the expectation, only the key of the timeout can be specified - it is the other half
 * of the configuration property, after the <code>"ibello.timeout."</code> prefix. With this rule we can
 * manage our timeout values in a more secure and general way, and can change them in different test execution
 * environment easily.
 * </p>
 * <p>
 * Implementations of the interfaces can be accesses through page objects, with the {@link PageObject#checkThat(WebElement)}
 * and {@link PageObject#get(WebElement)} methods.
 * </p>
 * <p>
 * Examples (in a page object):
 * </p>
 * <pre>
 * WebElement inputField = ...;
 * 
 * String value = get(inputField).value();
 * boolean displayed = checkThat(inputField).isDisplayed();
 * </pre>
 * @author Kornél Simon
 */
package hu.ibello.check;

import hu.ibello.elements.WebElement;
import hu.ibello.pages.PageObject;
