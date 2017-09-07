package hu.ibello.test;

import java.lang.reflect.Method;

public interface MethodCaller {

	public void callMethod(Method method, Object[] args) throws Exception;
	
}
