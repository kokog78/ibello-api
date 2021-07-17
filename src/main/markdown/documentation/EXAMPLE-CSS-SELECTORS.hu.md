# CSS szelektorok használata

Elemkeresés során nagyon fontos a megfelelő feltételek meghatározása. Sok esetben, a beépített keresési feltételek (`By.ID`, `By.CLASS_NAME`, stb.) nem elegendők. Ilyenkor lehetőségünk van CSS szelektorokat alkalmazni.

Az alábbi példa szemlélteti az "attribútum tartalmazza" szelektor használatát.

Szituáció: van egy alkalmazás ami termékek raktárkészletét kezeli. Az oldalon van három termék és hozzájuk egy-egy beviteli mező, amibe be lehet írni az elérhető mennyiségeket. Minden beviteli mező külön `id`-val rendelkezik, amik az alábbiak: "prodQuantity1", "prodQuantity2", "prodQuantity3". Ezeken kívül az oldal más beviteli mezőket is tartalmaz, de azok teljesen más `id`-val rendelkeznek és más funkciót töltenek be.

Feladat: minden termék mennyiségét állítsuk be 10-re! 

```
@Find(by = By.ID, using = "prodQuantity1")
private WebElement quantityField1;

@Find(by = By.ID, using = "prodQuantity2")
private WebElement quantityField2;

@Find(by = By.ID, using = "prodQuantity3")
private WebElement quantityField3;

public void set_quantities_to_ten() {
    doWith(quantityField1).setValue("10");
    doWith(quantityField2).setValue("10");
    doWith(quantityField3).setValue("10");
}
```

A fenti példában felvettünk minden mennyiséget tartalmazó beviteli mezőhöz egy-egy `WebElement`-et, majd azoknak az értékét a `set_quantities_to_ten()` metódusban 10-re állítottuk. Ez három elem esetén még nem okoz különösebb problémát, de sokkal több beviteli mező esetén már felesleges munkát jelent. Ennek a feladatnak az egyszerűbb megoldását mutatja az alábbi példa.

```
@Find(by = By.CSS_SELECTOR, using = "[id*=Quantity]")
private WebElements quantityInputFields;

public void set_quantities_to_ten() {
    for (WebElement quantityField : quantityInputFields) {
        doWith(quantityField).setValue("10");
    }
}
```

Ebben az esetben összegyűjtöttük az összes "Quantity" szót **tartalmazó** azonosítóval ellátott beviteli mezőt, majd azokon végiglépkedve beállítottuk az értéküket. 