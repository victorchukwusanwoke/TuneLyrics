package com.example.tunelyrics.controller;


import com.example.tunelyrics.dto.RegisterUserDto;
import com.example.tunelyrics.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MainController {
    @GetMapping("/register")
    private String register(Model model) {
        RegisterUserDto userDto = new RegisterUserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @GetMapping("admin/users")
    public String users(Model model) {
        List<User>
    }
}
