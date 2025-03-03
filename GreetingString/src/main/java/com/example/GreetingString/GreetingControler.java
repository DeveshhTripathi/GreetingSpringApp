package com.example.GreetingString;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/greet")
public class GreetingControler {
    private final GreetingService greetingService;

    @Autowired
    public GreetingControler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting getGreeting(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName) {
        return greetingService.getGreet(firstName, lastName);
    }
    @PostMapping("/post")
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return greetingService.postGreet(greeting);
    }

    @PutMapping("/update/{firstName}")
    public Greeting putGreeting(@PathVariable String firstName, @RequestBody Greeting greeting) {
        return greetingService.updateGreet(firstName, greeting);
    }

    @DeleteMapping("/remove/{firstName}")
    public Greeting deleteGreeting(@PathVariable String firstName) {
        return greetingService.deleteGreet(firstName);
    }
}
