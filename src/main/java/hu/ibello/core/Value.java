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
package hu.ibello.core;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * A wrapper class which encapsulates a value.
 * The encapsulated value can be <code>null</code>.
 * The class has different transformer methods, that
 * translate the value into different java types.
 * @author Kornél Simon
 *
 */
public interface Value {
	
	/**
	 * Transforms the encapsulated value into a String array.
	 * <ul>
	 * <li>If the value is <code>null</code>, result will be <code>null</code> also.</li>
	 * <li>If the value is an empty string, result will be an empty array.</li>
	 * <li>If the value is a string containing only whitespaces, result will be an empty array.</li>
	 * <li>If the value is a string which contains at least one non-whitespace character, then
	 * the method will split the value around commas and will return the array of parts.
	 * (Leading and trailing spaces are skipped.)</li>
	 * <li>If the value is an array or a collection, then the result will contains the string
	 * representations of the items in the array or collection.</li>
	 * </ul>
	 * @return the String array or <code>null</code>
	 */
	public String[] toStringArray();
	
	/**
	 * Transforms the encapsulated value into a string. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a String, then will be used. Otherwise, the method returns the String representation of the value.
	 * @param defaultValue default value which is used when the encapsulated value is <code>null</code>; cannot be <code>null</code>
	 * @return the value as String
	 * @throws NullPointerException if the given <code>defaultValue</code> is <code>null</code>
	 */
	public default String toString(String defaultValue) {
		if (defaultValue == null) {
			throw new NullPointerException();
		}
		String result = toString();
		return (result == null ? defaultValue : result);
	}
	
	/**
	 * Transforms the encapsulated value into a Long. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's long representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as long. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Long, or <code>null</code>
	 */
	public Long toLong();
	
	/**
	 * Transforms the encapsulated value into a long. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's long representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as long. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a long
	 * @return the value as long
	 */
	public default long toLong(long defaultValue) {
		Long result = toLong();
		return (result == null ? defaultValue : result.longValue());
	}
	
	/**
	 * Transforms the encapsulated value into an Integer. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's integer representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as integer. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Integer, or <code>null</code>
	 */
	public Integer toInteger();
	
	/**
	 * Transforms the encapsulated value into an int. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's integer representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as integer. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a int
	 * @return value as int
	 */
	public default int toInteger(int defaultValue) {
		Integer result = toInteger();
		return (result == null ? defaultValue : result.intValue());
	}
	
	/**
	 * Transforms the encapsulated value into a Short. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's short representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as short. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Short, or <code>null</code>
	 */
	public Short toShort();
	
	/**
	 * Transforms the encapsulated value into a short. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's short representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as short. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a short
	 * @return value as short
	 */
	public default short toShort(short defaultValue) {
		Short result = toShort();
		return (result == null ? defaultValue : result.shortValue());
	}
	
	/**
	 * Transforms the encapsulated value into a Byte. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's byte representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as byte. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Byte, or <code>null</code>
	 */
	public Byte toByte();
	
	/**
	 * Transforms the encapsulated value into a byte. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's byte representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as byte. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a byte
	 * @return value as byte
	 */
	public default byte toByte(byte defaultValue) {
		Byte result = toByte();
		return (result == null ? defaultValue : result.byteValue());
	}
	
	/**
	 * Transforms the encapsulated value into a Double. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's double representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as double. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Double, or <code>null</code>
	 */
	public Double toDouble();
	
	/**
	 * Transforms the encapsulated value into a double. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's double representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as double. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a double
	 * @return value as double
	 */
	public default double toDouble(double defaultValue) {
		Double result = toDouble();
		return (result == null ? defaultValue : result.doubleValue());
	}
	
	/**
	 * Transforms the encapsulated value into a Float. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's float representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as float. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Float, or <code>null</code>
	 */
	public Float toFloat();
	
	/**
	 * Transforms the encapsulated value into a float. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Number, then it's float representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as float. If it is not possible, the <code>defaultValue</code> will be returned.
	 * @param defaultValue default value which is used when the encapsulated value cannot be transformed to a float
	 * @return value as float
	 */
	public default float toFloat(float defaultValue) {
		Float result = toFloat();
		return (result == null ? defaultValue : result.floatValue());
	}
	
