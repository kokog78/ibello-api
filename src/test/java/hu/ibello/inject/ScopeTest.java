package hu.ibello.inject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import hu.ibello.inject.Scope;

public class ScopeTest {

	@Test
	public void prototype_accepts() throws Exception {
		Scope parent = Scope.PROTOTYPE;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isTrue();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isTrue();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void test_scope_accepts() throws Exception {
		Scope parent = Scope.TEST;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isTrue();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isTrue();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void specification_scope_accepts() throws Exception {
		Scope parent = Scope.SPECIFICATION;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isFalse();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isTrue();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void session_scope_accepts() throws Exception {
		Scope parent = Scope.SESSION;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isFalse();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isFalse();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void steps_scope_accepts() throws Exception {
		Scope parent = Scope.STEPS;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isFalse();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isFalse();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void page_scope_accepts() throws Exception {
		Scope parent = Scope.PAGE;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isFalse();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isFalse();
		assertThat(parent.accepts(Scope.SESSION)).isTrue();
		assertThat(parent.accepts(Scope.STEPS)).isTrue();
		assertThat(parent.accepts(Scope.PAGE)).isTrue();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
	@Test
	public void singleton_accepts() throws Exception {
		Scope parent = Scope.SINGLETON;
		assertThat(parent.accepts(Scope.PROTOTYPE)).isTrue();
		assertThat(parent.accepts(Scope.TEST)).isFalse();
		assertThat(parent.accepts(Scope.SPECIFICATION)).isFalse();
		assertThat(parent.accepts(Scope.SESSION)).isFalse();
		assertThat(parent.accepts(Scope.STEPS)).isFalse();
		assertThat(parent.accepts(Scope.PAGE)).isFalse();
		assertThat(parent.accepts(Scope.SINGLETON)).isTrue();
	}
	
}
