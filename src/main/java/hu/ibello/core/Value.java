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
import java.util.EnumSet;

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
	 * Transforms the encapsulated value into a Long. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's long representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as long. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Long, or <code>null</code>
	 */
	public Long toLong();
	
	/**
	 * Transforms the encapsulated value into an Integer. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's integer representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as integer. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Integer, or <code>null</code>
	 */
	public Integer toInteger();
	
	/**
	 * Transforms the encapsulated value into a Short. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's short representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as short. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Short, or <code>null</code>
	 */
	public Short toShort();
	
	/**
	 * Transforms the encapsulated value into a Byte. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's byte representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as byte. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Byte, or <code>null</code>
	 */
	public Byte toByte();
	
	/**
	 * Transforms the encapsulated value into a Double. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's double representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as double. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Double, or <code>null</code>
	 */
	public Double toDouble();
	
	/**
	 * Transforms the encapsulated value into a Float. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Number, then it's float representation will be used. Otherwise, the method tries to parse the
	 * String representation of the value as float. If it is not possible, <code>null</code> will be returned.
	 * @return the value as Float, or <code>null</code>
	 */
	public Float toFloat();
	
	/**
	 * Transforms the encapsulated value into a Boolean. If the value is <code>null</code>, the result will be also <code>null</code>.
	 * If the value is a Boolean, then it will be returned. Otherwise, the method parses the
	 * String representation of the value as boolean.
	 * @return the value as Boolean, or <code>null</code>
	 */
	public Boolean toBoolean();
	
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
	 * the constant. The method is able to find the right enum constants case-insensitively.
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
