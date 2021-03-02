# Paraméterek használata elemkeresés során

A `@Find` annotáció `using` tulajdonságában használhatunk paraméter hivatkozásokat is. Ezek helyére később értékeket helyettesíthetünk be. Az ibello rendszer
inicializálni fogja nekünk ezeket az elemeket, de a paramétereket csak akkor fogja behelyettesíteni, amikor később azok értékeit a `WebElement.applyParameters`
vagy a `WebElements.applyParameters` metódus segítségével megadjuk. A behelyettesítés a megadott paraméterek indexei alapján fog megtörténni, a `${0}`
karaktersorozattal jelölt helyre az első paraméter értéke fog kerülni, a `${1}` helyére a második paraméteré, stb.

```java
@Find(by=By.BUTTON_TEXT, using="${0}/${1} végrehajtása")
private WebElement button;

public void clickOperationButton(String operation, int index) {
    doWith(button.applyParameters(operation, index)).click();
}
```

Ha a fenti példában szereplő metódust az "A" és a 2 paraméterekkel hívjuk meg, akkor a teszt az "A/2 végrehajtása" feliratú gombot fogja megkeresni és megnyomni.

A szelektorokba konfigurációs paramétereket is behelyettesíthetünk. Ezek nevét az előzőhöz hasonló írásmóddal kell megadnunk.

```java
@Find(by=By.BUTTON_TEXT, using="${button.text} végrehajtása")
private WebElement button;
```

Az ilyen szelektort tesztfutás közben már nem tudjuk módosítani, ugyanis a konfigurációs paraméterek értéke még a tesztek indítása előtt eldől.
A megadott címkék viszont hatással vannak arra, hogy mely konfigurációs fájlok töltődnek be, így befolyásolják a konfigurációs paraméterek értékét is.
Ezen a módon tehát címkék megadásával módosíthatjuk a tesztfutás menetét.

Nemcsak a `@Find` annotáció szelektoránál használhatunk behelyettesítést. Mindez a `@Position` és a `@Relation` annotációknál is működik.

```java
@Find(by=By.BUTTON_TEXT, using="Letöltés")
@Relation(type=RelationType.DESCENDANT_OF, using=".${0}")
private WebElement button;

public void click_$_download_button(String className) {
	doWith(button.applyParameters(className)).click();
}
```