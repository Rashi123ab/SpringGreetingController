package com.example.GreetingController.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.example.GreetingController.service.GreetingServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.GreetingController.model.Greeting;

@RestController
@RequestMapping("/greet")
public class GreetingController {
    private final GreetingServices greetingService;
@Autowired
    public GreetingController(GreetingServices greetingService) {
    this.greetingService = greetingService;
}

    @PostMapping("/save")
    public Greeting saveGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
    }