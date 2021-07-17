# Appearance and disappearance of element groups

It can be a common task to wait for certain dynamic changes during the test run, during which completely new element groups or pages appear (or disappear) in the
the screen. Let's briefly look at the possibilities of this.

## Waiting for dynamic changes

If the change is fast and continuous enough, it may be sufficient to set a simple wait for the action,
that triggers the change. For example, if it is a button click, the following method can be used:

```java
public void openNewPage() {
	doWith(newPageOpenerButton).withPageRefreshWait().click();
}
```

In the example above, we use the `withPageRefreshWait` method of the operation, which causes ibello to wait for any page loads and dynamic changes  after the operation has run. However, ibello does not wait forever, if it finds that there has been no change for a long time (a few tenths of a second), it assumes there has been no more dynamic changes and the tests can continue to run. The same happens when the available time (the value of the `ibello.timeout.page.refresh`
configuration parameter, in seconds) runs out and the page is still changing. (No error is generated in this case.) This may require a
solution to better handle long waits, slow opening pages, slow displaying groups of elements.

## Waiting for a group of elements to appear

The technique is to create a separate page object for the page (or group of elements) to be opened. In it, we create a method that checks whether the page
(or group of elements) has appeared correctly. It also waits for the time until it is displayed. To do this, we first need to decide what the condition is that should be met until the
page (or group of elements) can be considered as opened. This is often the appearance of a single element:

```java
public class SlowPage extends PageObject{
	
	@Find(using="#page-header")
	private WebElement indicator;
	
	public void expectOpened() {
		expectations().expect(indicator).toBe().displayed();
	}
	
}
```

In extreme cases, we can examine the appearance of several elements, even in no particular order:

```java
public class SlowPage extends PageObject{
	
	@Find(using="#page-header")
	private WebElement indicator;
	
	@Find(using="#pahe-shadow")
	private WebElement shadow;
	
	public void expectOpened() {
		expectations().all(() -> {
			expectations().expect(indicator).toBe().displayed();
			expectations().expect(shadow).toBe().displayed();
		});
	}
	
}
```

## Custom waiting time

We can also assign a separate waiting time to the check, which is then defined larger in the configuration file:

```properties
ibello.timeout.OPEN_PAGE = 30
```

For waiting times, we should define our own enum class. The names of the constants in this class are the names that appear in the configuration:

```java
public enum MyTimeouts {
	OPEN_PAGE,
	CLOSE_PAGE,
	...
}
```

The waiting time is simply inserted into the check:

```java
public void expectOpened() {
	expectations().withTimeout(MyTimeouts.OPEN_PAGE).expect(indicator).toBe().displayed();
}
```

For a complex check, in order for the wait time to apply to the entire (complex) check, the `withTimeout` method must be called at the outermost position:

```java
public void expectOpened() {
	expectations().withTimeout(MyTimeouts.OPEN_PAGE).all(() -> {
		...
	});
}
```

## Wait for a group of elements to disappear

The disappearance of a page (or group of elements) can be handled in exactly the same way:

```java
public void expectClosed() {
	expectations().withTimeout(MyTimeouts.CLOSE_PAGE).expect(indicator).toNotBe().displayed();
}
```

In modern web applications, modal dialogue boxes appear in front of a full-screen shading. The shading neutralises mouse actions,
so we cannot click next to/behind the dialog. If we have such a dialog, we don't necessarily need to create an `expectClosed` method. It is likely that when the dialog is closed, the shadow is the last to disappear, so our tests will not operate on the elements behind the shadow while a chunk of the dialog
is still on the screen. However, this may not be the case and the shadow may disappear first. Then the elements behind the dialogue are released, and as the tests are much more
faster than a user, they may click on something that should not be clicked on before the dialogue actually disappears. If we want to be sure, a complex check should test for the disappearance of both the element specific to the dialog and the shadow:

```java
public void expectClosed() {
	expectations().withTimeout(MyTimeouts.CLOSE_PAGE).all(() -> {
		expectations().expect(indicator).toNotBe().displayed();
		expectations().expect(shadow).toNotBe().displayed();
	});
}

```

## Putting all together

Based on the above, an example of the acceptance of a slow message box in a test step library might look like this:

```java
public class MySteps extends StepLibrary {

	private MessageBoxPage messageBox;
	
	public void acceptMessage() {
		messageBox.expectOpened();
		messageBox.accept();
		messageBox.expectClosed();
	}

}
```
