package com.example.tunelyrics.controller;

import com.example.tunelyrics.repository.SongRepository;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

    @Autowired
    private SongRepository songRepository;
    private UserRepository userRepository;

    //Home Page
    @GetMapping("/")
    public  String home() {
        return "home"; //thymeleaf page

    }

    //About page
    @GetMapping("/about")
}
