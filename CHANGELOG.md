# ibello-api changelog

## 1.18.0

- New `PluginInitializer.regression()` method for regression operations on datasets
- New classes in `hu.ibello.functions` package implementing mathematical functions (constant, linear and logistic functions)
- New `PluginInitializer.graph()` method for drawing graphs
- New `PluginInitializer.table()` method for printing tables
- New `StepLibrary.csv()` and `PluginInitializer.csv()` methods for reading CSV files

## 1.17.0

2021-07-03

- New `Browser.getKind()` method
- New `Browser.getVersion()` method
- New `Browser.getCompositeId()` method
- New `Browser.getPageSource()` method
- New `FeatureHandler.existsFeatureFile()` method
- New `FeatureHandler.saveFeature(...)` method
- New `ExamplesHandler.existsExamplesFile()` method
- New `ExamplesHandler.saveExamples(...)` method
- New `Feature.namespace` field
- New `Feature.coverage` field

## 1.16.3

2021-01-29

- New `Browser.close()` method
- New `PluginInitializer.json()` and `StepLibrary.json()` methods for JSON transformation

## 1.16.2

2021-01-21

- New `PluginInitializer.testResults()` method to load test result

## 1.16.1

2021-01-17

- New `Scenario.getFlattenSteps()` and `ParentStep.getFlattenSteps()` methods
- New `Browser.getURL()` method which returns the address of the current page

## 1.16.0

2021-01-09

- New `PluginInitializer.features()` method to handle Cucumber features
- New `PluginInitializer.examples()` method to handle Cucumber examples

## 1.15.0

2020-11-26

- New `ElementRepository` class for separate elements from page objects
- New `HttpClient.url(URL)` and `RestClient.url(URL)` methods.
- New `PageObject.getMergedURL(...)` and `StepLibrary.getMergedURL(...)` methods.
- The `Browser.openURL(String)`, `HttpClient.url(String)` and `RestClient.url(String)` methods do not change the given absolute URL anymore.
- New `@Timeout` annotation which marks enum classes defining timeout constants.
- New `PluginInitializer.getMergedURL(String)` method
- New `IbelloTaskRunner` plugin interface for running tasks

## 1.14.0

2020-08-13

- The `@Name` annotation can be added to packages
- The `@Description` annotation can be added to packages
- New `Browser.findDownloadedFile(Pattern)` method for searching downloaded file
- New `TestDataTools` utility class for test data related tasks

## 1.13.1

2020-06-03

- New `Browser.findDownloadedFile(String)` and `Browser.getLatestDownloadedFile()` methods to access downloaded files.
- New `StepLibrary.httpClient()` method for HTTP API tests
- New `@Internal` annotation, with it we can exlude test step methods from listing

## 1.13.0

2020-04-05

- New `OutputHandler.recordCustomStep(...)` method
- New `StepLibrary.restClient()` method for REST API tests
- New `PageObject.tryWith(WebElement)` and `PageObject.tryWith(Browser)` methods for doing "soft" action
- New `By.BUTTON_CLASS` element search
- New `WebElementActionBuilder.setSelected(boolean)` method for selecting checkboxes and radio buttons
- The `@Name` annotation can be added to fields
- The `@Description` annotation can be added to fields

## 1.12.2

2019-11-17

- New `memory-usage` field in the the XML file containing the test results

## 1.12.1

2019-10-13

- Small change in the behavior of the `PageObject.repeat(Task)` method

## 1.12.0

2019-09-13

- New `PageObject.repeat(Task)` method which is able to repeat the given task multiple times
- New `WebElementHaveExpectations.textOrValue(...)` methods for verifying element's text or value
- New `testData().fromTxt(String)` method for loading textual test data
- New `testData().fromBinary(String)` method for loading binary test data
- New `testData().fromProperties(String).loadString()` and `testData().fromJson(Class).loadString()` methods for loading complex test data as text
- New `testData().fromProperties(String).openStream()` and `testData().fromJson(Class).openStream()` methods for loading complex test data as stream
- New `testData().fromProperties(String).withCharset(Charset)` and `testData().fromJson(Class).withCharset(Charset)` methods to specify the character set used during test data loading
- New `name-format` field for actions in the XML file containing the test results

## 1.11.0

2019-02-13

- New `@Description` annotation for adding short description to step libraries and step methods
- New `@Model` annotation for marking classes what are able to store loaded test data
- New `timeout-ms` attribute in the XML file containing the test results

## 1.10.1

- Test data files can be placed into sub-directories

## 1.10.0

2018-09-24

- Get information about elements with the help of `PageObject.checkThat(WebElement)` and `PageObject.get(WebElement)` methods
- Test data loading with `PageObject.testData()`, `StepLibrary.testData()` and `PluginInitializer.testData()` methods
- Create screenshot with the `PageObject.browser().saveScreenshot()` method
- The `Value` interface has some new conversion methods with default value argument
- New fields in the test result XML objects: `Screenshot.getWindow()`, `Screenshot.getFile()`

## 1.9.1

2018-06-20

- Parameter of `Plugin.initialize(...)` method is able to read the configuration
- New `ITestRun.getName()` method

## 1.9.0

2018-06-19

- New ibello plugin architecture, custom plugins can be added to test execution
- New `BrowserActionBuilder` class for alert window handling
- New `Browser.reload()` method which reloads the current URL
- New `Specification.version` property
- Name of step libraries and page objects (specified by `@Name` annotation) are contained by readable descriptions of test steps
- If a test step does not have `@Name` annotation, it's name contains `String`-type parameters with quotes

## 1.8.0

2018-05-28

- New `ActionBuilder.fireEvent(...)` for calling javascript events on elements
- New search methods: `By.PARTIAL_TEXT`, `By.PARTIAL_BUTTON_TEXT` és `By.PARTIAL_LABEL`
- New `Browser.localStorage()` és `Browser.sessionStorage()` methods for management of storages of the browser
- New `Browser.cookies()` method for management of cookies of the browser

## 1.7.1

2018-05-24

- Page objects and step libraries: `$` characters in method names will be replaced by parameters

## 1.7.0

2018-03-21

- New `Window` annotation for open new browser window (and tab)
- New relation-based element-search modifier: `RelationType.NEAREST_TO`
- New `Test` annotation which enables sorting and exclusion of test methods
- The project does not depend on `junit` package anymore

## 1.6.0

2018-02-03

- New interface for testing email communication: `FakeEmailServer`

## 1.5.0

2017-11-27

- New `ActionBuilder.selectOption(...)` methods to select an item from a dropdown list
- New `ActionBuilder.scrollTo(...)` method for scrolling
- New `WebElementHaveExpectations.selectedOption(...)` methods to verify the selected item in a dropdown list
- New `WebElementBeExpectations.readonly()` method to verify read-only fields
- New `SearchTool.nth(...)` method for index based search
- New `Specification.order` firld for sorting test classes
- New `Specification.includedTags` and `Specification.excludedTags` fields to turn on/off test classes

## 1.4.0

2017-11-15

- Test are executed in alphabetical order, based on the name of test classes.
- It is possible to add multiple `Frame` annotations to a single page object, in order to access HTML frames included by frames.

## 1.3.0

2017-11-13

- New `assume` methods in expectation engine for "soft" expectations
- `expectAny` and `expectAll` methods in expectation engine are deprecated
- `BrowserHaveExpectations.url(String)` method handles double asterisk (`**`)
- Element search based on `By.BUTTON_TEXT` finds elements where attribute `role` is `button`

## 1.2.0

2017-10-23

- New `Frame` annotation to access content in HTML frames

## 1.1.0

2017-10-14

## 1.0.0

2017-09-09
