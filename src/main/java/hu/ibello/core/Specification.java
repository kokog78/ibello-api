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

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks test classes (<em>specifications</em>).
 * Only classes marked with this annotation will be executed as tests.
 * The execution order is based on the annotation's {@link #order()}
 * property and the canonical name of the class - see {@link Class#getCanonicalName()}.
 * @author Kornél Simon
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Specification {

	/**
	 * An integer value which is used for sorting test classes. The classes will be executed in the calculated order.
	 * Sorting of test classes with the same index is based on the class name.
	 * @return and index used for sorting test classes; default value is 0
	 */
	int order() default 0;
	
	/**
	 * Array of included tags. If this field is specified, then the test class will run only if one of the specified tags
	 * is available.
	 * @return array of included tags; default value is the empty array
	 */
	String[] includedTags() default {};
	
	/**
	 * Array of excluded tags. If this field is specified, then the test class will run only if none of the specified tags
	 * is available.
	 * @return array of excluded tags; default value is the empty array
	 */
	String[] excludedTags() default {};
	
	/**
	 * An optional version number of the specification, which is added to the test report.
	 * @return version number of the specification
	 */
	String version() default "";
}
