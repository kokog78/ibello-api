# Hibakezelés `AssertionError` használatával

Ellenőrzések során előfordulhat olyan helyzet, amikor nem elegendő az `assume()` vagy `expect()` metódusok által biztosított eszköztár.
Ilyen esetekben érdemes a `throw new AssertionError()` műveletet használni, aminek hatására a hiba megjelenik a riportban és a logban is.

Az alábbi példában hiba generálódik, amennyiben nem létező indexszel ellátott gombot szeretnénk megnyomni. 

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_details_button_with_$_index(int buttonIndex) {
    if (detailsButtons.size() >= buttonIndex) {
        doWith(detailsButtons.get(buttonIndex)).click();
    } else {
        throw new AssertionError(String.format("Nem található %d indexű gomb!", buttonIndex));
    }
}
```

Ez a funkció arra is lehetőséget ad, hogy a tesztjeinkben más, ellenőrzés célját szolgáló könyvtárakat használjunk. Ezekről ugyanis
általánosságban elmondható, hogy sikertelenség esetén `AssertionError`-t dobnak - amit az ibello is megért. Az előbbi példa az
AssertJ eszköz használatával például így nézne ki:

```java
public void click_details_button_with_$_index(int buttonIndex) {
	org.assertj.core.api.Assertions.assertThat(detailsButtons.size()).isGreaterThan(buttonIndex);
	doWith(detailsButtons.get(buttonIndex)).click();
}
```

Az `ibello.use.soft.assertions` konfigurációs paraméterrel némiképp befolyásolhatjuk az `AssertionError`-alapú hibakezelést.
Ha a paraméter értékét `true`-ra állítjuk, akkor az ilyen kivétellel elbuktatott tesztlépések "lágy" módon buknak el. Ez azt jelenti,
hogy a tesztfutást nem szakítják meg, csak a teszteredményt teszik sikertelenné. Ha a konfigurációs paramétert nem adjuk meg, vagy
az értékét `false`-ra állítjuk, akkor a tesztbukás "kemény" lesz, a hiba az aktuális forgatókönyv futását is megszakítja.
