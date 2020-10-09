# Internal annotáció használata

`@Internal` annotációt akkor használunk, ha nem szeretnénk, hogy egy teszt lépés az ibello forgatókönyv kezelőjén belül kiválasztható legyen. Erre olyan publikus allépések esetén lehet szükség, amik nem számítanak önálló teszt lépésnek, hanem egy másik publikus metódus hívja meg.

Tételezzük fel, hogy az alábbi kód egy teszt lépés osztály tartalma és a `fill_out_form()` metódust másik teszt lépés osztály is használja (emiatt nem lehet private).

```
public void send_form() {
	fill_out_form();
    formPage.click_send_button();
    formPage.success_message_displayed();
}
```

```
@Internal
public void fill_out_form() {
    formPage.set_user_data();
    formPage.set_message();
}
```

A fenti kódban azért használjuk az `@Internal` annotációt, hogy a `fill_out_form()` metódust ne lehessen önálló tesztlépésként használni.