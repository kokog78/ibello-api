package hu.ibello.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.Test;

public class KeyTest {
	
	@Test
	public void length_returns_1() throws Exception {
		Key key = Key.SPACE;
		assertThat(key.length()).isEqualTo(1);
	}	

	@Test
	public void charAt_returns_the_only_character() throws Exception {
		Key key = Key.SPACE;
		char c = key.charAt(0);
		assertThat(c).isEqualTo('\uE00D');
	}
	
	@Test
	public void charAt_throws_exception_for_bigger_index() throws Exception {
		Key key = Key.SPACE;
		Throwable ex = catchThrowable(() -> key.charAt(1));
		assertThat(ex).isInstanceOf(IndexOutOfBoundsException.class);
	}
	
	@Test
	public void subSequence_returns_same_object() throws Exception {
		Key key = Key.SPACE;
		CharSequence result = key.subSequence(0, 1);
		assertThat(result).isEqualTo(key);
	}

	@Test
	public void subSequence_returns_empty_string() throws Exception {
		Key key = Key.SPACE;
		CharSequence result = key.subSequence(0, 0);
		assertThat(result.length()).isEqualTo(0);
	}

	@Test
	public void subSequence_throws_exception_for_bigger_sequence() throws Exception {
		Key key = Key.SPACE;
		Throwable ex = catchThrowable(() -> key.subSequence(0, 2));
		assertThat(ex).isInstanceOf(IndexOutOfBoundsException.class);
	}
	
	@Test
	public void subSequence_throws_exception_for_sequence_starting_with_1() throws Exception {
		Key key = Key.SPACE;
		Throwable ex = catchThrowable(() -> key.subSequence(1, 2));
		assertThat(ex).isInstanceOf(IndexOutOfBoundsException.class);
	}
	
	@Test
	public void toString_returns_string_containing_the_only_character() throws Exception {
		Key key = Key.SPACE;
		String result = key.toString();
		assertThat(result).isEqualTo("\uE00D");
	}
	

}