	/**
	 * Transforms the encapsulated value into a Boolean. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Boolean, then it will be returned. Otherwise, the method parses the
	 * String representation of the value as boolean.
	 * @return the value as Boolean, or <code>null</code>
	 */
	public Boolean toBoolean();
	
	/**
	 * Transforms the encapsulated value into a boolean. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a Boolean, then it will be returned. Otherwise, the method parses the
	 * String representation of the value as boolean.
	 * @param defaultValue default value which is used when the encapsulated value is <code>null</code>
	 * @return value as boolean
	 */
	public default boolean toBoolean(boolean defaultValue) {
		Boolean result = toBoolean();
		return (result == null ? defaultValue : result.booleanValue());
	}
	
	/**
	 * Transforms the encapsulated value into a {@link Date}. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a {@link Date}, then it will be returned. If the value is a date-like object ( {@link Calendar}, {@link Instant},
	 * {@link XMLGregorianCalendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into a {@link Date}. Otherwise, the method parses the String representation of the value as a {@link Date}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @return value as Date
	 */
	public Date toDate();
	
	/**
	 * Transforms the encapsulated value into a {@link Date}. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a {@link Date}, then it will be returned. If the value is a date-like object ( {@link Calendar}, {@link Instant},
	 * {@link XMLGregorianCalendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into a {@link Date}. Otherwise, the method parses the String representation of the value as a {@link Date}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @param defaultValue default value which is used when the encapsulated value is <code>null</code>
	 * @return value as Date
	 */
	public default Date toDate(Date defaultValue) {
		Date result = toDate();
		return (result == null ? defaultValue : result);
	}
	
	/**
	 * Transforms the encapsulated value into a {@link Calendar}. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a {@link Calendar}, then it will be returned. If the value is a date-like object ( {@link Date}, {@link Instant},
	 * {@link XMLGregorianCalendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into a {@link Calendar}. Otherwise, the method parses the String representation of the value as a {@link Calendar}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @return value as Calendar
	 */
	public Calendar toCalendar();
	
	/**
	 * Transforms the encapsulated value into a {@link Calendar}. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is a {@link Calendar}, then it will be returned. If the value is a date-like object ( {@link Date}, {@link Instant},
	 * {@link XMLGregorianCalendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into a {@link Calendar}. Otherwise, the method parses the String representation of the value as a {@link Calendar}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @param defaultValue default value which is used when the encapsulated value is <code>null</code>
	 * @return value as Calendar
	 */
	public default Calendar toCalendar(Calendar defaultValue) {
		Calendar result = toCalendar();
		return (result == null ? defaultValue : result);
	}
	
	/**
	 * Transforms the encapsulated value into an {@link XMLGregorianCalendar}. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is an {@link XMLGregorianCalendar}, then it will be returned. If the value is a date-like object ( {@link Date}, {@link Instant},
	 * {@link Calendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into am {@link XMLGregorianCalendar}. Otherwise, the method parses the String representation of the value as an {@link XMLGregorianCalendar}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @return value as XMLGregorianCalendar
	 */
	public XMLGregorianCalendar toXMLGregorianCalendar();
	
	/**
	 * Transforms the encapsulated value into an {@link XMLGregorianCalendar}. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is an {@link XMLGregorianCalendar}, then it will be returned. If the value is a date-like object ( {@link Date}, {@link Instant},
	 * {@link Calendar}, {@link OffsetDateTime}, {@link ZonedDateTime}, {@link LocalDate}, {@link LocalDateTime}), then
	 * it will be transformed into am {@link XMLGregorianCalendar}. Otherwise, the method parses the String representation of the value as an {@link XMLGregorianCalendar}.
	 * In this case relative date is noticed as well.
	 * A relative date always begins with a "+" or "-" sign, depending on whether you want a future or past date.
	 * This is followed by a combination of numbers and letters. Each number is followed by a letter that gives the unit of measure for the number.
	 * This may be:
	 * <ul>
	 * <li>"Y": year</li>
	 * <li>"M": month</li>
	 * <li>"D": day of month</li>
	 * <li>"h": hour</li>
	 * <li>"m": minute</li>
	 * <li>"s": second</li>
	 * </ul>
	 * Examples: "-1Y30h", "+1D".
	 * @param defaultValue default value which is used when the encapsulated value is <code>null</code>
	 * @return value as XMLGregorianCalendar
	 */
	public default XMLGregorianCalendar toXMLGregorianCalendar(XMLGregorianCalendar defaultValue) {
		XMLGregorianCalendar result = toXMLGregorianCalendar();
		return (result == null ? defaultValue : result);
	}
	
