# Ellenőrzés checkThat használatával

CheckThat segítségével egy boolean típussal visszatérő feltételt tudok ellenőrizni. Ez hasznos tud lenni, amikor például egy elem megjelenésének hatására szeretnénk valamilyen műveletet elvégezni. Nagyon fontos azonban, hogy a `checkThat()` nem várakozik. Ez a gyakorlatban azt jelenti, hogy amennyiben az ellenőrzés pillanatában még nem történt meg a változás akkor hibás eredményt fogunk kapni.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

A fenti példánál látszik, hogy a `sendMessageButton` megnyomása után ellenőrizzük a felugró hibaüzenetet. Azonban, ha később ugrik csak fel az üzenet mint ahogy az ellenőrzés megtörténik akkor nem teljesül a feltétel. Ennek a problémának a kiküszöbölésére egy várakozó, ellenőrző metódus (`assume()`, `expect()`) használata kínál megoldást. 

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    expectations().assume(errorMessage).toBe().displayed();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

Ebben az esetben a program az `expectations().assume(errorMessage).toBe().displayed()`sorban, előre definiált ideig várakozik. Ha a feltétel teljesült, a következő `checkThat()` függvény is a helyes eredménnyel fog visszatérni. Amennyiben az `expectation()` kicsúszik a várakozási időből a reportban hibaüzenet lesz látható.