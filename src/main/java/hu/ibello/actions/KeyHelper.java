package hu.ibello.actions;

import hu.ibello.inject.Injectable;
import hu.ibello.inject.Scope;
import hu.ibello.pages.PageObject;

/**
 * This abstract class offers public members and methods for special keys and
 * key modifiers. It inplements the {@link KeyModifier} interface so
 * {@link KeyModifier#SHIFT()}, {@link KeyModifier#CONTROL()} and {@link KeyModifier#ALT()}
 * are available in it. Enum constants from {@link Key} are available as public fields.
 * An instance of this abstract class is available with {@link PageObject#keys()}.
 * @author Kornél Simon
 * @see KeyModifier
 * @see Key
 */
@Injectable(Scope.SINGLETON)
public abstract class KeyHelper implements KeyModifier {

	public final Key CANCEL          = Key.CANCEL;
	public final Key HELP            = Key.HELP;
	public final Key BACK_SPACE      = Key.BACK_SPACE;
	public final Key TAB             = Key.TAB;
	public final Key CLEAR           = Key.CLEAR;
	public final Key RETURN          = Key.RETURN;
	public final Key ENTER           = Key.ENTER;
	public final Key PAUSE           = Key.PAUSE;
	public final Key ESCAPE          = Key.ESCAPE;
	public final Key SPACE           = Key.SPACE;
	public final Key PAGE_UP         = Key.PAGE_UP;
	public final Key PAGE_DOWN       = Key.PAGE_DOWN;
	public final Key END             = Key.END;
	public final Key HOME            = Key.HOME;
	public final Key LEFT            = Key.LEFT;
	public final Key UP              = Key.UP;
	public final Key RIGHT           = Key.RIGHT;
	public final Key DOWN            = Key.DOWN;
	public final Key INSERT          = Key.INSERT;
	public final Key DELETE          = Key.DELETE;
	public final Key SEMICOLON       = Key.SEMICOLON;
	public final Key EQUALS          = Key.EQUALS;

	// Number pad keys
	public final Key NUMPAD0         = Key.NUMPAD0;
	public final Key NUMPAD1         = Key.NUMPAD1;
	public final Key NUMPAD2         = Key.NUMPAD2;
	public final Key NUMPAD3         = Key.NUMPAD3;
	public final Key NUMPAD4         = Key.NUMPAD4;
	public final Key NUMPAD5         = Key.NUMPAD5;
	public final Key NUMPAD6         = Key.NUMPAD6;
	public final Key NUMPAD7         = Key.NUMPAD7;
	public final Key NUMPAD8         = Key.NUMPAD8;
	public final Key NUMPAD9         = Key.NUMPAD9;
	public final Key MULTIPLY        = Key.MULTIPLY;
	public final Key ADD             = Key.ADD;
	public final Key SEPARATOR       = Key.SEPARATOR;
	public final Key SUBTRACT        = Key.SUBTRACT;
	public final Key DECIMAL         = Key.DECIMAL;
	public final Key DIVIDE          = Key.DIVIDE;

	// Function keys
	public final Key F1              = Key.F1;
	public final Key F2              = Key.F2;
	public final Key F3              = Key.F3;
	public final Key F4              = Key.F4;
	public final Key F5              = Key.F5;
	public final Key F6              = Key.F6;
	public final Key F7              = Key.F7;
	public final Key F8              = Key.F8;
	public final Key F9              = Key.F9;
	public final Key F10             = Key.F10;
	public final Key F11             = Key.F11;
	public final Key F12             = Key.F12;

	public final Key META            = Key.META;
	public final Key COMMAND         = Key.COMMAND;

	public final Key ZENKAKU_HANKAKU = Key.ZENKAKU_HANKAKU;

}
