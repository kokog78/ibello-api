package hu.ibello.actions;

import hu.ibello.elements.WebElement;

/**
 * Interface which offers methods for accessing key modifiers.
 * A key modifier is one of the SHIFT, CONTROL or ALT. If this is
 * specified in an {@link ActionBuilder#sendKeys(KeyModifier, CharSequence...)} method,
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
