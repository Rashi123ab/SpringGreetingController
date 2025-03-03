package com.example.GreetingController.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetingController {

    // GET Method
    @GetMapping
    public Map<String, String> getGreeting() {
        return Map.of("message", "Hello!! This is a GET request.");
    }
    //tested get method using Curl command--  curl http://localhost:8080/greet

    // POST Method
    @PostMapping
    public Map<String, String> postGreeting(@RequestBody(required = false) Map<String, String> body) {
        String name = (body != null && body.containsKey("name")) ? body.get("name") : "Guest";
        return Map.of("message", "Hello, " + name + "!! This is a POST request.");
    }
    //tested post method using  curl.exe -X POST http://localhost:8080/greet -H 'Content-Type: application/json' -d '{\"name\": \"Rashi\"}'
    // PUT Method
    @PutMapping
    public Map<String, String> putGreeting(@RequestBody(required = false) Map<String, String> body) {
        String name = (body != null && body.containsKey("name")) ? body.get("name") : "Guest";
        return Map.of("message", "Hello, " + name + "!! This is a PUT request.");
    }
    //curl.exe -X PUT http://localhost:8080/greet -H 'Content-Type: application/json' -d '{\"name\": \"Alice\"}'

    // DELETE Method
    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        return Map.of("message", "Hello!! This is a DELETE request.");
    }
}
//curl.exe -X DELETE http://localhost:8080/greet -H 'Content-Type: application/json' -d '{\"name\": \"Alice\"}'