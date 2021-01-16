package hu.ibello.bdd.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class ScenarioTest {

	@Test
	public void getFlattenSteps_should_return_empty_list_if_scenario_is_empty() throws Exception {
		Scenario scenario = scenario();
		List<Step> steps = scenario.getFlattenSteps();
		assertThat(steps).isEmpty();
	}
	
	@Test
	public void getFlattenSteps_should_return_steps_without_children() throws Exception {
		Scenario scenario = scenario(step("A"), step("B"));
		List<Step> steps = scenario.getFlattenSteps();
		assertThat(steps).extracting(step -> step.getText()).containsExactly("A", "B");
	}
	
	@Test
	public void getFlattenSteps_should_collect_children() throws Exception {
		Scenario scenario = scenario(step("A", step("A1")), step("B", step("B1"), step("B2")));
		List<Step> steps = scenario.getFlattenSteps();
		assertThat(steps).extracting(step -> step.getText()).containsExactly("A1", "B1", "B2");
	}
	
	@Test
	public void getFlattenSteps_should_collect_parent_if_parameter_is_true() throws Exception {
		Scenario scenario = scenario(step("A", step("A1")), step("B", step("B1"), step("B2")));
		List<Step> steps = scenario.getFlattenSteps(true);
		assertThat(steps).extracting(step -> step.getText()).containsExactly("A", "A1", "B", "B1", "B2");
	}
	
	@Test
	public void getFlattenSteps_should_avoid_infinite_cycle_1() throws Exception {
		ParentStep step1 = step("A");
		ParentStep step2 = step("B");
		step1.getSteps().add(step2);
		step2.getSteps().add(step1);
		Scenario scenario = scenario(step1, step2);
		List<Step> steps = scenario.getFlattenSteps();
		assertThat(steps).extracting(step -> step.getText()).containsExactly("A", "B");
	}
	
	@Test
	public void getFlattenSteps_should_avoid_infinite_cycle_2() throws Exception {
		ParentStep step1 = step("A", step("A1"));
		ParentStep step2 = step("B", step("B1"));
		ParentStep step3 = step("C", step("C1"));
		step1.getSteps().add(step2);
		step2.getSteps().add(step3);
		step3.getSteps().add(step1);
		Scenario scenario = scenario(step1);
		List<Step> steps = scenario.getFlattenSteps();
		assertThat(steps).extracting(step -> step.getText()).containsExactly("A1", "B1", "C1", "A");
	}
	
	private Scenario scenario(ParentStep ... steps) {
		Scenario scenario = new Scenario();
		for (ParentStep step : steps) {
			scenario.getSteps().add(step);
		}
		return scenario;
	}
	
	private ParentStep step(String text, ParentStep ... steps) {
		ParentStep step = new ParentStep();
		step.setKeyword("Given");
		step.setKind(StepKind.GIVEN);
		step.setText(text);
		for (ParentStep child : steps) {
			step.getSteps().add(child);
		}
		return step;
	}
	
}
