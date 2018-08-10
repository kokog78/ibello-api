# HTML kereteket tartalmazó alkalmazások tesztelése

A böngészők (és a böngésző-meghajtók) a HTML kereket (`frame` illetve `iframe` elemek) a rajtuk kívül eső tartalmaktól elválasztva kezelik.
Ez az ibello tesztek szempontjából azt jelenti, hogy egy keretbeli tartalomhoz mindig új oldal-leírót kell létrehoznunk. Létrehozhatunk többet is,
viszont egy oldal-leíróban nem kezelhetünk kereten belüli és kívüli tartalmakat.

Azt, hogy egy oldal-leíró egy kereten belüli tartalmat kezel, a `@Frame` annotációval jelöljük, megadva neki a keret keresési feltételeit.

```html
<html>	
	<body>
		...
		<iframe src="..."></iframe>
	</body>	
</html>
```

```java
@Frame(using="iframe")
public class FrameContentPage extends PageObject {

	...

}
```
Az annotációval ellátott oldal-leírón belül minden elemkeresést a keretkez képest relatívan kell értenünk.

```html
<button>First</button>
<iframe src="...">
	<button>Second</button>
</iframe>
```

```java
@Frame(using="iframe")
public class FramedPage extends PageObject {

	@Find(using="button")
	private WebElements buttons;

}
```

A fenti példában a `buttons` elemlistában egyetlen elem lesz - az, amelyik a "Second" feliratot viseli.

## Kereten belüli keretek

Ha a kereten belül további keretek vannak, akkor azokat is külön oldal-leírókban kell kezelnünk. Ilyen esetben több `@Frame` annotációt is meg kell
adnunk. A második annotáció az első (legkülső) kereten belül keresi meg a második keretet, a harmadik annotáció a második kereten belül a harmadikat,
és így tovább.

```html
<iframe src="...">
	...
	<iframe src="..."></iframe>
</iframe>
```

```java
@Frame(using="iframe")
@Frame(using="iframe")
public class SecondLevelFrameContentPage extends PageObject {

	...

}
```

## Hasonló tartalmak kezelése különböző kereteken belül

Előfordulhat olyan eset, amikor hasonló tartalmat kell különböző kereteken belül kezelnünk.

```html
<iframe src="..." class="frame1">
	<button id="click-me">Click me!</button>
</iframe>
<iframe src="..." class="frame2">
	<button id="click-me">Click me!</button>
</iframe>
```

Mivel a `@Frame` annotációt közvetlenül az oldal-leíró osztályhoz adjuk hozzá, ezért azt nem lehet megváltoztatni. De így sem kell feltétlenül két ugyanolyan
oldal-leíró osztályt készítenünk. Készíthetünk egy "tiszta", annotáció nélküli oldal-leírót, ami leírja a tartalmat, majd ebből leszármaztatjuk a keretekhez
használt olfal-leírókat, immár annotációval ellátva.

```java
public class BasePage extends PageObject {

	@Find(using="#click-me")
	private WebElement button;

	public void clickButton() {
		doWith(button).click();
	}

}

@Frame(using=".frame1")
public class FirstPage extends BasePage {
}

@Frame(using=".frame2")
public class SecondPage extends BasePage {
}

```
Ezen a módon a `FirstPage` és a `SecondPage` osztályok már a saját keretükön belüli elemeket fogják kezelni.
