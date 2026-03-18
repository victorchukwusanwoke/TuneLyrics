package com.example.tunelyrics.controller;

import com.example.tunelyrics.model.User;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // List all users (admin only)
    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users"; // maps to templates/admin/users.html
    }

    // Optional: future admin pages (e.g., manage songs, reports)
}