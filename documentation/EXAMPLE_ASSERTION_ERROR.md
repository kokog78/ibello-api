# Hibakezelés AssertionError használatával

Ellenőrzések során előfordulhat olyan helyzet, amikor nem tudjuk az ibello saját `assume()` vagy `expect()` metódusait használni. Ilyen esetekben érdemes a `throw new AssertionError()` műveletet használni, aminek hatására a hiba a riportban és a logban is megjelenik.

Az alábbi példában hiba generálódik, amennyiben nem létező indexszel(TODO megnézni hoyg irják) ellátott gombot szeretnénk megnyomni. 

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_details_button_with_$_index(int buttonIndex) {
    if (detailsButtons.size() >= buttonIndex) {
        doWith(detailsButtons.get(buttonIndex)).click();
    } else {
        throw new AssertionError(String.format("Nem található %d indexű gomb!", 														buttonIndex));
    }
}
```
