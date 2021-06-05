package hu.ibello.bdd.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FeatureTest {

	@Test
	public void toString_should_concatenate_keyword_and_name() throws Exception {
		Feature feature = feature("Feature", "A");
		assertThat(feature.toString()).isEqualTo("Feature: A");
	}
	
	@Test
	public void toString_should_use_feature_keyword_by_default() throws Exception {
		Feature feature1 = feature(null, "A");
		assertThat(feature1.toString()).isEqualTo("Feature: A");
		Feature feature2 = feature("", "B");
		assertThat(feature2.toString()).isEqualTo("Feature: B");
	}
	
	private Feature feature(String keyword, String name) {
		Feature feature = new Feature();
		feature.setKeyword(keyword);
		feature.setName(name);
		return feature;
	}
}
