# **Tesztelendő alkalmazás verziószámának megadása**

Fontos lehet, hogy a tesztfutások eredményeiben lássuk a tesztelt alkalmazás verziószámát. Az ibello 1.20.0 verziótól erre az alábbi módokon van lehetőségünk.

## A konfigurációs paraméter megadásával

A `default.properties` konfigurációs fájlhoz adjuk hozzá a következő sort:

`ibello.application.version=5.5.50`

A legközelebbi teszt futtatáskor a verziószám benne lesz a riportban. 

## Kódból történő megadás

Kódból történő megadás esetén, hozzunk létre az ibello mappa szerkezetén belül egy plugins csomagot. Majd adjunk hozzá egy új osztályt  `ApplicationVersionCollector.java` néven, az alábbi tartalommal:

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

A `default.properties` fájlban lévő `ibello.plugin` paraméterhez adjuk hozzá az újonnan létrehozott plugin osztályt. Több plugin osztály esetén, azokat vesszővel elválasztva soroljuk fel.

`ibello.plugin=hu.ibello.test.plugins.TestPlugin1, hu.ibello.test.plugins.ApplicationVersionCollector`

A legközelebbi teszt futtatáskor a verziószám benne lesz a riportban. 
