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
 * This package contains interfaces for test data handling.
 * <p>
 * Test data is something which is used in tests as an input or assertion value.
 * For example, if the test enters some text into a text field, then the text can be stored
 * as test data. On the other side, if a test expects that a field on the page contains a
 * specific text, then that text also can be stored as test data.
 * </p>
 * <p>
 * Test data can be stored in different formats, in files. The ibello is able to load the test
 * data into complex objects. Test data classes are simple POJOs and can be marked with the {@link hu.ibello.data.Model}
 * annotation.
 * </p>
 * <p>
 * The test data objects can be loaded with the {@link hu.ibello.data.TestDataBuilder} interface.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.data;