# ibello-api

## Tesztek felépítése

Az ibello keretrendszerben létrehozott tesztek háromrétegűek. Egyrészről, az oldalak technikai funkcióit az ún. _oldal-leíró osztályok_ foglalja össze. Másrészről, egy vagy több
oldal-leíró osztály metódusai segítségével tesztlépéseket állítunk össze, amelyeket egy ún. _tesztlépés-könyvtár osztályba_ teszünk. Az egyes _tesztek_ a tesztlépés-könyvtárak
metódusainak hívásából állnak elő.

Ez a hármas tagozódás lehetővé teszi, hogy az üzletileg igényelt funkciókat elválasszuk azok technikai megvalósításától. Így a tesztek
üzleti szempontból is érthetőbbé válnak. A másik előny, hogy a weboldalak apróbb változásai nincsenek kihatással magukra a tesztekre, csak az oldal-leírókat kell megváltoztatni.
Ezáltal a tesztek időtállóbbak.

## A teszt osztály

A teszt osztályt a `@Specification` annotációval jelöljük. Az ibello rendszer innen tudja, hogy az osztály metódusai között teszt metódusok is vannak. Egy teszt metódust a `@Test`
annotáció jelöl. A tesztek futtatásához az ibello JUnit-ot használ.

A teszt osztályban tesztlépés-könyvtárakat használunk. Egy tesztlépés-könyvtárat úgy a legegyszerűbb elérni, ha a teszt osztályhoz adunk egy (akár privát) mezőt a kívánt típussal.
Az ibello automatikusan létre fogja majd hozni a tesztlépés-könyvtárat, amit a teszt metódusokban nyugodtan használhatunk:

```java
@Specification
public class LoginTests {

    // a tesztlépés-könyvtár automatikusan példányosodik
    private LoginSteps steps;
    
    @Test
    public void successful_login() {
        steps.given_that_login_page_is_opened();
        steps.when_i_login_with_valid_credentials();
        steps.then_i_get_to_the_welcome_page();
        steps.then_i_see_that_valid_user_is_logged_in();
        steps.then_is_see_the_standard_operations();
    }
}
```

A fenti példának az egyik fontos jellemzője, hogy a teszt metódusban meghívott utasítások nem tartalmaznak semmilyen technikai
részletet. Nem tudjuk, pontosan honnan is lehet tudni, hogy a bejelentkező oldalon vagyunk, és azt sem, hogy hogyan is lehet belépni egy érvényes felhasználóval. Mindezeket
az információkat a tesztlépés-könyvtár metódusai tartalmazzák.

## A tesztlépés-könyvtár

A tesztlépés-könyvtár osztály a `StepLibrary` ősosztályból származik. Minden publikus metódusa tesztlépésnek számít. A metódusok neveit érdemes beszédesre választani.
Jó gyakorlatot követünk, ha háromféle metódust különböztetünk meg, és ezeknek a metódusoknak a típusát a nevükben is jelezzük.

- Az előkészítő metódusok elérik, hogy a böngészőben beálljon valamilyen kiindulási állapot. A nevük kezdődhez `given` előtaggal.
- A végrehajtó metódusok végrehajtják a tesztelendő műveletet. A nevük kezdődhet `when_` előtaggal.
- Az ellenőrző metódusok ellenőrzik az előállt állapotot, és hibát dobnak, ha a kívánt feltétel nem teljesült. A nevük kezdődhez `then_` előtaggal.

A tesztlépés-könyvtár metódusai oldal-leírókat használnak. Az oldal-leírókat nyugodtan bejegyezhetjük (privát) mezőkként, az ibello rendszer automatikusan létre fogja
azokat hozni. Egy tesztlépés-könyvárban más tesztlépés-könyvtárakra is hivatkozhatunk, szintén elég csak egy (privát) mezőt létrehozni a megfelelő típussal.

