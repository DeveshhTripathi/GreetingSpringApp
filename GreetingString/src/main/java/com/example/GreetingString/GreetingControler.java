package com.example.GreetingString;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greet")
class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/{name}")
    public Greeting getGreet(@PathVariable String name) {
        return new Greeting("Hello, " + name + "!");
    }

    @PostMapping("/post")
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting);
    }

    @PutMapping("/update/{name}")
    public Greeting putGreeting(@PathVariable String name, @RequestBody Greeting greeting) {
        return new Greeting("Updated: " + name + " -> " + greeting.getMessage());
    }

    @DeleteMapping("/remove/{name}")
    public Greeting deleteGreeting(@PathVariable String name) {
        return new Greeting("Deleted: " + name);
    }

    // ✅ Corrected saving method
    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting);
    }
}

