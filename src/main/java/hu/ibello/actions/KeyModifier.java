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

import hu.ibello.elements.WebElement;

/**
 * Interface which offers methods for accessing key modifiers.
 * A key modifier is one of the SHIFT, CONTROL or ALT. If this is
 * specified in an {@link WebElementActionBuilder#sendKeys(KeyModifier, CharSequence...)} method,
 * then the corresponding modifier key will be activated during the character
 * sequences are sent to the current {@link WebElement}.
 * <p>
 * It is possible to combine key modifiers, for example <code>SHIFT().CONTROL()</code>
 * will activate the SHIFT and CONTROL modifiers in the same time.
 * </p>
 * @author Kornél Simon
 *
 */
public interface KeyModifier {

	/**
	 * Activates the SHIFT key modifier.
	 * Can be combined with other key modifiers.
	 * @return the {@link KeyModifier} instance with activated SHIFT
	 */
	public KeyModifier SHIFT();
	
	/**
	 * Activates the CONTROL key modifier.
	 * Can be combined with other key modifiers.
	 * @return the {@link KeyModifier} instance with activated CONTROL
	 */
	public KeyModifier CONTROL();
	
	/**
	 * Activates the ALT key modifier.
	 * Can be combined with other key modifiers.
	 * @return the {@link KeyModifier} instance with activated ALT
	 */
	public KeyModifier ALT();
	
}
