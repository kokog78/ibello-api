package hu.ibello.data;

public interface TestDataBuilder {

	public <T> JsonTestDataBuilder<T> fromJson(Class<T> dataType);
	
	public PropertiesTestDataBuilder fromProperties(String fileNamePrefix);
	
}
