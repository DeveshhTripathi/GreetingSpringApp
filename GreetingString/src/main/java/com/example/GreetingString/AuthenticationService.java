package com.example.GreetingString;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JavaMailSender mailSender;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        AuthUser newUser = new AuthUser();
        newUser.setFirstName(authUserDTO.getFirstName());
        newUser.setLastName(authUserDTO.getLastName());
        newUser.setEmail(authUserDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        authUserRepository.save(newUser);

        // Send a welcome email
        sendWelcomeEmail(newUser.getEmail());

        return "User registered successfully! A welcome email has been sent.";
    }

    private void sendWelcomeEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Our Platform");
        message.setText("Hello,\n\nThank you for registering with us! We are excited to have you onboard.\n\nBest regards,\nYour Team");

        mailSender.send(message);
    }

    public String loginUser(org.example.greetingspring.LoginDTO loginDTO) {
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            return "Login successful!";
        }
        throw new RuntimeException("Invalid credentials");
    }
}



