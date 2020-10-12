# Internal annotáció használata

`@Internal` annotációt akkor használunk, ha egy tesztlépés osztályban lévő metódus tesztlépéseit szeretnénk a metódus neve alatt csoportosítva látni a tesztriportban, viszont nem szeretnénk hogy a tesztlépések listájában megtalálható legyen. Alapesetben ilyenkor a `private` kulcsszó használata megoldást jelentene, viszont ilyenkor a metódus tesztlépései csoportosítás nélkül jelennének meg.

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
