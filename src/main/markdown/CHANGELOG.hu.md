# ibello-api változási napló

## 1.18.0

2021-09-20

- Új `PluginInitializer.regression()` metódus adathalmazokon végzett regressziós műveletekhez
- Új osztályok a `hu.ibello.functions` csomagban, amelyek matematikai függvényeket implementálnak (konstans, lineáris és logisztikus függvények)
- Új `PluginInitializer.graph()` metódus grafikonok rajzolásához
- Új `PluginInitializer.table()` metódus táblázatok kiíratásához
- Új `StepLibrary.csv()` és `PluginInitializer.csv()` metódusok CSV fájlok beolvasásához
- Új `PluginInitializer.requirements()` metódus követelmények beolvasásához
- Új `TestException` kivétel osztály teszthibák jelöléséhez
- Új `SystemFailureException` kivétel osztály rendszerhibák jelöléséhez

## 1.17.0

2021-07-03

- Új `Browser.getKind()` metódus
- Új `Browser.getVersion()` metódus
- Új `Browser.getCompositeId()` metódus
- Új `Browser.getPageSource()` metódus
- Új `FeatureHandler.existsFeatureFile()` metódus
- Új `FeatureHandler.saveFeature(...)` metódus
- Új `ExamplesHandler.existsExamplesFile()` metódus
- Új `ExamplesHandler.saveExamples(...)` metódus
- Új `Feature.namespace` mező
- Új `Feature.coverage` mező

## 1.16.3

2021-01-29

- Új `Browser.close()` metódus
- Új `PluginInitializer.json()` és `StepLibrary.json()` metódusok JSON transzformációhoz

## 1.16.2

2021-01-21

- Új `PluginInitializer.testResults()` metódus a teszteredmények betöltésére

## 1.16.1

2021-01-17

- Új `Scenario.getFlattenSteps()` és `ParentStep.getFlattenSteps()` metódusok
- Új `Browser.getURL()` metódus, ami visszaadja az aktuális oldal címét

## 1.16.0

2021-01-09

- Új `PluginInitializer.features()` metódus a Cucumber jellemzők kezelésére
- Új `PluginInitializer.examples()` metódus a Cucumber példák kezelésére

## 1.15.0

2020-11-26

- Új `ElementRepository` osztály elemek és oldal-leírók szétválasztására
- Új `HttpClient.url(URL)` és `RestClient.url(URL)` metódusok.
- Új `PageObject.getMergedURL(...)` és `StepLibrary.getMergedURL(...)` metódusok
- A `Browser.openURL(String)`, a `HttpClient.url(String)` és `RestClient.url(String)` metódusok többé nem változtatják meg a megadott abszolút URL-t
- Új `@Timeout` annotáció az időtúllépéseket felsoroló enum osztályok jelöléséhez
- Új `PluginInitializer.getMergedURL(String)` metódus
- Új `IbelloTaskRunner` bővítmény interfész feladatok futtatásához

## 1.14.0

2020-08-13

- A `@Name` annotációt csomagokhoz is hozzá lehet adni
- A `@Description` annotációt csomagokhoz is hozzá lehet adni
- Új `Browser.findDownloadedFile(Pattern)` metódus letöltött fájl keresésére
- Új `TestDataTools` eszköz osztály teszt adatokkal kapcsolatos feladatok kezelésére

## 1.13.1

2020-06-03

- Új `Browser.findDownloadedFile(String)` és `Browser.getLatestDownloadedFile()` metódusok a letöltött fájlok eléréséhez.
- Új `StepLibrary.httpClient()` metódus HTTP API teszteléshez
- Új `@Internal` annotáció tesztlépés metódusok listázásból történő kizárásához

## 1.13.0

2020-04-05

- Új `OutputHandler.recordCustomStep(...)` metódus
- Új `StepLibrary.restClient()` metódus REST API teszteléshez
- Új `PageObject.tryWith(WebElement)` és `PageObject.tryWith(Browser)` metódusok "lágy" műveletekhez
- Új `By.BUTTON_CLASS` típusú elemkeresés
- Új `WebElementActionBuilder.setSelected(boolean)` metódus jelölőnégyzetek és rádiógombok kiválasztására
- A `@Name` annotációt mezőkhöz is hozzá lehet adni
- A `@Description` annotációt mezőkhöz is hozzá lehet adni

## 1.12.2

2019-11-17

- Új `memory-usage` mező a műveletekhez a teszteredményeket tartalmazó XML fájlban

## 1.12.1

2019-10-13

- Kisebb változtatás a `PageObject.repeat(Task)` működésében

## 1.12.0

2019-09-13

- Új `PageObject.repeat(Task)` metódus, amivel adott feladatot lehet ismételtetni
- Új `WebElementHaveExpectations.textOrValue(...)` metódusok elemek szövegének vagy értékének ellenőrzéséhez
- Új `testData().fromTxt(String)` metódus szöveges tesztadat beolvasására
- Új `testData().fromBinary(String)` bináris tesztadat beolvasására
- Új `testData().fromProperties(String).loadString()` és `testData().fromJson(Class).loadString()` metódusok komplex tesztadatok szöveges formában történő beolvasásához
- Új `testData().fromProperties(String).openStream()` és `testData().fromJson(Class).openStream()` metódusok komplex tesztadatok beolvasásához
- Új `testData().fromProperties(String).withCharset(Charset)` és `testData().fromJson(Class).withCharset(Charset)` metódusok a betöltéskor használatos karakterkészlet meghatározásához
- Új `name-format` mező a műveletekhez a teszteredményeket tartalmazó XML fájlban

