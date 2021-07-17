# Using parameters when searching for elements

We can use parameter references in the `@Find` annotation's `using` property. These can be replaced by values later. The ibello system
will initialize these elements for us, but will only insert the parameters when we later use their values in the `WebElement.applyParameters`
or the `WebElements.applyParameters` method. The insertion will be done based on the indexes of the specified parameters, `${0}`
will be replaced by the value of the first parameter, `${1}` by the value of the second parameter, etc.

```java
@Find(by=By.BUTTON_TEXT, using="Perform ${0}/${1}")
private WebElement button;

public void clickOperationButton(String operation, int index) {
    doWith(button.applyParameters(operation, index)).click();
}
```

If the method in the example above is called with the parameters "A" and 2, the test will look for the button labeled "Perform A/2" and click it.

We can also insert configuration parameters into the selectors. These must be named using the same notation as before.

```java
@Find(by=By.BUTTON_TEXT, using="Perform ${button.text}")
private WebElement button;
```

Such a selector cannot be changed during a test run, because the values of the configuration parameters are decided before the tests are started.
However, the tags we specify affect which configuration files are loaded, and thus affect the values of the configuration parameters.
In this way, we can therefore modify the test run by specifying tags.

It is not only the `@Find` annotation selector where we can use parameter insertion. This also works for the `@Position` and `@Relation` annotations.

```java
@Find(by=By.BUTTON_TEXT, using="Download")
@Relation(type=RelationType.DESCENDANT_OF, using=".${0}")
private WebElement button;

public void click_$_download_button(String className) {
	doWith(button.applyParameters(className)).click();
}
```