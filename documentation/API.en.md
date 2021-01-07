# The ibello API

Tests have three layers in ibello framework. On the one hand, the *page-definition* classes  summerize the pages' technical functions. On the other hand, test steps can be compiled by one or even more methods of the page-definition class, which is put in a class, called *test-step-library class*. 

The business functions can be easily separated by this thee-layer structure, so the tests could be available from business perspective too. 
Furthermore, little changes in websites do not affect on tests, only the page-definition classes are needed to be changed, so the tests are more future-proof.

## The test class

Test classes are marked by `@Specification` annotation, from where ibello framework recognize, that the class has test methods. Test methods are marked by `@Test` annotation.

Test-step-libraries are used in test classes. The simpliest way to reach a test-step-library is to give a field with the desired type to the test class, which field can be even private.
An instance of a test-step-library will be automatically created, which can be used on the test methods:

```java
@Specification
public class LoginTests {

    // a test-step library instace is created automatically!
    private LoginSteps steps;
    
    @Test
    public void successful_login() {
        steps.given_that_login_page_is_opened();
        steps.when_i_login_with_valid_credentials();
        steps.then_i_get_to_the_welcome_page();
        steps.then_i_see_that_valid_user_is_logged_in();
        steps.then_i_see_the_standard_operations();
    }
}
```

In this example the instructions of the called test methods doesn't contain technical details. The tehcninal informations, like how we know, if we are in the login page or how we can log in with valid user are in the test-step library's methods.

### Running order of test classes

Methods of test classes with `@Specification` annotation run in alphabetical order by default. It can be changed by give the index in the annotations parameter, which ibello can use for systematization:

```java
@Specification(order = 3)
public class RunLater {
	...
}
```

If there isn't given index parameter, it's default value is 0.  If there is more than one class with the same index they run in alphabetical order.

### Excluding test classes by labels

Test classes can be excluded by `Specification.includedTags` and `Speficifation.excludedTags`parameters. We can specify labels, with which the actual test run can be described.

The labels in the `includedTags`parameter turn on the test class, which means that for run the test class, one of the listed labels have to be in the actual label group. Examples:

```java
@Specification(includedTags = "longRunning")
public class LongRunningTests{
    ...
}

@Specification(includedTags = {"smoke", "parallel"})
public class ParallelSmokeTests{
    ...
}
```

With the `excludedTags`parameter works the opposite way. If there is at least one listed label which is in the actual label group too the test class will not run.

```java
@Specification(excludedTags = "ci")
public class LocalTests {
    ...
}
```

### Order of running of test methods

Test methods in the class run in alphabetical order. It can be changed be the `@Test` annotations `order` parameter. The order parameters default value is 0.

```java
@Test(order = 3)
public void ordered_test_method() {
    ...
}
```

### Excluding test methods by labels

Test methods could be also excluded with `@Test`annotations `includedTags` and `excludedTags`parameters. The parameters in `includedTags` turn on and the parameters in `excludedTags` turn off the test method.

### Naming test methods

Names of the test methods are shown in logs and riports about accomplishments. Ibello tries make the shown names more readable (converts the "camelcase" és "snake case" type method names), but it isn't enough in some case. To solve this problem methods could get new shown name with the `@Name` annotation.

```java
@Test
@Name("Login with valid user")
public void t0_login() {
    ...
}

@Test
@Name("Check basic functions")
public void t1_check_basic_functions(){
    ...
}
```

Methods can get short descriptions with `@Description`annotation. Values of the annotations will be shown in one text.

## Test-step library

Test step library is from `StepLibrary`upper class. Every public method of it is a test step. It could be useful if methods name are meaningful. There are three methods in good practice:

- preparative methods are responsible for starting point in the browser.
- executive methods execute the operation which is needed to be tested.
- control methods monitor the created conditions and throw exception if it isn't the desired one.

Test step libraries methods use page-definitions. Page-definitions could be (private) fields, ibello will create the instances automatically.  Test-step libraries can refer other test-step libraries too by (private) fields with the corresponding type.

