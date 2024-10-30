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
package hu.ibello.script;

import hu.ibello.core.TimeoutRelated;

/**
 * With this interface one can execute JavaScript code in the browser window.
 * <p>
 * Normal script execution is synchronous, does not use timeout and can be performed with the {@link #execute(String, Object...)} method.
 * Asynchronous execution is possible with {@link #executeAsync(String, Object...)} method. In this case the script receives a callback
 * function which should be called when the operation is performed.
 */
public interface JavascriptExecutor extends TimeoutRelated<JavascriptExecutor> {
	
	/**
	 * Executes JavaScript in the browser window. The script
	 * fragment provided will be executed as the body of an anonymous function.
	 *
	 * <p>Within the script, use <code>document</code> to refer to the current document. Note that
	 * local variables will not be available once the script has finished executing, though global
	 * variables will persist.
	 *
	 * <p>If the script has a return value (i.e. if the script contains a <code>return</code>
	 * statement), then the following steps will be taken:
	 *
	 * <ul>
	 *   <li>For an HTML element, this method returns a WebElement
	 *   <li>For a decimal, a Double is returned
	 *   <li>For a non-decimal number, a Long is returned
	 *   <li>For a boolean, a Boolean is returned
	 *   <li>For all other cases, a String is returned.
	 *   <li>For an array, return a List&lt;Object&gt; with each object following the rules above. We
	 *       support nested lists.
	 *   <li>For a map, return a Map&lt;String, Object&gt; with values following the rules above.
	 *   <li>Unless the value is null or there is no return value, in which null is returned
	 * </ul>
	 *
	 * <p>Arguments must be a number, a boolean, a String, WebElement, or a List of any combination of
	 * the above. An exception will be thrown if the arguments do not meet these criteria. The
	 * arguments will be made available to the JavaScript via the "arguments" magic variable, as if
	 * the function were called via "Function.apply"
	 *
	 * @param script the JavaScript to execute
	 * @param args the arguments to the script. May be empty
	 * @return one of Boolean, Long, Double, String, List, Map or WebElement. Or null.
	 */
	Object execute(String script, Object ... args);
	
	/**
	 * Execute an asynchronous piece of JavaScript in the browser
	 * window. Unlike executing {@link #execute(String, Object...)},
	 * scripts executed with this method must explicitly signal they are finished by invoking the
	 * provided callback. This callback is always injected into the executed function as the last
	 * argument.
	 *
	 * <p>The first argument passed to the callback function will be used as the script's result. This
	 * value will be handled as follows:
	 *
	 * <ul>
	 *   <li>For an HTML element, this method returns a WebElement
	 *   <li>For a number, a Long is returned
	 *   <li>For a boolean, a Boolean is returned
	 *   <li>For all other cases, a String is returned.
	 *   <li>For an array, return a List&lt;Object&gt; with each object following the rules above. We
	 *       support nested lists.
	 *   <li>For a map, return a Map&lt;String, Object&gt; with values following the rules above.
	 *   <li>Unless the value is null or there is no return value, in which null is returned
	 * </ul>
	 *
	 * <p>The timeout for a script to be executed is the ibello's script execution timeout.
	 * It is possible to change this with the {@link #withTimeout(Enum)} or the {@link #withTimeout(String)}
	 * method call prior to this one.
	 *
	 * <p>Example: Synchronizing a test with an AJAX application:
	 *
	 * <pre>{@code
	 * JavascriptExecutor js = ...;
	 * js.withTimeout(Timeout.LOAD_WIDGET).executeAsync(
	 *     "var callback = arguments[arguments.length - 1];" +
	 *     "mailClient.getComposeWindowWidget().onload(callback);");
	 * }
	 * // here the compose mail widget is loaded</pre>
	 *
	 * <p>Script arguments must be a number, a boolean, a String, WebElement, or a List of any
	 * combination of the above. An exception will be thrown if the arguments do not meet these
	 * criteria. The arguments will be made available to the JavaScript via the "arguments" variable.
	 *
	 * @param script The JavaScript to execute.
	 * @param args The arguments to the script. May be empty.
	 * @return One of Boolean, Long, String, List, Map, WebElement, or null.
	 * @see #withTimeout(Enum)
	 * @see #withTimeout(String)
	 */
	Object executeAsync(String script, Object ... args);
	
}
