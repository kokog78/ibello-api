# ibello-api változási napló

## 1.9.0

- Új ibello plugin architektúra, egyedi beépülő modulok adhatóak a teszt futtatáshoz
- Tesztlépés-könyvtárak és oldal-leírók `@Name` annotációval megadott nevei szerepelnek a teszt-lépések nevében is
- `@Name` annotáció nélküli tesztlépések neveibe a `String` típusú paraméterek macskakörmök között kerülnek bele

## 1.8.0

- Új `ActionBuilder.fireEvent(...)` metódus elemeken futó javascript események meghívásához
- Új keresési módok: `By.PARTIAL_TEXT`, `By.PARTIAL_BUTTON_TEXT` és `By.PARTIAL_LABEL`
- Új `Browser.localStorage()` és `Browser.sessionStorage()` metódusok a böngésző tárolóinak kezeléséhez
- Új `Browser.cookies()` metódus a böngésző sütijeinek kezeléséhez

## 1.7.1

- Az oldal-leírók és a tesztlépés-könyvtárak metódusainak nevében `$` karakter jelöli a paraméterek helyét

## 1.7.0

- Új `Window` annotáció, amivel új böngészőablak (és fül) nyitható
- Új reláció szerinti elemkeresési módosító: `RelationType.NEAREST_TO`
- Új `Test` annotáció, ami lehetővé teszi teszt metódusok rendezését és kizárását
- A projekt többé nem függ a `junit` csomagtól

## 1.6.0

- Új interfész email kommunikáció tesztelésére: `FakeEmailServer`

## 1.5.0

- Új `ActionBuilder.selectOption(...)` metódusok legördülő listából történő választáshoz
- Új `ActionBuilder.scrollTo(...)` metódus görgetéshez
- Új `WebElementHaveExpectations.selectedOption(...)` metódusok legördülő lista kiválasztott elemének ellenőrzéséhez
- Új `WebElementBeExpectations.readonly()` metódus csak olvasható mezők ellenőrzéséhez
- Új `SearchTool.nth(...)` metódus index szerinti kereséshez
- Új `Specification.order` mező teszt osztályok sorbarendezéséhez
- Új `Specification.includedTags` és `Specification.excludedTags` mezők teszt osztályok ki/bekapcsolásához

## 1.4.0

- A tesztek futtatása a teszt osztályok nevei alapján ábécé szerinti sorrendben történik
- Az oldal-leírókhoz több `Frame` annotáció is hozzáadható azért, hogy HTML keretben levő keretet is elérhessünk

## 1.3.0

- Új `assume` metódusok az ellenőrzési motorban "lágy" ellenőrzésekhez
- Az ellenőrző motor `expectAny` és `expectAll` metódusai érvényüket vesztik
- `BrowserHaveExpectations.url(String)` metódus értelmezi a dupla csillagot (`**`) is
- `By.BUTTON_TEXT` alapján történő keresés megtalálja azokat az elemeket is, ahol a `role` attribútum értéke `button`

## 1.2.0

- Új `Frame` annotáció, amivel HTML keretek tartalma is elérhető
