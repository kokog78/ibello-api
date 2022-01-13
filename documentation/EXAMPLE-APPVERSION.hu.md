# **Tesztelendő alkalmazás verzió számának megadása**

Fontos lehet, mikor visszatekintünk a tesztfutások eredményeire, lássuk az alkalmazásunk épp milyen verzió számot viselt. Erre most lehetőség nyílik az ibello 1.20.-es verziótól. Több lehetőségünk is van:

## A konfigurációs fájlban az _ibello.application.version_ segítségével

A `default.properties` konfigurációs fájlhoz a következő sort adjuk:

`ibello.application.version=5.5.50`

A következő teszt futtatáskor a riportokban benne lesz a verziószám. 

## Kódból történő megadás

Kódból történő megadás esetén, létre kell hozzunk, az ibellos mappa szerkezeten belül egy plugins csomagot, ha még nem volna. Majd ide létrehozunk egy új java osztályt: `ApplicationVersionCollector.java` néven. Illesszük be az alábbi kódot:

```java
import hu.ibello.core.Name;
import hu.ibello.plugins.Plugin;
import hu.ibello.plugins.PluginException;
import hu.ibello.plugins.PluginInitializer;

@Name("Application version collector")
public class ApplicationVersionCollector implements Plugin {

  @Override
  public void initialize(PluginInitializer initializer) throws PluginException {
      String version = "5.5.55";
      initializer.info("Application version: " + version);
      initializer.setConfigurationValue("ibello.application.version", version);
  }

  @Override
  public void shutdown() throws PluginException {
    // do nothing
  }
    
}
```

A default.properties fájlban, hozzáadjuk az ApplicationVersionCollector plugint. Ha még nem volna ibello.plugin kezdetű sor, adjuk hozzá új sorba (ha már van vesszővel elválasztva). Írjuk be a plugin elérését (ibello-acceptance-test esetén hu.ibello.test.plugins.ApplicationVersionCollector). Az acceptance-test `ibello.plugin` sora így alakul:

`ibello.plugin=hu.ibello.test.plugins.TestPlugin2,hu.ibello.test.plugins.TaskTestPlugin,hu.ibello.test.plugins.TaskTestPlugin2,hu.ibello.test.plugins.ApplicationVersionCollector`

Ezt követően, a teszt futtatáskor bekerül a beállított verziószám.
