package com.example.GreetingString;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }
    @GetMapping("/{name}")
    public Greeting getGreet(@PathVariable String name){
        return new Greeting("Hello, "+name+"!");
    }
    @PostMapping("/post")
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return new Greeting("Received: " + greeting.getMessage());
    }

    @PutMapping("/update/{name}")
    public Greeting putGreeting(@PathVariable String name, @RequestBody Greeting greeting) {
        return new Greeting("Updated: " + name + " -> " + greeting.getMessage());
    }

    @DeleteMapping("/remove/{name}")
    public Greeting deleteGreeting(@PathVariable String name) {
        return new Greeting("Deleted: " + name);
    }

    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElse(new Greeting("Greeting not found!"));
    }
}
