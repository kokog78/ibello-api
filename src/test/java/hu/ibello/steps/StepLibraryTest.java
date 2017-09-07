package hu.ibello.steps;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import hu.ibello.steps.StepLibrary;
import hu.ibello.steps.StepLibraryTool;
import hu.ibello.test.AbstractBaseTest;
import hu.ibello.test.MethodCaller;

public class StepLibraryTest extends AbstractBaseTest {

	private MySteps steps;
	
	@Before
	public void init() throws Exception {
		steps = new MySteps();
	}
	
	@Test
	public void test_method_calls() throws Exception {
		testMethodCalls(steps, StepLibraryTool.class, (tool) -> {
			try {
				setField(steps, "tool", tool);
			} catch (Exception e) {
				throw new AssertionError("Cannot set tool", e);
			}
		});
	}
	
	public class MySteps extends StepLibrary implements MethodCaller {
		
		public void callMethod(Method method, Object[] args) throws Exception {
			method.invoke(this, args);
		}
	}
}
