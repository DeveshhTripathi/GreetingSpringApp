package com.example.GreetingString;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }


    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElse(new Greeting("Greeting not found!"));
    }
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
    public Greeting updateGreeting(Long id, Greeting newGreeting) {
        return greetingRepository.findById(id)
                .map(existingGreeting -> {
                    existingGreeting.setMessage(newGreeting.getMessage());
                    return greetingRepository.save(existingGreeting);
                })
                .orElseThrow(() -> new RuntimeException("Greeting not found with id: " + id));
    }

}
