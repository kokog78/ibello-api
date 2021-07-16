# How to use the `@Internal` annotation?

The `@Internal` annotation is used when we want to hide a method of a step library class in the ibello GUI.
In the interface used to edit gherkin features, it is possible to specify the test step we want to use from the
list of available test steps. We may not want to see certain steps in this list. For example, if a test step
must *always* be called before another step, and we do not want the script writer to have to keep this in mind, we can decide to hide this step instead, and have it called in another test step. If we remove irrelevant steps from the list of available test steps, we make the test design work easier.

A method marked with the annotation `@Internal` will still function as a test step, appearing in the test report and in the log.
Such methods can be called by other - test step - methods.

```
public void send_form() {
	fill_out_form();
    formPage.click_send_button();
    formPage.success_message_displayed();
}

@Internal
public void fill_out_form() {
    formPage.set_user_data();
    formPage.set_message();
}
```

If the "send_form" method in the example above is called by a test, we will see the following entries in the test report:

```
- send form
  - fill out form
    - set user data
    - set message
  - click send button
  - success message displayed
```

The `@Internal' annotation therefore has no effect on the test report.
