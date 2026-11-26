package hu.ibello.plugins.progress;

import java.util.function.Function;

import hu.ibello.plugins.PluginException;

public interface ProgressReport {
	
	public ProgressReport withTitle(String title);
	
	public String getTitle();

	public ProgressReport withDescription(String description);
	
	public String getDescription();
	
	public ProgressReport withScoreCard(String title, int count);
	
	public default ProgressReport withScoreCard(String title, int count, int maxCount) {
		return withScoreCard(title, ScoreCardKind.NEUTRAL, count, maxCount);
	}
	
	public ProgressReport withScoreCard(String title, ScoreCardKind kind, int count, int maxCount);
	
	public default ProgressReport withScoreCard(String title, double percentage) {
		return withScoreCard(title, ScoreCardKind.NEUTRAL, percentage);
	}
	
	public ProgressReport withScoreCard(String title, ScoreCardKind kind, double percentage);
	
	public <E extends Enum<?>> ProgressReport registerStatus(E status, String htmlColor);
	
	public ProgressReport withExtraColumn(String columnTitle, int relativeWidth);

	public ProgressItem addItem(String id);
	
	public ProgressItem findItem(String id);
	
	public BugItem addBug(String id);
	
	public BugItem findBug(String id);
	
	public ProgressReport buildFunctionalityTree(Function<ProgressItem, Boolean> validator) throws PluginException;
	
	public ProgressReport buildRequirementTree(Function<ProgressItem, Boolean> validator) throws PluginException;
	
}
