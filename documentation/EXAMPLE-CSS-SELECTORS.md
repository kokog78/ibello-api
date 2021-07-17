# Using CSS selectors

When looking for an element, it is very important to define the right conditions. In many cases, the built-in search criteria (`By.ID`, `By.CLASS_NAME`, etc.) are not sufficient. In such cases, we have the possibility to use CSS selectors.

The example below illustrates the use of the "attribute contains" selector.

Situation: there is an application that manages a stock of products. On the page there are three products and for each of them an input field where we can enter the available quantities. Each input field has a separate `id`, which are "prodQuantity1", "prodQuantity2", "prodQuantity3". In addition to these, the page also contains other input fields, but they have completely different `id` and perform different functions.

Task: set the quantity of each product to 10! 

```
@Find(by = By.ID, using = "prodQuantity1")
private WebElement quantityField1;

@Find(by = By.ID, using = "prodQuantity2")
private WebElement quantityField2;

@Find(by = By.ID, using = "prodQuantity3")
private WebElement quantityField3;

public void set_quantities_to_ten() {
    doWith(quantityField1).setValue("10");
    doWith(quantityField2).setValue("10");
    doWith(quantityField3).setValue("10");
}
```

In the example above, we created a `WebElement` for each input field containing quantities, and set their value to 10 in the `set_quantities_to_ten()` method. This is no big problem for three elements, but for many more input fields it is unnecessary work. The following example shows a simpler solution to this problem.

```
@Find(by = By.CSS_SELECTOR, using = "[id*=Quantity]")
private WebElements quantityInputFields;

public void set_quantities_to_ten() {
    for (WebElement quantityField : quantityInputFields) {
        doWith(quantityField).setValue("10");
    }
}
```

In this case, we collected all the input fields with the identifier **containing** the word "Quantity" and then stepped through them to set their values. 