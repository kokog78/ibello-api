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
