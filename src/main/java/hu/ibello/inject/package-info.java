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
 * This package contains API elements for the Ibello dependency injection system.
 * <h1>Ibello Dependency Injection System</h1>
 * <p>
 * In <em>ibello</em>, all dependency injections are performed by the <em>injector</em>.
 * The injector discovers the injectable fields and handles their values.
 * Sometimes a new instance will be created, and sometimes a previously created
 * instance will be used again.
 * </p>
 * <p>
 * The dependency injection is based on annotations.
 * </p>
 * <ul>
 * <li>
 * The {@link hu.ibello.inject.Inject} annotation marks injectable fields in classes.
 * </li>
 * <li>
 * <p>
 * The {@link hu.ibello.inject.Injectable} annotation marks classes suitable for injection.
 * The value of the annotation is the <em>scope</em> of the injection.
 * It's one of the constants from {@link hu.ibello.inject.Scope} type.
 * During the injection, the injector tries to use a previously created instance <i>from the same scope</i>.
 * Eg. if the injected class is test-scoped, then only instance created during the same test method
 * execution is valid for injection. If there is no valid instance, then the injector creates a new one.
 * </p>
 * <p>
 * If the injected class does not have a scope annotation, then the injector tries to
 * determine the scope from it's superclasses. The scope will be inherited from the nearest superclass
 * which has a scope annotation. If none of the superclasses has scope annotation, then the class will
 * be considered as session-scoped.
 * </li>
 * </ul>
 * <p>
 * The test execution consists of different stages. These are:
 * </p>
 * <ul>
 * <li>Session: each thread defines it's own session. The session lasts until the thread is running.</li>
 * <li>Specification: contains all test runs in the same test class. The specification lasts while it's test methods are
 * not finished.</li>
 * <li>Test: valid only for the execution of a single test method.</li>
 * </ul>
 * <p>
 * Instances of session-scoped classes are valid only for a session. In a new session, whole new instances will be created.
 * Similarly, instances of specification-scoped classes are valid for a specification execution, and instances of
 * test-scoped classes are valid for a single test run. Singleton instances do not lose they validity, so a singleton class
 * has only one instance for the entire program run.
 * </p>
 * <p>
 * Not all injections can be performed successfully. If the scope of the parent class is bigger that the scope of the injected field,
 * the the injection is forbidden and will result a runtime exception. So you can't inject specification-scoped instances
 * into a session-scoped class.
 * </p>
 * <p>
 * The injected class should have a default constructor. The injector calls this constructor if a new instance is needed
 * for this class.
 * </p>
 * <p>
 * If the injected class implements the {@link Initializable} interface, and the injector creates a new instance,
 * then the {@link Initializable#initialize()} method will be called automatically on that instance.
 * </p>
 * <p>
 * During the injection these steps will be performed in the given order:
 * </p>
 * <ol>
 * <li>The injector determines if the field needs a new instance, or an existing instance should be used.</li>
 * <li>If an existing instance should be used, then the injector assings that instance to the field, and the injection process ends.</li>
 * <li>If a new instance should be created, then the injector creates that instance with the default constructor of the injected class.</li>
 * <li>The injector performs a full injection process on the new instance, discovers it's injectable fields and assigns values to them.</li>
 * <li>If the injected class implements the {@link Initializable} interface, then the {@link Initializable#initialize()} method will be called.</li>
 * <li>The injection process for that field ends.</li>
 * </ol>
 * @author Kornél Simon
 */
package hu.ibello.inject;