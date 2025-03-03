package com.example.GreetingString;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting getGreet(String name) {
        return new Greeting("Hello, " + name + "!");
    }

    public Greeting postGreeting(Greeting greeting) {
        return new Greeting("Received: " + greeting.getMessage());
    }

    public Greeting putGreeting(String name, Greeting greeting) {
        return new Greeting("Updated: " + name + " -> " + greeting.getMessage());
    }

    public Greeting deleteGreeting(String name) {
        return new Greeting("Deleted: " + name);
    }
}
