# Példák

Az alábbi példák néhány összetettebb feladat megvalósítását mutatják be.

## Elemcsoportok megjelenése és eltűnése

Gyakori feladat lehet, hogy a tesztek futása közben ki kell várni bizonyos dinamikus változásokat, amik során egészen új elemcsoportok, oldalak jelennek meg a
képernyőn - vagy tűnnek el. Ennek a lehetőségeit nézzük át röviden.

### A dinamikus változások kivárása

Ha a változás elég gyorsan és folyamatosan lezajlik akkor esetleg elég lehet egy egyszerű várakozást beállítani annál a műveletnél,
ami a változást kiváltja. Például, ha ez egy gombnyomás, akkor a következő módszer használható:

```java
public void openNewPage() {
	doWith(newPageOpenerButton).withPageRefreshWait().click();
}
```

A fenti példában a művelet `withPageRefreshWait` metódusát használjuk, aminek a hatására az ibello igyekszik kivárni a művelet futása után minden oldalbetöltődést és
dinamikus változást. Az ibello azonban nem várakozik akármeddig, ha úgy találja, hogy hosszú idő (néhány tizedmásodperc) óta nem történt változás, akkor feltételezi,
hogy minden dinamikus változás véget ért, és a tesztek futhatnak tovább. Ugyanez történik akkor, amikor a rendelkezésre álló idő (az `ibello.timeout.page.refresh`
konfigurációs paraméter által meghatározott másodpercek) múlva is még mindig változik az oldal. (Ilyenkor nem keletkezik hiba.) Mindezek miatt szükség lehet egy olyan
megoldásra, amivel jobban lehet kezelni a hosszú várakozásokat, a lassan megnyíló oldalakat, a lassan megjelenő elemcsoportokat.

### Elemcsoport megjelenésének kivárása

A technika lényege, hogy a megnyitandó oldalhoz (elemcsoporthoz) külön oldal-leírót készítünk. Ebben létrehozunk egy olyan metódust, ami ellenőrzi, hogy az oldal
(elemcsoport) rendben megjelent-e. Egyben ki is várja azt az időt, amíg megjelenik. Ehhez először el kell dönteni, hogy mi az a feltétel, aminek a teljesülésekor az
oldalt (elemcsoportot) megnyitottnak tekinthetjük. Ez gyakran egyetlen elem megjelenése:

```java
public class SlowPage extends PageObject{
	
	@Find(using="#page-header")
	private WebElement indicator;
	
	public void expectOpened() {
		expectations().expect(indicator).toBe().displayed();
	}
	
}
```

Extrém esetekben több elem megjelenését is vizsgálhatjuk, akár sorrendiség nélkül is:

```java
public class SlowPage extends PageObject{
	
	@Find(using="#page-header")
	private WebElement indicator;
	
	@Find(using="#pahe-shadow")
	private WebElement shadow;
	
	public void expectOpened() {
		expectations().all(() -> {
			expectations().expect(indicator).toBe().displayed();
			expectations().expect(shadow).toBe().displayed();
		});
	}
	
}
```

### Egyedi várakozási idő

Az ellenőrzéshez rendelhetünk egy külön várakozási időt is, amit aztán a konfigurációs fájlban nagyobbra definiálunk:

```properties
ibello.timeout.OPEN_PAGE = 30
```

A várakozási időkhöz érdemes egy saját enum osztályt definiálni. Ebben a konstansok nevei a konfigurációban megjelenő nevek:

```java
public enum MyTimeouts {
	OPEN_PAGE,
	CLOSE_PAGE,
	...
}
```

A várakozási időt egyszerűen be kell illesztenünk az ellenőrzésbe:

```java
public void expectOpened() {
	expectations().withTimeout(MyTimeouts.OPEN_PAGE).expect(indicator).toBe().displayed();
}
```

Összetett ellenőrzésnél ahhoz, hogy a várakozási idő a teljes (összetett) vizsgálatra vonatkozzon, a `withTimeout` metódust a legkülső helyen kell hívni:

```java
public void expectOpened() {
	expectations().withTimeout(MyTimeouts.OPEN_PAGE).all(() -> {
		...
	});
}
```

### Elemcsoport eltűnésének kivárása

Teljesen hasonlóan kezelhetjük az oldal (elemcsoport) eltűnését is:

```java
public void expectClosed() {
	expectations().withTimeout(MyTimeouts.CLOSE_PAGE).expect(indicator).toNotBe().displayed();
}
```

A modern webalkalmazásokban a modális dialógus-dobozok egy teljes képernyős árnyékolás előtt jelennek meg. Az árnyékolás semlegesíti az egérműveleteket,
így nem lehet a modális dialógus mellé/mögé kattintani. Ha ilyen dialógusunk van, akkor nem feltétlenül kell `expectClosed` metódust készítenünk. Valószínű ugyanis,
hogy a dialógus bezárásakor az árnyék tűnik el utoljára, így a tesztjeink nem fognak addig az árnyék mögötti elemekkel műveletet végezni, amíg a dialógus egy darabkája
is a képernyőn van. Előfordulhat azonban, hogy ez nem így van, és előbb az árnyék tűnik el. Ekkor a dialógus mögötti elemek felszabadulnak, és mivel a tesztek sokkal
gyorsabban végzik a műveleteket, mint egy felhasználó, még a dialógus tényleges eltűnése előtt rákattinthatnak valamire, amire még nem lenne szabad. Ha biztosak akarunk
lenni a dolgunkban, akkor egy összetett ellenőrzéssel a dialógusra jellemző elem és az árnyék eltűnését egyszerre kell vizsgálnunk:

