# Handling of windows and tabs

Browser windows and tabs other than the default can be accessed using the `@Window` annotation. The annotation can be placed on a page object or step library
type field. The annotation value is inherited by the referenced page objects that do not have a separate annotation.

The value of the annotation is the identifier (given by us) of the window/tab. A browser tab ID starts with a colon. (And a window without a colon.)

## Manage a newly opened tab

Situation: the application under test opens a new tab with a button click, and loads a page containing an input field.
The application runs in the default window, the new page opens here, on a new tab.

The page that opens the new tab:

```java
public class OpenerPage extends PageObject {

	@Find(by=By.ID, using="openNewTabButton")
	private WebElement openNewTabButton;

	public void open_new_tab() {
		doWith(openNewTabButton).click();
	}

}
```

Page object of the opened tab:
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

The step library with the two page objects:
```java
public class Steps extends StepLibrary {

	// runs in the default window, in the default tab
	private OpenerPage opener;
	
	// runs in a new tab opened in the default window
	@Window(":new-tab");
	private OpenedPage opened;
	
	public void expect_that_new_tab_can_be_opened() {
		opener.open_new_tab();
		opened.expect_that_page_is_opened();
		opened.expect_input_value_is("OK");
	}

}
```

## Run tests in two windows at once

Situation: the application to be tested consists of two parts. In the editor we can edit content, while in the viewer we can view the content. The two parts are available at different URLs. In addition, the editor loads slowly. We would like to test that when we change the content in the editor, it changes in the viewer as well. We would like to do this as quickly as possible, so we open two separate browser windows, one for the editor and one for the viewer.

The editor page:

```java
public class EditorPage extends PageObject {

	@Find(using="#header-field")
	private WebElement headerField;

	@Find(using="#footer-field")
	private WebElement footerField;

	@Find(using="#save")
	private WebElement saveButton;
	
	public void open() {
		browser().openURL("/editor");
	}

	public void set_header(String content) {
		doWith(headerField).setValue(content);
	}

	public void set_footer(String content) {
		doWith(footerField).setValue(content);
	}
	
	public void save() {
		doWith(saveButton).click();
	}

}
```

The viewer page:
```java
public class ViewerPage extends PageObject {

	@Find(using="#header")
	private WebElement header;
	
	@Find(using="#footer")
	private WebElement footer;

	public void open() {
		browser().openURL("/viewer");
	}

	public void expect_header_content_is(String content) {
		expectations().expect(header).toHave().text(content);
	}

	public void expect_footer_content_is(String content) {
		expectations().expect(footer).toHave().text(content);
	}

}
```

The step library with the two page objects:
```java
public class Steps extends StepLibrary {

	// runs in the default window
	private EditorPage editor;
	
	// runs in a new window
	@Window("viewer-window")
	private ViewerPage viewer;
	
	public void expect_that_header_and_footer_can_be_changed() {
		editor.open();
		
		viewer.open();
		viewer.expect_header_content_is("");
		viewer.expect_footer_content_is("");
		
		editor.set_header("Test Header");
		editor.save();
		
		viewer.open();
		viewer.expect_header_content_is("Test Header");
		viewer.expect_footer_content_is("");
		
		editor.set_footer("Test Footer");
		editor.save();
		
		viewer.open();
		viewer.expect_header_content_is("Test Header");
		viewer.expect_footer_content_is("Test Footer");
	}

}
```