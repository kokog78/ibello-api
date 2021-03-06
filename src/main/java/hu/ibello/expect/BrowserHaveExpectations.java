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
package hu.ibello.expect;

import java.util.regex.Pattern;

/**
 * Instance of this interface contains methods to create and execute expectations
 * about the browser.
 * <p>
 * <i>About the <em>ibello</em> expectation engine, see {@link hu.ibello.expect}.</i>
 * </p>
 * @author Kornél Simon
 *
 */
public interface BrowserHaveExpectations {

	/**
	 * Runs an expectation which successes when the current URL matches a pattern.
	 * The pattern is created from the argument. If the method was called after
	 * {@link BrowserExpectationBuilder#toNotHave()}, then the outcome of the expectation is
	 * the opposite: if the current URL matches the pattern, then it will fail.
	 * <p>
	 * The argument may or may not contain a protocol. If there is no protocol in it,
	 * then the pattern will match <code>http</code> and <code>https</code> protocols
	 * in the current URL.
	 * </p>
	 * <p>
	 * The argument can be a relative URL, starting with <code>/</code>. In this case,
	 * <code>http</code> and <code>https</code> protocols, any host and any port in the current URL
	 * will be matched by the pattern.
	 * </p>
	 * <p>
	 * The argument may contain <code>*</code> and <code>?</code> wildchars.
	 * <code>*</code> means any path element, <code>?</code> means any character (except
	 * <code>/</code>). The double asterisk (<code>**</code>) means any characters
	 * (including <code>/</code>).
	 * </p>
	 * <p>
	 * If the <em>ibello</em> configuration contains the <code>ibello.url.base</code> property,
	 * then it's value will override the protocol, host and port in the argument.
	 * </p>
	 * @param url URL or URL pattern
	 */
	void url(String url);
	
	/**
	 * Runs an expectation which successes when the current URL matches the given regular
	 * expression. If the method was called after
	 * {@link BrowserExpectationBuilder#toNotHave()}, then the outcome of the expectation is
	 * the opposite: if the current URL matches the pattern, then it will fail.
	 * @param pattern a regular expression
	 */
	void url(Pattern pattern);
}
