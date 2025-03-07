package com.example.GreetingController.controller;
import com.example.GreetingController.DTOs.AuthUserDTO;
import com.example.GreetingController.DTOs.LoginDTO;
import com.example.GreetingController.repository.AuthUserRepository;
import com.example.GreetingController.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return ResponseEntity.ok(response);


    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }
    //Forgot password api
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<Map<String, String>> forgotPassword(
            @PathVariable String email,
            @RequestBody Map<String, String> requestBody) {

        String message = authService.forgotPassword(email, requestBody.get("password"));
        return ResponseEntity.ok(Map.of("message", message));
    }

    //Reset Password API
    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<Map<String, String>> resetPassword(
            @PathVariable String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        String message = authService.resetPassword(email, currentPassword, newPassword);
        return ResponseEntity.ok(Map.of("message", message));
    }
}
//http://localhost:8080/swagger-ui/index.html   to run swagger application