```java
public class LoginSteps extends StepLibrary {

    // an instance of page-definition is created automatically
    private LoginPage loginPage;
    
    // it is also created automatically
    private WelcomePage welcomePage;
    
    public void login_page_is_opened() {
        loginPage.open();
    }
    
    public void i_login_with_valid_credentials() {
        loginPage.setUsername("testuser");
        loginPage.setPassword("testpwd");
        loginPage.clickLoginButton();
    }
    
    public void i_get_to_the_welcome_page() {
        welcomePage.expectOpened();
    }
    
    public void i_see_that_valid_user_is_logged_in() {
        welcomePage.expectCurrentUser("testuser");
    }
    
    public void i_see_the_standard_operations() {
        welcomePage.expectOperations("Data Recording", "Queries", "Settings");
    }
}
```

Self-created classes can be also injected in test-step libraries. It could be greate if the username and the password didn't need to be given directly in the example above. There would be a tool-class through which these datas can be available, so if these datas or the methods of queries  will be changed in the future, the changing of the tool-class will be enough. Let's make a tool-class:

```java
public class UserData {

    private final String VALID_USERNAME = "testuser";
    private final String VALID_PASSWORD = "testpwd";

    public String getValidUsername() {
        return VALID_USERNAME;
    }
    
    public void getValidPassword() {
        return VALID_PASSWORD;
    }
}
```

Let's put an instance of our new tool-class (UserData) in the test-step library! `@Inject` annotation is needed to be used in this case, so ibello knows, that the class which is needed to be inject is self-created.

```Java
public class LoginSteps extends StepLibrary {
    
    @Inject
    private UserData userData;
    
    public void i_login_with_valid_credentials() {
        loginPage.setUsername(userData.getValidUsername());
        loginPage.setPassword(userData.getValidPassword());
        loginPage.clickLoginButton();
    }
}
```

### Naming test steps

Ibello always logs test step-libraries' public methods calling, so their names are shown in the test riport.

Expressions from methods name are shown in the log and the riport. E.g.: from `i_login_with_valid_credentials`to `I Login with Valid Credentials`. If this operation is needed to be changed, we can add a `@Name`annotation to the method, which has only one attribution: the name which have to be printed. Also parameters could be printed in the name. Their place can be indicated with `${0}`, `${1`, etc. strings. Parameters can be used without `@Name` annotation. In this case their place is indicated with `$`. (Type `String`parameters have to be given in  quation marks.)

```java
@Name("Open element ${0}")
public void openItem(int index) { ... }

public void push_$_button(String title) { ... }
```

If test step-library class also has `@Name` annotation, the given string added to all of the test steps' names as prefix. This prefix called namespace. 

Test steps name is "Home Page: Open Page" in the example below.

```java
@Name("Home Page")
public class HomePageSteps extends StepLibrary {

	public void open_page() {
	}
}
```

## Page-definition class

Page-definition classes summarize a websites or a websites well-defined parts' technical functions, like clicking on a button, filling an input field or "drag and drop" operations. Furthermore, page-definitons contain methods, which check the status of the page, like visibility of elements or content of text fields.

Every page-definition is a subtype of `PageObject` class. Ibello initializes page-definitions automatically.

Browsers functions can be reached by page-definition, e.g.: setting actual URL. For this function, we have to use `browser()` method. 

```java
public class LoginPage extends PageObject {
    
    public void open() {
        browser().openURL("/login.html");
    }
}
```

Page-definition could have special fields, which contains searching parameters of elements in the page. These elements have `WebElement `type. These fields are needed for operations with elements and check ups. Ibello initializes these fields automatically if searching parameters are given in `@Find` annotations parameters. 

For operations with elements, we have to use `doWith` method, which has only one parameter called `WebElement`. 

```java
public class LoginPage extends PageObject {

    // the field is created automatically, it contains elements data, which has "username" id
    @Find(using="#username")
    private WebElement usernameField;
    
    // the field is created automatically, it contains elements data, which has "password" id
    @Find(by=By.ID, using="password")
    private WebElement passwordField;
    
    // the field is created automatically, it contains the buttons or links of "Log in" data
    @Find(by=By.BUTTON_TEXT, using="Log in")
    private WebElement loginButton;
    
    public void setUsername(String username) {
        doWith(usernameField).setValue(username);
    }
    
    public void setPassword(String password) {
        doWith(passwordField).setValue(password);
    }
    
    public void clickLoginButton() {
        doWith(loginButton).click();
    }
}
```

