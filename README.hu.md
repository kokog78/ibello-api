# ibello-api

Az ibello keretrendszerben létrehozott tesztek háromrétegűek. Egyrészről, az oldalak technikai funkcióit az ún. _oldal-leíró osztályok_ foglalják össze. Másrészről, egy vagy több
oldal-leíró osztály metódusai segítségével tesztlépéseket állítunk össze, amelyeket egy ún. _tesztlépés-könyvtár osztályba_ teszünk. Az egyes _tesztek_ a tesztlépés-könyvtárak
metódusainak hívásaiból állnak elő.

Ez a hármas tagozódás lehetővé teszi, hogy az üzletileg igényelt funkciókat elválasszuk azok technikai megvalósításától. Így a tesztek üzleti szempontból is érthetőbbé válnak.
További előnyt jelent még az, hogy a weboldalak apróbb változásai nincsenek kihatással magukra a tesztekre, csak az oldal-leírókat kell megváltoztatni. Ezáltal a tesztek időtállóbbak.

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

## Konfigurációs paraméterek olvasása

A tesztlépés-könyvtárak és az oldal-leírók lehetőséget adnak az ibello konfigurációs paraméterek olvasására. Erre a célra a `getConfigurationValue(String)` metódus szolgál.
A metódus egyetlen argumentuma a kívánt konfigurációs paraméter neve, visszatérési értéke pedig egy `Value` típusú objektum, amivel a paraméter értékét lehet különböző java
típusokra konvertálni. A konverzió a `Value` osztály metódusaival lehetséged, pl. `Value.toString()`, `Value.toDouble()`, `Value.toStringArray()`.
A `getConfigurationValue` metódus akkor is visszatér egy `Value` objektummal, ha a kért konfigurációs paraméter nem létezik, ekkor azonban minden konverziós metódus
`null` értékkel ad. Példák:

```java
String username = getConfigurationValue("current.user").toString();
Integer backendPort = getConfigurationValue("backend.port").toInteger();
File parameterFile = getConfigurationValue("backend.parameter.file").toFile();
```

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
kívánt elemet is. Ha a `label` elemnek nincs `for` attribútuma, de a szülője vagy a nagyszülője alá tartozik egyetlen `input`, `textarea` vagy `select` típusú elem,
akkor az lesz a keresett elem.

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

### `iframe` kezelése

Egyes weboldalak még használnak `iframe` elemet. Az `iframe` HTML elem egy olyan másik oldal HTML kódját tartalmazza, amit a szülő oldal meghatározott területére illesztünk be.
Mivel az `iframe` tartalma független HTML oldalnak minősül, ezért külön információ nélkül az elemkeresések sem "látnak be" oda.

Egy `iframe`-en belül úgy lehet elemeket keresni, hogy az oldal-leíró osztályhoz rendelünk egy `Frame` annotációt, amiben megadjuk az `iframe`-re vonatkozó keresési paramétereket.
Ekkor az oldal-leírón beüli minden (dinamikus és statikus) elemkeresés az `iframe`-en belül értendő.

```java
@Frame(by=By.ID, using="my-frame")
public class FramedPage extends PageObject {
    ...
}
```

Az `iframe`-et érdemes az `id` tulajdonságának értéke segítségével azonosítani:

```java
@Frame(using="#my-frame")
```

Ha az oldal egyetlen `iframe`-et tartalmaz, akkor a típusnég is elegendő:

```java
@Frame(using="iframe")
```

Az `iframe`-en belüli `iframe` elemek tartalmát egyenlőre nem lehet elérni az ibello segítségével.

## Időtúllépések kezelése

