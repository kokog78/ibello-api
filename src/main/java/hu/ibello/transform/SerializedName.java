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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * We can use this annotation for a field in a model class. The content of the model will be serialized to JSON (or will be deserilaized from JSON).
 * With the annotation we can "rename" fields for the serializer tool. The JSON name of the field should be specified with the annotation.
 * It works for {@link JsonTransformer}. Example model class:
 * <pre>
 * public class MyData {
 * 
 *   {@literal @}SerializedName("my-field")
 *   private String myField = "a";
 *   
 * }
 * </pre>
 * Example code:
 * <pre>
 * MyData data = ...;
 * 
 * JsonTransformer transformer = ...;
 * String json = transformer.toJson(data);
 * </pre>
 * After this code, the value of the "json" variable will be:
 * <pre>
 * {"my-field":"a"}
 * </pre>
 * @author kokog
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface SerializedName {

	/**
	 * The serialized name of the field.
	 * @return serialized name of the field
	 */
	String value();
}
