# Difference between `moveTo` and `scrollTo`

The `moveTo()` method moves the mouse pointer to the center of an element represented by a `WebElement`. 

```
@Find(by = By.TEXT, using = "Example text")
private WebElement text;

public void move_to_example_text() {
	doWith(text).moveTo();
}
```

For the above example to work it is important that the 'text' `WebElement` is visible on the page. This does not mean that it has to be exactly in the field of view. If the "Example text" text can be brought into the field of view by scrolling the page, then the 'text' `WebElement` can be found and the `moveTo()` method will move the mouse pointer over the text.

There may be a case where the page has an inner panel with a scrollbar. If there is an element in this panel that is not currently visible, it will not be visible on the entire page - even if the panel itself is in the field of view.

Situation: there is a webpage we want to test. On the page there is an inner panel with a scrollbar that displays 5 elements at a time. In order to see other options below the first 5, we need to use the scrollbar on the panel. Suppose one of our test steps requires us to move the mouse pointer to the tenth item!

The code below shows a bad implementation.

```
@Find(by = By.TEXT, using = "text 10")
private WebElement textTen;

public void move_to_text_ten() {
	doWith(textTen).moveTo();
}
```

Since only the first 5 elements are visible in the panel by default, a search for 'textTen' `WebElement` will fail. For the search to succeed, we must first scroll the list until the text "text 10" is visible. To do this, we can use the `scrollTo()` method as follows.

```
@Find(by = By.ID, using = "select_list_1")
private WebElement selectList;

@Find(by = By.TEXT, using = "text 10")
private WebElement textTen;

public void scroll_to_text_in_list() {
    doWith(selectList).scrollTo(textTen);
}

public void move_to_text_ten() {
	doWith(textTen).moveTo();
}
```

In this example, the 'selectList' `WebElement` is actually the panel with the internal scrollbar. In the `doWith()` method, we select the panel, and then in the `.scrollTo(textTen)` method, we specify which element within it we want to scroll into the field of view. 