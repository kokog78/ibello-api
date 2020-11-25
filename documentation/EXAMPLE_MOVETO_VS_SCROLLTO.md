# A `moveTo` és a `scrollTo` közötti különbség

A `moveTo()` metódus egy `WebElement` által képviselt elem közepére mozgatja az egérmutatót. 

```
@Find(by = By.TEXT, using = "Example text")
private WebElement text;

public void move_to_example_text() {
	doWith(text).moveTo();
}
```

Ahhoz, hogy a fenti példa működjön fontos, hogy a 'text' `WebElement` az oldalon látható legyen. Ez nem azt jelenti, hogy éppen a látómezőben kell lennie. Amennyiben az "Example text" szöveg az oldal görgetésével a látómezőbe tud kerülni, akkor a 'text' `WebElement` megtalálható és a `moveTo()` metódus a szöveg fölé viszi az egérmutatót.

Előfordulhat olyan eset, hogy az oldalon található egy belső, görgetősávval ellátott panel. Egy ilyen panel esetén, a panel egy olyan eleme, ami éppen nem látható a panelben, az a teljes oldalon sem látható hiába van benne a látómezőben. 

Szituáció: van egy tesztelendő webes felület. Az oldalon van egy belső, görgetősávval ellátott panel, ami egyszerre 5 elemet jelenít meg. Ahhoz, hogy lássuk az első 5 opción kívül a többit is, használnunk kell a panelen található görgetősávot. Az egyik teszt lépésünk megkívánja, hogy a tizedik elemre mozgassuk az egérmutatót.

Az alábbi kód egy rossz implementációt mutat.

```
@Find(by = By.TEXT, using = "text 10")
private WebElement textTen;

public void move_to_text_ten() {
	doWith(textTen).moveTo();
}
```

Mivel alapesetben csak az első 5 elem látszódik a listában ezért, a 'textTen' `WebElement` megkeresése sikertelen lesz. Ahhoz, hogy a keresés sikeres legyen először addig kell görgetni a listát, hogy a "text 10" szöveg láthatóvá váljon. Ehhez a `scrollTo()` metódust tudjuk alkalmazni, az alábbi módon.

```
@Find(by = By.ID, using = "select_list_1")
private WebElement selectList;

@Find(by = By.TEXT, using = "text 10")
private WebElement textTen;

public void scroll_to_text_in_list() {
    doWith(selectList).scrollTo(textTen);
}

public void move_to_text_ten() {
	doWith(textTen).moveTo();
}
```

Ebben a példában a 'selectList' `WebElement` tulajdonképpen a belső, görgetősávval rendelkező panel. A `doWith()` metódusban kijelöljük a panelt, majd a `.scrollTo(textTen)` metódusban megadjuk, hogy a listán belül melyik elemet szerenénk a látómezőbe görgetni.  