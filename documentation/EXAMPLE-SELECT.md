# HTML `select` element

Consider the following HTML code:
```html
<select id="example">
	<option value="" selected></option>
	<option value="1">First</option>
	<option value="2">Second</option>
</select>
```

This code describes a drop-down list where we can choose one of three values. The selected option can be blank, "First" or "Second".
Below is a summary of how to perform operations and checks with this element.

## Select option by displayed text

This is easy, since that's what the `selectOption` method is for. It displays the options available and then selects the one we need.

```java
@Find(using="#example")
private WebElement select;

public void selectOptionByText(String text) {
	doWith(select).selectOption(text);
}
```

Note: in our tests, we should use the visible properties of the elements, as they are used by the users. Therefore, it is not recommended to use
options (`value` attribute).

## Select option by index

We also use the `selectOption` method, to which we now pass the index of the option we are looking for. The index starts with 0, so the index of the first option is 0,
the second option has index 1, and so on.

```java
public void selectOptionByText(int index) {
	doWith(select).selectOption(index);
}
```

## Verify the selected text

The text of the selected option is the visible property that is most worth checking. There is a simple solution. We just have to look at the `select` element:

```java
public void expectSelectedText(String selectedText) {
	expectations().expect(select).toHave().selectedOption(selectedText);
}
```

Note: if neither option is selected, we can use an empty string or a `null` value for the check.

## Verify the selected value

This is also possible, although the value (the value of the `value` attribute) is less important for our tests, as it is not directly visible.
The `select` element should be examined:

```java
public void expectSelectedValue(String selectedValue) {
	expectations().expect(select).toHave().value(selectedValue);
}
```
