package com.example.GreetingString;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting getGreet(String firstName, String lastName) {
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            return new Greeting("Hello, " + firstName + " " + lastName + "!");
        } else if (firstName != null && !firstName.isEmpty()) {
            return new Greeting("Hello, " + firstName + "!");
        } else if (lastName != null && !lastName.isEmpty()) {
            return new Greeting("Hello, " + lastName + "!");
        } else {
            return new Greeting("Hello World!");
        }
    }


    public Greeting postGreet(Greeting greeting) {
        return new Greeting("Received: " + greeting.getMessage());
    }

    public Greeting updateGreet(String firstName, Greeting greeting) {
        return new Greeting("Updated: " + firstName + " -> " + greeting.getMessage());
    }

    public Greeting deleteGreet(String firstName) {
        return new Greeting("Deleted: " + firstName);
    }
}
