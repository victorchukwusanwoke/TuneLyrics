package com.example.tunelyrics.controller;

import com.example.tunelyrics.model.User;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String users(Model model) {
        List<User> uploaders = userRepository.findAllByRole("ROLE_UPLOADER");

        model.addAttribute("uploaders", uploaders);

        return "users";
    }

    public  User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }
}