# Az `@Internal` annotáció használata

`@Internal` annotációt akkor használunk, ha egy tesztlépés osztályban lévő metódust szeretnénk az ibello grafikus felületén elrejteni.
A gherkin jellemzők szerkesztésére használt felületen ugyanis lehetőség van arra, hogy az éppen használni kívánt tesztlépést az elérhető
lépések listájából válasszuk ki. Előfordulhat, hogy bizonyos lépéseket mégsem szeretnénk ebben a listában látni. Például, ha egy tesztlépés
előtt *mindenképpen* meg kell hívni egy másikat, és nem szeretnénk, ha ezt a forgatókönyvek írójáak fejben kellene tartania, akkor dönthetünk
úgy, hogy ezt a lépést inkább elrejtjük, és a meghívásáról egy másik tesztlépésben gondoskodunk. Ha az elérhető tesztlépések listájából
el tudjuk távolítani az irreleváns lépéseket, azzal megkönnyítjük a teszt tervezési munkát.

Az `@Internal` annotációval megjelölt metódus továbbra is tesztlépésként funkcionál, megjelenik a tesztriportban és a logban is.
Az ilyen metódusokat más - tesztlépésként működő - metódusok tudják meghívni.

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

Ha a fenti példában szereplő "send_form" metódust valamelyik teszt meghívja, akkor a tesztriportban az alábbi bejegyzéseket fogjuk látni:

```
- send form
  - fill out form
    - set user data
    - set message
  - click send button
  - success message displayed
```

Az `@Internal` annotációnak tehát nincs hatása a tesztriportra.
