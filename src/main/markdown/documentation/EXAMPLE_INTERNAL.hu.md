# Az `@Internal` annotáció használata

`@Internal` annotációt akkor használunk, ha egy tesztlépés osztályban lévő metódust szeretnénk elrejteni az ibello grafikus felületén. A metódus továbbra is tesztlépésként funkcionál, megjelenik a tesztriportban és a logban is, de a felületen nem lehet majd kiválasztani.

Az annotációval jelölt metódusokat más - tesztlépésként működő - metódusok tudják meghívni.

```
public void send_form() {
	fill_out_form();
    formPage.click_send_button();
    formPage.success_message_displayed();
}

@Internal
public void fill_out_form() {
    formPage.set_user_data();
    formPage.set_message();
}
```
