Tests have three layers in ibello framework. On the one hand, the *page-description* classes  summerize the pages' technical functions. On the other hand, test steps can be compiled by one or even more methods of the page-description class, which is put in a class, called *test-step-library class*. 

The business functions can be easily separated by this thee-layer structure, so the tests could be available from business perspective too. 
Furthermore, little changes in websites do not affect on tests, only the page-description classes are needed to be changed, so the tests are more future-proof.

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

###Running order of test classes###

Methods of test classes with `@Specification` annotation run in alphabetical order by default. It can be changed by give the index in the annotations parameter, which ibello can use for systematization:

```java
@Specification(order = 3)
public class RunLater {
	...
}
```

If there isn't given index parameter, it's default value is 0.  If there is more than one class with the same index they run in alphabetical order.

### Excluding test classes by labels###

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

