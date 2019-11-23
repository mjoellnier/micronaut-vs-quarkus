package getting.started;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class HelloControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void testHello() {
        HttpRequest<String> request = HttpRequest.GET("/hello");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello", body);
    }

    @Test
    public void testHelloName() {
        String uuid = UUID.randomUUID().toString();
        HttpRequest<String> request = HttpRequest.GET("/hello/greeting/" + uuid);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello " + uuid, body);
    }
}