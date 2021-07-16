# Verification with `checkThat()` method

The `checkThat()` method can be used to check a condition that returns a boolean type. This can be useful, for example, when you want to perform some action on the appearance of an element. However, it is very important that `checkThat()` does not wait. In practice, this means that if the change has not yet occurred at the time of the check, you will get an incorrect result.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

In the example above, we can see that after pressing `sendMessageButton`, we check the error message that pops up. However, if the message pops up only after the check is done, the condition is not met. A solution to this problem is to use a waiting-checking method (`assume()`, `expect()`). We can wait for a condition after which the subsequent `checkThat` test can be performed for sure.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    expectations().assume(resultPanel).toBe().displayed();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

Ebben az esetben a program az `expectations().assume(resultPanel).toBe().displayed()` sorban, előre definiált ideig várakozik. (Itt feltételezzük, hogy az `errorMessage` elem a `resultPanel` panelen jelenik meg.) Ha a feltétel teljesült, a következő `checkThat()` függvény is a helyes eredménnyel fog visszatérni. Amennyiben az `expectation()` kicsúszik a várakozási időből, a riportban hibaüzenet lesz látható.

In this case, the program waits in the `expectations().assume(resultPanel).toBe().displayed()` line for a predefined amount of time. (Here, we assume that the `errorMessage` element is displayed on the `resultPanel` panel.) If the condition is met, the next `checkThat()` function will also return the correct result. If the `expectation()` misses the wait time, an error message will be displayed in the report.
