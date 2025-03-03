package com.example.GreetingController.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.GreetingController.model.Greeting;
import com.example.GreetingController.repository.GreetingRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Service
public class GreetingServices{

    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {
          return greetingRepository.findAll();
    }
    // New method to find a Greeting by IDs
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found with ID: " + id));
    }
    //method to edit a greeting message
    public Greeting updateGreeting(Long id, String message) {
        Greeting existingGreeting = greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));

        existingGreeting.setMessage(message);  // Update message
        return greetingRepository.save(existingGreeting);  // Save updated greeting
    }
}
