package hu.ibello.data;

public interface JsonTestDataBuilder<T> {

	public JsonTestDataBuilder<T> withId(String testDataId);
	
	public T load();
	
}
