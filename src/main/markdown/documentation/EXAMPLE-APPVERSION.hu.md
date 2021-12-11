# Tesztelendő alkalmazás verziószámának megadása

Fontos lehet, mikor visszatekintünk a tesztfutások eredményeire, lássuk az alkalmazásunk épp milyen verziószámot viselt. Erre most lehetőség nyílik az ibello 1.20.-es verziójától. Több lehetőségünk is van hogy beállíthassuk ezt:

* A projectkönyvtárban lévő default.properties fájlban az _ibello.application.version=_ segítségével.
* A kódban majd a default.properties fájlban a plugin megadásával.

Előbb az *első* aztán a *második* módszert mutatom meg. Adott egy ibello project. Itt meg kell keressük, a mappa struktúrában a default.properties fájlt, ezt a következő útvonalon találjuk meg: _ibello projekt mappa\ibello\config\default.properties_. Majd egy tetszőleges szerkesztővel megnyitjuk a fájlt, és hozzá adunk egy új sort :

`ibello.application.version=kívánt verziószám megadása`

Mentjük a beállításokat, majd ha lefuttatjuk az előbb beállított project tesztjeit, láthatjuk, hogy mint a ._html_ ,  _.xml_ riportba, mint az ibello felületén megjelenik a beállított verziószám.



Kódból történő megadás esetén, létre kell hozzunk, az ibellós mappaszerkezet belül egy plugins mappát (packaget) ha még nem volna. Tehát az _ibello projekt mappa/src/main/java/**akármi**/plugins_. Majd ide hozzunk létre egy új java osztályt: ApplicationVersionCollector.java néven. Ezt célszerű valamilyen IDE-ből csinálni, így a létrehozott java osztály tartalmazza már a csomag elérést. Illesszük be az alábbi kódot,

```java
package az én plugin csomag elérésem

import hu.ibello.core.Name;
import hu.ibello.plugins.Plugin;
import hu.ibello.plugins.PluginException;
import hu.ibello.plugins.PluginInitializer;

@Name("Application version collector")
public class ApplicationVersionCollector implements Plugin {

  @Override
  public void initialize(PluginInitializer initializer) throws PluginException {
      String version = "verziószám";
      initializer.info("Application version: " + version);
      initializer.setConfigurationValue("ibello.application.version", version);
  }

  @Override
  public void shutdown() throws PluginException {
    // do nothing
  }
}
```

majd írjuk be a logokban látni kívánt verziószámot a 

 ` String version = "verziószám";`(verziószám helyére).

Most már csak annyi dolgunk van, hogy a default.properties fájlban, hozzáadjuk az  ApplicationVersionCollector plugint. Ezt a következőképp tehetjük meg: megnyitjuk egy szerkesztővel az alábbi fájlt: _projekt könyvtár\ibello\config\default.properties_. Ha még nem volna ibello.plugin kezdetű sor adjuk hozzá új sorba (ha már van vesszővel elválasztva). Írjuk be a plugin elérést (az ibello acceptance-test esetében hu.ibello.test.plugins.ApplicationVersionCollector). Tehát több plugin esetén, az acceptance-test plugin `ibello.plugin` sora így fog kinézni:

`ibello.plugin=hu.ibello.test.plugins.TestPlugin2,hu.ibello.test.plugins.TaskTestPlugin,hu.ibello.test.plugins.TaskTestPlugin2,hu.ibello.test.plugins.ApplicationVersionCollector`

Mentsük el a konfigurációs fájlt. Ezt követően, a tesztfutásokkor bekerül a beállított verziószám.