Az ibello rendszert webalkalmazások teszteléséhez használjuk. A tesztelendő alkalmazást egy böngészőben futtatjuk, a tesztek pedig ezt a böngészőt automatizálják. Az automatizálás
_aszinkron_ abban az értelemben, hogy a tesztkódból kiadott parancsok és az azok hatására történő változások nincsenek szorosan
összekötve a teszteket futtató java folyamattal. A parancs végrehajtásának eredménye tehát csak valamikor a közeli jövőben várható - de hogy pontosan mikor, azt nem lehet előre tudni.
Az ilyen típusú vezérléseknél bevett gyakorlat az, hogy a parancsot indító folyamat várakozik addig, amíg tudomást nem szerez a parancs végrehajtásának sikerességéről vagy
sikertelenségéről. Mindez azért fontos, mert ha nem várná meg a parancs eredményét, és rögtön kiadna egy újabb parancsot, akkor _versenyhelyzetet_ teremtene a két parancs végrehajtása
között, aminek a kimenetele kétesélyes. Az ilyen versenyhelyzetek viszont kerülendőek a tesztekben, azoktól ugyanis elvárjuk, hogy újra és újra lefuttatva ugyanazt az eredményt adják.

Képzeljük el például, hogy egy hivatkozásra szeretnénk kattintani, aminek a hatására az oldal tartalma meg fog változni. A tesztkód kiadja a kattintás parancsot, amit a böngésző végrehajt.
Ezután a böngésző elindítja az új oldal betöltését és megjelenítését, ami csak valamikor a jövőben fog befejeződni (aszinkron folyamat).
Azt, hogy a kattintás sikeres volt, onnan tudhatjuk meg, hogy az új oldal URL címe megjelenik a böngésző címmezőjében, vagy még biztosabban onnan, hogy az új oldalra jellemző tartalom
megjelenik a böngészőben. Ha a hivatkozás - egy programhiba miatt - máshová mutatott, akkor másik oldal töltődik be, és más tartalom jelenik meg a böngészőben.

A fenti esetet legjobban úgy tudjuk kezelni, ha a kattintás után a teszkód várakozik addig, amíg az új oldal megjelenésével kapcsolatos feltétel nem teljesül. Ha viszont programhiba van,
akkor a feltétel sohasem fog teljesülni - viszont erről értelmes időben tudomást szeretnénk kapni. Meg kell adnunk tehát egy határidőt, amin túl nem vagyunk hajlandóak várakozni. Ha a
feltétel nem teljesül a határidő alatt, akkor feltételezhetjük, hogy hiba van, és a tesztnek el kell törnie.

De milyen határidőt válasszunk? Egyes műveletek tovább tartanak az átlagosnál. Ha pl. az előbbi példánkban a kattintás eredményeképpen egy hosszadalmas számolási folyamat indul be a
böngészőben vagy a böngésző által hívott webszolgáltatásban, akkor az átlagosnak gondolt időtúllépés nem lesz megfelelő. Természetes elvárás tehát a tesztrendszertől, hogy az
időtúllépések mértéke egyedileg megadható legyen.

Az ibello rendszer - ellentétben más keretrendszerekkel - nem ad lehetőséget az időtúllépések közvetlen meghatározására. Minden időtúllépés-értéket egy szöveges azonosítóhoz, vagy egy
enum konstanshoz kell kötni. A tesztkódban ezen a módon lehet hivatkozni az időtúllépésre. A konkrét - másodpercekben mért - értéket az ibello konfigurációs fájlokból olvassa fel.
Ezen a módon, ha a tesztjeinket egy olyan környezetben akarjuk futtatni, ami természetszerűen lassabb (mert például több hasonló folyamat is fut rajta), akkor elegendő csupán a
konfigurációs fájlok szintjén változtatnunk, a tesztkódot nem kell átírni.

Az ibello néhány időtúllépést előre definiál.

Az alapértelmezett időtúllépést a "default" kulcsszó azonosítja. Ez - ha a konfigurációs fájlokban máshogyan nem rendelkezünk - 5 másodperc. A legtöbb esetben, amikor nincs más érték
külön megadva, akkor ez van használva.

