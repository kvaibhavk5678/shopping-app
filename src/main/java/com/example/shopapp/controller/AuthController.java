package com.example.shopapp.controller;

import com.example.shopapp.entity.User;
import com.example.shopapp.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupForm signupForm, Model model) {
        if (userRepository.existsByUsername(signupForm.getUsername())) {
            model.addAttribute("error", "That username is already taken.");
            return "signup";
        }
        if (userRepository.existsByEmail(signupForm.getEmail())) {
            model.addAttribute("error", "That email is already registered.");
            return "signup";
        }
        if (signupForm.getPassword() == null || signupForm.getPassword().length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters.");
            return "signup";
        }

        User user = new User(
                signupForm.getUsername(),
                signupForm.getEmail(),
                passwordEncoder.encode(signupForm.getPassword())
        );
        userRepository.save(user);

        return "redirect:/login?registered";
    }

    /** Simple form-backing object for the signup page. */
    public static class SignupForm {
        @NotBlank
        private String username;

        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 6)
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
