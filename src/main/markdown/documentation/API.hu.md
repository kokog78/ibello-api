# Az ibello API

Az ibello keretrendszerben létrehozott tesztek háromrétegűek. Egyrészről, az oldalak technikai funkcióit az ún. _oldal-leíró osztályok_ foglalják össze. Másrészről, egy vagy több
oldal-leíró osztály metódusai segítségével tesztlépéseket állítunk össze, amelyeket egy ún. _tesztlépés-könyvtár osztályba_ teszünk. Az egyes _tesztek_ a tesztlépés-könyvtárak
metódusainak hívásaiból állnak elő.

Ez a hármas tagozódás lehetővé teszi, hogy az üzletileg igényelt funkciókat elválasszuk azok technikai megvalósításától. Így a tesztek üzleti szempontból is érthetőbbé válnak.
További előnyt jelent még az, hogy a weboldalak apróbb változásai nincsenek kihatással magukra a tesztekre, csak az oldal-leírókat kell megváltoztatni. Ezáltal a tesztek időtállóbbak.

## A teszt osztály

A teszt osztályt a `@Specification` annotációval jelöljük. Az ibello rendszer innen tudja, hogy az osztály metódusai között teszt metódusok is vannak. Egy teszt metódust a `@Test`
annotáció jelöl.

A teszt osztályban tesztlépés-könyvtárakat használunk. Egy tesztlépés-könyvtárat úgy a legegyszerűbb elérni, ha a teszt osztályhoz adunk egy (akár privát) mezőt a kívánt típussal.
Az ibello automatikusan létre fogja majd hozni a tesztlépés-könyvtár egy példányát, amit a teszt metódusokban nyugodtan használhatunk:

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
        steps.then_i_see_the_standard_operations();
    }
}
```

A fenti példának az egyik fontos jellemzője, hogy a teszt metódusban meghívott utasítások nem tartalmaznak semmilyen technikai
részletet. Nem tudjuk, pontosan honnan is lehet tudni, hogy a bejelentkező oldalon vagyunk, és azt sem, hogy hogyan is lehet belépni egy érvényes felhasználóval. Mindezeket
az információkat a tesztlépés-könyvtár metódusai tartalmazzák.

### A teszt osztályok futási sorrendje

A `Specification` annotációval jelölt teszt osztályok metódusai alapértelmezés szerint a nevük szerint ábécé sorrendben futnak.
Ezen úgy lehet változtatni, hogy az annotáció `order` paraméterében megadjuk azt az indexet, amit az ibello a rendezéshez felhasznál:

```java
@Specification(order = 3)
public class RunLater {
	...
}
```

Ha az index értéke nincs megadva, akkor azt 0-nak veszi. Az azonos indexű teszt osztályok közül az fog előbb futni, aminek a neve ábécé sorrendben előbb van.

### Teszt osztályok kizárása címke alapján

A `Specification.includedTags` és `Specification.excludedTags` mezők segítségével kizárhatunk egyes teszt osztályokat. Amikor a teszteket futtatjuk, meghatározhatunk egy címke-halmazt.
Ez tulajdonképpen néhány szóból álló halmaz, amivel jellemezzük az aktuális tesztfutást. A címkék alapján kikapcsolhatjuk a teszt osztályokat.

Az `includedTags` mezőben felsorolt címkék bekapcsolják a teszt osztályt. Ez azt jelenti, hogy ahhoz, hogy a teszt osztály fusson, a felsorolt címkék közül legalább egynek
benne kell lennie az aktuális címke-állományunkban. Példák:

```java
@Specification(includedTags = "longRunning")
public class LongRunningTests {
	...
}

@Specification(includedTags = {"smoke", "parallel"})
public class ParallelSmokeTests {
	...
}
```

Az `excludedTags` mezővel éppen fordított viselkedést tudunk elérni. Ha az itt felsorolt címkék közül akár egy is szerepel a címke-állományban, akkor a teszt osztály nem fog futni.

```java
@Specification(excludedTags = "ci")
public class LocalTests {
	...
}
```

### Teszt metódusok futási sorrendje

Egy teszt osztályon belül a teszt metódusok a nevük szerint ábécé sorrendben futnak. Ezen úgy tudunk változtatni, hogy a `Test` annotáció `order` paraméterét megadjuk a rendezéshez
használandó indexet. Ha ez nincs megadva, akkor az értéke 0-nak számít.

```java
@Test(order = 3)
public void ordered_test_method() {
	...
}
```

### Tesztek metódusok kizárása címke alapján

A teszt osztályokhoz hasonlóan a metódusokat is kizárhatjuk a `Test` annotáció `includedTags` és `excludedTags` mezőivel. Az `includedTags` mezőben felsorolt címkék bekapcsolják
a teszt metódust, míg az `excludedTags` mező címkéi kizárják azt a futtatottak közül.

### Teszt metódusok elnevezése

A teszt metódusok neve megjelenik a logokban és az eredmény riportban is. A megjelenített nevet az ibello megpróbálja olvashatóbbá tenni (átalakítja a "camelcase" és "snake case"
formában megadott metódusnevet), de ez nem mindig elég. Ezért lehetőségünk van arra, hogy a `@Name` annotációval megjelenítendő nevet adjuk a metódusoknak.

```java
@Test
@Name("Login with valid user")
public void t0_login() {
	...
}

@Test
@Name("Check basic functions")
public void t1_check_basic_functions() {
	...
}
```

A megnevezés mellett a metódusokhoz rövid leírást is lehet adni egy vagy több `@Description` annotáció segítségével. Az egyes annotációk
értékei egyetlen szöveggé lesznek összefűzve.

## A tesztlépés-könyvtár

A tesztlépés-könyvtár osztály a `StepLibrary` ősosztályból származik. Minden publikus metódusa tesztlépésnek számít. A metódusok neveit érdemes beszédesre választani.
Jó gyakorlatot követünk, ha háromféle metódust különböztetünk meg:

- Az előkészítő metódusok elérik, hogy a böngészőben beálljon valamilyen kiindulási állapot.
- A végrehajtó metódusok végrehajtják a tesztelendő műveletet.
- Az ellenőrző metódusok ellenőrzik az előállt állapotot, és kivételt dobnak, ha a kívánt feltétel nem teljesült.

A tesztlépés-könyvtár metódusai oldal-leírókat használnak. Az oldal-leírókat nyugodtan bejegyezhetjük (privát) mezőkként, az ibello rendszer automatikusan létre fogja
hozni a példányokat. Egy tesztlépés-könyvárban más tesztlépés-könyvtárakra is hivatkozhatunk, szintén elég csak egy (privát) mezőt létrehozni a megfelelő típussal.

```java
public class LoginSteps extends StepLibrary {

    // az oldal-leíró automatikusan példányosodik
    private LoginPage loginPage;
    
    // szintén automatikusan létrejön
    private WelcomePage welcomePage;
    
    public void login_page_is_opened() {
        loginPage.open();
    }
    
    public void i_login_with_valid_credentials() {
        loginPage.setUsername("testuser");
        loginPage.setPassword("testpwd");
        loginPage.clickLoginButton();
    }
    
    public void i_get_to_the_welcome_page() {
        welcomePage.expectOpened();
    }
    
    public void i_see_that_valid_user_is_logged_in() {
        welcomePage.expectCurrentUser("testuser");
    }
    
