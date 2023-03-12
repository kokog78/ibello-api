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
 * Utility interface which transforms java object into JSON and JSON to java object.
 * <p>
 * During the serialization / deserialization the JSON name of the fields will be the same as in java.
 * To change that, you can use {@link SerializedName} annotation on the java field.
 * <p>
 * With this interface it is possible to register special JSON serializer / deserializer instances into the entire ibello
 * - see {@link #registerSerializer(JsonTypeSerializer)} and {@link #registerDeserializer(JsonTypeDeserializer)}.
 * 
 * @author Kornél Simon
 *
 */
public interface JsonTransformer extends JsonSerializer, JsonDeserializer {

	/**
	 * Registers a deserializer instance into the ibello. The registered instance will deserialize a specific
	 * java type into JSON.
	 * @param <T> the deserializer will deserialize this type of objects
	 * @param deserializer the deserializer instance we want to register
	 * @throws TransformerException if the registration of the deserializer is not possible
	 */
	public <T> void registerDeserializer(JsonTypeDeserializer<T> deserializer) throws TransformerException;
	
	/**
	 * Registers a serializer instance into the ibello. The registered instance will serialize a JSON string into a specific java type.
	 * @param <T> the serializer will serialize this type of objects
	 * @param serializer the serializer instance we want to register
	 * @throws TransformerException if the registration of the serializer is not possible
	 */
	public <T> void registerSerializer(JsonTypeSerializer<T> serializer) throws TransformerException;
	
}
