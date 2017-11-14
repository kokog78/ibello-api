# ibello-api változási napló

## 1.2.0

- Új Frame annotáció, amivel HTML keretek tartalma is elérhető


## 1.3.0

- Új `assume` metódusok az ellenőrzési motorban "lágy" ellenőrzésekhez
- Az ellenőrző motor `expectAny` és `expectAll` metódusai érvényüket vesztik
- `BrowserHaveExpectations.url(String)` metódus értelmezi a dupla csillagot (`**`) is
- `By.BUTTON_TEXT` alapján történő keresés megtalálja azokat az elemeket is, ahol a `role` attribútum értéke `button`

## 1.3.1

- A tesztek futtatása a teszt osztályok nevei alapján ábécé szerinti sorrendben történik.
