package com.example.GreetingController.controller;
import com.example.GreetingController.DTOs.AuthUserDTO;
import com.example.GreetingController.DTOs.LoginDTO;
import com.example.GreetingController.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;




    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return ResponseEntity.ok(response);


    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }
}
