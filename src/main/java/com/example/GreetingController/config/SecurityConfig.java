package com.example.GreetingController.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF (for REST API)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login", "/error").permitAll() // ✅ Allow authentication paths
                        .requestMatchers("/h2-console/**").permitAll() // ✅ Allow H2 Console
                        .anyRequest().authenticated() // ✅ Protect other endpoints
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // ✅ Required for H2 Console
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // ✅ Use JWT instead of sessions
                .httpBasic(httpBasic -> httpBasic.disable()); // ✅ Disable default Basic Auth

        return http.build();
    }
}
