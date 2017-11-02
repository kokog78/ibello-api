package hu.ibello.expect;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ExpectationBuilderTest {

	private ExpectationBuilder builder;
	
	@Before
	public void init() {
		builder = Mockito.mock(ExpectationBuilder.class, Mockito.CALLS_REAL_METHODS);
	}
	
	@Test
	public void expectAll_calls_all() throws Exception {
		Runnable arg = new Runnable() {
			@Override
			public void run() {
			}
		};
		builder.expectAll(arg);
		Mockito.verify(builder).all(arg);
	}
	
	@Test
	public void expectAny_calls_any() throws Exception {
		Runnable arg = new Runnable() {
			@Override
			public void run() {
			}
		};
		builder.expectAny(arg);
		Mockito.verify(builder).any(arg);
	}
	
}
