# Internal annotáció használata

`@Internal` annotációt akkor használunk, ha nem szeretnénk, hogy egy lépés az ibello forgatókönyv kezelőjén belül kiválasztható legyen. 

MIÉRT?????

muszáj h publikus legyen egy allépés de nem akarjuk, hogy külön stepsként használható legyen, egy másik publikus metodus hivja meg csak.

```
@Internal
public void send_form() {
    page.click_send_button();
    page.success_message_displayed();
}
```
