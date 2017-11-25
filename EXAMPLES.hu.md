# Példák

Az alábbi példák néhány komplex feladat megvalósítását mutatják be.

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

### Opció kiválasztása érték szerint

Pontosan úgy kell csinálnunk, mint ahogyan a felhasználók is csinálják. Először a `select` elemre kell kattintanunk, hogy megjelenjenek a választható opciók.
Ezután rá kell kattintanunk a kívánt `option` elemre. Az `option` elemet paraméteresen fogjuk megkeresni - ami valamennyi rugalmasságot ad nekünk.

```java
@Find(using="#example")
private WebElement select;

@Find(using="#example option[value='${0}']")
WebElement optionByValue;

public void selectOptionByValue(String value) {
	doWith(select).click();
	doWith(optionByValue.applyParameters(value)).click();
}
```

### Érték nélküli opció kiválasztása

Ha a kiválasztandó `option` elemnek nincs `value` attribútuma, akkor azt nem kereshetjük meg az előbbi módszerrel. Más CSS szelektort kell alkalmaznunk. HTML kód:

```html
<select id="example">
	<option></option>
	<option value="1" selected>First</option>
</select>
```

Java kód:

```java
@Find(using="#example option:not([value])")
WebElement emptyOption;

public void selectEmptyOption() {
	doWith(select).click();
	doWith(emptyOption).click();
}
```

### Opció kiválasztása megjelenített szöveg szerint

Az előző példához teljesen hasonlóan kell eljárnunk, csak máshogyan kell megkeresnünk az `option` elemet:

```java
@Relation(type=RelationType.DESCENDANT_OF, using="#example")
@Find(by=By.TEXT, using="${0}")
private WebElement optionByText;

public void selectOptionByText(String text) {
	doWith(select).click();
	doWith(optionByText.applyParameters(text)).click();
}
```

### A kiválasztott érték ellenőrzése

Két lehetőségünk van. Ellenőrizzük közvetlenül a `select` elemet:

```java
public void expectSelectedValue(String selectedValue) {
	expectations().expect(select).toHave().value(selectedValue);
}
```

Vagy keressük meg az éppen kiválasztott `option` elemet:

```java
@Find(using="#example option:checked")
private WebElement selectedOption;

public void expectSelectedValue(String selectedValue) {
	expectations().expect(selectedOption).toHave().value(selectedValue);
}
```

### A kiválasztott szöveg ellenőrzése

Elképzelhető, hogy inkább azt szeretnénk ellenőrizni, hogy mi a kiválasztott opcióhoz tartozó megjelenített szöveg - ugyanis a
felhasználók is ezt látják. Ehhez az előbbi példa szerint meg kell keresnünk a kiválasztott `option` elemet, és azt kell vizsgálnunk:

```java
public void expectSelectedText(String selectedText) {
	expectations().expect(selectedOption).toHave().text(selectedText);
}
```