```java
public class LoginSteps extends StepLibrary {

    // az oldal-leíró automatikusan példányosodik
    private LoginPage loginPage;
    
    // szintén automatikusan létrejön
    private WelcomePage welcomePage;
    
    public void given_that_login_page_is_opened() {
        loginPage.open();
    }
    
    public void when_i_login_with_valid_credentials() {
        loginPage.setUsername("testuser");
        loginPage.setPassword("testpwd");
        loginPage.clickLoginButton();
    }
    
    public void then_i_get_to_the_welcome_page() {
        welcomePage.expectOpened();
    }
    
    public void then_i_see_that_valid_user_is_logged_in() {
        welcomePage.expectCurrentUser("testuser");
    }
    
    public void then_is_see_the_standard_operations() {
        welcomePage.expectOperations("Adatrögzítés", "Lekérdezések", "Beállítások");
    }
}
```

Lehetőségünk van arra, hogy a tesztlépés-könyvtárba más, általunk létrehozott osztályokat is injektáljunk. A fenti példában például jó lenne, ha nem kellene a felhasználónevet és a
jelszót közvetlenül megadni, hanem lenne egy olyan eszköz-osztály, amitől ezeket az adatokat lekérdezhetjük. Ha esetleg később ezek az adatok megváltoznak, vagy a lekérdezésük
módja változik, akkor elegendő lesz csak ezt az egy osztályt átírnunk. Hozzunk tehát létre egy ilyen osztályt:

```java
public class UserData {

    private final String VALID_USERNAME = "testuser";
    private final String VALID_PASSWORD = "testpwd";

    public String getValidUsername() {
        return VALID_USERNAME;
    }
    
    public void getValidPassword() {
        return VALID_PASSWORD;
    }
}
```

Most pedig illesszük me a `UserData` osztály egy példányát a teszlépés-könyvtárba! Az oldal-leírókkal és a tesztlépés-könyvtárakkal ellentétben
ehhez most használnunk kell az `@Inject` annotációt, az ibello innen tudja, hogy egy egyéni osztály példányát injektálnia kell:

```java
public class LoginSteps extends StepLibrary {
    
    @Inject
    private UserData userData;
    
    public void when_i_login_with_valid_credentials() {
        loginPage.setUsername(userData.getValidUsername());
        loginPage.setPassword(userData.getValidPassword());
        loginPage.clickLoginButton();
    }
}
```

A teszlépés-könyvtárak publikus metódusainak hívását az ibello rendszer folyamatosan loggolja, valamint azok neveit az elkészült teszt riportban is szerepelteti.
A logba és a riportba a metódus nevéből kiszámolt kifejezés kerül bele. Például a `when_i_login_with_valid_credentials` metódusnévből ez lesz:
`When I Login With Valid Credentials`. Ha ezt a működést meg szeretnénk változtatni, akkor a metódushoz adnunk kell egy `@Name` annotációt, aminek egyetlen tulajdonságaként
meg kell adnunk a kiírandó szöveget. A szövegben a metódus esetleges paraméterei is megjelennek, azoknak a helyét `${0}`, `${1}`, stb. karaktersorozatokkal jelölhetjük.

```java
@Name("A(z) ${0}. elem megnyitása")
public void openItem(int index) { ... }
```

## Az oldal-leíró

Egy oldal-leíró osztály összefoglalja egy weboldalnak, vagy egy weboldal-struktúra jól elkülöníthető részének technikai funkcióit. Ilyen funkciók például
gombokra történő kattintás, egy beviteli mező értékének kitöltése, "fog és vidd" ("drag and drop") művelet. Az oldal-leíró emellett olyan metódusokat is tartalmaz, amikkel
az oldal állapotát ellenőrizhetjük, mint például elemek láthatósága, szövegmezők tartalma.

Minden oldal-leíró a `PageObject` osztályból származik. Az ibello keretrendszer az oldal-leírókat automatikusan inicializálja.

