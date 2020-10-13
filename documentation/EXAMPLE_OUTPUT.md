# Tetszőleges információk riporton való megjelenítése

Gyakran előfordulhat olyan, eset amikor tetszőleges információval szeretnénk kiegészíteni a teszt riportot. Ehhez az alábbi metódusokat tudjuk használni. 

## Egyedi ellenőrzés eredményének megjelenítése

Az `output().recordCustomExpectation(String)` metódus segítségével egy általunk létrehozott vizsgálatot jeleníthetünk meg a riportban.

```
output().recordCustomExpectation(String.format("The %s is in the %s", name, item));
```

A fenti példa az alábbi formában fog megjelenni a riportban:

`Expectation came true: The apple is in the basket`

## Egyedi művelettel kapcsolatos szöveg megjelenítése

Az `output().recordCustomAction(String)` metódus segítségével kiegészítő információt tudunk a riportban megjeleníteni.

```
output().recordCustomAction("The index of the row: " + index);
```

A fenti példa az alábbi formában fog megjelenni a riportban:

`The index of the row: 3`

## Egyedi tesztlépés megjelenítése

Az előbbi példához hasonlóan az  `output().recordCustomStep(String, String)` is kiegészítő információ megjelenítésére szolgál. A különbség csupán annyi, hogy ebben az esetben a szöveg egy külön lépésként (step) jelenik meg a riportban. Ezzel tovább növelhető a riport áttekinthetősége.

```
File fileJson = new File("example.json");
String text = new String(Files.readAllBytes(fileJson.toPath()));
output().recordCustomStep("JSON content", text);
```

A fenti példában a JSON fájl tartalma egy "JSON content" tesztlépés alatt fog megjelenni a riportban.

```
JSON content
    {"field": "value"}
```