For check ups page-definitions `expectations()` method is needed to be called. The returned object has some `expect(...)` methods, which get the object which needed to be checked up as parameter. It could be a browser instance which is returned by `browser()` or an instance of `WebElement`. 

```java
public class WelcomePage extends PageObject {

    @Find("#user-name")
    private WebElement userField;

    public void expectOpened() {
        expectations().expect(browser()).toHave().url("/welcome.html");
    }
    
    public void expectCurrentUser(String username) {
        expectations().expect(userField).toHave().value(username);
    }
}
```

More than one elements can be searched. For this, we need to use `WebElements` class, which is an implementation of `List<WebElement>`. Ibello creates and monitors list of elements automatically and if an element disappear or a new element shown  we don't need to create the field again.

```java
public class WelcomePage extends PageObject {

    @Find(by=By.CLASS_NAME, using="operation-button")
    private WebElements operations;
    
    public void expectOperations(String ... buttonTexts) {
        expectations().expect(operations).toHave().size(buttonTexts.length);
        for (int i = 0; i < buttonTexts.length; i++) {
            expectations().expect(operations.get(i)).toHave().text(buttonTexts[i]);
        }
    }
}
```

Page-definitions method are shown in the log and the riport too. The shown name can be customize with `@Name` annotation. The input parameter in page-definitions `@Name` annotation will be the prefix of the methods name. 

## Reading configuration parameters

Ibello configuration parameters can be read by test step-libraries and page-definition classes, with `getConfigurationValue(String)` method.  This method have only one argument, which is the configuration parameters name and its return type is `Value`. The parameters can be converted to any java type with `Value`.  Conversion can be made by `Value` class' methods, e.g.: `Value.toString()`, `Value.toDouble()`, `Value.toStringArray()`. If the configuration parameter doesn't exist, the `getConfigurationValue` returns with a `Value` object anyway, but in this case, the value is `null`, e.g.:

```java
String username = getConfigurationValue("current.user").toString();
Integer backendPort = getConfigurationValue("backend.port").toInteger();
File parameterFile = getConfigurationValue("backend.parameter.file").toFile();
```

Methods, which start with "to" usually have a pair, which can get an input parameter as default. This parameter will be used, if the configuration parameter doesn't exist, e.g.:

```java
String username = getConfigurationValue("current.user").toString("default");
Integer backendPort = getConfigurationValue("backend.port").toInteger(0);
```

## Searching elements

With Ibello we have the chance to search one or more element. The searching can be static or dinamic. This function is only available through page-definitions.

### Searching static elements

A search is static if the page-definition's `WebElement` or `WebElements`type field has `@Find` annotation, so ibello initializes this field automatically.  `@Find` annotation could have two attributions. 
`by` attribution can get values from `By`enum's set of values, and it gives the method of searching. This attribution's default value is `By.CSS_SELECTOR` - if `by` doesn't get a value, it will be the method of searching. 
`using` attribution is the parameter of searching method, it depends on it. 

The table below shows the searching attributes and methods.

| Searching method                 | Value of `by`            | Value of `using`                                             |
| -------------------------------- | ------------------------ | ------------------------------------------------------------ |
| by CSS selector                  | `By.CSS_SELECTOR`        | CSS selector                                                 |
| by `id` attributes               | `By.ID`                  | the searched element's `id` attribute                        |
| by `name` attributes             | `By.NAME`                | the searched element's `name` attribute                      |
| by type of element               | `By.TAG_NAME`            | the searched element's type name                             |
| by CSS class                     | `By.CLASS_NAME`          | the name of the searched element's CSS class                 |
| by button text                   | `By.BUTTON_TEXT`         | the subtitle of the button which has the searched function   |
| gomb részleges felirata szerinti | `By.PARTIAL_BUTTON_TEXT` | a part of the subtitle of the button which has the searched function |
| by label                         | `By.LABEL`               | the subtitle of the searched element's label                 |
| by partial label                 | `By.PARTIAL_LABEL`       | a part of the subtitle of the searched element's label       |
| by content                       | `By.TEXT`                | the content of the searched label                            |
| by partial content               | `By.PARTIAL_TEXT`        | a part of the searched label's content                       |
| by class of the button           | `By.BUTTON_CLASS`        | the CSS class of the button which has the searched function  |

