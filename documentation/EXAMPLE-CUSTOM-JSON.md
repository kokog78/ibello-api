# Custom JSON serialization

In some cases, we want to serialize or deserialize JSON objects in tests that require a unique solution.

For example, consider the following class:

```java
public class MyObject {
	MyInterface field;
}
```

The type of `field` field is an interface:

```java
public interface MyInterface {
}
```

There are two implementations of this interface:

```java
public class MyField1 implements MyInterface {
	int value1;
}
```

and

```java
public class MyField2 implements MyInterface {
	long value2;
}
```

If we want to read an object of type MyObject from a JSON in a step library, we get an error because we cannot create an object of type MyInterface. 

```java
public class MySteps extends StepLibrary {

	public void read_json() {
		String json = "{\"value1\": 1}";
		MyObject object = json().fromJson(json, MyObject.class); // this throws an exception
	}
}
```

It is necessary to teach ibello how to handle MyInterface type items. To do this, we need to register two classes that serialize
and deserialize these types of values. We can do this, for example, by creating an ibello plugin.

```java
public class MyPlugin implements Plugin {

	@Override
	public void initialize(PluginInitializer initializer) throws PluginException {
		try {
			initializer.json().registerDeserializer(new JsonTypeDeserializer<MyInterface>() {
				@Override
				public Class<MyInterface> getType() {
					return MyInterface.class;
				}
				@Override
				public MyInterface deserialize(String source, JsonDeserializer context) throws TransformerException {
					MyInterface result;
					if (source.indexOf("\"value1\"") > 0) {
						result = context.fromJson(source, MyField1.class);
					} else {
						result = context.fromJson(source, MyField2.class);
					}
					return result;
				}
			});
			initializer.json().registerSerializer(new JsonTypeSerializer<MyInterface>() {
				@Override
				public Class<MyInterface> getType() {
					return MyInterface.class;
				}
				@Override
				public String serialize(MyInterface source, JsonSerializer context) throws TransformerException {
					return context.toJson(source);
				}
			});
		} catch (Exception ex) {
			throw new PluginException("Cannot register JSON processor classes for MyInterface.", ex);
		}
	}

	@Override
	public void shutdown() throws PluginException {
	}
	
}
```

The `deserialize` method of the class used for deserialization obtains the JSON fragment that applies to the MyInterface type.
Somehow we have to decide whether to read a MyField1 or MyField2 object from this.
In the example, we simply look at which class-specific field name the JSON snippet contains.
Of course, other solutions are possible as well.

Serialization is easier in this case. The `serialize` method gets the MyInterface object to be handled,
which we know is actually MyField1 or MyField2 type.
Here it is enough to use only the `context.toJson` method offered by ibello, it will automatically perform the serialization according to the specific type.

The MyPlugin class in the example above still needs to be entered in the ibello configuration, such as the "default.properties" file:

```properties
ibello.plugin=MyPlugin
```

After that, ibello will automatically load the plugin before the test runs, which will create custom JSON handling classes.
Thus, the code snippet previously presented in the step library will no longer throw an exception.
