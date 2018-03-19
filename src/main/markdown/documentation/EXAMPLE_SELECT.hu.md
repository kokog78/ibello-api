# HTML `select` elem

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

## Opció kiválasztása megjelenített szöveg szerint

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

## Opció kiválasztása index szerint

Szintén a `selectOption` metódust használjuk, aminek most a keresett opció indexét adjuk át. Az index 0-val indul, vagyis az első opciónak az index 0,
a másodiknak 1, és így tovább.

```java
public void selectOptionByText(int index) {
	doWith(select).selectOption(index);
}
```

## A kiválasztott szöveg ellenőrzése

A kiválasztott opció szövege az a látható tulajdonság, amit leginkább ellenőrizni érdemes. Erre van egyszerű megoldás. Elegendő a `select` elemet vizsgálnunk:

```java
public void expectSelectedText(String selectedText) {
	expectations().expect(select).toHave().selectedOption(selectedText);
}
```

Megjegyzés: ha egyik opció sincs kiválasztva, akkor az ellenőrzéshez használhatunk üres sztringet vagy `null` értéket.

## A kiválasztott érték ellenőrzése

Erre is van lehetőség, bár az érték (a `value` attribútum értéke) a tesztjeink szempontjából kevésbé fontos, mivel közvetlenül nem látható.
Szintén a `select` elemet kell vizsgálnunk:

```java
public void expectSelectedValue(String selectedValue) {
	expectations().expect(select).toHave().value(selectedValue);
}
```
