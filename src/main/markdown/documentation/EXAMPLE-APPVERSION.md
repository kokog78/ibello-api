# **Specify the version number of the application to test**

It may be important to see the version number of the application being tested in the test run results. As of ibello version 1.20.0, we can do this in the following ways.

## By specifying the configuration parameter

The `default.properties` add the following line to the configuration file:

`ibello.application.version=5.5.50`

The next time you run the test, the version number will be included in the report.

## By entering from code

When entering from code, create a plugin package within the ibello folder structure. Then add a new class called `ApplicationVersionCollector.java` with the following content:
```java
import hu.ibello.core.Name;
import hu.ibello.plugins.Plugin;
import hu.ibello.plugins.PluginException;
import hu.ibello.plugins.PluginInitializer;

@Name("Application version collector")
public class ApplicationVersionCollector implements Plugin {

  @Override
  public void initialize(PluginInitializer initializer) throws PluginException {
      String version = "5.5.55";
      initializer.info("Application version: " + version);
      initializer.setConfigurationValue("ibello.application.version", version);
  }

  @Override
  public void shutdown() throws PluginException {
    // do nothing
  }
    
}
```

Add the newly created plugin class to the `ibello.plugin` parameter in the` default.properties` file. For multiple plugin classes, they are listed separated by commas.

`ibello.plugin = hu.ibello.test.plugins.TestPlugin1, hu.ibello.test.plugins.ApplicationVersionCollector`

The next time you run the test, the version number will be included in the report.
