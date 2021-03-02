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

A szelektorokba konfigurációs paramétereket is behelyettesíthetünk. Ezek nevét szintén `${...}` írásmóddal kell megadnunk.

```java
@Find(by=By.BUTTON_TEXT, using="${button.text} végrehajtása")
private WebElement button;
```

Nemcsak a `@Find` annotáció szelektoránál használhatunk behelyettesítést. Mindez a `@Position` és a `@Relation` annotációknál is működik.