`By.BUTTON_TEXT`, `By.PARTIAL_BUTTON_TEXT` and `By.BUTTON_class` find `button`, `input type=button`, `input type=reset`and `input type=image` type elements,  links and elements, which can have button like functions. 

`By.LABEL` and `By.PARTIAL_LABEL` firstly search `label` type elements by content, then they find the searched element by `for` attribute. If `label` doesn't have `for` attributes, but its parents or grandparents have one `input`, `textarea` or `select` type element, then it will be the searched element. 

If one element is searched, but the system find more than one elements, it will return the first one. 

Parameters can be used in`@Find` annotation's `using` attribute. These parameters can be replaced by values. Ibello initializes these elements and the elements will be find later, when we give values to the parameters by `WebElement.applyParameters` or `WebElements.applyParameters` methods. The replacement happens by indexes of parameters. `${0}` is the first parameter, `${1}` is the second parameter, etc.

```java
@Find(by=By.BUTTON_TEXT, using="${0}/${1} executing")
private WebElement button;

public void clickOperationButton(String operation, int index) {
    doWith(button.applyParameters(operation, index)).click();
}
```

Ibello make it possible to find elements by relations with other elements.  These search condition can be given by annotations next to `@Find` annotation. More than one conditions can be given by these annotations In this case, they work together.

Searching by position works by `@Position` annotation. `@Position` gives search conditions to a reference element (`by` and `using` attribution), for which we would like to give the position of the searched element. Furthermore, this position can be given by `type` attribute. In the exam below we try to find a "field" element with CSS class which is left from "Save" button.

```java
@Position(type=PositionType.LEFT_FROM, by=By.BUTTON_TEXT, using="Save")
@Find(using=".field")
private WebElement usernameField;
```

Value of `type` is from the range of `PositionType` enum.

| ``PositionType`` | Meaning                                               |
| ---------------- | ----------------------------------------------------- |
| `ROW`            | Elements in the same row as the reference element.    |
| `COLUMN`         | Elements in the same column as the reference element. |
| `LEFT_FROM`      | Elements left from reference element.                 |
| `RIGHT_FROM`     | Elements right from reference element.                |
| `ABOVE`          | Elements above reference element.                     |
| `BELOW`          | Elements below reference element.                     |

Searching by DOM structure can work with `@Relation` annotation. Reference element can be given by `by` and `using` like with `@Position` annotation. In this case value of `type` is from `RelationType` enum. It show the relationship of searhed element and reference element. 

| ``RelationType`` | Meaning                                                  |
| ---------------- | -------------------------------------------------------- |
| `DESCENDANT_OF`  | The searched element is descendant of reference element. |
| `ANCESTOR_OF`    | The searched element is ancestor of reference element.   |

In ibello the default value of `type` is `RelationType.DESCENDANT_OF`. Place of the searhed element can be given by more parent elements. In the exam below we try to find a `form` element through an element which has "modal-window" CSS class, than inside it element with "main-fields" class, than inside it the element which has name attribute "username".

```java
@Relation(using=".modal-window")
@Relation(using="form")
@Relation(using=".main-fields")
@Find(by=By.NAME, using="username")


private WebElement usernameField;
```

### Searching dinamic elements

During dinamic element search we use classic java methods. There are two ways to do this. First, every `WebElement` instance has a `find()` method, which we can start a new searchint the instance of `WebElement`. Second, we can start a search in the whole page by `PageObject.find()` method. Both `find()` methods give back an instance of `SearchTool`, which with searching parameters can be given  continuously. Searching can be closed by one of these methods: `first()`, `nth(int)` or `all()`. `first()`  gives back the first found element. `nth(int)` gives back the element by index. These methods are equivalent. `all()` gives back all of the found elements.

