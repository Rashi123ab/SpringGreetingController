package com.example.GreetingController.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    public List<Greeting> getAllGreetings() {//UC6
        return greetingService.getAllGreetings();
    }

    //http://localhost:8080/greet/save?message=Hello&&message=Rashi
    //http://localhost:8080/greet/all

    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }
    //to fetch msg with id:2----http://localhost:8080/greet/2

    @PutMapping("/update/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestParam String message) {
        return greetingService.updateGreeting(id, message);
    }
    //http://localhost:8080/greet/update/2?message=hi...
}