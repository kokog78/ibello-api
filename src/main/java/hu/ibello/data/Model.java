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
package hu.ibello.data;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * With this annotation we can mark if a class is used to hold test data.
 * For example, we can create the User class:
 * </p>
 * <pre>
 * {@literal @}Model
 * public class User {
 * 
 *     public String id;
 *     public String username;
 *     public boolean male;
 * 
 * }
 * </pre>
 * <p>
 * If we use the annotation, then in some circumstances, the ibello will be able to recognize
 * that the class can be used to load test data into it.
 * Instances of the class can also be used as parameters for test steps.
 * </p>
 * <p>
 * We can add the {@link Editable} annotation to the fields of a model class.
 * With it we can specify what kind of editor should be used to specify value for that field.
 * For example, the "requirementIds" field in this model class will contain requirement identifiers:
 * </p>
 * <pre>
 * {@literal @}Model
 * public class User {
 * 
 *     {@literal @}Editable(Editor.REQUIREMENT)
 *     public List{@literal <}String{@literal >} requirementIds;
 * 
 * }
 * </pre>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Model {
}
