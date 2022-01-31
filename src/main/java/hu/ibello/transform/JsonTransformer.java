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
package hu.ibello.transform;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import javax.xml.bind.JAXBElement;

/**
 * Utility class which transforms java object into JSON and JSON to java object.
 * <p>
 * During the serialization / deserialization the JSON name of the fields will be the same as in java.
 * To change that, you can use {@link SerializedName} annotation on the java field.
 * 
 * @author Kornél Simon
 *
 */
public interface JsonTransformer {

	/**
	 * This method serializes the specified object into its equivalent JSON representation.
	 * This method should be used when the specified object is not a generic type.
	 *  This method uses {@link Class#getClass()} to get the type for the specified object,
	 *  but the <code>getClass()</code> loses the generic type information because of the Type Erasure feature of Java.
	 *  Note that this method works fine if the any of the object fields are of generic type,
	 *  just the object itself should not be of a generic type.
	 *  If the object is of generic type, use {@link #toJson(Object, Type)} instead.
	 * @param src the object for which JSON representation is to be created
	 * @return the JSON representation of <code>src</code>
	 */
	public String toJson(Object src);
	
	/**
	 * This method serializes the specified object, including those of generic types, into its equivalent JSON representation.
	 * This method must be used if the specified object is a generic type.
	 * For non-generic objects, use {@link #toJson(Object)} instead.
	 * @param src the object for which JSON representation is to be created
	 * @param typeOfSrc the specific genericized type of <code>src</code>
	 * @return the JSON representation of <code>src</code>
	 */
	public String toJson(Object src, Type typeOfSrc);
	
	/**
	 * This method deserializes the specified JSON into an object of the specified type.
	 * This method automatically boxes field values into {@link JAXBElement}.
	 * If you have the Json in a Reader instead of a String, use {@link #fromJson(Reader, Type)} instead.
	 * @param <T> the type of the desired object
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT the specific genericized type of <code>json</code>
	 * @return an object of type T from the string. Returns <code>null</code> if <code>json</code> is <code>null</code>.
	 * @throws TransformerException if json is not a valid representation for an object of type typeOfT
	 */
	public <T> T fromJson(String json, Type typeOfT) throws TransformerException;
	
	/**
	 * This method deserializes the JSON read from the specified reader into an object of the specified type.
	 * This method automatically boxes field values into {@link JAXBElement}.
	 * If you have the JSON in a String form instead of a Reader, use {@link #fromJson(String, Type)} instead.
	 * @param <T> the type of the desired object
	 * @param json  the reader producing Json from which the object is to be deserialized
	 * @param typeOfT the specific genericized type of <code>json</code>
	 * @return an object of type T from the string. Returns <code>null</code> if <code>json</code> is <code>null</code>.
	 * @throws TransformerException if json is not a valid representation for an object of type typeOfT
	 */
	public <T> T fromJson(Reader json, Type typeOfT) throws TransformerException;
	
	/**
	 * This method deserializes the specified JSON into a list of objects of the specified type.
	 * This method automatically boxes field values into {@link JAXBElement}.
	 * If you have the Json in a Reader instead of a String, use {@link #fromJsonToList(Reader, Class)} instead.
	 * @param <T> the type of the desired objects
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT the type of the desired objects
	 * @return a list with objects of type T. Returns <code>null</code> if <code>json</code> is <code>null</code>.
	 * @throws TransformerException if json is not a valid representation for an object of type typeOfT
	 */
	public <T> List<T> fromJsonToList(String json, Class<T> typeOfT) throws TransformerException;
	
	/**
	 * This method deserializes the specified JSON into a list of objects of the specified type.
	 * This method automatically boxes field values into {@link JAXBElement}.
	 * If you have the JSON in a String form instead of a Reader, use {@link #fromJsonToList(String, Class)} instead.
	 * @param <T> the type of the desired objects
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT the type of the desired objects
	 * @return a list with objects of type T. Returns <code>null</code> if <code>json</code> is <code>null</code>.
	 * @throws TransformerException if json is not a valid representation for an object of type typeOfT
	 */
	public <T> List<T> fromJsonToList(Reader json, Class<T> typeOfT) throws TransformerException;
	
}