```java
public void expectClosed() {
	expectations().withTimeout(MyTimeouts.CLOSE_PAGE).all(() -> {
		expectations().expect(indicator).toNotBe().displayed();
		expectations().expect(shadow).toNotBe().displayed();
	});
}

```

### Az egész összerakva

A fentiek alapján például egy így nézhet ki egy lassan megjelenő üzenetdoboz elfogadása, egy tesztlépés-könyvtárban:

```java
public class MySteps extends StepLibrary {

	private MessageBoxPage messageBox;
	
	public void acceptMessage() {
		messageBox.expectOpened();
		messageBox.accept();
		messageBox.expectClosed();
	}

}
```

## HTML `select` elem

Vegyük a következő HTML kódot:
```html
<select id="example">
	<option value="" selected></option>
	<option value="1">First</option>
	<option value="2">Second</option>
</select>
```

Ez a kód egy olyan legördülő listát ír le, amiben három érték közül választhatunk egyet. A kiválasztott opció lehet üres, "First" feliratú vagy "Second" feliratú.
Az alábbiakban összefoglaljuk, hogy hogyan végezhetünk műveleteket és ellenőrzéseket ezzel az elemmel.

### Opció kiválasztása megjelenített szöveg szerint

Ez egyszerű, hiszen erre való a `selectOption` metódus. Ez megjeleníti a választható opciókat, majd kiválasztja azt, amire szükségünk van.

```java
@Find(using="#example")
private WebElement select;

public void selectOptionByText(String text) {
	doWith(select).selectOption(text);
}
```

Megjegyzés: a tesztjeinkben érdemes az elemek látható tulajdonságait használnunk, hiszen a felhasználók is azokat használják. Ezért nem érdemes az
opciók értéke (`value` attribútuma) szerint választanunk.

### Opció kiválasztása index szerint

Szintén a `selectOption` metódust használjuk, aminek most a keresett opció indexét adjuk át. Az index 0-val indul, vagyis az első opciónak az index 0,
a másodiknak 1, és így tovább.

```java
public void selectOptionByText(int index) {
	doWith(select).selectOption(index);
}
```

### A kiválasztott szöveg ellenőrzése

A kiválasztott opció szövege az a látható tulajdonság, amit leginkább ellenőrizni érdemes. Erre van egyszerű megoldás. Elegendő a `select` elemet vizsgálnunk:

```java
public void expectSelectedText(String selectedText) {
	expectations().expect(select).toHave().selectedOption(selectedText);
}
```

Megjegyzés: ha egyik opció sincs kiválasztva, akkor az ellenőrzéshez használhatunk üres sztringet vagy `null` értéket.

### A kiválasztott érték ellenőrzése

Erre is van lehetőség, bár az érték (a `value` attribútum értéke) a tesztjeink szempontjából kevésbé fontos, mivel közvetlenül nem látható.
Szintén a `select` elemet kell vizsgálnunk:

```java
public void expectSelectedValue(String selectedValue) {
	expectations().expect(select).toHave().value(selectedValue);
}
```

## Annak vizsgálata, hogy nem történt változás

Az ibello jó abban, hogy kevés kódolással ellenőrizhessük a HTML oldalon bekövetkező változásokat. Egy `expect(...)` vagy `assume(...)` ellenőrzéskor az ibello
automatikusan kivárja a feltételnek megfelelő változás megjelenését. Egyes esetekben viszont éppen azt szeretnénk ellenőrizni, hogy egy művelet végrehajtása után
nem történik változás. Például egy szövegmező kitöltését követően egy gomb még mindig megnyomható, egy gomb megnyomását követően nem jelenik meg hibaüzenet a képernyőn.
Az ilyen típusú ellenőrzések azonban nem egyszerűek. Ha a változás az ellenőrzés pillanatában *még* nem történik meg, de egy pillanattal később *már* megtörténne, akkor
az ellenőrzésünk sikerrel zárul, de valójában a tesztnek el kellene törnie.

```java
doWith(saveButton).click();
expectations().expect(errorMessage).toNotBe().displayed();
// ha itt jelenik meg a hibaüzenet, azt már nem vesszük észre, mert az ellenőrzés sikeresen lefutott
```

Egy ilyen ellenőrzés kezelésének az egyik módja az, hogy kiegészítjük egy másikkal, amit amúgy is explicit beleértenénk. Arra a változásra kell gondolnunk, amiből az
alkalmazás felhasználója tudhatja, hogy a program nem fagyott le, hanem a művelet sikeresen lezajlott. Például, ha egy gomb megnyomásával menteni szeretnénk a képernyőn
látható adatokat, és a mentés sikeressége után a gombnak letiltott állapotba kell kerülnie, vagy egy megfelelő szöveges területen meg kell jelennie egy feliratanak arról,
hogy a mentés sikeres. Ha van ilyen változás, akkor előbb azt kell ellenőriznünk - az ibello ugyanis automatikusan ki fogja várni annak a változásnak a megjelenését. Ez után
már biztosabban ellenőrizhetjük azt, hogy valami más nem változott meg.

```java
doWith(saveButton).click();
expectations().expect(saveButton).toNotBe().enabled(); // a gomb állapota megváltozik
expectations().expect(errorMessage).toNotBe().displayed(); //a hibaüzenet nem jelenik meg
```

A fenti módszernek az esetek legnagyobb részében működnie kell - márcsak azért is, mert felhasználói élmény szempontjából mindenképpen jó döntés visszajelzést
adni a műveletek sikerességéről. Valami változásnak, amit ellenőrizhetünk, valószínűleg lennie kell.
