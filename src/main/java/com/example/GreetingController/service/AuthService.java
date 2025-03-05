package com.example.GreetingController.service;
import com.example.GreetingController.DTOs.AuthUserDTO;
import com.example.GreetingController.DTOs.LoginDTO;
import com.example.GreetingController.model.AuthUser;
import com.example.GreetingController.repository.AuthUserRepository;
import com.example.GreetingController.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;


    public String registerUser(AuthUserDTO userDTO) {
        Optional<AuthUser> existingUser = authUserRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already in use!");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Save user
        AuthUser user = new AuthUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashedPassword);
        authUserRepository.save(user);

        // Generate JWT Token upon registration
        String token = JWTUtil.generateToken(user.getEmail());

        return "User registered successfully! Token: " + token;
    }


    public String loginUser(LoginDTO loginDTO) {
        try {
            AuthUser user = authUserRepository.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid email or password!");
            }

            sendLoginNotification(user.getEmail());
            String token = JWTUtil.generateToken(user.getEmail());

            return "Login successful! Check your email for a login notification, token: " + token;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during login: " + e.getMessage();
        }
    }


    //Send Email Notification on Login
    public void sendLoginNotification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Login Notification");
        message.setText("Hello,\n\nYou have successfully logged into your account.\n\nIf this wasn't you, please secure your account.");
        mailSender.send(message);
        System.out.println("Login notification email sent to: " + email);
    }
}