Az oldal-leírókon belül érjük el a böngésző funkcióit is, mint pl. az aktuális URL beállítása. Ehhez a `browser()` metódust kell használnunk.

```java
public class LoginPage extends PageObject {

    public void open() {
        browser().openURL("/login.html");
    }
}
```

Egy oldal-leíró tartalmazhat olyan speciális mezőket, amik az oldalon fellelhető elemek keresési paramétereit tartalmazzák. Ezek az elemek `WebElement` típusúak. Az elemekkel történő
műveletekhez, ellenőrzésekhez ezekre rendre szükség van. Az ibello keretrendszer automatikusan inicializálja ezeket a mezőket, ha a `@Find` annotáció segítségével megadtuk hozzájuk a
keresési feltételeket.

Az elemekkel történő művelethez az oldal-leíró `doWith` metódusát kell használjuk. Ennek egyetlen paramétere a `WebElement` példány, az elvégzendő művelet folytatólagosan hívható
a visszaadott objektumból.

```java
public class LoginPage extends PageObject {

    // automatikusan létrehozott mező, a "username" id-jű elem adatait tartalmazza
    @Find(using="#username")
    private WebElement usernameField;
    
    // automatikusan létrehozott mező, a "password" id-jű elem adatait tartalmazza
    @Find(by=By.ID, using="password")
    private WebElement passwordField;
    
    // automatikusan létrehozott mező, a "Belépés" feliratú gomb vagy link adatait tartalmazza
    @Find(by=By.BUTTON_TEXT, using="Belépés")
    private WebElement loginButton;
    
    public void setUsername(String username) {
        doWith(usernameField).setValue(username);
    }
    
    public void setPassword(String password) {
        doWith(passwordField).setValue(password);
    }
    
    public void clickLoginButton() {
        doWith(loginButton).click();
    }
}
```

Az ellenőrzések futtatásához az oldal-leíró az `expectations()` metódusát kell meghívnunk, majd folytatólagosan használnunk a kívánt ellenőrzést.
A visszakapott objektumnak van néhány `expect(...)` metódusa, amik paraméterként megkapják az ellenőrizendő objektumot. Ez lehet a `browser()` metódus által visszaadott
böngésző-példány, vagy egy `WebElement` példány is.

```java
public class WelcomePage extends PageObject {

    @Find("#user-name")
    private WebElement userField;

    public void expectOpened() {
        expectations().expect(browser()).toHave().url("/welcome.html");
    }
    
    public void expectCurrentUser(String username) {
        expectations().expect(userField).toHave().value(username);
    }
}
```

Arra is lehetőségünk van, hogy egyszerre több elemet keressünk és ellenőrizzünk. Ehhez a `WebElements` osztályt kell használnunk, ami egy `List<WebElement>` implementáció.
Az ibello rendszer automatikusan létrehozza és monitorozza az elemlistákat, ha eltűnik egy elem, vagy új jelenik meg, nem kell újra létrehozzuk a mezőt.

```java
public class WelcomePage extends PageObject {

    @Find(by=By.CLASS_NAME, using="operation-button")
    private WebElements operations;
    
    public void expectOperations(String ... buttonTexts) {
        expectations().expect(operations).toHave().size(buttonTexts.length);
        for (int i = 0; i < buttonTexts.length; i++) {
            expectations().expect(operations.get(i)).toHave().text(buttonTexts[i]);
        }
    }
}
```

Az oldal-leírók metódusai is bekerülnek a logba és a riportba. A megjelenő szöveg ezeknél is pontosítható a `@Name` annotáció segítségével.

## Elemek keresése

Az ibello rendszer lehetőséget ad egyszerre egy vagy több elem keresésére. A keresés lehet _statikus_ vagy _dinamikus_.

### Statikus elemkeresés

