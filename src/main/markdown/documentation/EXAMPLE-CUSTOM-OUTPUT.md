# Display custom information on the report

Often, there may be a case where we want to add custom information to the test report. To do this, we can use the following methods.

## Displaying the result of a custom check

With the `output().recordCustomExpectation(String)` method, we can display a customized expectation in the report.

```
output().recordCustomExpectation(String.format("The %s is in the %s", name, item));
```

The above example will appear in the report in the following format:

`Expectation came true: The apple is in the basket`

## Display text related to a custom operation

The `output().recordCustomAction(String)` method can be used to display additional information in the report.

```
output().recordCustomAction("The index of the row: " + index);
```

The above example will appear in the report in the following format:

`The index of the row: 3`

## Display a custom test step

Like the previous example, `output().recordCustomStep(String, String)` is used to display additional information. The only difference is that in this case the text is displayed as a separate step in the report. This further increases the clarity of the report.

```
File fileJson = new File("example.json");
String text = new String(Files.readAllBytes(fileJson.toPath()));
output().recordCustomStep("JSON content", text);
```

In the example above, the contents of the JSON file will appear in the report below a "JSON content" test step.

```
JSON content
    {"field": "value"}
```