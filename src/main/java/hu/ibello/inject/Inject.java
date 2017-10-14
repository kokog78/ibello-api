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
package hu.ibello.inject;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Main annotation of the dependency injection system.
 * It marks an injected field in a class.
 * Example:
 * </p>
 * <pre>
 * public class MyClass {
 * 
 *    {@literal @}Inject
 *    private InjectedClass injectedField;
 * 
 * }
 * </pre>
 * <p>
 * The injector automatically discovers fields marked with {@link Inject} annotation.
 * The field can be public, protected, package private or private, it's value will be handled by the injector.
 * </p>
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Inject {

}
