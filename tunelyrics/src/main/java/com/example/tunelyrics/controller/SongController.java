package com.example.tunelyrics.controller;

import com.example.tunelyrics.dto.RegisterUserDto;
import com.example.tunelyrics.model.Song;
import com.example.tunelyrics.repository.SongRepository;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {

    private UserRepository userRepository;
    private SongRepository songRepository;
    @Autowired
    public SongController(UserRepository userRepository,SongController songController){
        this.userRepository = userRepository;
        this.songRepository = songController;
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

    //Songs Page
    @GetMapping("/songs")
    public String songs(Model model) {
        List<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "songs/list";
    }

   @GetMapping("songs/{id}")
    public String songPage(@PathVariable long id, Model model) {
       Optional<Song> song =songRepository.findById(id);
       if (song.isPresent()) {
           model.addAttribute("song", song.get();
           return "songs"; //page for viewing lyrics
       }
       else {
           return "error404";
       }
   }


}
