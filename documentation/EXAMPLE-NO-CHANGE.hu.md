# Annak vizsgálata, hogy nem történt változás

Az ibello jó abban, hogy kevés kódolással ellenőrizhessük a HTML oldalon bekövetkező változásokat. Egy `expect(...)` vagy `assume(...)` ellenőrzéskor az ibello
automatikusan kivárja a feltételnek megfelelő változás megjelenését. Egyes esetekben viszont éppen azt szeretnénk ellenőrizni, hogy egy művelet végrehajtása után
nem történik változás. Például egy szövegmező kitöltését követően egy gomb még mindig megnyomható, egy gomb megnyomását követően nem jelenik meg hibaüzenet a képernyőn.
Az ilyen típusú ellenőrzések azonban nem egyszerűek. Ha a változás az ellenőrzés pillanatában *még* nem történik meg, de egy pillanattal később *már* megtörténne, akkor
az ellenőrzésünk sikerrel zárul, de valójában a tesztnek el kellene törnie.

```java
doWith(saveButton).click();
expectations().expect(errorMessage).toNotBe().displayed();
// ha itt jelenik meg a hibaüzenet, azt már nem vesszük észre, mert az ellenőrzés sikeresen lefutott
```

Egy ilyen ellenőrzés kezelésének az egyik módja az, hogy kiegészítjük egy másikkal, amit amúgy is explicit beleértenénk. Arra a változásra kell gondolnunk, amiből az
alkalmazás felhasználója tudhatja, hogy a program nem fagyott le, hanem a művelet sikeresen lezajlott. Például, ha egy gomb megnyomásával menteni szeretnénk a képernyőn
látható adatokat, és a mentés sikeressége után a gombnak letiltott állapotba kell kerülnie, vagy egy megfelelő szöveges területen meg kell jelennie egy feliratanak arról,
hogy a mentés sikeres. Ha van ilyen változás, akkor előbb azt kell ellenőriznünk - az ibello ugyanis automatikusan ki fogja várni annak a változásnak a megjelenését. Ez után
már biztosabban ellenőrizhetjük azt, hogy valami más nem változott meg.

```java
doWith(saveButton).click();
expectations().expect(saveButton).toNotBe().enabled(); // a gomb állapota megváltozik
expectations().expect(errorMessage).toNotBe().displayed(); //a hibaüzenet nem jelenik meg
```

A fenti módszernek az esetek legnagyobb részében működnie kell - márcsak azért is, mert felhasználói élmény szempontjából mindenképpen jó döntés visszajelzést
adni a műveletek sikerességéről. Valami változásnak, amit ellenőrizhetünk, valószínűleg lennie kell.
