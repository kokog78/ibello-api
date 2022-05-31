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
 * An interface which serializes a specific java type into JSON format.
 * Instances of this interface can be registered for the ibello system with the {@link JsonTransformer#registerSerializer(JsonTypeSerializer)} method.
 * @author Kornél Simon
 *
 * @param <TYPE> the java type serialized by this interface
 */
public interface JsonTypeSerializer<TYPE> {

	/**
	 * This method should return with the java type.
	 * @return the java type
	 */
	public Class<TYPE> getType();

	/**
	 * This method receives a java object and should serialize it into a JSON string.
	 * During the process it can use the <code>context</code> to serialize another objects.
	 * @param source the object to serialize
	 * @param context a context object which can be used to serialize another objects
	 * @return the JSON string
	 * @throws TransformerException if the serialization is not possible
	 */
	public String serialize(TYPE source, JsonSerializer context) throws TransformerException;
}
