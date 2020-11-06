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
package hu.ibello.inject;

/**
 * <p>
 * This enum specifies the <em>scope</em> of a dependency injection. During the injection, the injector
 * should decide that it will use a previously created value, of will create a whole new instance.
 * It is allowed to use existing values only from the injection's current scope. If there is no valid value for the
 * injection, a new instance will be created.
 * </p>
 * <p>
 * These are the scopes:
 * </p>
 * <ul>
 * <li>PROTOTYPE: if the injected class is in this scope, then every new request will result a new instance.</li>
 * <li>TEST: this scope is valid while the current test method runs.</li>
 * <li>SPECIFICATION: this scope is valid while the test class (<em>specification</em>) is unchanged. </li>
 * <li>SESSION: this scope means a java thread.</li>
 * <li>SINGLETON: this scopes will be not invalidated, all instances created in it will be persisted for
 * the whole test execution.</li>
 * <li>PAGE: classes marked with this scope should be handled as session-scoped, but after the "normal"
 * injection some other special (page object-related) injections should be performed on them.</li>
 * <li>STEPS: classes marked with this scope should be handled as session-scoped, but after the "normal"
 * injection some other special (step library-related) injections should be performed on them.</li>
 * </ul>
 * <p>
 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
 * </p>
 * @author Kornél Simon
 */
public enum Scope {

	/**
	 * Marks a prototype-scoped class with {@link Injectable} annotation.
	 * Prototype scope means that every time when an instance of the marked class needs to be injected, a new instance will be created.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	PROTOTYPE,
	
	/**
	 * Marks a test-scoped class with {@link Injectable} annotation.
	 * Test scope means that during a test method execution the same instance is used.
	 * Instances are not shared between test executions.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	TEST,

	/**
	 * Marks a specification-scoped class with {@link Injectable} annotation.
	 * Specification scope means that during the execution of a specification (a test class) the same instance is used.
	 * Instances are not shared between specification executions.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	SPECIFICATION,

	/**
	 * Marks a session-scoped class with {@link Injectable} annotation.
	 * Session scope means that during a session (in a single thread) the same instance is used.
	 * Instances are not shared between sessions.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	SESSION,
	
	/**
	 * Marks a singleton class with {@link Injectable} annotation.
	 * Singleton scope means that during the whole test execution the same instance is used.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	SINGLETON,
	
	/**
	 * Marks a page object class with {@link Injectable} annotation.
	 * The marked class will be in session scope. It means that during a session (in a single thread) the same instance is used.
	 * Instances are not shared between sessions.
	 * Additionally the class is considered as a page object, and some special injections are performed on every instances.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	PAGE,

	/**
	 * Marks an element repository class with {@link Injectable} annotation.
	 * The marked class will be in session scope. It means that during a session (in a single thread) the same instance is used.
	 * Instances are not shared between sessions.
	 * Additionally the class is considered as an element repository, and some special injections are performed on every instances.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	ELEMENTS,

	/**
	 * Marks a step library class with {@link Injectable} annotation.
	 * The marked class will be in session scope. It means that during a session (in a single thread) the same instance is used.
	 * Instances are not shared between sessions.
	 * Additionally the class is considered as a step library, and some special injections are performed on every instances.
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 */
	STEPS;
	
	private int getLevel() {
		switch (this) {
		case PROTOTYPE:
			return 0;
		case TEST:
			return 1;
		case SPECIFICATION:
			return 2;
		case SESSION:
		case PAGE:
		case ELEMENTS:
		case STEPS:
			return 3;
		case SINGLETON:
			return 4;
		default:
			return -1;
		}
	}
	
	/**
	 * <p>
	 * Checks if this scope accepts the given scope as child.
	 * </p>
	 * <p>
	 * Imagine that you have a class marked with {@link Injectable} annotation, with the current scope.
	 * This class has an injected field. The injected class is also marked with {@link Injectable} annotation,
	 * but with the given scope. If this method returns false, then the field cannot be injected because it's scope
	 * does not fit into this scope.
	 * </p>
	 * <p>
	 * Example: you cannot inject a test-scoped field into a singleton class, because instance in test scope are valid
	 * only during a single test run, while singleton instances are valid for the entire execution.
	 * </p>
	 * <p>
	 * <i>About the <em>ibello</em> dependency injection system, see {@link hu.ibello.inject}.</i>
	 * </p>
	 * @param childScope the scope which will be verified as child scope
	 * @return <code>true</code> if instances from <code>childScope</code> can be injected into this scope, <code>false</code> if not
	 */
	public boolean accepts(Scope childScope) {
		if (childScope==PROTOTYPE) {
			return true;
		}
		return childScope.getLevel() >= getLevel();
	}
}
