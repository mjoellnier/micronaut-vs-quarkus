# What is this all about?

As I like to learn new stuff I thought about giving other _Microservice Frameworks_ besides Spring Boot a chance. Two main player came to my mind: [Micronaut](https://www.micronaut.io) and [Quarkus](https://www.quarkus.io). On the first look I could see nearly no difference between these two. So I started this project to compare the two.

The idea is to continue working on this. So I will start with the basics (Project initialisation, Dependency Injection, Testing) and as I continue learning I will continue comparing these two frameworks. **Feedback is highly welcomed of course!** If you're interested in follow me along this learning journey you can also [follow me on Twitter](https://www.twitter.com/coding_max).

# Current project scope

Both framework should be used to create the following:

- basic project structure
- a `/hello` endpoint that returns a simple `Hello` String
- a `/greeting/ {name}` endpoint that returns `Hello {name}` as a String
- Unit tests for all endpoints

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

## Dependency Injection

According to the [getting started docs](https://quarkus.io/guides/getting-started#using-injection) Quarkus' DI is based upon ArC that already comes as a dependency of `quarkus-resteasy`.

Working with DI is pretty straight forward as it uses the `javax` notation: After creating a `@ApplicationScoped` class it can be injected via `@Inject` into the needed location, in this case the `GreetingResource` class. As we started the server in `dev` mode the new injected bean can be used without restarting the server.

## Testing

Quarkus comes with JUnit5 and RestAssured per default. There is nothing special to think about. I wrote one more test to the default test, running this two tests took my machine 7.661s.
