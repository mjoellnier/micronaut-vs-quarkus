package getting.started;

import javax.inject.Inject;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/hello")
public class HelloController {

    @Inject
    private GreetingService service;

    @Get("/greeting/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting(String name) {
        return service.greeting(name);
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello";
    }
}