Ha a böngészőbe közvetlenül egy új URL-t töltünk be (az oldal-leíró `browser().openURL(...)` metódusával), akkor az ibello megpróbálja kivárni az új oldal betöltődését. Ehhez a
"page.load" azonosítójú időtúllépést használja. Ennek az alapértelmezett értéke 10 másodperc. Ha ez alatt az idő alatt az oldal nem töltődik be, akkor a tesztfutás hiba nélkül folytatódik
(viszont a későbbi ellenőrzések, amik feltételezik, hogy az oldal betöltődött, elbukhatnak).

Dinamikus weboldalakon, ahol a tartalmat javascript kód állítja elő, gyakori eset, hogy egy művelet után a kívánt tartalom még nem látható, mivel az oldalon futó szkriptek még
dolgoznak. Az ibello rendszernek erre az esetre van egy megoldása, amivel figyeli, hogy az oldalon folyamatban van-e még valamilyen változás. Lehetőség van arra, hogy a műveletek
után ezt a változást kivárjuk, mielőtt a tesztfutás továbbmegy. Ehhez a várakozáshoz is tartozik egy időtúllépés, "page.refresh" azonosítóval. Az alapértelmezett értéke megyegyezik
az alapértelmezett időtúllépésével.

Az előre definiált időtúllépések mellett sajátokat is definiálhatunk. A legjobb, ha erre a célra létrehozunk egy java enum-ot. Az enum konstansok fogják azonosítani a különböző
típusú időtúllépéseket. Ezeket a konstansokat megadhatjuk a műveletek és az ellenőrzések hívásakor. A konkrét értékeket az ibello konfigurációs fájlokban kell majd megadnunk,
másodpercekben.

## Műveletek elemekkel

Az oldal-leírókban a `doWith(WebElement)` metódus segítségével végezhetünk műveletet az elemekkel. A metódus által visszaadott objektumból kiindulva folytatólagosan lehet
hívni a kívánt műveletet.

```java
WebElement usernameField = find().using("#user-name").first();
// beállítjuk a megtalált mező értékét
doWith(usernameField).setValue("testuser");
```

Amikor egy kívánt művelet meghívódik, még nem biztos, hogy az elem, amivel a műveletet el szeretnénk végezni, elérhető a böngészőben. Ezért minden egyes művelethez tartozik egy
implicit időtúllépés - ennyi ideig próbálkozik az ibello a művelet végrehajtásával. Ha ez idő alatt nem sikerül végrehajtani a műveletet, akkor a teszt elbukik, és _hibásnak_
minősül. A várakozási idő értéke az alapértelmezett időtúllépéssel egyezik meg, de ezt módosíthatjuk a `withTimeout(...)` metódussal. Paraméternek a kívánt időtúllépés értékét
adhatjuk meg, szövegesen vagy enum konstanssal.

```java
// várakozási idő beállítása a saját Timeouts.MEDIUM enum konstanssal
doWith(usernameField).withTimeout(Timeouts.MEDIUM).setValue("testuser");
```

A `withPageRefreshWait()` metódus segítségével elérhetjük, hogy a művelet végrehajtása után az ibello rendszer várakozzon addig, amíg az oldalon minden változás megtörténik,
vagy amíg a beállított határidő ("page.refresh" időtúllépés) lejár. Ez akkor lehet hasznos, ha előre sejtjük, hogy a művelet változást fog okozni az oldalon - például egy másik oldalra juttat, vagy a DOM-ból
eltüntet egyes elemeket. Ha a határidő lejár anélkül, hogy az oldal frissítése befejeződött volna, akkor a tesztfutás hiba nélkül folytatódik.

```java
// az egérkattintás után megvárja az oldal frissülését
doWith(button).withPageRefreshWait().click();
```

### Egérműveletek

| Metódus                     | Leírás                                                                         |
| --------------------------- | ------------------------------------------------------------------------------ |
| `click()`                   | Balgombos egérkattintás az elem közepére.                                      |
| `contextClick()`            | Jobbgombos egérkattintás az elem közepére.                                     |
| `doubleClick()`             | Dupla egérkattintás az elem közepére.                                          |
| `moveTo()`                  | Az egérmutatót az elem közepére állítja.                                       |
| `dragAndDropTo(WebElement)` | "Fog és vidd" művelettel az elemet a paraméterben megadott másik elemre húzza. |

