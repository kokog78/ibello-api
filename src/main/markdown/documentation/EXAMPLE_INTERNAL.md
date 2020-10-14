# Internal annotáció használata

`@Internal` annotációt akkor használunk, ha egy tesztlépés osztályban lévő metódust szeretnénk a tesztriportban látni, viszont nem szeretnénk ha önálló tesztlépésként funkcionálna. Alapesetben ilyenkor a `private` kulcsszó használata megoldást jelentene, viszont az ibello ezeket a metódusokat nem jeleníti meg a riportban.

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
