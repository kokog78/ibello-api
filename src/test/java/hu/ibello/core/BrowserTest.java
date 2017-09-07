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
