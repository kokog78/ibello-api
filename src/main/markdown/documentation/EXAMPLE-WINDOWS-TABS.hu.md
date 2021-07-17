# Ablakok és fülek kezelése

Az alapértelmezettől eltérő böngésző-ablakokat és füleket a `@Window` annotáció segítségével érhetünk el. Az annotációt oldal-leíró vagy tesztlépés-könyvtár
típusú mezőre lehet tenni. Az annotáció értéke öröklődik azokra a hivatkozott oldal-leírókra, amik nem rendelkeznek külön annotációval.

Az annotáció értéke az ablak/fül (általunk adott) azonosítója. Egy böngésző fül azonosítója kettősponttal kezdődik. (Egy ablaké pedig kettőspont nélkül.)

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

	// az alapértelmezett ablakban, az alapértelmezett fülön fut
	private OpenerPage opener;
	
	// az alapértelmezett ablakban megnyílt új fülön fut
	@Window(":new-tab");
	private OpenedPage opened;
	
	public void expect_that_new_tab_can_be_opened() {
		opener.open_new_tab();
		opened.expect_that_page_is_opened();
		opened.expect_input_value_is("OK");
	}

}
```

## Tesztek futtatása egyszerre két ablakban

Szituáció: a tesztelendő alkalmazás két részből áll össze. A szerkesztőben tartamat tudunk szerkeszteni, míg a megtekintőben a tartalmat tekinthetjük meg. A két rész különböző URL-eken
érhető el. A szerkesztő ráadásul lassan töltődik be. Szeretnénk letesztelni, hogy amikor a szerkesztőben átírjuk a tartalmat, akkor az a megtekintőben is megváltozik. Mindezt szeretnénk
minél gyorsabban végrehajtani, ezért két külön böngészőablakot nyitunk, az egyikbe a szerkesztőt, a másikba a megtekintőt töltjük be.

A szerkesztő oldal:
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

A megjelenítő oldal:
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

A tesztlépés-könyvtár a két oldal-leíróval:
```java
public class Steps extends StepLibrary {

	// az alapértelmezett ablakban fut
	private EditorPage editor;
	
	// egy új ablakban fut
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