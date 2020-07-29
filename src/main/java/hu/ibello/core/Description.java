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

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * With this annotation we can assign visible description to a class (step library) or a method
 * (test step). The description is used by the graphical interface of ibello.
 * </p>
 * <p>
 * It is possible to add multiple annotations to a method or class. If this is the case, all the texts
 * specified in those annotations will be appended together as description.
 * </p>
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE, FIELD, PACKAGE})
@Repeatable(Descriptions.class)
public @interface Description {

	/**
	 * Visible description of a class (representing a step library) or a method (representing a test step).
	 * @return visible description of class or method
	 */
	String value();
}
