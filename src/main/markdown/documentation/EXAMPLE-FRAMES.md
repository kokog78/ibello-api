# Testing applications with HTML frames

Browsers (and browser drivers) handle HTML frames (`frame` or `iframe` elements) separately from the content outside them.
For ibello tests, this means that we always have to create a new page object for content inside a frame. We can create more than one,
but we cannot manage in-frame and out-of-frame content in the same page object.

The fact that a page object handles content within a frame is indicated by the annotation `@Frame`, specifying the search conditions for the frame.

```html
<html>	
	<body>
		...
		<iframe src="..."></iframe>
	</body>	
</html>
```

```java
@Frame(using="iframe")
public class FrameContentPage extends PageObject {

	...

}
```
Within the annotated page object, all element searches must be relative to the frame.

```html
<button>First</button>
<iframe src="...">
	<button>Second</button>
</iframe>
```

```java
@Frame(using="iframe")
public class FramedPage extends PageObject {

	@Find(using="button")
	private WebElements buttons;

}
```

In the example above, there will be only one item in the `buttons` item list - the one with the label "Second".

## Frames within frames

If there are additional frames within the frame, they should also be handled in separate page objects. In such a case, we have to specify multiple `@Frame` annotations. The second annotation will find the second frame within the first (outermost) frame, the third annotation will find the third frame within the second frame, and so on.

```html
<iframe src="...">
	...
	<iframe src="..."></iframe>
</iframe>
```

```java
@Frame(using="iframe")
@Frame(using="iframe")
public class SecondLevelFrameContentPage extends PageObject {

	...

}
```

## Managing similar content in different frames

There may be cases where we need to manage similar content in different frames.

```html
<iframe src="..." class="frame1">
	<button id="click-me">Click me!</button>
</iframe>
<iframe src="..." class="frame2">
	<button id="click-me">Click me!</button>
</iframe>
```

Since the `@Frame` annotation is added directly to the page object class, it cannot be changed. But even so, we do not necessarily need two identical
page object classes. We can create a "clean" page object without annotation, which describes the content, and then inherit from that the one we use for the frames, now with annotation.

```java
public class BasePage extends PageObject {

	@Find(using="#click-me")
	private WebElement button;

	public void clickButton() {
		doWith(button).click();
	}

}

@Frame(using=".frame1")
public class FirstPage extends BasePage {
}

@Frame(using=".frame2")
public class SecondPage extends BasePage {
}

```
In this way, the `FirstPage` and `SecondPage` classes will now handle the elements within their own frames.
