# Error handling with `AssertionError`

During element verification, there may be a situation where the help provided by the `assume()` or `expect()` methods is not sufficient.
In such cases, it is a good idea to use `throw new AssertionError()`, which will cause the error to appear in the report and in the log.

In the example below, an error is generated when trying to press a button with a non-existent index. 

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_details_button_with_$_index(int buttonIndex) {
    if (detailsButtons.size() >= buttonIndex) {
        doWith(detailsButtons.get(buttonIndex)).click();
    } else {
        throw new AssertionError(String.format("Nem található %d indexű gomb!", buttonIndex));
    }
}
```

This feature also allows us to use other libraries in our tests for verification purposes. These are
generally throw `AssertionError` on failure - which ibello understands. The example above would look like this with
AssertJ library:

```java
public void click_details_button_with_$_index(int buttonIndex) {
	org.assertj.core.api.Assertions.assertThat(detailsButtons.size()).isGreaterThan(buttonIndex);
	doWith(detailsButtons.get(buttonIndex)).click();
}
```

The `ibello.use.soft.assertions` configuration parameter can be used to fine-tune `AssertionError`-based error handling.
If the parameter is set to `true`, test steps that fail with this exception will fail "softly". This means,
the test run is not aborted, only the test result is failed. If the configuration parameter is not specified, or
its value is set to `false`, the test failure will be "hard", the failure will also abort the current scenario run.
