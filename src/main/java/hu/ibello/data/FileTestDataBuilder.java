package hu.ibello.data;

import java.io.File;

/**
 * 
 * @see TestDataBuilder#fromFile(String)
 * @author Kornél Simon
 *
 */
public interface FileTestDataBuilder extends TestDataBuilderBase {

	public File getFile();
	
}
