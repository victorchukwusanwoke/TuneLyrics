package com.example.tunelyrics.controller;


import com.example.tunelyrics.dto.RegisterUserDto;
import com.example.tunelyrics.model.User;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Home Page
    @GetMapping("/")
    public String home() {
        return "home"; //thymeleaf page
    }

    //About Page
    @GetMapping("/about")
    public String about() {
        return  "about";
    }

    //Login page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterUserDto userDto = new RegisterUserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserDto userDto) {

        // Convert DTO to User entity
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // TODO: encode password in production
        user.setRole("UPLOADER"); // default role

        // Save to database
        userRepository.save(user);

        // Redirect to login after registration
        return "redirect:/login";
    }

    //Error404 page
    @GetMapping("/error404")
    public String error404(){
        return "error404";
    }

}
