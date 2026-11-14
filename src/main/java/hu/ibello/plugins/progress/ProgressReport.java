package hu.ibello.plugins.progress;

import java.util.function.Function;

import hu.ibello.plugins.PluginException;

public interface ProgressReport {
	
	public ProgressReport withTitle(String title);
	
	public String getTitle();

	public ProgressReport withDescription(String description);
	
	public String getDescription();

	public ProgressReport withScoreCard(String title, int count);
	
	public ProgressReport withScoreCard(String title, int count, int maxCount);
	
	public ProgressReport withScoreCard(String title, double percentage);
	
	public <E extends Enum<?>> ProgressReport registerStatus(E status, String htmlColor);
	
	public ProgressItem addItem(String id);
	
	public ProgressItem findItem(String id);
	
	public ProgressReport buildFunctionalityTree(Function<ProgressItem, Boolean> validator) throws PluginException;
	
	public ProgressReport buildRequirementTree(Function<ProgressItem, Boolean> validator) throws PluginException;
	
}
