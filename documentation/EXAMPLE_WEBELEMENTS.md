# Elemcsoportok keresése

Van lehetőségünk arra, hogy egyszerre több elemet keressünk és ellenőrizzünk. Ehhez a `WebElements` osztályt kell használnunk, ami egy `List<WebElement>` implementáció.
Az ibello rendszer automatikusan létrehozza és monitorozza az elemlistákat, ha eltűnik egy elem, vagy új jelenik meg, nem kell újra létrehozzuk a mezőt.

Gyakori eset lehet, hogy egy elemcsoport valahanyadik elemével szeretnénk műveletet végezni. Az alábbi példán látszik, hogy a `get(index)` függvény segítségével ez könnyedén megoldható.

A példában az összes "Details" feliratú gomb közül az utolsót szeretnénk megnyomni. 

```
@Find(by = By.BUTTON_TEXT, using = "Details")
private WebElements detailsButtons;

public void click_last_button() {
    int lastButtonIndex = detailsButtons.size()-1;
    doWith(detailsButtons.get(lastButtonIndex)).click();
}
```

Mivel a `WebElements`egy `List<WebElements>` implementáció, ezért nyugodtan használhatjuk a `for` illetve `foreach` ciklusokat. 

Az alábbi példában az összes `checkbox` mezőre rákattintunk egy foreach segítségével.

```
@Find(by = By.CSS_SELECTOR, using = "input[type='checkbox']")
private WebElements checkboxes;

public void select_all_checkboxes() {
    for (WebElement checkbox : checkboxes) {
        doWith(checkbox).click();
    }
}
```

Az előző példa egy `for` ciklus segítségével.

```
@Find(by = By.CSS_SELECTOR, using = "input[type='checkbox']")
private WebElements checkboxes;

public void select_all_checkboxes() {
    for (int index = 0; index < checkboxes.size(); index++) {
        doWith(checkboxes.get(index)).click();
    }
}
```