Statikus keresésről akkor beszélhetünk, ha az oldal-leíró `WebElement` vagy `WebElements` típusú mezőjét `@Find` annotációval látjuk el, aminek hatására az ibello rendszer automatikusan
inicializálja a mezőt. A `@Find` annotációnak két tulajdonságot adhatunk meg. A `by` tulajdonság a `By` enum értékkészletéből vehet fel értéket és a keresés módját határozza meg.
Ennek a tulajdonságnak van alapértelmezett értéke: `By.CSS_SELECTOR` - ha a `by` értékét nem adjuk meg, akkor ez lesz a keresési mód. A másik tulajdonság a `using`, ami a keresési
mód paramétere, jelentése a keresési módtól függ. Az alábbi táblázat foglalja össze az egyes keresési módokat és tulajdonságokat.

| Keresési mód               | `by` értéke       | `using` értéke                            |
| -------------------------- | ----------------- | ----------------------------------------- |
| CSS szelektor szerinti     | `By.CSS_SELECTOR` | CSS szelektor                             |
| `id` attribútum szerinti   | `By.ID`           | a keresett elem `id` attribútuma          |
| `name` attribútum szerinti | `By.NAME`         | a keresett elem `name` attribútuma        |
| elem típusa szerinti       | `By.TAG_NAME`     | a keresett elem típusneve                 |
| CSS osztály szerinti       | `By.CLASS_NAME`   | a keresett elem CSS osztályának neve      |
| gomb felirata szerinti     | `By.BUTTON_TEXT`  | a keresett gomb funkciójú elem felirata   |
| címke szerinti             | `By.LABEL`        | a keresett elemhez tartozó címke felirata |
| tartalom szerinti          | `By.TEXT`         | a keresett elem tartalma                  |

A `By.BUTTON_TEXT` keresés megtalálja a `button` típusú, az `input type=button` típusú, az `input type=submit` típusú, az `input type=reset` típusú elemeket és a linkeket is -
mindent, aminek gombszerű funkciója lehet. A `By.LABEL` keresés először a `label` típusú elemet keresi meg tartalom szerint, majd annak a `for` attribútumát követve megtalálja a
kívánt elemet is.

Ha egyetlen elemet keresünk, de a rendszer többet is talál, akkor a legelső megtalált elemet kapjuk vissza a mezőben.

A `@Find` annotáció `using` tulajdonságában használhatunk paramétereket is. Ezek helyére később értékeket helyettesíthetünk be. Az ibello rendszer inicializálni fogja
nekünk ezeket az elemeket, de megkeresni csak akkor fogja, amikor később a paraméterek értékeit a `WebElement.applyParameters` vagy a `WebElements.applyParameters`
metódus segítségével megadjuk. A behelyettesítés a megadott paraméterek indexei alapján fog megtörténni, a `${0}` karaktersorozattal jelölt helyre az első paraméter
értéke fog kerülni, a `${1}` helyére a második paraméteré, stb.

```java
@Find(by=By.BUTTON_TEXT, using="${0}/${1} végrehajtása")
private WebElement button;

public void clickOperationButton(String operation, int index) {
    doWith(button.applyParameters(operation, index)).click();
}
```

Az ibello rendszer lehetővé tesz olyan elemkereséseket is, amikor az keresett elem más elemekhez való viszonya a keresés tárgya. Ezeket a keresési feltételeket a `@Find`
mellett szereplő más annotációkkal adjuk meg. A `@Find` annotációval ellentétben ezekből több is kapcsolódhat a mezőhöz, ilyenkor azok hatása egyszerre érvényesül.


A pozíció szerinti keresést a `@Position` annotációval oldhatjuk meg. Az annotáció egyrészről keresési feltételeket ad egy _referencia-elemhez_ (`by` és `using` tulajdonságok),
amihez a keresendő mezők helyzetét meg szeretnénk adni. Másrészről, a `type` tulajdonság segítségével pont ezt a helyzetet lehet megadni. Például, az alábbi példában egy olyan
"field" CSS osztállyal rendelkező elemet keresünk, ami a "Mentés" gombtól balra található.

