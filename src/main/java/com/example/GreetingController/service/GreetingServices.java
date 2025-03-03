package com.example.GreetingController.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.GreetingController.model.Greeting;
import com.example.GreetingController.repository.GreetingRepository;
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
}
