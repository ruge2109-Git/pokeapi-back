# pokeapi-back

## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Execute Test
```shell
mvn test
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.modyo.pokeapi.PokeapiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


## Deploy
This application is deploy in AWS using service Elastic Beanstalk

![image](https://user-images.githubusercontent.com/53587500/179534332-2f799d69-adaa-4be6-87f6-f37f5411772f.png)
