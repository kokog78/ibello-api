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
 * The <em>ibello</em> page object API.
 * <p>
 * In <em>ibello</em>, technical functions of a page are specified in a <em>page object</em>.
 * The page object is a class which extends {@link hu.ibello.pages.PageObject}.
 * </p>
 * <p>
 * All page objects are discovered and instantiated automatically by the injector.
 * It is enough to add a page object field to a step library class, it will be automatically
 * instantiated and initialized before any test method. The field can be private.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.pages;