## 1.11.0

2019-02-13

- Új `@Description` annotáció a tesztlépések és tesztlépés-könyvtárak rövid leírásához
- Új `@Model` annotáció a tesztadatok betöltését fogadó osztályok jelöléséhez
- Új `timeout-ms` mező a teszteredményeket tartalmazó XML fájlban

## 1.10.1

- A tesztadatokat tartalmazó fájlok alkönyvtárakban is elhelyezhetőek.

## 1.10.0

2018-09-24

- Információszerzés elemekről a `PageObject.checkThat(WebElement)` és a `PageObject.get(WebElement)` metódusok segítségével
- Teszt adat betöltés a `PageObject.testData()`, `StepLibrary.testData()` és `PluginInitializer.testData()` metódusok segítségével
- Képernyőkép készítése a `PageObject.browser().saveScreenshot()` metódus segítségével
- A `Value` interfész alapértelmezett érték paraméterrel rendelkező konverziós metódusokkal gazdagodott
- Új mezők a teszteredmény XML objektumokban: `Screenshot.getWindow()`, `Screenshot.getFile()`

## 1.9.1

2018-06-20

- A `Plugin.initialize(...)` metódus paramétere képes a konfiguráció lekérdezésére is
- Új `ITestRun.getName()` metódus

## 1.9.0

2018-06-19

- Új ibello plugin architektúra, egyedi beépülő modulok adhatóak a teszt futtatáshoz
- Böngésző riasztások kezelése az `BrowserActionBuilder` osztállyal
- Új `Browser.reload()` metódus, ami újratölti az aktuális URL-t
- Új `Specification.version` tulajdonság
- Tesztlépés-könyvtárak és oldal-leírók `@Name` annotációval megadott nevei szerepelnek a teszt-lépések nevében is
- `@Name` annotáció nélküli tesztlépések neveibe a `String` típusú paraméterek macskakörmök között kerülnek bele

## 1.8.0

2018-05-28

- Új `ActionBuilder.fireEvent(...)` metódus elemeken futó javascript események meghívásához
- Új keresési módok: `By.PARTIAL_TEXT`, `By.PARTIAL_BUTTON_TEXT` és `By.PARTIAL_LABEL`
- Új `Browser.localStorage()` és `Browser.sessionStorage()` metódusok a böngésző tárolóinak kezeléséhez
- Új `Browser.cookies()` metódus a böngésző sütijeinek kezeléséhez

## 1.7.1

2018-05-24

- Az oldal-leírók és a tesztlépés-könyvtárak metódusainak nevében `$` karakter jelöli a paraméterek helyét

## 1.7.0

2018-03-21

- Új `Window` annotáció, amivel új böngészőablak (és fül) nyitható
- Új reláció szerinti elemkeresési módosító: `RelationType.NEAREST_TO`
- Új `Test` annotáció, ami lehetővé teszi teszt metódusok rendezését és kizárását
- A projekt többé nem függ a `junit` csomagtól

## 1.6.0

2018-02-03

- Új interfész email kommunikáció tesztelésére: `FakeEmailServer`

## 1.5.0

2017-11-27

- Új `ActionBuilder.selectOption(...)` metódusok legördülő listából történő választáshoz
- Új `ActionBuilder.scrollTo(...)` metódus görgetéshez
- Új `WebElementHaveExpectations.selectedOption(...)` metódusok legördülő lista kiválasztott elemének ellenőrzéséhez
- Új `WebElementBeExpectations.readonly()` metódus csak olvasható mezők ellenőrzéséhez
- Új `SearchTool.nth(...)` metódus index szerinti kereséshez
- Új `Specification.order` mező teszt osztályok sorbarendezéséhez
- Új `Specification.includedTags` és `Specification.excludedTags` mezők teszt osztályok ki/bekapcsolásához

## 1.4.0

2017-11-15

- A tesztek futtatása a teszt osztályok nevei alapján ábécé szerinti sorrendben történik
- Az oldal-leírókhoz több `Frame` annotáció is hozzáadható azért, hogy HTML keretben levő keretet is elérhessünk

## 1.3.0

2017-11-13

- Új `assume` metódusok az ellenőrzési motorban "lágy" ellenőrzésekhez
- Az ellenőrző motor `expectAny` és `expectAll` metódusai érvényüket vesztik
- `BrowserHaveExpectations.url(String)` metódus értelmezi a dupla csillagot (`**`) is
- `By.BUTTON_TEXT` alapján történő keresés megtalálja azokat az elemeket is, ahol a `role` attribútum értéke `button`

## 1.2.0

2017-10-23

- Új `Frame` annotáció, amivel HTML keretek tartalma is elérhető

## 1.1.0

2017-10-14

## 1.0.0

2017-09-09
