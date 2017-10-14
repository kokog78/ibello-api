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

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.ibello.core.Browser;

public class BrowserTest {

	private Browser tested;
	
	@Before
	public void init() {
		tested = Mockito.mock(Browser.class, Mockito.CALLS_REAL_METHODS);
	}
	
	@Test(expected=NullPointerException.class)
	public void openURL_throws_exception_for_null() throws Exception {
		URL url = null;
		tested.openURL(url);
	}
	
	@Test
	public void openURL_uses_external_form() throws Exception {
		URL url = new URL("http://localhost/1");
		tested.openURL(url);
		Mockito.verify(tested).openURL("http://localhost/1");
	}
	
}
