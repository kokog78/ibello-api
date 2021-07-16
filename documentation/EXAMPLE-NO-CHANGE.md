# Checking that no change has occurred

Ibello is good for checking changes on an HTML page with little coding. When using an `expect(...)` or `assume(...)` check, ibello
automatically waits for the change to occur that matches the condition. In some cases, however, we want to check that after an operation no change occurs. For example: a button can still be pressed after filling in a text field, no error message appears on the screen after pressing a button, ...
These types of checks are not easy. If the change does *not* happen at the moment of the check, but *might* happen a moment later, then
our check succeeds, but in fact the test should fail.

```java
doWith(saveButton).click();
expectations().expect(errorMessage).toNotBe().displayed();
// if the error message appears here, it is no longer noticed because the check was successful
```

One way to handle such a check is to add another one that would be explicitly included anyway. We need to think about the change from that
the user knows that the program has not frozen, and the operation has completed successfully. For example, if we want to save the data on the screen by pressing a button, the button should be disabled after the save is successful, or a text should appear in an appropriate text area and tell us
that the save was successful. If there is such a change, we need to check it first - ibello will automatically wait for the change to appear. After that
we can check with more certainty that something else has not changed.

```java
doWith(saveButton).click();
expectations().expect(saveButton).toNotBe().enabled(); // a gomb állapota megváltozik
expectations().expect(errorMessage).toNotBe().displayed(); //a hibaüzenet nem jelenik meg
```

The above method should work in most cases, because - from UX perspective - it is a good decision to provide feedback about the success of operations. There should be some change we can check.
