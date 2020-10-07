# Hibakezelés AssertionError használatával

Ellenőrzések során előfordulhat olyan helyzet amikor, nem tudjuk az ibello saját `assume()` vagy `expect()` metódusait használni. Ilyen esetekben érdemes a `throw new AssertionError()` metódust használni, aminek hatására a hiba a reportban megjelenik.

Az alábbi példában hiba generálódik amennyiben nem létező index-szel ellátott gombot szeretnénk megnyomni. 

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_details_button_with_$_index(int buttonIndex) {
    if (!detailsButtons.isEmpty()) {
        doWith(detailsButtons.get(buttonIndex)).click();
    } else {
        throw new AssertionError(String.format("Nem található %d indexű gomb!", 														buttonIndex));
    }
}
```

