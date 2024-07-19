# ibello-api változási napló

## 1.22.0

- Új `Feature.getBackground()` metódus, ami a jellemzők hátterének tesztlépéseit tartalmazó objektumot adja vissza
- Új `Examples.getTags()` és `Example.getTags()` metódusok a példákban szereplő címkék számára
- Új `HttpClient.multipart(...)` és `RestClient.multipart(...)` metódusok, amik segítségével a multipart/form-data kérést lehet összeállítani
- A `RestClient.sendAndReceive(...)` metódus képes `byte[]` és `InputStream` típusú válasz fogadására is
- Új `RestClient.bodyConsumer(...)` metódus, amivel a kérés törzsét lehet feldolgozni még a REST hívás kiküldése előtt
- Az XML formátumú teszteredményekben a tesztlépések kategóriája is megjelenik
- `DATATABLE` típusú paraméter megjelenik a `Step` osztály paraméterei között
- Új `GraphTool.createLabeledGraph(...)` metódus címkézett grafikonok készítésére
- Új `PluginInitializer.functionalities()` metódus funkcionalitások adatainak betöltéséhez
- Új `PluginInitializer.pdf()` metódus HTML oldalak PDF fájlba történő mentéséhez
- Új `Regression.getErrorSquare()` metódus
- Új `Outcome.BLOCKED` és `Outcome.SKIPPED` teszteredmény értékek
- A `StepElement`, `TestElement`, `SpecElement` és `TestRun` osztályok új `description` mezővel bővültek
- A `StepElement`, `TestElement`, `SpecElement` és `TestRun` osztályok új `comment` mezővel bővültek
- Új `TestRun.responsibleParty` mező a teszteredményért felelős személy/szervezet feltüntetésére
- Új `PageObject.pageObject(...)` és `StepLibrary.pageObject(...)` metódusok, amik visszaadják a megadott típusú oldal-leíró példányt
- Új `StepLibrary.stepLibrary(...)` metódus, ami visszaadja a megadott típusú tesztlépés-könyvtár példányt
- Az `@Inject` annotáció publikus metódusok paramétereinek injektálására is használható
- A `TestDataBuilder.fromJson(...)` és a `TestDataBuilder.fromCsv(...)` által visszaadott adatbetöltők függőségeket injektálnak a létrehozott objektumokba
- Új `@Editable` annotáció, amivel modell osztályok mezőihez lehet speciális szerkesztőt rendelni
- Új `Requirement.getColor()` metódus, amivel a követelményhez rendelt szín kérhető le
- Új szövegformátum a teszteredményekben: `TextFormatKind.PROPERTIES`
- Új `HttpClient.urlBase()` és `RestClient.urlBase()` metódusok, amik segítségével be lehet állítani a használni kívánt URL első felét
- Új `HttpClient.get()` és `RestClient.get()` metódusok, amik segítségével GET kérést lehet összeállítani
- Új `HttpClient.post()` és `RestClient.post()` metódusok, amik segítségével POST kérést lehet összeállítani
- Új `HttpClient.put()` és `RestClient.put()` metódusok, amik segítségével PUT kérést lehet összeállítani
- Új `HttpClient.delete()` és `RestClient.delete()` metódusok, amik segítségével DELETE kérést lehet összeállítani
- Új `HttpClient.patch()` és `RestClient.patch()` metódusok, amik segítségével PATCH kérést lehet összeállítani
- Új `latencyMs` mező a teszteredmény elemeiben 
- Új `Value.toXMLGregorianCalendar` metódus
- A `@Performance` annotációban több csoportazonosító is megadható
- A teszteredmény APDEX listájába bekerül a mérési pontok száma, az elégedettségi és a tolerancia határ is

## 1.21.1

2022-06-04

- Új `JsonTransformer.registerDeserializer` metódus, aminek a segítségével finomhangolhatjuk a JSON deszerializálás folyamatát
- Új `JsonTransformer.registerSerializer` metódus, aminek a segítségével finomhangolhatjuk a JSON szerializálás folyamatát

## 1.21.0

2022-05-29

- Tesztadatok betöltése CSV fájlból is lehetséges
- Új `FeatureHandler.loadFeatures()` metódus
- Új `RequirementHandler.calculateTestCoverage(...)` és `RequirementHandler.calculateExampleCoverage(...)` metódusok követelmények lefedettségének számításához
- Új `Requirements.getRelativePath()` metódus
- Új `@Performance` annotáció performancia mérések támogatásához
- A mért APDEX értékek megjelennek a teszteredményekben
- Új `fromCsv(...)` és `toCsv(...)` metódusok a `CsvTransformer` interfészben
- Új `TextFormatKind.HTML` konstans HTML formátumú szövegek jelölésére
- A `ParentElement` osztályban a `successCount`, `failureCount`, `errorCount` és `pendingCount` mezők értéke lehet `null` is
- Az `Element` osztályban a `durationMs` mező értéke lehet `null` is
- Az `ActionElement` osztályban a `timeoutMs` mező értéke lehet `null` is
- Az `ExpectationElement` osztályban a `timeoutMs` mező értéke lehet `null` is

## 1.20.1

2022-02-26

- Új `RestClient.sendAndReceiveList(...)` metódus listát visszaadó REST végpontokkal történő kommunikációhoz
- A grafikon készítő eszközzel időskálával rendelkező diagramot is lehet készíteni
- Új `Value.toDate(...)` és `Value.toCalendar(...)` metódusok értékek dátummá alakításához

## 1.20.0

2022-02-11

- A teszteredményt tartalmazó XML struktúrában a `SpecElement` és a `TestElement` osztályok új `tag` mezőt kaptak
- A teszteredményt tartalmazó XML struktúrába bekerült a modell verziószám
- A teszteredményt tartalmazó XML struktúrába bekerült a tesztelt alkalmazás verziószáma
- A `Requirements` és a `Requirement` interfészek kaptak egy új `getLinks()` metódust
- Új `setConfigurationValue(...)` metódus a `PluginInitializer` interfészben
- Új `HttpResponse.getHeader(...)` metódus, amikkel a HTTP válasz fejlécek érhetők el a `HttpClient` osztályon keresztül
- Új `SerializedName` annotáció, amivel a java mezők nevét lehet megadni JSON szerializáció és deszerializáció során

## 1.19.0

2021-11-20

- Az `Initializable` interfész kapott egy új `isInitialized()` metódust
- A `HttpClient` és a `RestClient` osztályok kaptak egy új `withoutCertificateValidation()` metódust
- A `TestDataBuilder` interfész kapott egy új `expression` metódust, amivel tesztadat kifejezést lehet kiértékelni

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
