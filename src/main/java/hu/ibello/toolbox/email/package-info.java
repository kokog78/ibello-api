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
 * Fake email server API (as a part of the <em>ibello</em> toolbox).
 * <p>
 * Some programs are sending emails to recipients, and in some cases we want to verify that an email with some attributes was sent.
 * For this, we need to inject {@link hu.ibello.toolbox.email.FakeEmailServer} into a step library and use it to check the emails.
 * </p>
 * @author Kornél Simon
 */
package hu.ibello.toolbox.email;