package com.example.GreetingController.service;
import org.springframework.stereotype.Service;

@Service
public class GreetingServices{
 //UC2
    public String getGreetMessage() {
        return "Hello, World!";
    }
}
