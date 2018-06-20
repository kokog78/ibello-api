package hu.ibello.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public interface ITestRun {

	public String getName();
	
	public Date getStartTime();
	
	public String getBaseDirectory();
	
	public BrowserKind getBrowser();
	
	public boolean isHeadless();
	
	public long getDefaultTimeout();
	
	public WindowSize getWindowSize();
	
	public List<String> getTag();
}
