# ibello-api változási napló

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