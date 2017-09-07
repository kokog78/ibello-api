package hu.ibello.actions;

/**
 * This enum collects special characters and implements the {@link CharSequence} interface.
 * Every constant of this enum is a 1-length character sequence.
 * It can be used in {@link ActionBuilder#sendKeys(CharSequence...)} and
 * {@link ActionBuilder#sendKeys(KeyModifier, CharSequence...)} methods.
 * @author Kornél Simon
 * @see KeyHelper
 */
public enum Key implements CharSequence {

	CANCEL       ('\uE001'), // ^break
	HELP         ('\uE002'),
	BACK_SPACE   ('\uE003'),
	TAB          ('\uE004'),
	CLEAR        ('\uE005'),
	RETURN       ('\uE006'),
	ENTER        ('\uE007'),
	PAUSE        ('\uE00B'),
	ESCAPE       ('\uE00C'),
	SPACE        ('\uE00D'),
	PAGE_UP      ('\uE00E'),
	PAGE_DOWN    ('\uE00F'),
	END          ('\uE010'),
	HOME         ('\uE011'),
	LEFT         ('\uE012'),
	UP           ('\uE013'),
	RIGHT        ('\uE014'),
	DOWN         ('\uE015'),
	INSERT       ('\uE016'),
	DELETE       ('\uE017'),
	SEMICOLON    ('\uE018'),
	EQUALS       ('\uE019'),

	// Number pad keys
	NUMPAD0      ('\uE01A'),
	NUMPAD1      ('\uE01B'),
	NUMPAD2      ('\uE01C'),
	NUMPAD3      ('\uE01D'),
	NUMPAD4      ('\uE01E'),
	NUMPAD5      ('\uE01F'),
	NUMPAD6      ('\uE020'),
	NUMPAD7      ('\uE021'),
	NUMPAD8      ('\uE022'),
	NUMPAD9      ('\uE023'),
	MULTIPLY     ('\uE024'),
	ADD          ('\uE025'),
	SEPARATOR    ('\uE026'),
	SUBTRACT     ('\uE027'),
	DECIMAL      ('\uE028'),
	DIVIDE       ('\uE029'),

	// Function keys
	F1           ('\uE031'),
	F2           ('\uE032'),
	F3           ('\uE033'),
	F4           ('\uE034'),
	F5           ('\uE035'),
	F6           ('\uE036'),
	F7           ('\uE037'),
	F8           ('\uE038'),
	F9           ('\uE039'),
	F10          ('\uE03A'),
	F11          ('\uE03B'),
	F12          ('\uE03C'),

	META         ('\uE03D'),
	COMMAND      (Key.META),

	ZENKAKU_HANKAKU ('\uE040');

	private final char character;

	private Key(char character) {
		this.character = character;
	}

	private Key(Key key) {
		this(key.charAt(0));
	}

	/**
	 * If this method is called with <code>0</code> argument, then it returns the only
	 * available character. If it is called with another argument, then it throws an
	 * {@link IndexOutOfBoundsException}.
	 * @throws IndexOutOfBoundsException when this method is called with an argument which is not <code>0</code>
	 */
	@Override
	public char charAt(int index) {
		if (index == 0) {
			return character;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	@Override
	public int length() {
		return 1;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		if (start == 0 && end == 1) {
			return String.valueOf(charAt(0));
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public String toString() {
		return Character.toString(character);
	}
}
