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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * With this annotation we can mark calculated fields in a test data class and specify the expression for the calculation.
 * If a field has this annotation the the value of the field will be calculated using the expression, the model object which holds the field and the properties
 * specified here or in the {@link Model} annotation of the class.
 * </p>
 * <p>
 * The expression may contain references to another values. The referenced values will be included in the final value, replacing the reference.
 * A reference can point to:
 * </p>
 * <ul>
 * <li>another field in the same class; in this case the name of the field should be in the reference, for example <i>${anotherField}</i>,</li>
 * <li>the data identifier which was used to load the test data; in this case the reference should be <i>${$id}</i>,</li>
 * <li>a configuration value, for example <i>${ibello.browser.type}</i>,</li>
 * <li>a field in the properties object; in this case the reference should start with the "properties." prefix, for example <i>${properties.timestamp}</i>.</li>
 * </ul>
 * <p>
 * Example:
 * </p>
 * <pre>
 * {@literal @}Model
 * public class User {
 * 
 *     // the field will contain the timestamp property of the MyData instance
 *     {@literal @}Calculated(expression = "${properties.timestamp}", properties = MyData.class)
 *     public String id;
 *     
 *     // the field will contain the "User" text and the id of this object
 *     {@literal @}Calculated(expression = "User ${id}")
 *     public String name;
 * 
 * }
 * </pre>
 * <p>
 * If the properties class is not specified in this annotation, then it will be the one specified in the {@link Model} annotation of the class. Example:
 * </p>
 * <pre>
 * {@literal @}Model(properties = MyData.class)
 * public class User {
 * 
 *     // the field will contain the timestamp property of the MyData instance
 *     {@literal @}Calculated(expression = "${properties.timestamp}")
 *     public String id;
 * 
 * }
 * </pre>
 * @see Model
 * @author kokog
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Calculated {

	String expression();
	
	Class<?> properties() default Object.class;
}
