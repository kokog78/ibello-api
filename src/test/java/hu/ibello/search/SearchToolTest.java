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
package hu.ibello.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.ibello.elements.WebElements;

public class SearchToolTest {

	private SearchTool tested;
	
	@Before
	public void init() {
		tested = Mockito.mock(SearchTool.class, Mockito.CALLS_REAL_METHODS);
	}
	
	@Test
	public void using_sets_default_by() throws Exception {
		tested.using("a", "b");
		Mockito.verify(tested).using(By.CSS_SELECTOR, "a", "b");
	}
	
	@Test
	public void asAncestorOf_sets_default_by() throws Exception {
		tested.asAncestorOf("a", "b");
		Mockito.verify(tested).asAncestorOf(By.CSS_SELECTOR, "a", "b");
	}
	
	@Test
	public void nth_returns_element_by_index() throws Exception {
		WebElements elements = Mockito.mock(WebElements.class);
		Mockito.when(tested.all()).thenReturn(elements);
		tested.nth(5);
		Mockito.verify(tested).all();
		Mockito.verify(elements).get(5);
	}
}
