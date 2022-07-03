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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * With this annotation we can marks test step methods we want to include in performance measurement.
 * All steps marked with this annotation will participate in the Application Performance Index (APDEX) calculation,
 * after each test run. An optional group identifier can be specified for detailed analysis.
 * 
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface Performance {

	/**
	 * If specified, it divides the performance measurements into groups.
	 * Test steps with the same group identifier will be added to the same APDEX calculation.
	 * @return the identifiers of the measurement groups, separated by comma
	 */
	String group() default "";
	
}
