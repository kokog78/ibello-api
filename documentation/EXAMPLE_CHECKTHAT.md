# Ellenőrzés `checkThat()` metódus használatával

A `checkThat()` metódus segítségével egy boolean típussal visszatérő feltételt tudok ellenőrizni. Ez hasznos tud például lenni akkor, amikor egy elem megjelenésének hatására valamilyen műveletet szeretnénk elvégezni. Nagyon fontos azonban, hogy a `checkThat()` nem várakozik. Ez a gyakorlatban azt jelenti, hogy amennyiben az ellenőrzés pillanatában még nem történt meg a változás, akkor hibás eredményt fogunk kapni.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

A fenti példánál látszik, hogy a `sendMessageButton` megnyomása után ellenőrizzük a felugró hibaüzenetet. Azonban ha az üzenet csak az ellenőrzés megtörténte után ugrik fel, akkor a feltétel nem teljesül. Ennek a problémának a kiküszöbölésére egy várakozó, ellenőrző metódus (`assume()`, `expect()`) használata kínál megoldást. Érdemes olyan feltételre várnunk, aminek teljesülése után a későbbi `checkThat` vizsgálat már biztosan elvégezhető.

```
public void click_send_message_button() {
    doWith(sendMessageButton).click();
    expectations().assume(resultPanel).toBe().displayed();
    boolean msgDisplayed = checkThat(errorMessage).isDisplayed();
    if (msgDisplayed) {
        System.out.println("Error message is displayed");
    }
}
```

Ebben az esetben a program az `expectations().assume(resultPanel).toBe().displayed()` sorban, előre definiált ideig várakozik. (Itt feltételezzük, hogy az `errorMessage` elem a `resultPanel` panelen jelenik meg.) Ha a feltétel teljesült, a következő `checkThat()` függvény is a helyes eredménnyel fog visszatérni. Amennyiben az `expectation()` kicsúszik a várakozási időből, a riportban hibaüzenet lesz látható.
