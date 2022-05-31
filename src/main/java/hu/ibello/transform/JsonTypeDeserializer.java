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

/**
 * An interface which deserializes a specific java type into JSON format.
 * Instances of this interface can be registered for the ibello system with the {@link JsonTransformer#registerDeserializer(JsonTypeDeserializer)} method.
 * @author Kornél Simon
 *
 * @param <TYPE> the java type deserialized by this interface
 */
public interface JsonTypeDeserializer<TYPE> {

	/**
	 * This method should return with the java type.
	 * @return the java type
	 */
	public Class<TYPE> getType();

	/**
	 * This method receives a JSON string and should create the java object with the specific type.
	 * During the process it can use the <code>context</code> to deserialize another JSON strings.
	 * @param source the JSON string to deserialize
	 * @param context a context object which can be used to deserialize another JSON strings
	 * @return the deserialized java object
	 * @throws TransformerException if the deserialization is not possible
	 */
	public TYPE deserialize(String source, JsonDeserializer context) throws TransformerException;
	
}