### Billentyűzet művelet

Billentyűzet műveleteket a `sendKeys(...)` metódussal tudunk kiváltani. A metódus tetszőleges számú `CharSequence` típusú paramétert kaphat, amiket sorban elküld az elemnek,
mintha begépeltük volna azokat. Az elküldendő karaktereket megadhatjuk egyszerűen egy `String` példánnyal is:

```java
doWith(usernameField).sendKeys("testuser");
```

Arra is lehetőségünk van, hogy speciális karaktereket küldjünk el. Ehhez az oldal-leíró `keys()` metódusát kell használnunk, a visszaadott objektumnak vannak erre a célra konstans
mezői:

```java
doWith(usernameField).sendKeys(keys().HOME);
```

Ha `CTRL`, `ALT` vagy `SHIFT` lenyomása mellett szeretnénk karaktereket küldeni az elemnek, akkor a `sendKeys` első paraméterének a módosítót kell megadnunk:

```java
doWith(usernameField).sendKeys(keys().CONTROL(), "a");
```

### Egyéb műveletek

| Metódus            | Leírás                                                                |
| ------------------ | --------------------------------------------------------------------- |
| `setValue(String)` | Beállítja az `input` vagy `textarea` típusú mező értékét.             |
| `setFile(String)`  | Beállítja az `input type=file` típusú mező értékét a megadott fájlra. |
| `submit()`         | Elküldi a `form` típusú elem tartalmát.                               |

## A böngésző objektum műveletei

Az oldal-leírókon belül a `browser()` metódus segítségével megkapjuk a böngésző objektumot, amivel a tesztek által vezérelt böngészővel végezhetünk műveleteket.
Ezek a műveletek egyszerű metódushívások.

### Új oldal megnyitása

A `browser().openURL(...)` segítségével beállíthatjuk a böngésző aktuálisan megnyitott URL-jét. A metódus először beállítja az URL-t, majd vár addig, amíg az új oldal elemei
betöltődnek. Ha az oldal nem töltődik be a "page.load" időtúllépésben meghatározott ideig, akkor a tesztfutás hiba nélkül folytatódik.

A metódusnak kétféle paraméterezése van. Kaphat `String` vagy `URL` objektumot. `String` paraméterezés esetében a paraméter lehet:

- teljes URL, pl. `http://localhost:8080/page`;
- protokoll nélküli cím, pl. `localhost:8080/page` - ebben az esetben a protokoll értéke `http://` lesz;
- relatív cím, ami egy `/` jellel kezdődik, pl. `/page` - ebben az esetben mindenképpen szükség van a `ibello.url.base` konfigurációs paraméterre, anélkül a metódus kivételt dob.

Mindkét paraméterezésnél számít a `ibello.url.base` konfigurációs paraméter értéke. Ha ez meg van adva, akkor a ténylegesen beállított URL a konfigurációs paraméter és a metódus
paraméter ötvözete lesz:

- ha a metódust teljes URL-lel vagy protokoll nélkül hívták, akkor a protokoll, a tartománynév és a portszám a konfigurációs paraméterből fog származni, az elérési út pedig a
  metódus paraméteréből;
- ha a metódust relatív címmel hívták, akkor a konfigurációs paraméter és a relatív cím összefűzött értéke kerül a böngésző címsorába.

### Böngészőablak mérete

A `browser().maximize()` metódus maximalizálja a böngészőablak méretét. A `broswer().resize(int, int)` metódussal közvetlenül megadhatjuk a böngészőablak új szélességét és
hosszúságát, képernyőpontokban.

## Ellenőrzések

A tesztlépések egyik legfontosabb mozzanata az, amikor ellenőrizzük az aktuális oldal állapotát. Erre az ibello rendszer folytatólagosan írható API-val nyújt segítséget.

