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
package hu.ibello.actions;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.ibello.actions.ActionBuilder;

public class ActionBuilderTest {

	private ActionBuilder builder;
	
	@Before
	public void init() {
		builder = Mockito.mock(ActionBuilder.class, Mockito.CALLS_REAL_METHODS);
	}
	
	@Test
	public void setFile_uses_absolute_path() throws Exception {
		File file = new File("x");
		builder.setFile(file);
		Mockito.verify(builder).setFile(file.getAbsolutePath());
	}
}
