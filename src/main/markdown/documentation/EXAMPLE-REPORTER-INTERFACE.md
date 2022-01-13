# Implementing custom reporting

By implementing the `IbelloReporter` interface, we can implement custom reporting in ibello. The following example class logs the duration of the test run.

```java
import hu.ibello.model.ITestRun;
import hu.ibello.model.SpecElement;
import hu.ibello.model.TestRun;
import hu.ibello.plugins.IbelloReporter;
import hu.ibello.plugins.PluginException;
import hu.ibello.plugins.PluginInitializer;
import java.util.Arrays;

public class IbelloReporterTester implements IbelloReporter {
	
	public static final String PARAMETER_INITIALIZED = "reporter.initialized";
	public static final String PARAMETER_TESTRUN_STARTED = "reporter.initialized";
	public static final String PARAMETER_SPECIFICATIONS = "reporter.specifications";
	
	private PluginInitializer initializer;
	private long testRunStartTime;

	@Override
	public void initialize(PluginInitializer initializer) throws PluginException {
		initializer.info("Reporter initialized");
		initializer.setConfigurationValue(PARAMETER_INITIALIZED, true);
		this.initializer = initializer;
	}

	@Override
	public void testRunStarting(ITestRun tests) {
		testRunStartTime = System.nanoTime();
		initializer.info("Test run started");
		initializer.setConfigurationValue(PARAMETER_TESTRUN_STARTED, true);
	}

	@Override
	public void specificationFinished(SpecElement spec) {
		initializer.info("Specification finished: " + spec.getName());
		String[] specs = initializer.getConfigurationValue(PARAMETER_SPECIFICATIONS).toStringArray();
		if (specs == null) {
			specs = new String[] {spec.getName()};
		} else {
			specs = Arrays.copyOf(specs, specs.length + 1);
			specs[specs.length-1] = spec.getName();
		}
		initializer.setConfigurationValue(PARAMETER_SPECIFICATIONS, specs);
	}

	@Override
	public void testRunFinished(TestRun tests) {
		double testRunInSecond = (double) (System.nanoTime()-testRunStartTime)/1000000000;
		initializer.info("Test run finished in: " + testRunInSecond + " seconds");
	}

	@Override
	public void shutdown() throws PluginException {
		initializer.info("Reporter shutdown called");
	}

}
```

The result of the example after the test run:

`2021-12-13 22:58:00.903 INFO Ibello Reporter Tester: Test run finished in: 12.6588547 seconds`

Add the newly created reporter class to the `ibello.plugin` parameter in the `default.properties` file. For multiple plugin classes, list them separated by commas.

`ibello.plugin=hu.ibello.test.plugins.TestPlugin1, hu.ibello.test.plugins.IbelloReporterTester`