Egy oldal-leíró osztályon belül hívható az `expectations()` metódus. Ez egy olyan objektumot ad, amiből minden ellenőrzés kiindul.

### Egyszerű ellenőrzések

Az `expect(...)` metódussal egy adott objektummal kapcsolatos ellenőrzést indítunk. Az objektum a metódus paramétere, típus szerint lehet:

- egy `WebElement` példány,
- egy `WebElements` példány,
- vagy a `browser()` metódus által visszaadott böngésző objektum.

A háromféle paraméterezés háromféle ellenőrzést jelent, mindegyiknél más metódusok érhetőek el az `expect(...)` által visszaadott objektumból. Mindegyik esetben még két metódust
kell hívnunk az ellenőrzés indításához, amikkel az objektumra vonatkozó feltételt szabjuk ki. Az első metódus valamilyen tulajdonság létezését vagy nem létezését határozza meg:

| Metódus       | Leírás                                                 |
| ------------- | ------------------------------------------------------ |
| `toHave()`    | Az objektum rendelkezik valamilyen tulajdonsággal.     |
| `toNotHave()` | Az objektum nem rendelkezik valamilyen tulajdonsággal. |
| `toBe()`      | Az objektumra igaz valamilyen logikai állítás.         |
| `toNotBe()`   | Az objektumra nem igaz valamilyen logikai állítás.     |

Mind a négy metódus tulajdonképpen egy tulajdonság meglétét vagy hiányát köti ki, azért van belőlük kettő helyett négy, hogy az ellenőrzés metódusai egymás után összeolvasva értelmes
szöveget adjanak ki.

Az előző metódust követi a tulajdonság megnevezése. A lehetséges tulajdonságok a vizsgált objektum típusától függenek. `WebElement` típus esetén ezek érhetőek el:

| Metódus                        | Jelentés                                                                                |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| `id(...)`                      | Az elem `id` attribútuma a megadott értékkel rendelkezik.                               |
| `value(...)`                   | Az elem `value` attribútuma a megadott értékkel rendelkezik.                            |
| `fileName(...)`                | Az elem `input type=file` típusú, és a kiválasztott fájl a megadott névvel rendelkezik. |
| `attribute(...)`               | Az elem rendelkezik a megadott nevű attribútummal.                                      |
| `attributeWithValue(..., ...)` | Az elem adott attribútuma a megadott értéket veszi fel.                                 |
| `tagName(...)`                 | Az elem a megadott típusú.                                                              |
| `cssClassName(...)`            | Az elem rendelkezik a megadott CSS osztálynévvel.                                       |
| `cssValue(..., ...)`           | Az elem adott CSS tulajdonsága a megadott értéket veszi fel.                            |
| `text(...)`                    | Az elem tartalma a megadott.                                                            |
| `cssClassName(...)`            | Az elem rendelkezik a megadott CSS osztálynévvel.                                       |
| `displayed()`                  | Az elem láthatósága.                                                                    |
| `enabled()`                    | Az elem engedélyezett / nem engedélyezett állapota.                                     |
| `clickable()`                  | Az elem kattinthatósága.                                                                |
| `selected()`                   | Az elem kiválasztott / nem kiválasztott állapota.                                       |
| `present()`                    | Az elem jelenléte a DOM-ban.                                                            |

Példák:

```java
WebElement element = ...;

// a mező tartalma "testuser"
expectations().expect(element).toHave().value("testuser");
// az elem nem rendelkezik "active" CSS osztálynévvel
expectations().expect(element).toNotHave().cssClassName("active");
// az elem látható
expectations().expect(element).toBe().displayed();
// az elem nem engedélyezett
expectations().expect(element).toNotBe().enabled();
```

`WebElements` típusú objektum esetén ezek a metódusok érhetőek el:

| Metódus                        | Jelentés                                                                                |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| `size(...)`                    | Az elemek pontos száma.                                                                 |
| `sizeGreaterThan(...)`         | Az elemek száma nagyobb, mint a megadott érték.                                         |
| `sizeLessThan(...)`            | Az elemek száma kisebb, mint a megadott érték.                                          |
| `sizeBetween(..., ...)`        | Az elemek száma a megadott határok között van.                                          |

