package hu.ibello.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface TestDataBuilderBase {

	/**
	 * Loads the test data from the target file.
	 * For this, the UTF-8 character set is used.
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return the loaded test data as {@link String}
	 */
	public String loadString();
	
	/**
	 * Opens a stream with the content of the test data.
	 * If the test data cannot be found then returns <code>null</code>.
	 * @return an opened stream or <code>null</code>
	 */
	public default InputStream openStream() throws IOException {
		String content = loadString();
		if (content != null) {
			return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
		} else {
			return null;
		}
	}
}
