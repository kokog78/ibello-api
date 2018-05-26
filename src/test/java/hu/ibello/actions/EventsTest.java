package hu.ibello.actions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EventsTest {

	@Test
	public void of_gets_null_if_input_is_null() throws Exception {
		Events result = Events.of(null);
		assertThat(result).isNull();
	}
	
	@Test
	public void of_gets_null_if_event_does_not_known() throws Exception {
		Events result = Events.of("unknown-event");
		assertThat(result).isNull();
	}
	
	@Test
	public void of_gets_enum_value_by_name() throws Exception {
		Events result = Events.of(Events.MOUSE_ENTER.name());
		assertThat(result).isEqualTo(Events.MOUSE_ENTER);
	}
	
	@Test
	public void of_gets_enum_value_by_event_name() throws Exception {
		Events result = Events.of(Events.MOUSE_ENTER.eventName);
		assertThat(result).isEqualTo(Events.MOUSE_ENTER);
	}
	
	@Test
	public void of_gets_enum_value_by_event_name_case_insensitively() throws Exception {
		Events result = Events.of(Events.MOUSE_ENTER.eventName.toUpperCase());
		assertThat(result).isEqualTo(Events.MOUSE_ENTER);
	}
	
}