Példák:

```java
WebElements elements = ...;

// az elemek száma nagyobb, mint 5
expectations().expect(elements).toHave().sizeGreaterThan(5);
// az elemek száma nem kisebb, mint 6
expectations().expect(elements).toNotHave().sizeLessThan(6);
```

Böngészővel kapcsolatos metódusok:

| Metódus                        | Jelentés                                                                                |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| `url(...)`                     | A böngésző címsorában található URL.                                                    |
| `loaded()`                     | Az aktuális oldal betöltöttsége.                                                        |

Példák:

```java
// az aktuális URL "/login.html"
expectations().expect(browser()).toHave().url("/login.html");
// az aktuális URL nem "/welcome.html"
expectations().expect(browser()).toNotHave().url("/welcome.html");
// az oldal minden eleme be van töltve
expectations().expect(browser()).toBe().loaded();
```

Az összes ellenőrzésnek van egy várakozási ideje. Ha ezen idő alatt nem teljesül a vizsgált feltétel, akkor a teszt elbukik, és _sikertelennek_ minősül. A várakozási idő alapesetben
az alapértelmezett időtúllépés értékével egyezik meg, de ettől a `withTimeout(...)` metódus segítségével eltérhetünk:

```java
// várakozási idő beállítása a saját Timeouts.MEDIUM enum konstanssal
expectations().expect(element).withTimeout(Timeouts.MEDIUM).toBe().displayed();
```

### "Lágy" ellenőrzések

Az `expect(...)` metódusokkal kényszer-ellenőrzéseket tudunk készíteni. Ez azt jelenti, hogy ha egy ilyen ellenőrzés elbukik, akkor az éppen aktuális teszt futtatása félbeszakad
(és ha van következő teszt, akkor az indul el). Ez egyben azt is jelenti, hogy ha az elbukó ellenőrzés után még írtunk más ellenőrzéseket is, akkor azok eredményét nem tudjuk meg,
hiszen le sem futnak.

Előfordulhat olyan eset, amikor azt szeretnénk, hogy az egyik ellenőrzés elbukása után még a többi ellenőrzés is fusson le, mert az összes eredményét látni szeretnénk a
riportban. Ilyen esetekben "lágy" ellenőrzést kell használnunk. A "lágy" ellenőrzés nem akasztja meg a tesztfutást akkor sem, ha elbukik. Bukás esetén az azt követő ellenőrzések is
lefutnak, viszont a teszteset a végén hibásnak lesz jelölve.

"Lágy" ellenőrzést az `assume(...)` metódusokkal tudunk készíteni, teljesen hasonlóan az `expect(...)` metódusokkal készítettekhez.

```java
expectations().assume(button).toHave().text("OK");
// ha az előző ellenőrzés elbukik, akkor még megtudjuk, hogy a gomb kattintható volt-e
expectations().expect(button).toBe().clickable();
```

### Összetett ellenőrzések

Az `all(Runnable)` metódussal több feltételt közösen vizsgálunk. Minden feltételnek teljesülnie kell a várakozási időn belül, de a teljesülésük sorrendje mindegy. A feltételeket
egy `Runnable` interfészen keresztül adhatjuk át, a legegyszerűbb a java 8 lambda kifejezését használni. Példa:

```java
expectations().all(() -> {
    expectations().expect(browser()).toHave().url("ibello.hu");
    expectations().expect(registerButton).toBe().clickable();
});
```

Ha az ellenőrzésnek nem adunk meg explicit várakozási időt, akkor azt ki fogja számolni a feltételekből - a feltételek várakozási idejének összege lesz a teljes várakozási idő.

Az `any(Runnable)` hasonlóan több feltételt kaphat a `Runnable` interfészen keresztül. Az ellenőrzés akkor lesz sikeres, ha közülük egy teljesül.