	/**
	 * Transforms the encapsulated value into the given type of enum. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is the right type, then it will be returned. Otherwise, the method tries to parse the result
	 * from the String representation of the value. For that, the name of the enum constants will be used (see {@link Enum#name()}).
	 * The method is able to find the right enum constant case-insensitively. If it is not possible, then the result will be <code>null</code>.
	 * @param <E> the type of the result
	 * @param type the enum type we want to have as result
	 * @return the value as the given type, or <code>null</code>
	 */
	public <E extends Enum<?>> E toEnum(Class<E> type);
	
	/**
	 * Transforms the encapsulated value into an enum. If the value is <code>null</code>, the result will be the given <code>defaultValue</code>.
	 * If the value is the right type, then it will be returned. Otherwise, the method tries to parse the result
	 * from the String representation of the value. For that, the name of the enum constants will be used by default (see {@link Enum#name()}).
	 * The method is able to find the right enum constant case-insensitively. If an enum constant has a {@link Name} annotation, then the search uses it's value too.
	 * If the desired constant cannot be found, then the result will be the <code>defaultValue</code>.
	 * @param <E> the type of the result
	 * @param defaultValue default value which will be returned if value cannot be parsed to an enum constant; should be non-null
	 * @return the value as enum constant
	 * @throws NullPointerException if the given <code>defaultValue</code> is <code>null</code>
	 */
	public default <E extends Enum<?>> E toEnum(E defaultValue) {
		if (defaultValue == null) {
			throw new NullPointerException();
		}
		@SuppressWarnings("unchecked")
		E result = (E) toEnum(defaultValue.getClass());
		return (result == null ? defaultValue : result);
	}
	
	/**
	 * Transforms the encapsulated value into an {@link EnumSet} of the given enum type.
	 * <ul>
	 * <li>If the value is <code>null</code>, result will be <code>null</code> also.</li>
	 * <li>If the value is an empty string, result will be an empty set.</li>
	 * <li>If the value is a string containing only whitespaces, result will be an empty set.</li>
	 * <li>If the value is a string which contains at least one non-whitespace character, then
	 * the method will split the value around commas and will try to parse the parts as enum constants.</li>
	 * <li>If the value is an array or a collection, then the result will try to parse the items
	 * in the array or collection as enum constants.</li>
	 * </ul>
	 * If the method parses a string as enum constant, then it tries to match the string value to the name of
	 * the constant by default (see {@link Enum#name()}). The method is able to find the right enum constants case-insensitively.
	 * If an enum constant has a {@link Name} annotation, then the search uses it's value too.
	 * Invalid names will not result an exception - they just skipped.
	 * Therefore it is possible to have an empty set as result.
	 * @param <E> the desired enum type
	 * @param type the enum type we want to have in the result set
	 * @return value as the given type of enum set, or <code>null</code>
	 */
	public <E extends Enum<E>> EnumSet<E> toEnumSet(Class<E> type);
	
	/**
	 * Transforms the encapsulated value into a {@link File}.
	 * If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a File instance, then it will be returned.
	 * Otherwise, a new File instance will be constructed from the string
	 * representation of the value.
	 * @return the value as File, or <code>null</code>
	 */
	public File toFile();
	
	/**
	 * Transforms the encapsulated value into a {@link Class}.
	 * If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Class instance, then it will be returned.
	 * Otherwise, a new Class instance will be constructed from the string
	 * representation of the value.
	 * @return the value as Class, or <code>null</code>
	 */
	public Class<?> toClass();
	
	/**
	 * Transforms the encapsulated value into an array of classes.
	 * <ul>
	 * <li>If the value is <code>null</code>, result will be <code>null</code> also.</li>
	 * <li>If the value is an empty string, result will be an empty array.</li>
	 * <li>If the value is a string containing only whitespaces, result will be an empty array.</li>
	 * <li>If the value is a string which contains at least one non-whitespace character, then
	 * the method will split the value around commas and will try to parse the parts as classes.</li>
	 * <li>If the value is an array or a collection, then the result will try to parse the items
	 * in the array or collection as classes.</li>
	 * </ul>
	 * To parse a string into a class, the full class name is used.
	 * @return the value as array of classes, or <code>null</code>
	 */
	public Class<?>[] toClassArray();
	
}
