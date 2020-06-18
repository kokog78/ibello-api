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

### Test-step library

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

### Page-definition class

Page-definition classes summarize a websites or a websites well-defined parts' technical functions, like clicking on a button, filling an input field or "drag and drop" operations. Furthermore, page-definitons contain methods, which check the status of the page, like visibility of elements or content of text fields.

Every page-definition is subtype of `PageObject`class. Ibelli initializes page-definitions automatically.

Browsers functions can be reached by page-definition, e.g. setting actual URL. For this function, we have to use `browser()` method. 

```java
public class LoginPage extends PageObject {
    
    public void open() {
        browser().openURL("/login.html");
    }
}
```

Page-definition could have special fields, which contains searching parameters of elements in the page. These elements have `WebElement`type. These fields are needed for operations with elements and check ups. Ibello initializes these fields automatically, if searching parameters are given in `@Find` annotations parameters. 

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

More than one elements can be searched. For this, we need to use `WebElements` class, which is an implementation of `List<WebElement>`. Ibello creates and monitors list of elements automatically and, if an element disappear or a new element shown  we don't need to create the field again.

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

Page-definitions method are shown in the log and the riport too. The shown name can be customize with `@Name` annotation. The page-definitions `@Name` annotation is the prefix of the methods name. 