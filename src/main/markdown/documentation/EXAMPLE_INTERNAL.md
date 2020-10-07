# Internal annotáció használata

`@Internal` annotációt akkor használunk ha nem szeretnénk,  hogy egy lépés az ibello forgatókönyv kezelőjén belül kiválasztható legyen. 

MIÉRT?????

```
@Internal
public void send_form() {
    page.click_send_button();
    page.success_message_displayed();
}
```

Az `@Internal` annotációval ellátott lépések kézzel továbbra is megadhatóak a forgatókönyvekben.