# What is this all about?

As I like to learn new stuff I thought about giving other _Microservice Frameworks_ besides Spring Boot a chance. Two main player came to my mind: [Micronaut](https://www.micronaut.io) and [Quarkus](https://www.quarkus.io). On the first look I could see nearly no difference between these two. So I started this project to compare the two.

The idea is to continue working on this. So I will start with the basics (Project initialisation, Dependency Injection, Testing) and as I continue learning I will continue comparing these two frameworks. **Feedback is highly welcomed of course!** If you're interested in follow me along this learning journey you can also [follow me on Twitter](https://www.twitter.com/coding_max).

# Current project scope

Both framework should be used to create the following:

- basic project structure
- a `/hello` endpoint that returns a simple `Hello` String
- a `/greeting/ {name}` endpoint that returns `Hello {name}` as a String
- Unit tests for all endpoints

# The Micronaut way

## Project creation

Micronaut has its own CLI tool which has to be installed separatly. In my case I used the Homebrew way of doing it, but you can find the installation ways in the [official docs](https://docs.micronaut.io/snapshot/guide/index.html#cli). As soon as the CLI tool is installed you can bootstrap your project with `mn create-app my-app`. Micronaut uses Gradle as default build tool, as I'm more familiar with Maven and to be able to compare it better with Quarkus I used the `--build maven` flag to initialize it with Maven.

The command `mn create-app getting-started --build maven` took my machine around 5 seconds. There is no detailled logging on the time, so it is just a guessed number. It created:

- the basic project structure for a Maven project
- a `Application` class
- an `index.html` file with basic information which can be accessed on `localhost:8080`
- a `Dockerfile`
- a rudimentary prefilled `application.yml` and a preconfigured `logback.xml`

You can run the application with `./mvnw clean compile exec:exec`. My machine needed 1.380s to startup the default application.

## Creating the REST endpoints

As the CLI tool does not create an empty controller class the first step is to create this. As Micronaut uses the standard javax specification it is pretty straight forward to create the controller: the class needs an `@Controller` annotation, the methods need the coresponding `@Get("path")` and `@Produces` annotations.

## Dependency Injection

Micronaut uses compile time information to create the DI capability. This makes reflection and proxies nearly obsolete. This minimizes the memory footprint and the startup times. If you want to, you can use the DI/IoC parts of Micronaut without the entire framework. Details can be found in the [official docs](https://docs.micronaut.io/latest/guide/index.html#ioc).

Analogue to the endpoint creation the DI implementation is according to the [Javax specification](http://javax-inject.github.io/javax-inject/). This offers the annotations system for different scopes and the naming capability. Furthermore for different Bean implementations there is a Micronaut `@Primary` to tag the primary implementation of a bean.

Starting up the application with the Service bean and the two endpoints took my machine 1.085s.

## Testing

Micronaut comes with JUnit5 per default. Unittests should be annnotated with `@MicronautTest` so Micronaut boots up the Application context and the embedded server for the test. Micronaut also comes with a minimal Client implementation and a fluid `HttpRequest` API to quickly unittest the endpoints.

As the CLI tool did not create a default test class I started my test implementation with creating the file. After writing the two basic tests my machine took 4.726s to run them with `mvn test`.

# The Quarkus way

## Project creation

Quarkus uses Maven as creation tool for project bootstraping. With the following command the project can be initialized:

```bash
mvn io.quarkus:quarkus-maven-plugin:1.0.0.CR2:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=getting-started \
    -DclassName="org.acme.quickstart.GreetingResource" \
    -Dpath="/hello"
```

This command took on my machine 18.002s - not bad at all. It created the following:

- the basic project structure for a Maven project
- a `GreetingResource` file that exposes a `/hello` REST endpoint
- an `index.html` file with basic information which can be accessed on `localhost:8080`
- Unittest for the `GreetingResource` class
- a `Dockerfile` for both: native and jvm
- an empty configuration file

You can start your application with `./mvnw compile quarkus:dev` and access it on `localhost:8080`. Starting up the service with this command took my machine 1.606s. Something nice: starting up the service in `quarkus:dev` allows you to live code without restarting your application.

## Creating the REST endpoints

The initial project setup already created the minimal `/hello` endpoint so there is no further doing necessary here. For creating the "named" endpoint it is enough to create a new method with the needed annotations `@GET`, `@Produces(MediaType.TEXT_PLAIN)` and `@Path("/greeting/{name}")`. Interesting side note: Quarkus does not need an `Application` class, but it would support one. Furthermore all resources get created as a Singleton per default (which can be adjusted via a `Scoped` annotation).

## Dependency Injection

According to the [getting started docs](https://quarkus.io/guides/getting-started#using-injection) Quarkus' DI is based upon ArC that already comes as a dependency of `quarkus-resteasy`.

Working with DI is pretty straight forward as it uses the `javax` notation: After creating a `@ApplicationScoped` class it can be injected via `@Inject` into the needed location, in this case the `GreetingResource` class. As we started the server in `dev` mode the new injected bean can be used without restarting the server.

## Testing

Quarkus comes with JUnit5 and RestAssured per default. There is nothing special to think about. I wrote one more test to the default test, running this two tests took my machine 7.661s.
