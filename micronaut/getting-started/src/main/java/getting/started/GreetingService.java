package getting.started;

import javax.inject.Singleton;

@Singleton
public class GreetingService {

    public String greeting(String name) {
        return "Hello " + name;
    }

}