package hu.ibello.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.ibello.search.By;
import hu.ibello.search.SearchTool;

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
}
