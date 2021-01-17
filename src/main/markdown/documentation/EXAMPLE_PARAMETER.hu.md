# Paraméterek használata elemkeresés során

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
