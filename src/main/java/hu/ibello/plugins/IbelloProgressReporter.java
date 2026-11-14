package hu.ibello.plugins;

import hu.ibello.plugins.progress.ProgressReport;

public interface IbelloProgressReporter extends Plugin {

	public void generateReport(ProgressReport report) throws PluginException;
	
}
