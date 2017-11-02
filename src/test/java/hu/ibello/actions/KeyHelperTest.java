package hu.ibello.actions;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

public class KeyHelperTest {

	private KeyHelper helper;
	
	@Before
	public void init() {
		helper = new KeyHelper() {
			
			@Override
			public KeyModifier SHIFT() {
				return null;
			}
			
			@Override
			public KeyModifier CONTROL() {
				return null;
			}
			
			@Override
			public KeyModifier ALT() {
				return null;
			}
		};
	}
	
	@Test
	public void public_fields_should_be_coming_from_Key() throws Exception {
		for (Field field : KeyHelper.class.getFields()) {
			Object value = field.get(helper);
			Object keyValue = Key.class.getField(field.getName()).get(null);
			assertThat(value).isSameAs(keyValue);
		}
	}
	
}
