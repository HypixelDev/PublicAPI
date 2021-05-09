Hypixel Public API (Java)
======
This is a Java implementation of the Hypixel API. For discussing the API, requesting help or suggestions you can use the
GitHub [Discussions](https://github.com/HypixelDev/PublicAPI/discussions).

### Documentation

Hypixel Public API documentation can be found at [https://api.hypixel.net/](https://api.hypixel.net/). Java
documentation can be found in the code.

### GitHub Issues

GitHub issues should only be used to report bugs. Everything else should either be in GitHub discussions or use the
Hypixel [Code Creations](https://hypixel.net/forums/code-creations.65/) forum.

### Usage

You can use this API as a dependency via the public Hypixel maven repo. You can also use
the [Example Code](https://github.com/HypixelDev/PublicAPI/tree/master/Example) as a good starting point.


#### Hypixel Maven Repo
```xml
<repository>
    <id>Hypixel</id>
    <url>https://repo.hypixel.net/repository/Hypixel/</url>
</repository>
```

#### Transports

We include two built in options communicating with the Hypixel API, you can include either of these or even include the
core API directly and create your own instance of HypixelHTTPClient.

Below is an example of using the Apache HttpClient based transport dependency via Maven.
```xml
<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>hypixel-api-transport-apache</artifactId>
    <version>4.0.0</version>
</dependency>
```

```java
public class Main {
    public static void main(String[] args) {
        HypixelHTTPClient client = new ApacheHTTPClient(UUID.fromString("your-api-key-here"));
        HypixelAPI hypixelAPI = new HypixelAPI(client);
        hypixelAPI.getPlayerByName("Hypixel").thenAccept(System.out::println);
    }
}
```

#### Gradle

This repo can also be used with Gradle in the following form.

```gradle
repositories {
    maven { url 'https://repo.hypixel.net/repository/Hypixel/' }
}
```
```gradle
dependencies {
    implementation 'net.hypixel:hypixel-api-transport-apache:4.0.0'
}
```

### Dependencies

The Hypixel API Core implementation has the following dependencies:

* Google Gson library

Transports will also have dependencies where required.


### Contributing

When contributing changes to the Java API please provide as much detail on the changes and the reasons for them. We will
not accept changes that have no meaningful contribution to the project.