```java
@Position(type=PositionType.LEFT_FROM, by=By.BUTTON_TEXT, using="Mentés")
@Find(using=".field")
private WebElement usernameField;
```

A `type` tulajdonság értéke a `PositionType` enum értékkészletéből származik.

| `PositionType` | Jelentése                                                |
| -------------- | -------------------------------------------------------- |
| `ROW`          | A referencia-elemmel egy sorban levő elemet keresünk.    |
| `COLUMN`       | A referencia-elemmel egy oszlopban levő elemet keresünk. |
| `LEFT_FROM`    | A referencia-elemtől balra levő elemet keresünk.         |
| `RIGHT_FROM`   | A referencia-elemtől jobbra levő elemet keresünk.        |
| `ABOVE`        | A referencia-elem felett elhelyezkedő elemet keresünk.   |
| `BELOW`        | A referencia-elem alatt elhelyezkedő elemet keresünk.    |

A DOM struktúra szerinti keresést a  `@Relation` annotációval oldhatjuk meg. Hasonlóan a `@Position` annotációhoz, a `by` és a `using` tulajdonságokkal itt is egy referencia-elemet
határozunk meg. A `type` tulajdonság értéke itt a `RelationType` enum egyik konstansa, ami meghatározza a keresett elem viszonyát a referencia-elemhez képest.

| `RelationType`  | Jelentése                                                                  |
| --------------- | -------------------------------------------------------------------------- |
| `DESCENDANT_OF` | A keresett elem a referencia-elem leszármazottja.                          |
| `ANCESTOR_OF`   | A keresett elem a referencia-elemet közvetlenül vagy közvetve tartalmazza. |

Ha a `type` értéke nincs megadva, akkor az ibello rendszer azt `RelationType.DESCENDANT_OF`-nak veszi. Több `@Relation` annotációval lehetőségünk van több szülőelemen keresztül megadni
a keresett elem helyét. Az alábbi példa a "modal-window" CSS osztállyal rendelkező elemen belül keres egy `form` element, majd azon belül egy "main-fields" osztállyal rendelkező elemet,
majd azon belül végül megkeresi azt az elemet, aminek a `name` attribútuma "username".

```java
@Relation(using=".modal-window")
@Relation(using="form")
@Relation(using=".main-fields")
@Find(by=By.NAME, using="username")


private WebElement usernameField;
```

### Dinamikus elemkeresés

Dinamikus elemkeresésnél tulajdonképpen klasszikus java metódushívásokkal keresünk elemeket. Ezt kétféleképpen tehetjük meg. Egyrészről, minden `WebElement` példány rendelkezik egy
`find()` metódussal, amit meghívva elindíthatunk egy új keresést, méghozzá a `WebElement` példányon belül. Másrészről, a `PageObject.find()` metódusával a teljes oldalon belül indíthatunk
elemkeresés. Mindkét `find()` metódus egy előkonfigurált `SearchTool` példányt ad vissza, amivel folytatólagosan adhatjuk meg a keresés paramétereit. A keresést a `first()` vagy az `all()`
metódusok egyikével zárhatjuk le aszerint, hogy `WebElement` vagy `WebElements` példányt szeretnénk-e kapni eredményül. Az előző fejezet példái:

```java
// paraméteres keresés
WebElement button = find()
    .using(By.BUTTON_TEXT, "${0}/${1} végrehajtása", "mentés", 1)
    .first();
// pozíció szerinti keresés
WebElement usernameField = find()
    .using(".field")
    .leftFrom( find().using(By.BUTTON_TEXT, "Mentés").first() )
    .first();
// DOM struktúra szerinti keresés
usernameField = find()
    .using(".modal-window")
    .first().find()
        .using("form")
        .first().find()
            .using(".main-fields")
            .first().find()
                .using(By.NAME, "username")
                .first();
```

## Függőségek injektálása