    public void i_see_the_standard_operations() {
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

Most pedig illesszük be a `UserData` osztály egy példányát a teszlépés-könyvtárba! Az oldal-leírókkal és a tesztlépés-könyvtárakkal ellentétben
ehhez most használnunk kell az `@Inject` annotációt, az ibello innen tudja, hogy egy egyéni osztály példányát injektálnia kell:

```java
public class LoginSteps extends StepLibrary {
    
    @Inject
    private UserData userData;
    
    public void i_login_with_valid_credentials() {
        loginPage.setUsername(userData.getValidUsername());
        loginPage.setPassword(userData.getValidPassword());
        loginPage.clickLoginButton();
    }
}
```

### Tesztlépések nevei

A teszlépés-könyvtárak publikus metódusainak hívását az ibello rendszer folyamatosan loggolja, valamint azok neveit az elkészült teszt riportban is szerepelteti.
A logba és a riportba a metódus nevéből kiszámolt kifejezés kerül bele. Például az `i_login_with_valid_credentials` metódusnévből ez lesz:
`I Login With Valid Credentials`. Ha ezt a működést meg szeretnénk változtatni, akkor a metódushoz adnunk kell egy `@Name` annotációt, aminek egyetlen tulajdonságaként
meg kell adnunk a kiírandó szöveget. A szövegben a metódus esetleges paraméterei is megjelennek, azoknak a helyét `${0}`, `${1}`, stb. karaktersorozatokkal jelölhetjük.
A paramétereket beilleszthetjük a `@Name` annotáció megadása nélkül is, ekkor a helyüket a metódus nevében `$` karakter jelöli. (A `String` típusú paraméterek ilyenkor macskakörmök
közé kerülnek.)

```java
@Name("A(z) ${0}. elem megnyitása")
public void openItem(int index) { ... }

public void a_$_gomb_megnyomása(String title) { ... }
```

Ha a tesztlépés-könyvtár osztály is rendelkezik `@Name` annotációval, akkor az abban megadott szöveg előtagként hozzáadódik az összes tesztlépés nevéhez is.
Ezt az előtagot *névtér*nek is nevezzük. Az alábbi példában a tesztlépés neve "Home Page: Open Page" lesz.

```java
@Name("Home Page")
public class HomePageSteps extends StepLibrary {

	public void open_page() {
	}
}
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

Az oldal-leírók metódusai is bekerülnek a logba és a riportba. A megjelenő név ezeknél is pontosítható a `@Name` annotáció segítségével. Az oldal-leíró osztályhoz rendelt
`@Name` annotáció szövege előtagként szerepel a névben.

## Konfigurációs paraméterek olvasása

A tesztlépés-könyvtárak és az oldal-leírók lehetőséget adnak az ibello konfigurációs paraméterek olvasására. Erre a célra a `getConfigurationValue(String)` metódus szolgál.
A metódus egyetlen argumentuma a kívánt konfigurációs paraméter neve, visszatérési értéke pedig egy `Value` típusú objektum, amivel a paraméter értékét lehet különböző java
típusokra konvertálni. A konverzió a `Value` osztály metódusaival lehetséged, pl. `Value.toString()`, `Value.toDouble()`, `Value.toStringArray()`.
A `getConfigurationValue` metódus akkor is visszatér egy `Value` objektummal, ha a kért konfigurációs paraméter nem létezik, ekkor azonban minden konverziós metódus
`null` értéket ad. Példák:

```java
String username = getConfigurationValue("current.user").toString();
Integer backendPort = getConfigurationValue("backend.port").toInteger();
File parameterFile = getConfigurationValue("backend.parameter.file").toFile();
```

A "to" kezdetű metódusok legtöbbjének van olyan párja, ami bemenő paraméterként megkapja az alapértelmezett értéket. Ez akkor lesz felhasználva,
ha az adott nevű konfigurációs paraméter nem létezik. Például:

```java
String username = getConfigurationValue("current.user").toString("default");
Integer backendPort = getConfigurationValue("backend.port").toInteger(0);
```

## Elemek keresése

Az ibello rendszer lehetőséget ad egyszerre egy vagy több elem keresésére. A keresés lehet _statikus_ vagy _dinamikus_. A funkció csak oldal-leírókon belül érhető el.

### Statikus elemkeresés

Statikus keresésről akkor beszélhetünk, ha az oldal-leíró `WebElement` vagy `WebElements` típusú mezőjét `@Find` annotációval látjuk el, aminek hatására az ibello rendszer automatikusan
inicializálja a mezőt. A `@Find` annotációnak két tulajdonságot adhatunk meg. A `by` tulajdonság a `By` enum értékkészletéből vehet fel értéket és a keresés módját határozza meg.
Ennek a tulajdonságnak van alapértelmezett értéke: `By.CSS_SELECTOR` - ha a `by` értékét nem adjuk meg, akkor ez lesz a keresési mód. A másik tulajdonság a `using`, ami a keresési
mód paramétere, jelentése a keresési módtól függ. Az alábbi táblázat foglalja össze az egyes keresési módokat és tulajdonságokat.

| Keresési mód                     | `by` értéke              | `using` értéke                                               |
| -------------------------------- | ------------------------ | ------------------------------------------------------------ |
| CSS szelektor szerinti           | `By.CSS_SELECTOR`        | CSS szelektor                                                |
| `id` attribútum szerinti         | `By.ID`                  | a keresett elem `id` attribútuma                             |
| `name` attribútum szerinti       | `By.NAME`                | a keresett elem `name` attribútuma                           |
| elem típusa szerinti             | `By.TAG_NAME`            | a keresett elem típusneve                                    |
| CSS osztály szerinti             | `By.CLASS_NAME`          | a keresett elem CSS osztályának neve                         |
| gomb felirata szerinti           | `By.BUTTON_TEXT`         | a keresett gomb funkciójú elem felirata                      |
| gomb részleges felirata szerinti | `By.PARTIAL_BUTTON_TEXT` | a keresett gomb funkciójú elem feliratának egy része         |
| címke szerinti                   | `By.LABEL`               | a keresett elemhez tartozó címke felirata                    |
| részleges címke szerinti         | `By.PARTIAL_LABEL`       | a keresett elemhez tartozó címke feliratának egy része       |
| tartalom szerinti                | `By.TEXT`                | a keresett elem tartalma                                     |
| részleges tartalom szerinti      | `By.PARTIAL_TEXT`        | a keresett elem tartalmának egy része                        |
| gomb osztálya szerinti           | `By.BUTTON_CLASS`        | a keresett gomb funkciójú elem (vagy annak egy leszármazottjának) CSS osztálya |

A `By.BUTTON_TEXT`, a `By.PARTIAL_BUTTON_TEXT` és a `By.BUTTON_CLASS` keresés megtalálja a `button` típusú, az `input type=button` típusú, az `input type=submit` típusú, az `input type=reset` típusú, az `input type=image` típusú elemeket, a linkeket és azokat az elemeket is, amiknek a `role` attribútuma `button` - mindent, aminek gombszerű funkciója lehet.

A `By.LABEL` és `By.PARTIAL_LABEL` keresés először a `label` típusú elemet keresi meg tartalom szerint, majd annak a `for` attribútumát követve megtalálja a kívánt elemet is. Ha a `label` elemnek nincs `for` attribútuma, de a szülője vagy a nagyszülője alá tartozik egyetlen `input`, `textarea` vagy `select` típusú elem, akkor az lesz a keresett elem.

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

| `RelationType`  | Jelentése                                                    |
| --------------- | ------------------------------------------------------------ |
| `DESCENDANT_OF` | A keresett elem a referencia-elem leszármazottja.            |
| `ANCESTOR_OF`   | A keresett elem a referencia-elemet közvetlenül vagy közvetve tartalmazza. |
| `NEAREST_TO`    | A referencia elemhez legközelebb eső (a feltételnek megfelelő) elem keresése. A távolság a DOM struktúrában van mérve. |

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
elemkeresés. Mindkét `find()` metódus egy előkonfigurált `SearchTool` példányt ad vissza, amivel folytatólagosan adhatjuk meg a keresés paramétereit. A keresést a `first()`, `nth(int)`
vagy az `all()` metódusok egyikével zárhatjuk le aszerint, hogy `WebElement` vagy `WebElements` példányt szeretnénk-e kapni eredményül. A `first()` az első megtalált elemet adja vissza.
Az `nth(int)` index alapján adja vissza a megtalált elemet. A `first()` és az `nth(0)` hívások egyenértékűek. Az `all()` az összes megtalált elemet visszaadja.

Az előző fejezet példái:

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

### HTML keretek kezelése

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

Az `iframe`-et érdemes az `id` tulajdonságának segítségével azonosítani:

```java
@Frame(using="#my-frame")
```

Ha az oldal egyetlen `iframe`-et tartalmaz, akkor a típusnév is elegendő:

```java
@Frame(using="iframe")
```

Az `iframe`-en belüli `iframe` elemek tartalmát úgy lehet elérni, hogy egy oldal-leíróhoz több `Frame` annotációt is rendelünk. Az első annotáció a legkülső keret adatait tartalmazza,
a második az ezen belüli keretét, és így tovább.

```java
@Frame(using="#parent-frame")
@Frame(using="#child-frame")
public class ChildFramedPage extends PageObject {
    ...
}
```

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

A `doWith(...)` metódussal létrehozott művelet leállítja a teszt futtatását, ha sikertelenül végződik. Ha ezt el szeretnénk kerülni, vagyis a tesztfutást sikertelen művelet után is szeretnénk folytatni, akkor a `tryWith(...)` metódust kell használnunk. A metódus minden másban a `doWith(...)`-re hasonlít.

### Egérműveletek

| Metódus                     | Leírás                                                       |
| --------------------------- | ------------------------------------------------------------ |
| `click()`                   | Balgombos egérkattintás az elem közepére.                    |
| `contextClick()`            | Jobbgombos egérkattintás az elem közepére.                   |
| `doubleClick()`             | Dupla egérkattintás az elem közepére.                        |
| `selectOption(...)`         | Legördülő listában a megadott feliratú opció kiválasztása.   |
| `moveTo()`                  | Az egérmutatót az elem közepére állítja.                     |
| `dragAndDropTo(WebElement)` | "Fog és vidd" művelettel az elemet a paraméterben megadott másik elemre húzza. |
| `scrollTo(WebElement)`      | Az elem görgetése úgy, hogy a paraméterben megadott másik elem látható legyen |
| `setSelected(boolean)`      | Egy jelölőnégyzet kiválasztása, vagy éppen a kiválasztásának megszüntetése |

A `scrollTo(WebElement)` művelet olyan esetben használható, amikor egy konténer elemben görgetősávok vannak. Ez eléggé elterjedt gyakorlat, sok webalkalmazásban nem a teljes oldal görgethető, hanem annak egy része (egy konténer). Egy ilyen konténer görgetősávjait olyan állapotba hozhatjuk, hogy a konténerben levő gyerekelem látható legyen. Mindezt a `doWith(konténer).scrollTo(gyerekelem)` metódusláncal érjük el.

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
| `fireEvent(...)`   | Meghív egy javascript eseményt az elemen.                             |

## A böngésző objektum műveletei

Az oldal-leírókon belül a `browser()` metódus segítségével megkapjuk a böngésző objektumot, amivel a tesztek által vezérelt böngészővel végezhetünk műveleteket.
Ezek a műveletek egyszerű metódushívások.

### Oldal megnyitása és újratöltése

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

A megnyitott URL-t a `browser().reload()` metódus segítségével újratölthetjül.

### Oldal bezárása

Azt az oldalt, amire az oldal-leíró objektum hivatkozik, a `browser().close()` metódussal lehet bezárni. Ez lehet egy fül is.

### Böngészőablak mérete

A `browser().maximize()` metódus maximalizálja a böngészőablak méretét. A `broswer().resize(int, int)` metódussal közvetlenül megadhatjuk a böngészőablak új szélességét és
hosszúságát, képernyőpontokban.

### A "local storage" és a "session storage" kezelése

Webalkalmazások adatokat tárolhatnak a böngésző "local storage" és "session storage" tárolójában. A két tároló a tárolt adatok érvényességi idejében különbözik egymástól.
S "session storage" adatai törlődnek a böngészőből történő kilépéskor, míg a "local storage" adatai nem. Mivel ezektől az adatoktól függően a webalkalmazások különbözőképpen
viselkedhetnek, ezért előfordulhat, hogy a tesztjeinkben szeretnénk ezeket megváltoztatni.

A két tárolót képviselő objektum a `browser().localStorage()` és a `browser().sessionStorage()` metódusokkal kérhető el. Mindkettőnek azonos felülete van. Az elérhető metódusokat
az alábbi táblázat foglalja össze.

| Metódus                   | Leírás                                                       |
| ------------------------- | -------------------------------------------------------------|
| `clear()`                 | Törli a tárolót, eltávolít belőle minden kulcs-érték párt.   |
| `removeItem(String)`      | A tárolóból eltávolítja a megadott kulcsú elemet.            |
| `setItem(String, String)` | Beállítja a megadott kulcshoz tartozó értéket.               |
| `getItem(String)`         | Visszaadja a megadott kulcshoz tartozó értéket.              |

### Sütik kezelése

A "local storage" és a "session" storage kezeléséhez hasonlóan tudjuk menedzselni a böngésző sütijeit is. A képviselő objektumot a `browser().cookies()` metódus adja vissza,
ami ugyanazzal a felülettel rendelkezik, mint a másik kettő.

A felület `setItem(String, String)` metódusával lehet beállítani egy süti értékét. Ha a süti már létezik, akkor az értéke felül lesz írva, ha pedig még nem létezik, akkor a
teljes süti létrehozásra kerül.

A `getItem(String)` metódus csak a süti értékét adja vissza, a többi tulajdonságát nem.

### Képernyőképek készítése

Az ibello automatikusan készít képernyőképeket, például akkor, amikor valamilyen hiba történik. Arra is lehetőségünk van azonban,
hogy mi határozzuk meg, mikor szeretnénk képernyőképet készíteni. Erre való a `browser().saveScreenshot()` metódus. Ezzel az aktív ablakról
ill. fülről készítünk képernyőképet, ami a tesztriporthoz is hozzá lesz adva. Az eredmény a képek adatait tartalmazó objektum.

### Riasztások kezelése

Egyes alkalmazások ún. *riasztásokat* (alert window) nyitnak meg. Egy riasztás mindig modális, blokkolja az oldal többi elemével történő interakciót, ráadásul nem is lehet
szokványos elemként kezelni. Ezért az ibello is speciális parancsokat ad a riasztások kezeléséhez.

Ezeket a parancsokat az oldal-leíró `doWith(Browser)` metódusával lehet elérni. Hasonlóan az elemekkel történő műveletekhez, itt is benne foglaltatik a műveletben egy várakozási
idő. Ha ez idő alatt a műveletet nem sikerül végrehajtani, akkor hiba történik. Amíg az idő nem telik le, az ibello a sikertelen műveletet folyamatosan újra megpróbálja
végrehajtani. Itt is használhatóak a `withTimeout(...)` és a `withPageRefreshWait()` kiegészítések.

| Metódus            | Leírás                                                                |
| ------------------ | --------------------------------------------------------------------- |
| `dismissAlert()`   | A riasztás bezárása a "mégsem" gombra kattintással.                   |
| `acceptAlert()`    | A riasztás bezárása az "ok" gombra kattintással.                      |

A `doWith(...)` metódussal létrehozott művelet leállítja a teszt futtatását, ha sikertelenül végződik. Ha ezt el szeretnénk kerülni, vagyis a tesztfutást sikertelen művelet után is szeretnénk folytatni, akkor a `tryWith(...)` metódust kell használnunk. A metódus minden másban a `doWith(...)`-re hasonlít.

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

| Metódus                        | Jelentés                                                                                                                   |
| ------------------------------ | -------------------------------------------------------------------------------------------------------------------------- |
| `id(...)`                      | Az elem `id` attribútuma a megadott értékkel rendelkezik.                                                                  |
| `value(...)`                   | Az elem `value` attribútuma a megadott értékkel rendelkezik.                                                               |
| `fileName(...)`                | Az elem `input type=file` típusú, és a kiválasztott fájl a megadott névvel rendelkezik.                                    |
| `attribute(...)`               | Az elem rendelkezik a megadott nevű attribútummal.                                                                         |
| `attributeWithValue(..., ...)` | Az elem adott attribútuma a megadott értéket veszi fel.                                                                    |
| `tagName(...)`                 | Az elem a megadott típusú.                                                                                                 |
| `cssClassName(...)`            | Az elem rendelkezik a megadott CSS osztálynévvel.                                                                          |
| `cssValue(..., ...)`           | Az elem adott CSS tulajdonsága a megadott értéket veszi fel.                                                               |
| `text(...)`                    | Az elem tartalma a megadott szöveg (vagy megfelel a megadott reguláris kifejezésnek).                                      |
| `textOrValue(...)`             | Egyesíti a `text(...)` és a `value(...)` metódusokat. Vagy az elem tartalmát, vagy a `value` attribútum értékét ellenőrzi. |
| `cssClassName(...)`            | Az elem rendelkezik a megadott CSS osztálynévvel.                                                                          |
| `displayed()`                  | Az elem láthatósága.                                                                                                       |
| `enabled()`                    | Az elem engedélyezett / nem engedélyezett állapota.                                                                        |
| `readonly()`                   | Az elem csak olvasható / írható állapota. Nem engedélyezett elem mindig csak olvasható.                                    |
| `clickable()`                  | Az elem kattinthatósága.                                                                                                   |
| `selected()`                   | Az elem kiválasztott / nem kiválasztott állapota.                                                                          |
| `present()`                    | Az elem jelenléte a DOM-ban.                                                                                               |

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

- `toHave().url(...)`: A böngésző címsorában található URL ellenőrzése. A paraméter lehet `String` vagy `Pattern`. `String` paraméter esetén használhatunk helyettesítő mintákat:
  `*` tetszőleges számú karaktert helyettesít, az útvonalelválasztó `/` kivételével, `?` egyetlen karaktert helyettesít (az útvonalelválasztó kivételével), `**` tetszőleges
  karaktert helyettesít (akár az útvonalelválasztót is).
- `toBe().loaded()`: Az aktuális oldal betöltöttségének ellenőrzése. Az oldal elemeinek betöltötődése mellett az esetleges dinamikus változásokat is kivárja.
- `toBe().open()`: Ellenőrzi, hogy az oldal-leíróhoz köthető böngésző ablak/fül megnyílt-e. Az ablak/fül azonosítóit a `@Window` annotációval kell megadni. (Részleteket lásd később.)

Példák:

```java
// az aktuális URL "/login.html"
expectations().expect(browser()).toHave().url("/login.html");
// az aktuális URL nem ".html"-re végződik
expectations().expect(browser()).toNotHave().url("/*.html");
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
    expectations().expect(browser()).toHave().url("https://ibello.eu");
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

## Műveletek ismétlése

Lehetőségünk van arra, hogy - időtúllépés figyelembevételével - többször egymás után lefuttassunk egy tetszőleges kódot. A rendszer újra és újra
lefuttatja a kódot, egészen addig, amíg a kilépési feltétel nem teljesül, vagy a rendelkezésre álló idő le nem telik. Az egyes futtatások között
rövid ideig várakozik.

Az ismétlést a `repeat(Task).untilSucceeds()` és a `repeat(Task).untilFails()` hívási lánccal lehet elvégezni. Az első verzió akkor fejezi be a futtatást,
amikor a kód először sikeresen lefut, míg a második akkor, amikor először kivételt dob. A kódot a `Task` interfésszel kell megadni, aminek egyetlen
`run()` metódusa van - ezt kell implementálni. Példa:

```java
repeat(() -> {
	doWith(button).click();
	expectations().expect(image).toBe().displayed();
}).untilSucceeds();
```

Az alapértelmezett időtúllépés lejárta után a művelet kivétellel zárul. A `withTimeout(...)` metódussal megadhatunk saját időtúllépést is,
még az `untilSucceeds()` és az `untilFails()` hívása előtt:

```java
repeat(...).withTimeout(CustomTimeout.PAGE_LOADING).untilSucceeds();
```

Az időtúllépések használata lényeges. Fogalmilag kétféle időtúllépésről beszélhetünk: egy "külső" időtúllépésről, amit a
teljes művelethet definiálunk, az előbb felvázolt módon. Maga a művelet meghívhat olyan más műveleteket és ellenőrzéseket, amik szintén
használnak időtúllépéseket. Ezek a "belső" időtúllépések akkor jönnek számításba, amikor egy ilyen művelet vagy ellenőrzés elbukik - ekkor ugyanis
a bukása előtt kivárja a saját időtúllépését. Ha túl sokáig vár, letelhet a teljes műveletre meghatározott "külső" időtúllépés is. Ezért fontos
lehet a különböző időtúllépések összehangolása. A "belső" időtúllépésekhez mindig jóval kisebb értéket használjunk, mint a "külső"-höz!

Például:

```java
repeat(() -> {
	doWith(button).click();
	expectations().withTimeout("short").expect(image).toBe().displayed();
}).withTimeout("long").untilSucceeds();

```

Itt két "belső" időtúllépésünk van. A `click()` metódus az alapértelmezett időtúllépést használja, az `expect(image)` pedig a "short" elnevezésűt.
Ha a `click()` eltörik, akkor az alapértelmezett időtúllépésig vár a rendszer, ha pedig az `expect(...)` törik el, akkor a "short" elnevezésűig.
A kettő közül a nagyobbik lesz az effektív "belső" időtúllépésünk. A "long" elnevezésű időtúllépést ennek valahányszorosára (pl. 5-10-szeresére) kell
beállítanunk ahhoz, hogy a `repeat(...)` hívásunk jól működjön.

A `repeat(Task)` metódus legalább kétszer ismétli a műveletet, mielőtt sikertelenül záródna. Ez igaz akkor is, ha a teljes művelethez meghatározott
"külső" időtúllépés egyébként lejárt volna. Ezzel a rendszer ellensúlyozni igyekszik a hibás működést, amit akkor tapasztalhatunk,
ha nem igazítottuk egymáshoz a "külső" és a "belső" időtúllépéseket.

## Értéklekérések

Az ibello arra is lehetőséget ad, hogy egy-egy elem valamely tulajdonságát visszakapjuk. Ehhez az oldal-leírók kétféle metódusát használhatjuk.

A `checkThat(WebElement)` metódus segítségével logikai érték birtokába jutunk. A metódus által visszaadott objektumnak több lekérő metódusa van,
ezeket hívhatjuk:

| Metódus                        | Mikor `true`?                                                                           |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| `isDisplayed()`                | Az elem látható a képernyőn.                                                            |
| `isEnabled()`                  | Az elem elérhető ill. engedélyezett.                                                    |
| `isReadonly()`                 | Az elem csak olvasható.                                                                 |
| `isClickable()`                | Az elemre rá lehet kattintani, vagyis látható és engedélyezett.                         |
| `isSelected()`                 | Az elem ki van választva.                                                               |
| `isPresent()`                  | Az elem benne van az oldal DOM struktúrájában.                                          |
| `hasId()`                      | Az elem `id` attribútima nem üres és nem csak szóközöket tartalmaz.                     |
| `hasValue()`                   | Az elem `value` attribútima nem üres és nem csak szóközöket tartalmaz.                  |
| `hasAttribute(String)`         | Az elem megadott nevű attribútima nem üres és nem csak szóközöket tartalmaz.            |
| `hasText()`                    | Az elem szöveges tartalma nem üres és nem csak szóközöket tartalmaz.                    |
| `hasClassName()`               | Az elem rendelkezik legalább egy CSS osztállyal.                                        |
| `hasClassName(String)`         | Az elem rendelkezik a megadott CSS osztállyal.                                          |
| `hasCssValue(String)`          | Az elem megadott nevű CSS tulajdonsága nem üres és nem csak szóközöket tartalmaz.       |

A `get(WebElement)` metódus hasonlóan működik, az általa visszaadott objektum lekérő metódusaival visszakapjuk az elem bizonyos tulajdonságainak
értékét:

| Metódus                        | Visszaadott érték                                                                       |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| `id()`                         | Az elem `id` attribútumának értéke.                                                     |
| `value()`                      | Az elem `value` attribútumának értéke.                                                  |
| `fileName()`                   | Fájl választó elem esetében a kiválasztott fájl neve.                                   |
| `attribute(String)`            | Az elem adott nevű attribútumának értéke.                                               |
| `tagName()`                    | Az elem neve.                                                                           |
| `cssClassNames()`              | Az elem CSS osztályainak nevei.                                                         |
| `cssValue(String)`             | Az elem adott nevű CSS tulajdonságának értéke.                                          |
| `text()`                       | Az elem tartalma.                                                                       |
| `selectedOptions()`            | Az elem kiválasztott opcióinak címkéi (`select` típusú elem esetén).                    |

Mind a `checkThat(...)`, mind a `get(...)` metódusok várakoznak addig, amíg az adott elem elérhető lesz a DOM struktúrában. (Ez alól a
`checkThat(...).isPresent()` kivétel, hiszen az épp azt ellenőrzi, hogy elérhető-e az elem.) A várakozás időtúllépését a műveleteknél és az
ellenőrzéseknél megismert módon be lehet állítani a `withTimeout(...)` metódus segítségével.

Példák:

```java
boolean buttonIsVisible = checkThat(button).isDisplayed();
boolean outputPrinted = checkThat(outputElement).withTimeout(Timeout.SLOW).hasText();
String value = get(inputField).value();
String[] classNames = get(button).withTimeout(Timeout.NORMAL).cssClassNames();
```

## Több böngészőablak vezérlése

Az ibello rendszer lehetőséget ad arra, hogy egy tesztben több böngészőablakot is vezéreljünk. Alapesetben az összes művelet egy alapértelmezett ablakban hajtódik végre. Ha azonban egy oldal-leíró mezőre rátesszük a `Window` annotációt, aminek megadunk egy szöveges azonosítót, akkor az oldal-leíró által indított műveletek már egy új böngészőablakban fognak futni.

```java
public class MySteps extends StepLibrary {

	// az alapértelmezett böngészőablakban fut
	private PresenterPage presenter;

	// egy új böngészőablakban fut
	@Window("editor")
	private EditorPage editor;
	
}
```

Az újonnan megnyíló böngészőablakot az átadott szöveges értékkel azonosítjuk. Ha egy másik oldal-leíró mezőnél ugyanezt az azonosítót adjuk meg, akkor az ugyanabban az ablakban fog futni (nem fog új ablak nyílni).

Arra is lehetőségünk van, hogy új ablak helyett új fület nyissunk. Ha a `@Window` annotáció értéke kettősponttal kezdődik, akkor az egy új fület fog nyitni, méghozzá az alapértelmezett ablakban. Ha az azonosító kettőspontot tartalmaz (de nem azzal kezdődik), akkor szintúgy új fület jelent, azonban egy új ablakban; az ablakot a kettőspont előtti szövegrész, míg a fület a kettőspont utáni rész fogja azonosítani.

```java
public class MyTabbedSteps extends StepLibrary {

	// az alapértelmezett böngészőablakban fut, de új fülön
	@Window(":new-tab")
	private SomePage page1;

	// egy új böngészőablakban fut, új fülön
	@Window("new-window:new-tab")
	private SomePage page2;
	
}
```

Egyes böngészők (és böngésző-meghajtók) nem támogatják egyszerre több ablak megnyitását. Ezeknél az új ablak nyitására vonatkozó kérések rendre új füleket nyitnak meg, méghozzá az alapértelmezett ablakban. Az előbbi példát tekintve: a két oldal-leíró ilyenkor az alapértelmezett ablakban két új fület fog külön-külön vezérelni.

A böngészőablakok azonosítói öröklődnek is az oldal-leírók között. Amennyiben egy oldal-leíró tartalmaz más oldal-leírókra történő hivatkozásokat, amiknél nincs `Window` annotáció, akkor azok
öröklik a tartalmazó objektum böngészőablakát. Vagyis a `Window` annotáció hiánya nem feltétlenül jelenti azt, hogy a műveletek az alapértelmezett ablakban futnak.

A fentiek alapján lehetőség van arra is, hogy tesztlépés-könyvtárakra történő hibatkozásokhoz adjunk meg `Window` annotációt. Ezzel azt érjük el, hogy a tesztlépés-könyvtár
által hivatkozott (annotációval nem rendelkező) oldal-leírók mint a tesztlépés-könyvtárnak megadott azonosítójú böngészőablakban fognak futni.

```java
@Specification
public class MySpecs {

	@Window("presenter")
	private MySteps steps;

}
```

A fenti példával azt érjük el, hogy a `steps.presenter` oldal-leíró a "presenter" ablakban fog futni - mivel az a `MySteps` osztályban nem kapott külön annotációt. Ugyanakkor a
`steps.editor` oldal-leíró (az annotációjának köszönhetően) továbbra is az "editor" ablakot vezérli.

### Az alkalmazás által megnyitott ablak/fül elérése

Egyes esetekben a tesztelt alkalmazás nyit új ablakot (vagy fület), amit szeretnénk egy oldal-leíróval összekötni. Ehhez létre kell hoznunk egy oldal-leírót, amit a megfelelő `@Window`
annotációval hozzákötünk a megnyitó oldal-leíró ablakához, viszont a kettőspont után tetszőleges új azonosítót adunk:

```java
// az alapértelmezett ablak által megnyitott új fül vagy ablak
@Window(":new-tab")
private NewlyOpenedPage newPage;
```

Fontos, hogy a kettőspont előtt a megnyitó ablak (már létező) azonosítója álljon, a kettőspont után pedig egy új azonosító, amivel még nem nyitottunk meg sem ablakot, sem fület.

Az új oldal-leíróban az `expectations().expect(browser)).toBe().open()` ellenőrzéssel két legyet üthetünk egy csapásra: egyrészről megvárhatjuk az új fül megnyílását, másrészről
összeköthetjük az oldal-leírót az új füllel. Ennek kell lennie az első műveletnek, amit az oldal-leíróban hívunk. A metódus (első hívásakor) megkeresi az első "szabad"
(más oldal-leíróhoz nem tartozó) fület.

```java
expectations().expect(browser)).toBe().open();
expectations().expect(someElement).toHave().value("some value");
```

## Tesztadatok kezelése

Tesztadatnak hívunk minden olyan értéket, amiket a tesztek futása közben felhasználunk. Tesztadat az a szöveg,
amit a tesztkód beleír egy beviteli mezőbe, de az is tesztadat, amit valamilyen ellenőrzéshez használ fel.

### Tesztadatok konfigurációs fájlokban

A tesztadatokat beírhatjuk a konfigurációs fájlokba is. Bevett gyakorlat, hogy egy tesztadatot tartalmazó bejegyzés neve pontokat tartalmaz,
amik logikai egységekre bontják a nevet. Az azonos csoportba tartozó tesztadatok neve ugyanúgy kezdődik. Például:

```
user.valid.name: user
user.valid.firstName: John
user.valid.lastName: Doe
user.valid.male: true
user.valid.age: 23
```

Az ilyen értékeket aztán oldal-leírókban vagy tesztlépés-könyvtárakban a `getConfigurationValue(String)` metódussal olvashatjuk ki. Például:

```java
String userName = getConfigurationValue("user.valid.name").toString();
boolean isMale = getConfigurationValue("user.valid.male").toBoolean(false);
int age = getConfigurationValue("user.valid.age").toInteger(0);
```

### Tesztadatok fájlokban

Az ibello képes arra, hogy különböző típusú fájlokból tesztadatokat töltsön be. Ezek a fájlok az `ibello/data` mappán belül helyezkednek el.
Akár almappákat is létrehozhatunk (tetszőleges névvel), és azokba tehetjük a fájlokat - az ibello úgy is megtalálja azokat.

Az ibello parancssori eszköz segítségével a fájlokat titkosíthatjuk is. Ekkor azok a megszokott módon nem lesznek érthetők, viszont az
ibello továbbra is képes lesz olvasni azokban. A titkosított fájlok nevébe bekerül az "@encrypted" szöveg.

#### Tesztadatok JSON fájlokban

Az ibello JSON formátumú fájlokból egész objektumokat tud felolvasni. Ehhez először is készítenünk kell egy java osztályt,
ami leírja a tesztadatot. Az előbbi példát továbbgondolva ilyesmiről van szó:

```java
@Model
@Name("User data")
@Description("Password is not included!")
public class User {
    public String name;
    public String firstName;
    public String lastName;
    public boolean male;
    public int age;
}
```

Az osztályhoz itt hozzárendeltük a `@Model` annotációt. Ez nem kötelező, viszont ezzel jelölhetjük, hogy tesztadatokat tároló osztályról van szó. Ezt az információt az ibello bizonyos esetekben felhasználja, és könnyebbé teszi a számunkra a fejlesztést.

Az osztályhoz hozzárendelhetünk `@Name` annotációt. Ezzel nevet adhatunk a tesztadatnak, amit az ibello bizonyos
esetekben megjelenít majd. Hasonlóképpen, a `@Description` annotációval leírást adhatunk az osztályhoz - ebből
lehet egynél több is.

Az osztály meghatározza a JSON fájlok struktúráját is. Például az előző objektumhoz ilyen JSON tartalom képzelhető el:

```json
{
    "name": "user",
    "firstName": "John",
    "lastName": "Doe",
    "male": true,
    "age": 23
}
```

A fájlok nevének szabályszerűnek kell lennie. A kiterjesztés legyen ".json".
A többi rész az alábbiak szerint épül fel:

- A fájl neve annak az osztálynak a rövid és kisbetűsre alakított nevével kezdődik, amivé a tesztadatot alakítani szeretnénk.
  Ez lesz a tesztadat típusa. A fenti példánkban ez "user".
- Ezt opcionálisan egy kötőjel és egy egyedi azonosító követi. Ennek a szerepe a típuson belüli megkülönböztetés. A fenti példánkban
  ez lehetne pl. "-valid".
- Megint csak opcionálisan, egy pontot követően egy vagy több címke következhet, egymástól kötőjellel elválasztva. Ezeknek a címkéknek
  ugyanaz a szerepe, mint a konfigurációs fájloknál: általuk a tesztek futtatásakor kiválaszthatjuk, hogy mely fájlok töltődhessenek be.
  Azok a fájlok, amiknek a nevében olyan címke szerepel, amit a tesztfuttatáskor *nem* adtunk meg, nem fognak betöltődni. A fenti példa
  szerint a "user-valid.hu.json" a "valid" azonosítóval ellátott felhasználó adatait tartalmazza "hu" címke esetén, míg a
  "user-valid.en.json" ugyanezen felhasználó adatait "en" címke esetén.

A tesztadatot a `testData()` metódus segítségével tölthetjük be. Ez elérhető oldal-leírókban, tesztlépés-könyvtárakban és bővítmények
inicializálásakor, a `PluginInitializer` interfészben is. JSON fájlból történő adatbetöltés esetén a `testData().fromJson(Class)`
metódusláncot kell használnunk. Ennek megadható az egyedi azonosító is, a `withId(String)` metódussal. A végén a `load()` metódussal
kell zárnunk a hívási láncot. Példa:

```java
User user = testData().fromJson(User.class).withId("valid").load().
```

Ennek a metódusláncnak az eredményeképp kapunk egy olyan (megadott típusú) objektumot, ami a rendelkezésre álló JSON fájlokban tárolt adatokat
tartalmazza. Csak azok a fájlok lesznek figyelembevéve, amiknek a típusa és a címkéi megfelelőek. Először az azonosító nélküli fájlok töltődnek
be, majd a betöltött értékeket felülírják az azonosítóval rendelkező fájlok. Ha több fájl létezik azonosítóval vagy anélkül, különböző címkékkel,
akkor a betöltés sorrendje a címkék szerint ábécében történik. A fenti példához igazodva, "hu" és "prod" címkék megadásakor ezek a fájlok töltődnek
be, és ilyen sorrendben:

```
user.json
user.hu.json
user.hu-prod.json
user-valid.json
user-valid.hu.json
user-valid.hu-prod.json
```

##### Dátumok kezelése

A JSON formátum nem definiál dátum típust. A dátummezőket mind sztringként kell megadnunk. Ehhez többféle formátumot is használhatunk.

Az egyik lehetőségünk az XML séma dátumformátuma. Ez minden nyelvi beállítás esetén működik. Példa:

```json
{
    "dateField": "2010-02-15",
    "dateTimeField": "2010-02-15T20:44:13"
}
```

Használhatjuk a java környezet alapértelmezett (rövid) dátumformátumát vagy dátum- és időformátumát. Ezek már nyelvi beállítástól függenek, ezért a használatuk csak körültekintéssel javasolt. Példa:

```json
{
    "dateField": "2001.02.03.",
    "dateTimeField: "2001.02.03. 10:11"
}
```

Megadhatunk relatív dátumot is. Ekkor az ibello az éppen aktuális időponthoz képest fogja kiszámolni a mező értékét. A relatív dátum megadása mindig "+" vagy "-" jellel kezdődik aszerint, hogy jövőbeli vagy múltbeli dátumot szeretnénk. Ezt számok és betűk kombinációja követi. Minden szám után egy betű áll, ami megadja a szám mértékegységét. Ez lehet:

- "Y": év
- "M": hónap
- "D": nap
- "h": óra
- "m": perc
- "s": másodperc

Példa:

```json
{
    "dateTime1": "-1Y30h",
    "tomorrow": "+1D"
}
```

Java oldalon a dátummezők lehetnek `XMLGregorianCalendar` típusúak is. Ennek a típusnak egy jellegzetessége az, hogy a dátum egyes részei (pl. nap, óra, perc) üresek maradhatnak. Ha egy ilyen mezőben üresen hagyjuk az óra, perc és másodperc elemeket, akkor időpont nélküli dátumot tudunk kezelni.

Relatív dátummegadásnál, ha a dátummező java típusa `XMLGregorianCalendar`, akkor az ibello egyes elemeket üresen hagy. Azok maradnak üresen, amik nem voltak megnevezve a formátumban, és nálunk kisebb elem sem volt megnevezve. Vagyis például az alábbi esetek mindegyikében az óra, perc és másodperc üresen marad, az év, hónap nap viszont nem:

```json
{
    "date1": "+3D",
    "date2": "-1Y2D"
}
```

##### Konfigurációs paraméterek behelyettesítése

Arra is lehetőségünk van, hogy a JSON fájlokban tárolt szövegekbe konfigurációs paraméterek értékeit helyettesítsük be. Ehhez a `${paraméter}` írásmódot kell használnunk, a zárójelek közé a konfigurációs paraméter neve kerül.

Példa:

A betöltendő fájl tartalma legyen az alábbi.

```json
{
    "color": "#${color.value}"
}
```

Ha a "color.value" konfigurációs paraméter értéke "FFFFFF", akkor a tesztadat betöltése után a "color" mezőben a "#FFFFFF" szöveg lesz.

##### Függőségek injektálása

A JSON fájl alapján létrehozott objektum függőségeit az ibello automatikusan injektálja. Az alábbi példában a `Request` típusú objektum létrehozása után az `initialize` metódus automatikusan meghívódik az injektált `User` típusú paraméterrel. Hasonlóan, a `logger` mező is automatikusan értéket kap.

```java
public class Request {

    @Inject
    private transient MyLogger logger;

    @Inject
    public void initialize(User currentUser) {
        ...
    }
}
```

##### Speciális adatbetöltési lehetőségek

A JSON fájlok egész komplex szerkezetűek is tudnak lenni, ezáltal képesek objektumstruktúra leírására is. A JSON fájl mezői lehetnek tömbök és más objektumok is. Ezek betöltődését picit módosíthatjuk az alábbi két metódus segítségével.

Az ibello alapértelmezésben a különböző betöltött fájlokban levő tömböket összeolvasztja. A `doNotJoinArrays()` metódussal ezt tudjuk megtiltani.
Ennek hatására a később betöltött fájlok tömb mezői felülírják a korábban betöltöttekből érkezett adatot. Nézzünk egy példát! Tegyük fel, hogy az alábbi két JSON fájl egymás után töltődik be:

```json
{
    "hobbies": ["fishing", "singing"]
}
```
```json
{
    "hobbies": ["basketball"]
}
```

Ha ezeket a `testData().fromJson(User.class).withId("valid").load()` hívás segítségével töltjük be, akkor a `hobbies` mező értéke háromelemű tömb (vagy lista) lesz: "fishing", "singing" és "basketball". Ha azonban a `testData().fromJson(User.class).withId("valid").doNotJoinArrays().load()`
láncot használjuk, akkor a `hobbies` csak a "basketball" sztringet fogja tartalmazni.

Hasonlóan, tesztadat betöltéskor az ibello alapértelmezésben az objektumokat is összefésüli. Ezt a  doNotMergeObjects()` metódussal tudjuk tiltani. Ilyenkor az utóbb betöltött objektumok felülírják az előbb betöltötteket. Egy példán kifejtve ezt:

```json
{
    "mother": {
        "name": "Jane Doe"
    }
}
```
```json
{
    "mother": {
        "birthName": "Jane Butterfly"
    }
}
```

Ha ezt a két fájlt ebben a sorrendben a `testData().fromJson(User.class).withId("valid").load()` hívással töltjük be, akkor a `mother` mező
értéke egy olyan objektum lesz, amiben a `name` tartalma "Jane Doe", a `birthName` tartalma pedig "Jane Butterfly". Ha azonban a
`testData().fromJson(User.class).withId("valid").doNotMergeObjects().load()` lánccal történik a betöltés, akkor a `mother` mezőben csak a
`birthName` lesz kitöltve, mivel az egész `mother` értéke felülíródik az utóbb betöltött fájl adataival.

Az ibello alapértelmezés szerint UTF-8 karakterkódolással próbálja betölteni a JSON fájlokban tárolt információkat. Ha ezen változtatni szeretnénk,
akkor a `withCharset(Charset)` metódust kell meghívnunk. Például:

```java
testData().fromJson(User.class).withId("valid").withCharset(StandardCharsets.ISO_8859_1).load();
```

A `load()` metódus helyett a hívási láncot zárhatjuk `loadString()` metódussal is. Ekkor nem objektumot kapunk, hanem egy szöveges értéket,
ami a tesztadat JSON reprezentációját tartalmazza.

Ha a hívási láncot az `openStream()` metódussal zárjuk, akkor egy `InputStream` típusú nyitott adatfolyamot kapunk, amiből kiolvashatjuk a
JSON formátumú tartalmat.

##### Tesztadat osztályok csoportosítása

A java osztályokkal definiált tesztadat típusokat csoportosítani is lehet. A csoportosításnak csak az ibello szerkesztőfelületén van jelentősége, a tesztek futását nem befolyásolja. A csoportosításhoz a tesztadat osztály java csomagjához kell nevet (és igény szerint leírást) rendelni. Ehhez a java csomag definícióját el kell látni egy `@Name`  annotációval. A leírás a `@Description` annotáció segítségével adható meg, akár több sorban is.

Példaként nézzük az alábbi tesztadat osztályt (MyTestData.java):

```java
package my.tests.model;

@Model
public class MyTestData {
    ...
}
```

A "my.tests.model" csomaghoz készítünk egy leíró fájlt (package-info.java) az alábbi tartalommal:

```java
@hu.ibello.core.Name("Tesztadat osztályok")
@hu.ibello.core.Description("Első sor.")
@hu.ibello.core.Description("Második sor")
package my.tests.model;
```

A "mytestdata" tesztadat típus így a "Tesztadat osztályok" elnevezésű csoportba tartozik, mivel a java csomagjához ("my.tests.model") ezt az elnevezést rendeltük.

Ha egy tesztadat osztály csomagja nincs elnevezve a `@Name` annotációval, akkor a tesztadat nem fog tartozni egyetlen csoportba sem.

#### Tesztadatok `properties` fájlokban

Az előzőekben tárgyal tesztadat-betöltési módok keveréke a java `properties` fájlokból történő betöltés. A fájlnév felépítése:

- Egy tetszőleges azonosítóval kezdődik. Ez tartalmazhat kötőjelet is.
- Ezt opcionálisan egy pont és a használt címkék követik (kötőjellel elválasztva).
- Végül a ".properties" kiterjesztés zárja.

Példák:

```
user-valid.properties
user-valid.hu.properties
user-valid.hu-prod.properties
```

A betöltést a `testData().fromProperties(String).load()` metódussal lehet elvégezni. A sztring paraméter az azonosító kell legyen.
Csak azok a fájlok töltődnek be, amiknek nincs olyan címkéje, amit a tesztfuttatásnál *nem* adtunk meg.

A betöltés eredménye itt nem egy tetszőleges java objektum lesz. Az eredmény `Values` típusú. Ettől az egyes értékeket a `getValue(String)`
metódussal kérhetjük le. Ennek az eredménye pedig `Value` típusú lesz, csakúgy, mint a `getConfigurationValue(String)` metódusnál. Példa:

```java
Values values = testData().fromProperties("user-valid").load();
String userName = values.getValue("user.valid.name").toString();
boolean isMale = values.getValue("user.valid.male").toBoolean(false);
int age = values.getValue("user.valid.age").toInteger(0);
```

A később betöltött fájlok adatai itt mindig felülírják a korábban betöltöttekét. (De ha egy érték nincs megadva egy később
betöltött fájlban, akkor az természetesen nem törlődik.)

Az ibello alapértelmezés szerint UTF-8 karakterkódolással próbálja betölteni a fájlokban tárolt információkat. Ha ezen változtatni szeretnénk,
akkor a `withCharset(Charset)` metódust kell meghívnunk. Például:

```java
testData().fromProperties("user-valid").withCharset(StandardCharsets.ISO_8859_1).load();
```

A `load()` metódus helyett a hívási láncot zárhatjuk `loadString()` metódussal is. Ekkor nem objektumot kapunk, hanem egy szöveges értéket, ami a tesztadatokat `properties` fájl formában tartalmazza.

Ha a hívási láncot az `openStream()` metódussal zárjuk, akkor egy `InputStream` típusú nyitott adatfolyamot kapunk, amiből kiolvashatjuk a szöveges tartalmat.

##### Konfigurációs paraméterek behelyettesítése

Arra is lehetőségünk van, hogy a fájlokban tárolt szövegekbe konfigurációs paraméterek értékeit helyettesítsük be. Ehhez a `${paramName}` írásmódot kell használnunk, a zárójelekbe a konfigurációs paraméter neve kerül.

Példa:

A betöltendő fájl tartalma legyen az alábbi.

```properties
color: #${color.value}
```

Ha a "color.value" konfigurációs paraméter értéke "FFFFFF", akkor a tesztadat betöltése után a "color" kulcshoz tartozó érték "#FFFFFF" lesz.

#### Tesztadatok szövegfájlokban

Egyes esetekben nem strukturált adatra van szükségünk, hanem csak szövegre. Ezt ".txt" kiterjesztésű fájlokban tárolhatjuk. A fájlnév felépítése:

- Egy tetszőleges azonosítóval kezdődik. Ez tartalmazhat kötőjelet is.
- Ezt opcionálisan egy pont és a használt címkék követik (kötőjellel elválasztva).
- Végül a ".txt" kiterjesztés zárja.

Példák:

```
message.txt
message.hu.txt
message.hu-prod.txt
```

A betöltést a `testData().fromTxt(String).loadString()` metódussal lehet elvégezni. A sztring paraméter az azonosító kell legyen.
Az eszköz csak azokat a fájlokat veszi figyelembe, amiknek nincs olyan címkéje, amit a tesztfuttatásnál *nem* adtunk meg. Az azonosítóhoz (és a címkékhez) passzoló fájlok közül a legutolsó lesz csak betöltve.

Az ibello alapértelmezés szerint UTF-8 karakterkódolással próbálja betölteni a fájlokban tárolt információkat. Ha ezen változtatni szeretnénk, akkor a `withCharset(Charset)` metódust kell meghívnunk. Például:

```java
testData().fromTxt("message").withCharset(StandardCharsets.ISO_8859_1).loadString();
```

Ha a hívási láncot az `openStream()` metódussal zárjuk, akkor egy `InputStream` típusú nyitott adatfolyamot kapunk, amiből kiolvashatjuk a szöveges tartalmat.

##### Konfigurációs paraméterek behelyettesítése

Arra is lehetőségünk van, hogy a fájlok tartalmába konfigurációs paraméterek értékeit helyettesítsük be. Ehhez a `${paraméter}` írásmódot kell használnunk, a zárójelek közé a konfigurációs paraméter neve kerül.

Példa:

A betöltendő fájl tartalma legyen az alábbi.

```
három ${color} kiscica
```

Ha a "color" konfigurációs paraméter értéke "szürke", akkor a betöltött tartalom "három szürke kiscica" lesz.

#### Tesztadatok bináris fájlokban

Bináris fájlokból is végezhetünk adatbetöltést. Ehhez egy tetszőleges fájlnevet kell megadnunk, kiterjesztéssel együtt, amit a rendszer sablonként
használ majd. Azokat a fájlokat keresi, amiknek a neve:

- a megadott sablon első részével (a kiterjesztés előtti rész) kezdődik,
- ez után opcionálisan egy pont következik, majd a használt címkék (kötőjellel elválasztva),
- végül a megadott kiterjesztéssel végződik.

Például, ha a "file.dat" nevet adjuk meg, akkor ezek a fájlok mind szóba jöhetnek a keresésnél:

```
file.dat
file.hu.dat
file.hu-prod.dat
```

A betöltést a `testData().fromBinary(String).openStream()` metódussal által visszaadott `InputStream` példányon lehet elvégezni.
Az eszköz csak azokat a fájlokat veszi figyelembe, amiknek nincs olyan címkéje, amit a tesztfuttatásnál *nem* adtunk meg. A megadott névhez
(és a címkékhez) passzoló fájlok közül a legutolsó lesz csak betöltve.

Ha a hívási láncot a `getFile()` metódussal zárjuk, akkor visszakapjuk a megtalált fájlt.

### Felsorolás típusú osztályok mint tesztadatok

A `@Model` annotációval felsorolás (enum) típusú osztályokat is megjelölhetünk. Az ibello így a felsorolás értékeit tesztadatként kezeli, és azokat megadhatjuk például teszt metódusok paramétereként.

Példaként nézzük a következő felsorolás osztályt:

```java
@Model
public enum UserKind {
    ADMIN,
    NORMAL;
}
```

Ennek az osztálynak a konstansait paraméterként megadhatjuk egy tesztlépésben:

```java
public void I_log_in_with_$_user(UserKind userKind) {
    ...
}
```

Ha az előbbi metódust egy tesztben meghívjuk:

```java
...
loginSteps.I_log_in_with_$_user(UserKind.ADMIN);
...
```

akkor a teszt riportban és a logban is  valami hasonlót fogunk látni:

```cucumber
Login: I log in with ADMIN user
```

A fentiek működnek akkor is, ha a felsorolás osztályhoz nem adtunk meg `@Model` annotációt. Az annotáció nélkül viszont nem működik a fordított irány. Egy gherkin formátumú forgatókönyvben ugyanis akár meg is hívhatjuk a felsorolás paraméterrel rendelkező tesztlépéseket - feltéve, hogy az annotáció hozzá van adva az osztályhoz.

#### Felsorolás értékek egyedi neve és leírása

A felsorolás osztályok konstansaihoz `@Name` annotációval elnevezést rendelhetünk. Egy vagy több `@Description` annotáció megadásával leírást is fűzhetünk hozzájuk. Ezeket csak az ibello grafikus felülete használja. Az annotációk segítségével érthetőbb tesztadatokat tudunk definiálni.

Példa:

```json
@Model
public enum UserKind {
    
    @Name("Adminisztrátor")
	@Description("Kiemelt felhasználó.")
	@Description("Hozzáfér a beállításokhoz.")
    ADMIN,

	@Name("Normál felhasználó")
    NORMAL;
}
```

## Egyedi loggolás

Az ibello a tesztriportot a végrehajtott tesztlépések, művelet és ellenőrzések alapján automatikusan készíti el. Előfordulhatnak viszont olyan esetek, amikor saját szöveggel ki szeretnénk egészíteni a riportot. Erre a tesztlépés-könyvtárakon belül van lehetőségünk, az `output()` metódus által visszaadott objektum metódusaival.

Ezek a metódusok paraméterként kapják azt a szöveget, amit majd meg fognak jeleníteni a tesztriportban. Automatikusan felismerik, ha a szöveg JSON vagy XML formátumú, ekkor azt a tesztriportban is kiemelt formázással jelölik.

| Metódus                                                   | Leírás                                                       |
| --------------------------------------------------------- | ------------------------------------------------------------ |
| `output().recordCustomAction(String name)`                | Egy új egyedi műveletet szúr be a megadott tartalommal.      |
| `output().recordCustomExpectation(String name)`           | Egy új egyedi ellenőrzést szúr be a megadott tartalommal. Az ellenőrzés sikeresnek minősül. |
| `output().recordCustomStep(String stepName, String text)` | Egy új tesztlépést szúr be a megadott névvel, ami alá rögtön beszúr egy műveletet is a megadott tartalommal. |

## Függőségek injektálása

Az ibello keretrendszer képes egyedi osztályainkat automatikusan a teszlépés-könyvtár és az oldal-leíró osztályokba injektálni. Sőt, az ily módon injektált osztályokba is hasonlóan injektálhatunk más osztályokat.

Csak olyan osztály injektálható, aminek van alapértelmezett konstruktora. Az injektáláshoz be kell illesztenünk egy mezőt a hivatkozó osztályba - ez lehet privát is. A mezőt meg kell jelölnünk az `@Inject` annotációval.

```java
public class MyPage extends PageObject {

    // a tool mező automtikusan kap egy MyTool példányt
    @Inject
    private MyTool tool;
}
```

Arra is lehetőség van, hogy publikus metódusok paramétereit injektáljuk. Ha egy metódust az `@Inject` annotációval jelölünk meg, akkor az ibello meg fogja hívni azt a metódust az osztálypéldány létrehozásakor úgy, hogy átadja neki a megfelelő paramétereket is.

```java
public class MyPage extends PageObject {

    // a metódus meghívódik, a tool paraméter automatikusan kap egy MyTool példányt
    @Inject
    public void setTool(MyTool tool) {
        ...
    }
}
```

Az injektálás a fentieken kívül még finomítható. Amennyiben az injektált osztály implementálja az `Initializable` interfészt, úgy az ibello rendszer az innen örökölt `initialize()` metódust automatikusan meg fogja hívni, miután létrehozta az osztály egy példányát.

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
| `Scope.PROTOTYPE`       | Minden új injektálási helyhez új példány keletkezik.       |
| `Scope.TEST`           | Minden egyes teszt metódus futáshoz új példány keletkezik. |
| `Scope.SPECIFICATION`   | Minden teszt osztály futásához új példány keletkezik.      |
| `Scope.SESSION`        | Minden java szálhoz új példány keletkezik.                 |
| `Scope.SINGLETON`      | A tesztek futása során csak egyetlen példány jön létre.    |

Az ibello rendszerben az `@Inject` annotációval végrehajtott injektálás a támogatott módja annak, hogy extra logikát illesszünk a tesztjeinkbe. A tesztek futása során szükség lehet
például háttérrendszerek konfigurálására, hogy a kívánt eredményt adják, miközben a böngészőt vezéreljük. Az ilyen típusú igényekhez létrehozhatunk az ibello API-tól független
osztályokat, amiket az ibello függőség injektálásának segítségével illeszthetünk be a tesztek kódjába.

Az `@Inject` annotáció segítségével nemcsak saját osztályokat injektálhatunk. Az alábbi ibello-s interfészek implementációjit is felhasználhatjuk:

| Interfész                           | Leírás                                                    |
| ----------------------------------- | --------------------------------------------------------- |
| `hu.ibello.data.TestDataBuilder`       | Tesztadatok betöltésére szolgál.                          |
| `hu.ibello.transform.JsonTransformer`  | JSON formátumú adatok objektumokká alakítása és vissza.    |
| `hu.ibello.transform.CsvTransformer`   | CSV formátumú adatok objektum listává alakítása és vissza. |
| `hu.ibello.functions.RegressionTool`   | Függvényillesztés ponthalmazra.                            | 

## Egyéb eszközök

A `hu.ibello.toolbox` csomag olyan osztályokat (interfészeket) tartalmaz, amik integrációs tesztekben használhatók külső rendszerek kiváltására. A közös ezekben az eszközökben, hogy
az `@Inject` annotáció segítségével a tesztlépés-könyvtárakba injektálhatóak. Az alábbiakban ezen eszközök bemutatása következik.

### Email ellenőrzés

Amennyiben a tesztelt alkalmazás email-eket küld ki, úgy szükség lehet arra, hogy a tesztekben a kiküldött email-eket is ellenőrizzük. Ezt a `FakeEmailServer` segítségével érhetjük el.

Az osztály segítségével indíthatunk egy memóriában futó SMTP szervert, ami képes fogadni a tesztelt alkalmazás által küldött leveleket, és aminek a segítségével a tesztkódban
ellenőrizhetjük a levelet tartalmát.

#### Előzetes konfiguráció

Az eszköz használatához a tesztelt alkalmazásban is el kell végeznünk némi konfigurációt annak érdekében, hogy az a teszt SMTP szerverhez
küldje a leveleket. Java környezet esetében ezt általában elérhetjük a `mail.smtp.host` és a `mail.smtp.port` rendszerváltozók beállításával. Az elsőnek `localhost` értéket
kell adnunk, míg a másodiknak az SMTP szerver portját (lásd később).

Ha az előbbi módszer nem működik, akkor vegyük fel a kapcsolatot az alkalmazás fejlesztőivel. A legtöbb rendszerben elvégezhető ez a konfiguráció.

#### Tesztkód előkészítése

A `FakeEmailServer` példányt tipikusan egy tesztlépés-könyvtárba injektáljuk.

```java
public class EmailSteps extends StepLibrary {

	@Inject
	private FakeEmailServer emailServer;

	public void startEmailServer() {
		emailServer.start(2525);
	}
	
}
```

A `start(int)` metódus segítségével indíthatjuk el a szervert a megadott porton. Ha már egyszer elindítottuk azon a porton, akkor ez a metódus nem csinál semmit.
(Ha azonban más portszámot írunk be, mint amin már egyszer indítottuk, akkor a metódus kivételt dob.)

A tesztlépés-könyvtárat ezután felhasználhatjuk egy teszt osztályban. Ha egy `@Before` blokkba tesszük a szerverindítást, akkor a szerver az első tesztünk előtt el fog
indulni, így a tesztekben használhatjuk.

```java
@Specification
public MyTests {

	private EmailSteps email;

	@Before
	public void startEmailServer() {
		email.startEmailServer();
	}
```

Ha a szervert le szeretnénk állítani, akkor a `stop()` metódusát kell meghívjuk. Ha ezt elmulasztjuk, sem probléma, mert az utolsó teszt futását követően az ibello automatikusan
leállítja.

Itt érdemes megjegyezni, hogy a `FakeEmailServer` session-szkópban fut bár, de egy közös szervert vezérel. Ezt azt jelenti, hogy nem tudunk két szervert két különböző porton
indítani. (A session-szkóp a logok miatt lényeges, a különböző szálakon futó ellenőrzések egymástól függetlenül képesek loggolni.)

#### Ellenőrzések

Az eszköz az `expectLastEmail()` és `assumeLastEmail()` metódusok segítségével folytonos (kemény és lágy) ellenőrzési lehetőséget ad nekünk. Mint ahogyan a metódusok nevéből
is sejthető, mindig a legutolsó üzenetet tudjuk ellenőrizni. Az alábbi lehetőségeink vannak:

| Java metódushívás                             | Magyarázat                                                                                                            |
| --------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| `expectLastEmail().toBe().present()`          | Az üzenet létezésének ellenőrzése. Csak az első üzenet elküldésekor teljesül.                                         |
| `expectLastEmail().toHave().sender(...)`      | A feladó email-címének ellenőrzése.                                                                                   |
| `expectLastEmail().toHave().recipient(...)`   | A fogadó email-címének ellenőrzése.                                                                                   |
| `expectLastEmail().toHave().subject(...)`     | Az üzenet tárgyának ellenőrzése.                                                                                      |
| `expectLastEmail().toHave().text(...)`        | Az üzenet szövegének ellenőrzése.                                                                                     |
| `expectLastEmail().toHave().header(..., ...)` | Az üzenet egy fejlécének ellenőrzése. Az első paraméter a fejléc neve (kis/nagybetű érzéketlen), a második az értéke. |

A `toHave()` ellenőrzéseknek természetesen van `toNotHave()` párja, amivel a feltételt tudjuk tagadni. Ugyanez áll fenn a `toBe()` ellenőrzésre is (a párja `toNotBe()`).
A `toHave()` ellenőrzések utolsó paramétere lehet az elvárt érték (`String` típus), vagy egy reguláris kifejezés, aminek az aktuális értéket meg szeretnénk feleltetni
(`Pattern` típus). Példák:

```java
expectLastEmail().toHave().subject("registration");
expectLastEmail().toNotHave().text(Pattern.compile(".*error.*"));
expectLastEmail().toHave().header("content-type", Pattern.compile("text/html(;.*)?"));
```

Fontos megjegyezni, hogy a `toNotHave()` tagadás akkor is teljesül, ha egyetlen üzenet sem kerül elküldésre. Ha arról szeretnénk megbizonyosodni, hogy már jött egy email, aminek
valamely tulajdonsága nem felel meg egy feltételnek, akkor előbb a `toBe().present()` ellenőrzést kell futtatnunk. Például:

```java
expectLastEmail().toBe().present();
expectLastEmail().toNotHave().subject("password changed");
```

Az ellenőrzések a többi ibello ellenőrzésekhez hasonlóan bekerülnek a logokba és az eredmény riportba is.

#### Időtúllépés

Az összes ellenőrzés a `toolbox.email` címkével jelzett időtúllépést használja. (Ezt a konfigurációban `ibello.timeout.toolbox.email` kulccsal adhatjuk meg.) Ha nincs megadva,
akkor az alapértelmezett időtúllépés lesz a mérvadó. Lehetőségünk van saját időtúllépés használatára is, az `expectLastEmail().withTimeout(...)` metódusok valamelyikével.
Az időtúllépés címkéjét kell megadnunk, vagy egy `Enum` konstanst.

#### Alaphelyzetbe állás

A `reset()` metódus segítségével alaphelyzetbe állíthatjuk az email szerverünket, ami elfelejt minden addigi üzenetet. Ez akkor hasznos, ha a rendszer többször egymás után hasonló
üzeneteket küld ki, és nem szeretnénk hamis pozitív eredményt kapni a tesztjeinkben azért, mert egy korábbról bennragadt üzenetnek ugyanazok a tulajdonságai vannak, mint az újonnan
vártnak.
