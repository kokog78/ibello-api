package hu.ibello.bdd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Internal utility class for step collection.
 * @author Kornél Simon
 */
class StepUtils {

	/**
     * Returns the list of the descendant steps, collected into a flat list.
     * If a child step has children, then instead of the original child it's children will be included in the result.
     * This method avoids infinite loops caused by recursive step references.
     * @param steps the first level steps
     * @param includeParentSteps should the parent steps be included in the result?
     * @return all the descendant steps in a flat list
     */
	public static List<Step> getFlattenSteps(List<ParentStep> steps, boolean includeParentSteps) {
		List<Step> result = new ArrayList<>();
		if (steps != null && !steps.isEmpty()) {
			flattenSteps(steps, result, includeParentSteps, new HashSet<>());
		}
		return result;
	}
	
	private static void flattenSteps(List<ParentStep> source, List<Step> dest, boolean includeParentSteps, Set<ParentStep> visitedSteps) {
		for (ParentStep step : source) {
			if (step.hasStep() && !visitedSteps.contains(step)) {
				if (includeParentSteps) {
					dest.add(step);
				}
				visitedSteps.add(step);
				flattenSteps(step.getSteps(), dest, includeParentSteps, visitedSteps);
				visitedSteps.remove(step);
			} else {
				dest.add(step);
			}
		}
	}
	
}
