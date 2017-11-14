# ibello-api changelog

## 1.2.0

- New Frame annotation to access content in HTML frames

## 1.3.0

- New `assume` methods in expectation engine for "soft" expectations
- `expectAny` and `expectAll` methods in expectation engine are deprecated
- `BrowserHaveExpectations.url(String)` method handles double asterisk (`**`)
- Element search based on `By.BUTTON_TEXT` finds elements where attribute `role` is `button`

## 1.3.1

- Test are executed in alphabetical order, based on the name of test classes.
