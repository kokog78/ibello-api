package hu.ibello.pages;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import hu.ibello.pages.PageObject;
import hu.ibello.pages.PageObjectTool;
import hu.ibello.test.AbstractBaseTest;
import hu.ibello.test.MethodCaller;

public class PageObjectTest extends AbstractBaseTest {

	private MyPageObject page;
	
	@Before
	public void init() throws Exception {
		page = new MyPageObject();
	}
	
	@Test
	public void test_method_calls() throws Exception {
		testMethodCalls(page, PageObjectTool.class, (tool) -> {
			try {
				setField(page, "tool", tool);
			} catch (Exception e) {
				throw new AssertionError("Cannot set tool", e);
			}
		});
	}
	
	public class MyPageObject extends PageObject implements MethodCaller {
		
		public void callMethod(Method method, Object[] args) throws Exception {
			method.invoke(this, args);
		}
	}
	
}
