# Tetszőleges információk riporton való megjelenítése

Gyakran előfordulhat olyan, eset amikor tetszőleges információval szeretnénk kiegészíteni a teszt riportot. Ehhez az alábbi metódusokat tudjuk használni. 

## output().recordCustomExpectation(String name);

Az `output().recordCustomExpectation()` metódus segítségével egy általunk létrehozott kivételt jeleníthetünk meg a riportban.

```
output().recordCustomExpectation(String.format("The %s is in: %s", name, item));
```

A fenti példa az alábbi formában fog megjelenni a riportban:

`INFO Expectation came true: The "name" is in: "item"`

## output().recordCustomAction(String name);

Az `output().recordCustomAction()` metódus segítségével kiegészítő információt tudunk a riportban megjeleníteni. Amennyiben a megjelenített szöveg több soros, lehetőség van a riportban összecsukni azt. Ezáltal a riport olvashatósága nem romlik a hosszú szöveg megjelenítésével.

```
output().recordCustomAction("The index of the row: " + index);
```

A fenti példa az alábbi formában fog megjelenni a riportban:

`INFO The index of the row: "index"`

## output().recordCustomStep(String name, String text);

Az előbbi példához hasonlóan az  `output().recordCustomStep()` is kiegészítő információ megjelenítésére szolgál. A különbség csupán annyi, hogy ebben az esetben egy külön lépésként (step) jelenik meg a riportban a szöveg. Ezzel tovább növelhető a riport áttekinthetősége.

```
File fileJson = new File("example.json");
String text = new String(Files.readAllBytes(fileJson.toPath()));
output().recordCustomStep("JSON content", text);
```

A fenti példában a JSON fájl tartalma egy "JSON content" tesztlépés alatt fog megjelenni a riportban.

```
INFO 	Start step "JSON content"
INFO     "text"    
INFO    Stop step "JSON content"
```