Examples from previous chapters:

```java
// searching parameters
WebElement button = find()
    .using(By.BUTTON_TEXT, "${0}/${1} végrehajtása", "save", 1)
    .first();
// pozíció szerinti keresés
WebElement usernameField = find()
    .using(".field")
    .leftFrom( find().using(By.BUTTON_TEXT, "Mentés").first() )
    .first();
// DOM struktúra szerinti keresés
usernameField = find()
    .using(".modal-window")
    .first().find()
        .using("form")
        .first().find()
            .using(".main-fields")
            .first().find()
                .using(By.NAME, "username")
                .first();
```

### Handling HTML frames

Some webpages still use `iframe` element. `iframe` element contains another page's HTML code, which is inserted in the parent page's defined area. Without specified information element searching can't see in the   content of`iframe`, because content of `iframe` counts as an independent HTML page. 

We can search element inside `iframe` if we add a `@Frame` annotation to the page-definition class, where searching parameters for `iframe` can be given. Every seacrhing of (dinamic and static) elements will be in `iframe` in this case.

```java
@Frame(by=By.ID, using="my-frame")
public class FramedPage extends PageObject {
    ...
}
```

It is recommended to identify `iframe` by `id`. 

```java
@Frame(using="#my-frame")
```

If the page has only one `iframe` the name of the type is enough.

```java
@Frame(using="iframe")
```

`iframe` elements' contents in `iframe` can be reached if we add more `Frame` annotations to a page-definition class.  The first annotation contains the outermost frame's data, the second contains the inner frame and so on. 

```java
@Frame(using="#parent-frame")
@Frame(using="#child-frame")
public class ChildFramedPage extends PageObject {
    ...
}
```

### Handling timeouts

Ibello is used for testing webapplications. The application to be tested is run in a browser - tests automatise it. Automatization is an asynchronous, because commands from test code and its affections aren't linked with java process which make tests running. So the result of the command execution can be expected in the near future, but the exact time can't be known. In these case it's a standard practice that the process, which make the command run waits, until it finds out the command was successful or unsuccessful. It is important because if the process doesn't wait and make another command run, it would make a competitive situation between the commands' execution, so its outcome is doubtful. These competitive situations are have to be avoided, because it's an expected operation in tests, that if we run and run them again. they give the same result. 

E.g.:

We would like to click on a link, which with the content of the page changes. The test code runs the click command so the browser execute it. Than the browser start to load and display the new page, which will be loaded in the near future (asynchronous process).

If the new page's URL is shown in the browser's address bar or the new page's content is shown in the browser, we know the clicking was successful. If the link directed to another page - because program error, than another page was loaded and was shown in the browser.

The best practice to hande this case is if the test code waits until the conditions about the new page's display is met. But if there is program error these conditions will never met, and we need to know that in time - so we have to specify a deadline. If the conditions don't met until this deadline we can assume that there is an error, and the test has to fail. 

But what deadline should we choose? Some operations need more time than others. If, in the previous example, a long counting process starts by clicking in the browser the usual timeout time wouldn't be right. So timeout need to be determined individually. 

Ibello - unlike other frameworks - doesn't have the possibility to set timeouts directly. Every timeout value has a text ID or az enum constant. Timeout can be referred by this way in the test code. Ibello reads concrete time - measured in seconds from configuration files. So if we would like to change the test environment by increase the time of timeout we just need to change the configuration file and don't need to change the test code. 

Ibello define some timeout by default.

