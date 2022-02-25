package hu.ibello.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import org.junit.Test;

import hu.ibello.search.By;

public class ValueTest {

	private MyValue value = new MyValue();
	
	@Test
	public void toString_uses_default_value() throws Exception {
		value.value = "test";
		assertThat(value.toString("def")).isEqualTo("test");
		value.value = null;
		assertThat(value.toString("def")).isEqualTo("def");
		assertThatThrownBy(() -> value.toString(null)).isInstanceOf(NullPointerException.class);
	}
	
	@Test
	public void toLong_uses_default_value() throws Exception {
		value.value = 1L;
		assertThat(value.toLong(2L)).isEqualTo(1L);
		value.value = null;
		assertThat(value.toLong(2L)).isEqualTo(2L);
	}
	
	@Test
	public void toInteger_uses_default_value() throws Exception {
		value.value = 1;
		assertThat(value.toInteger(2)).isEqualTo(1);
		value.value = null;
		assertThat(value.toInteger(2)).isEqualTo(2);
	}
	
	@Test
	public void toShort_uses_default_value() throws Exception {
		value.value = (short)1;
		assertThat(value.toShort((short)2)).isEqualTo((short)1);
		value.value = null;
		assertThat(value.toShort((short)2)).isEqualTo((short)2);
	}
	
	@Test
	public void toByte_uses_default_value() throws Exception {
		value.value = (byte)1;
		assertThat(value.toByte((byte)2)).isEqualTo((byte)1);
		value.value = null;
		assertThat(value.toByte((byte)2)).isEqualTo((byte)2);
	}
	
	@Test
	public void toDouble_uses_default_value() throws Exception {
		value.value = 1.0;
		assertThat(value.toDouble(2.0)).isEqualTo(1.0);
		value.value = null;
		assertThat(value.toDouble(2.0)).isEqualTo(2.0);
	}
	
	@Test
	public void toFloat_uses_default_value() throws Exception {
		value.value = 1.0f;
		assertThat(value.toFloat(2.0f)).isEqualTo(1.0f);
		value.value = null;
		assertThat(value.toFloat(2.0f)).isEqualTo(2.0f);
	}
	
	@Test
	public void toBoolean_uses_default_value() throws Exception {
		value.value = true;
		assertThat(value.toBoolean(false)).isEqualTo(true);
		value.value = null;
		assertThat(value.toBoolean(false)).isEqualTo(false);
		assertThat(value.toBoolean(true)).isEqualTo(true);
	}
	
	@Test
	public void toDate_uses_default_value() throws Exception {
		Date date1 = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		Date date2 = calendar.getTime();
		value.value = date2;
		assertThat(value.toDate(date1)).isEqualTo(date2);
		value.value = null;
		assertThat(value.toDate(date1)).isEqualTo(date1);
	}
	
	@Test
	public void toCalendar_uses_default_value() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.clear();
		value.value = calendar2;
		assertThat(value.toCalendar(calendar1)).isEqualTo(calendar2);
		value.value = null;
		assertThat(value.toCalendar(calendar1)).isEqualTo(calendar1);
	}
	
	@Test
	public void toEnum_uses_default_value() throws Exception {
		value.value = By.BUTTON_TEXT;
		assertThat(value.toEnum(By.CLASS_NAME)).isEqualTo(By.BUTTON_TEXT);
		value.value = null;
		assertThat(value.toEnum(By.CLASS_NAME)).isEqualTo(By.CLASS_NAME);
		assertThatThrownBy(() -> value.toEnum((By)null)).isInstanceOf(NullPointerException.class);
	}
	
	private class MyValue implements Value {
		
		private Object value;
		
		@Override
		public String toString() {
			return (String)value;
		}

		@Override
		public Long toLong() {
			return (Long)value;
		}

		@Override
		public Integer toInteger() {
			return (Integer)value;
		}

		@Override
		public Short toShort() {
			return (Short)value;
		}

		@Override
		public Byte toByte() {
			return (Byte)value;
		}

		@Override
		public Double toDouble() {
			return (Double)value;
		}

		@Override
		public Float toFloat() {
			return (Float)value;
		}

		@Override
		public Boolean toBoolean() {
			return (Boolean)value;
		}
		
		@Override
		public Date toDate() {
			return (Date)value;
		}
		
		@Override
		public Calendar toCalendar() {
			return (Calendar)value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public <E extends Enum<?>> E toEnum(Class<E> type) {
			return (E)value;
		}

		@Override
		public <E extends Enum<E>> EnumSet<E> toEnumSet(Class<E> type) {
			return null;
		}

		@Override
		public String[] toStringArray() {
			return null;
		}

		@Override
		public File toFile() {
			return null;
		}

		@Override
		public Class<?> toClass() {
			return null;
		}

		@Override
		public Class<?>[] toClassArray() {
			return null;
		}
		
	}
	
}
