# ibello-api changelog

## 1.2.0

- New Frame annotation to access content in HTML frames

## 1.3.0

- New `assume` methods in expectation engine for "soft" expectations
- `expectAny` and `expectAll` methods in expectation engine are deprecated
- `BrowserHaveExpectations.url(String)` method handles double asterisk (`**`)
- Element search based on `By.BUTTON_TEXT` finds elements where attribute `role` is `button`

## 1.4.0

- Test are executed in alphabetical order, based on the name of test classes.
- It is possible to add multiple `Frame` annotations to a single page object, in order to access HTML frames included by frames.

## 1.5.0

- New `ActionBuilder.selectOption(...)` methods to select an item from a dropdown list
- New `WebElementHaveExpectations.selectedOption(...)` methods to verify the selected item in a dropdown list
- New `WebElementBeExpectations.readonly()` method to verify read-only fields