```java
expectations().any(() -> {
    expectations().expect(loginButton).toBe().clickable();
    expectations().expect(registerButton).toBe().clickable();
});
```

Ha nincs megadva együttes várakozási idő, akkor annak az értéke automatikusan kerül meghatározásra - az egyes feltételek várakozási idejének maximuma lesz.

Az összetett ellenőrzéseken belül használhatunk `expect(...)` és `assume(...)` metódust is.

Ha `all(...)` metódussal létehozott összetett ellenőrzést használunk, akkor az akkor bukik el, amikor az első befoglalt ellenőrzés elbukik. Ha ez `expect(...)` metódussal volt
létrehozva, akkor a teszt futása is végetér, míg ha `assume(...)` metódussal, akkor a tesztfutás folytatódik.

Az `any(...)` metódussal létrehozott összetett ellenőrzéseknél kicsit más a helyzet. Ha a befoglalt ellenőrzések között akár egyetlen olyan is akad, amit `expect(...)` metódussal
hoztunk létre, akkor a tesztfutás félbe fog szakadni akkor, amikor az összetett ellenőrzés elbukik. (Ilyenkor ugyanis biztos, hogy a kényszer-ellenőrzés is elbukott.)

## Függőségek injektálása

Az ibello keretrendszer képes egyedi osztályainkat automatikusan a teszlépés-könyvtár és az oldal-leíró osztályokba injektálni. Sőt, az ily módon injektált osztályokba is hasonlóan
injektálhatunk más osztályokat.

Csak olyan osztály injektálható, aminek van alapértelmezett konstruktora. Az injektáláshoz be kell illesztenünk egy mezőt a hivatkozó osztályba - ez lehet privát is. A mezőt meg kell
jelölnünk az `@Inject` annotációval.

```java
public class MyPage extends PageObject {

    // a tool mező automtikusan kap egy MyTool példányt
    @Inject
    private MyTool tool;
}
```

Az injektálás a fentieken kívül még finomítható. Amennyiben az injektált osztály implementálja az `Initializable` interfészt, úgy az ibello rendszer az innen örökölt `initialize()`
metódust automatikusan meg fogja hívni, miután létrehozta az osztály egy példányát.

```java
public class MyTool implements Initializable {

    @Override
    public void initialize() {
        // inicializáljuk az osztálypéldányt
    }
}
```

Alapértelmezésben az injektálás ún. _session szkópban_ történik. Ez azt jelenti, hogy az ibello szálanként csak egyetlen példányt hoz létre egy adott injektált típusból.
Ezen változtathatunk, ha az `@Injectable` annotáció segítségével megadjuk az injektált típus szkópját.

```java
@Injectable(Scope.TEST)
public class MyTool implements Initializable { ... }
```

Az `@Injectable` annotáció egyetlen paramétere a kívánt szkóp, ami lehet:

| Paraméter             | Szkóp leírása                                              |
| --------------------- | ---------------------------------------------------------- |
| `Scope.PROTOTYPE`     | Minden új injektálási helyhez új példány keletkezik.       |
| `Scope.TEST`          | Minden egyes teszt metódus futáshoz új példány keletkezik. |
| `Scope.SPECIFICATION` | Minden teszt osztály futásához új példány keletkezik.      |
| `Scope.SESSION`       | Minden java szálhoz új példány keletkezik.                 |
| `Scope.SINGLETON`     | A tesztek futása során csak egyetlen példány jön létre.    |

Az ibello rendszerben az `@Inject` annotációval végrehajtott injektálás a támogatott módja annak, hogy extra logikát illesszünk a tesztjeinkbe. A tesztek futása során szükség lehet
például háttérrendszerek konfigurálására, hogy a kívánt eredményt adják, miközben a böngészőt vezéreljük. Az ilyen típusú igényekhez létrehozhatunk az ibello API-tól független
osztályokat, amiket az ibello függőség injektálásának segítségével illeszthetünk be a tesztek kódjába.
