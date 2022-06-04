# Egyedi JSON szerializáció

Egyes esetekben olyan JSON objektumokat is szeretnénk szerializálni illetve deszerializálni a tesztekben, amikben egyedi megoldásra van szükségünk.

Például vegyük az alábbi osztályt:

```java
public class MyObject {
	MyInterface field;
}
```

A `field` mező típusa egy interfész:

```java
public interface MyInterface {
}
```

Az interfésznek két megvalósítása létezik:

```java
public class MyField1 implements MyInterface {
	int value1;
}
```

és

```java
public class MyField2 implements MyInterface {
	long value2;
}
```

Ha egy tesztlépés-könyvtárban MyObject típusú objektumot szeretnénk egy JSON-ból kiolvasni, akkor hibát kapunk, mert MyInterface típusú objektumot nem tudunk létrehozni. 

```java
public class MySteps extends StepLibrary {

	public void read_json() {
		String json = "{\"value1\": 1}";
		MyObject object = json().fromJson(json, MyObject.class); // ez nem fut le
	}
}
```

Szükséges, hogy megtanítsuk az ibello-t arra, hogyan kell kezelnie a MyInterface típusú elemeket. Ehhez regisztrálnunk kell két osztályt, amik szerializálják
és deszerializálják az ilyen típusú értékeket. Ezt meg tudjuk tenni például egy ibello bővítmény létrehozásával.

```java
public class MyPlugin implements Plugin {

	@Override
	public void initialize(PluginInitializer initializer) throws PluginException {
		try {
			initializer.json().registerDeserializer(new JsonTypeDeserializer<MyInterface>() {
				@Override
				public Class<MyInterface> getType() {
					return MyInterface.class;
				}
				@Override
				public MyInterface deserialize(String source, JsonDeserializer context) throws TransformerException {
					MyInterface result;
					if (source.indexOf("\"value1\"") > 0) {
						result = context.fromJson(source, MyField1.class);
					} else {
						result = context.fromJson(source, MyField2.class);
					}
					return result;
				}
			});
			initializer.json().registerSerializer(new JsonTypeSerializer<MyInterface>() {
				@Override
				public Class<MyInterface> getType() {
					return MyInterface.class;
				}
				@Override
				public String serialize(MyInterface source, JsonSerializer context) throws TransformerException {
					return context.toJson(source);
				}
			});
		} catch (Exception ex) {
			throw new PluginException("Cannot register JSON processor classes for MyInterface.", ex);
		}
	}

	@Override
	public void shutdown() throws PluginException {
	}
	
}
```

A deszerializáláshoz használt osztály `deserialize` metódusa megkapja azt a JSON töredéket, ami a MyInterface típusra vonatkozik.
Valahogyan el kell dönteni, hogy ebből MyField1 vagy MyField2 objektumot olvasunk ki.
A példában egyszerűen megnézzük, hogy a JSON töredék melyik osztályra jellemző mezőnevet tartalmazza.
Természetesen más megoldás is elképzelhető.

A szerializálás jelen esetben könnyebb. A `serialize` metódus megkapja a kezelendő MyInterface objektumot,
amiről tudjuk, hogy valójában MyField1 vagy MyField2 típusú.
Itt elég csak az ibello által felajánlott `context.toJson` metódust használni, az automatikusan a konkrét típus szerint fogja elvégezni a szerializációt.

A fenti példában szereplő MyPlugin osztályt még be kell írnunk az ibello konfigurációba, például a "default.properties" fájlba:

```properties
ibello.plugin=MyPlugin
```

Ez után az ibello a tesztfutások előtt már automatikusan be fogja tölteni a bővítményt, ami pedig létre fogja hozni az egyedi JSON kezelő osztályokat.
Így a tesztlépés-könyvtárban korábban bemutatott kódrészlet már nem fog kivételt dobni.
