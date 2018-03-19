# Ablakok és fülek kezelése

## Újonnan megnyílt fül kezelése

Szituáció: a tesztelendő alkalmazás gombnyomásra új fület nyit, amibe betölt egy beviteli mezőt tartalmazó oldalt.
Az alkalmazás az alapértelmezett ablakban fut, az új oldal is itt nyílik meg, egy új fülön.

Az új fület megnyitó oldal:
```java
public class OpenerPage extends PageObject {

	@Find(by=By.ID, using="openNewTabButton")
	private WebElement openNewTabButton;

	public void open_new_tab() {
		doWith(openNewTabButton).click();
	}

}
```

A megnyílt fül oldal-leírója:
```java
public class OpenedPage extends PageObject {

	@Find(by=By.ID, using="checkedInputField")
	private WebElement checkedInputField;

	public void expect_that_page_is_opened() {
		expectations().expect(browser()).toBe().open();
	}
	
	public void expect_input_value_is(String value) {
		expectations().expect(checkedInputField).toHave().value(value);
	}

}
```

A tesztlépés-könyvtár a két oldal-leíróval:
```java
public class Steps extends StepLibrary {

	private OpenerPage opener;
	
	@Window(":new-tab");
	private OpenedPage opened;
	
	public void execute() {
		opener.open_new_tab();
		opened.expect_that_page_is_opened();
		opened.expect_input_value_is("OK");
	}

}
```
