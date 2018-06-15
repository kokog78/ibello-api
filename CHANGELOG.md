# ibello-api changelog

## 1.9.0

- New ibello plugin architecture, custom plugins can be added to test execution
- Name of step libraries and page objects (specified by `@Name` annotation) are contained by readable descriptions of test steps
- If a test step does not have `@Name` annotation, it's name contains `String`-type parameters with quotes

## 1.8.0

- New `ActionBuilder.fireEvent(...)` for calling javascript events on elements
- New search methods: `By.PARTIAL_TEXT`, `By.PARTIAL_BUTTON_TEXT` és `By.PARTIAL_LABEL`
- New `Browser.localStorage()` és `Browser.sessionStorage()` methods for management of storages of the browser
- New `Browser.cookies()` method for management of cookies of the browser

## 1.7.1

- Page objects and step libraries: `$` characters in method names will be replaced by parameters

## 1.7.0

- New `Window` annotation for open new browser window (and tab)
- New relation-based element-search modifier: `RelationType.NEAREST_TO`
- New `Test` annotation which enables sorting and exclusion of test methods
- The project does not depend on `junit` package anymore

## 1.6.0

- New interface for testing email communication: `FakeEmailServer`

## 1.5.0

- New `ActionBuilder.selectOption(...)` methods to select an item from a dropdown list
- New `ActionBuilder.scrollTo(...)` method for scrolling
- New `WebElementHaveExpectations.selectedOption(...)` methods to verify the selected item in a dropdown list
- New `WebElementBeExpectations.readonly()` method to verify read-only fields
- New `SearchTool.nth(...)` method for index based search
- New `Specification.order` firld for sorting test classes
- New `Specification.includedTags` and `Specification.excludedTags` fields to turn on/off test classes

## 1.4.0

- Test are executed in alphabetical order, based on the name of test classes.
- It is possible to add multiple `Frame` annotations to a single page object, in order to access HTML frames included by frames.

## 1.3.0

- New `assume` methods in expectation engine for "soft" expectations
- `expectAny` and `expectAll` methods in expectation engine are deprecated
- `BrowserHaveExpectations.url(String)` method handles double asterisk (`**`)
- Element search based on `By.BUTTON_TEXT` finds elements where attribute `role` is `button`

## 1.2.0

- New `Frame` annotation to access content in HTML frames
