# Searching for element groups

It is possible to search and check several elements at once. To do this, we need to use the `WebElements` class, which is a `List<WebElement>` implementation.
The ibello system automatically creates and monitors lists of elements. If an element disappears or a new one appears, we do not need to recreate the field.

It is a common case that we want to perform an operation on some element of an element group. The example below shows that this can be easily done using the `get(index)` function.

In the example, we want to press the last of all the buttons labelled "Details".

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_last_button() {
    int lastButtonIndex = detailsButtons.size()-1;
    doWith(detailsButtons.get(lastButtonIndex)).click();
}
```

In the example below, all `checkbox` fields are clicked using a foreach.

```
@Find(by = By.CSS_SELECTOR, using = "input[type='checkbox']")
private WebElements checkboxes;

public void select_all_checkboxes() {
    for (WebElement checkbox : checkboxes) {
        doWith(checkbox).click();
    }
}
```

The previous example with a `for` loop:

```
@Find(by = By.CSS_SELECTOR, using = "input[type='checkbox']")
private WebElements checkboxes;

public void select_all_checkboxes() {
    for (int index = 0; index < checkboxes.size(); index++) {
        doWith(checkboxes.get(index)).click();
    }
}
```