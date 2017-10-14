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
/**
 * The <em>ibello</em> step library API.
 * <p>
 * In <em>ibello</em>, business-level test steps are specified in <em>step libraries</em>.
 * The step library is a class which extends {@link hu.ibello.steps.StepLibrary}.
 * </p>
 * <p>
 * All step libraries are discovered and instantiated automatically by the injector.
 * It is enough to add a step library field to a test class, it will be automatically
 * instantiated and initialized before any test method. The field can be private.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.steps;