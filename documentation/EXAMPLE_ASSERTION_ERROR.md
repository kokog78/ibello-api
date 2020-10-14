# Hibakezelés AssertionError használatával

Ellenőrzések során előfordulhat olyan helyzet, amikor nem elegendő az `assume()` vagy `expect()` metódusai által biztosított eszköztár. Ilyen esetekben érdemes a `throw new AssertionError()` műveletet használni, aminek hatására a hiba megjelenik a riportban és a logban is.

Az alábbi példában hiba generálódik, amennyiben nem létező indexszel ellátott gombot szeretnénk megnyomni. 

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

