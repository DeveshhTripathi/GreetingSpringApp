package com.example.GreetingString;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greet")
class GreetingController {
    private final GreetingService greetingService;

    GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
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


    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting);
    }

    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
    @PutMapping("/update/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody Greeting greeting) {
        return greetingService.updateGreeting(id, greeting);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
        return "Greeting deleted successfully!";
    }
}

