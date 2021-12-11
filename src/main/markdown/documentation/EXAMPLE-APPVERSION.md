# **Setting / providing version number of the application tested**

When looking back at the results of the tests ran, it might be important to see what was the version number of the tested application at hand. It is now possible from version 1.20 of ibello. We will have several options to set this:

* With the help of the *ibello.application.version=* in the project library, in the default.properties file. 
* By specifying/adding the plugin within the code, then within the default.properties file.

I will show the *first method* first, then, following this, the *second method.* In a given ibello project, we have to find the default.properties file in the folder structure. We can find it here: *ibello project folder\ibello\config\default.properties*. Following this, we open the file with any editor and we add a new row as shown below: 

`ibello.application.version=desired version of the application`

Let’s save these settings, and when we run the tests of the project set as above, the version number will be visible in the .*html*, *.xml* report and on the ibello platform as well.



In case we would like to set the version number from the code, we will have to create a plugins folder (package) within the ibello folder structure, if we don’t have one yet. So a folder like *ibello project folder/src/main/java/**whatever**/plugins*. We should then create here a new java class, called *ApplicationVersionCollector.java*. It is best to create it from an IDE, so that the java class created would already include the package access path. Let’s insert the code below:

```java
package my plugin package access

import hu.ibello.core.Name;
import hu.ibello.plugins.Plugin;
import hu.ibello.plugins.PluginException;
import hu.ibello.plugins.PluginInitializer;

@Name("Application version collector")
public class ApplicationVersionCollector implements Plugin {

  @Override
  public void initialize(PluginInitializer initializer) throws PluginException {
      String version = "version number";
      initializer.info("Application version: " + version);
      initializer.setConfigurationValue("ibello.application.version", version);
  }

  @Override
  public void shutdown() throws PluginException {
    // do nothing
  }
}
```

then type in the version number that we would like to see in the logs here:

 ` String version = "version number";`

Now all we have to do is to add the ApplicationVersionCollector plugin to the default.properties file. We can do it as follows: we open the *ibello project folder\ibello\config\default.properties* with an editor. If we don’t have a row there starting with *ibello.plugin,* let’s add it to a new row (if there is one already, we should add it again separated by a comma). Type in the plugin access pathway (in case of an ibello acceptance-test, hu.ibello.test.plugins.ApplicationVersionCollector). So, in case of more than one plugins, the row of the acceptance-test plugin `ibello.plugin` shall look like this:

`ibello.plugin=hu.ibello.test.plugins.TestPlugin2,hu.ibello.test.plugins.TaskTestPlugin,hu.ibello.test.plugins.TaskTestPlugin2,hu.ibello.test.plugins.ApplicationVersionCollector`

Let’s save the configuration file. Following this, the version number set will be included in the tests.