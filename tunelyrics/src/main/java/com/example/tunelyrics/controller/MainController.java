package com.example.tunelyrics.controller;


import com.example.tunelyrics.dto.RegisterUserDto;
import com.example.tunelyrics.model.User;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    // Handle registration form submission


    //Home Page
    @GetMapping("/")
    public  String home() {
        return "home";
    }

    //Login page
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/register")
    public  String register(Model model) {
        RegisterUserDto userDto = new RegisterUserDto();
        model.addAttribute("userDto", userDto);
        return  "register";
    }


    @PostMapping("/register")
    public  String doRegister(@ModelAttribute RegisterUserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setRole("ROLE_UPLOADER");
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return  "redirect:/login";
    }
    //Error404 page
    @GetMapping("/error404")
    public String error404(){
        return "error404";
    }

}
