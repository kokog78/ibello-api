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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * With this annotation we can assign visible name to a class (step library or test data class) or a method
 * (test step) or an enum constant (test data enum value) or a package (test data group).
 * If the annotation presents on a class or method, then <em>ibello</em>
 * will log the name specified by the annotation when the class/method name is going to be
 * logged.
 * </p>
 * <p>
 * If the annotation is added to a method, the value may contain parameter substitution markers. A parameter substitution marker
 * is a 0-based index surrounded by <code>${</code> and <code>}</code>. These markers will
 * be replaced by string version of method parameters. For example, if the annotation's value
 * is <code>"${0}-${1}"</code>, then the method will replace <code>${0}</code> with the first
 * method parameter, and <code>${1}</code> with the second parameter. If method parameters are
 * <code>"a"</code> and <code>1</code>, then the result will be <code>"a-1"</code>.
 * </p>
 * <p>
 * Test data classes can be named with this annotation. The name will be displayed by the <em>ibello</em> UI only.
 * Packages containing test data classes may have this annotation too - in this case they define a group for
 * the test data classes.
 * </p>
 * <p>
 * Enum constants used by test data classes also can be named. The name will be displayed by te <em>ibello</em> UI only.
 * </p>
 * @author Kornél Simon
 * @see Description
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE, FIELD, PACKAGE})
public @interface Name {

	/**
	 * Visible name of a class (representing a step library) or a method (representing a test step).
	 * @return visible name of class or method
	 */
	String value();
}