To use default timeout we need to use "default", which is (if we don't change it in the configuration files) 5 sec. In most cases, if other value isn't given it is used. 

If we load a new URL directly (with the page-definition class' `browser().openURL(...)` method) ibello tries to wait until the new page is loaded. For this it uses timeout called "page.load", which has 10 seconds as default value. If during this time the page isn't loaded the test running continous without error (but if other test cases are based on the loaded page, they can fail).

In dinamic pages, where content made by javascript code, it is a common case, that after the operation the content isn't available, because the scripts run in the page are working. Ibello can solve this case by check if there is any change in the page. We have the possibility to wait until changes done, before test running continous. There is a timeout for this waiting, called "page.refresh", which as the default value as default timeout. 

We can define other timeouts. Best solution for it is to make a java enum. Constants of the enum will identify timeouts. We can give these constants during executions of operations. The concrete value can be given in configuration files of ibello, in seconds. 

## Operations with elements

Operations with elements works by `doWith(WebElement)` methods in page-definition classes. From the returned object we can call other required methods. 

```java
WebElement usernameField = find().using("#user-name").first();
// setting the found field's value
doWith(usernameField).setValue("testuser");
```

When we call an operation, it's not sure, that the required element is available in the browser, so every operation has an implicit timeout - that is the time limit while ibello tries to execute the operation. If during this limit the operation can't be executed the test fails and the it count as a fail. This waiting time has the same value as default timeout, but it can be changed by `withTimeout()` method. The parameter is the value of timeout as text or enum constant.

```java
// setting waiting time by own Timeouts.MEDIUM constants
doWith(usernameField).withTimeout(Timeouts.MEDIUM).setValue("testuser");
```

With `withPageRefreshWait()` method ibello waits until every changes in the page happened or the set time limit expired ("page.refresh" timeout). It can be useful in cases when we know an operation makes changes - e.g. directing to another page or delete elements from DOM. If time limit expire while the page refreshing isn't done the test running without error.

```java
// it waits after mouse clicking until the page is refreshed
doWith(button).withPageRefreshWait().click();
```

`doWith(...)` method stops test running, if it failed. If we would like to avoid it (we would like it to run in the case is failure), we have to use `tryWith(...)` method. It is like `doWith(...)` method from every other aspect.

### Mouse operations

| Methods                     | Description                                                  |
| --------------------------- | ------------------------------------------------------------ |
| `click()`                   | Left-click by mouse to the center of the element.            |
| `contextClick()`            | Right-click by mouse to the center of the element.           |
| `doubleClick()`             | Double mouse click to the center of the element.             |
| `selectOption(...)`         | Choose option with the given subtitle from drop-down list.   |
| `moveTo()`                  | Move cursor the the center of the element.                   |
| `dragAndDropTo(WebElement)` | Move element to another element with "Drag and drop" operation. |
| `scrollTo(WebElement)`      | Scroll element to make seen another element given as parameter. |
| `setSelected(boolean)`      | Check or uncheck a checkbox.                                 |

`scrollTo(WebElement)` operation can be used in the case, when a container elem has scroll bars. It is a widespread practice that in webapplications just a part of the page is scrollable (a container). In a container child elements can be made seen with `doWith(container).scrollTo(child element)` method chain. 

### Keyboard operations

To choose keyboard operations we need to use `sendKeys()` method. This method could have any number of `CharSequence` type parameters, which are sent as they are typed. These characters can be given az a `String` instance. 

```java
doWith(usernameField).sendKeys("testuser");
```

Also, special characters can be sent. Page-definition's `keys()` method can be used for this, which has constants for this aim. 

```java
doWith(usernameField).sendKeys(keys().HOME);
```

If we want to send characters with `CTRL`, `ALT` or `SHIFT` the first parameter of `sendKeys` need to have a modifier.

```java
doWith(usernameField).sendKeys(keys().CONTROL(), "a");
```

### Other operations

| Method             | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| `setValue(String)` | Set the value of `input` or `textarea` fields.               |
| `setFile(String)`  | Set the value of `input type = file` fields to a given file. |
| `submit()`         | Send the content of `form` type element.                     |
| `fireEvent(...)`   | Call a javascript event in an element.                       |

## Object operations of browser

In page-definition class browser object can be get by `browser()`, with which we can do operations in the browser controlled by tests. 

These methods are simple method callings.

### Open the page and refresh

Actually opened URL can be set by `browser().openURL(...)`. Firstly, it set the URL, then it waits the elements of the page are loaded. If 