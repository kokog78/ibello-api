# Ellenőrzés checkThat használatával

CheckThat segítségével lehet ellenőrizni egy boolean típussal visszatérő feltételt. Ez hasznos tud lenni, amikor valamilyen műveletet szeretnénk elvégezni egy elem megjelenésének hatására. Nagyon fontos azonban, hogy a `checkThat()` nem várakozik. Ez a gyakorlatban azt jelenti, hogy amennyiben az ellenőrzés pillanatában még nem történt meg a változás akkor hibás eredményt fogunk kapni.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

A fenti példánál látszik, hogy a `sendMessageButton` megnyomása után ellenőrizzük a felugró hibaüzenetet. Azonban, ha az üzenet késöbb ugrik fel, mint ahogy az ellenőrzés megtörténik, akkor a feltétel nem teljesül. Ennek a problémának a kiküszöbölésére kínál megoldást egy várakozó, ellenőrző metódus (`assume()`, `expect()`) használata. 

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

Ebben az esetben a program az `expectations().assume(errorMessage).toBe().displayed()`sorban várakozik előre definiált ideig. Ha a feltétel teljesült, a következő `checkThat()` függvény is a helyes eredménnyel fog visszatérni. Amennyiben az `expectation()` kicsúszik a várakozási időből, a riportban hibaüzenet lesz látható.