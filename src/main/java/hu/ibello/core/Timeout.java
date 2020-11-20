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

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation marks an enum class which defines constants for timeout values.
 * The name of those constants can be used in configuration files to assign numeric values to them.
 * The constants can be used in {@link TimeoutRelated#withTimeout(Enum)} method to select the right
 * timeout value for an action or expectation.
 * @author Kornél Simon
 *
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Timeout {

}
