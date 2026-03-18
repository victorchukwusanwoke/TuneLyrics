package com.example.tunelyrics.controller;

import com.example.tunelyrics.dto.SongDto;
import com.example.tunelyrics.model.Song;
import com.example.tunelyrics.model.User;
import com.example.tunelyrics.repository.SongRepository;
import com.example.tunelyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class SongController {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Autowired
    public SongController(UserRepository userRepository, SongRepository songRepository){
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    // ============================================================
    //              PUBLIC PAGES
    // ============================================================

    // List all songs
    @GetMapping("/songs")
    public String songs(Model model) {
        List<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "songs/list";
    }

    // View a single song by ID
    @GetMapping("/songs/{id}")
    public String songPage(@PathVariable long id, Model model) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            model.addAttribute("song", song.get());
            return "songs/view";
        } else {
            return "error404";
        }
    }

    // ============================================================
    //              UPLOADER ONLY
    // ============================================================

    // Show upload form
    @GetMapping("/songs/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("songDTO", new SongDto());
        return "songs/upload";
    }

    // Handle upload form submission
    @PostMapping("/songs/upload")
    public String uploadSong(@ModelAttribute SongDto songDto, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User uploader = userRepository.findByEmail(principal.getName()); // corrected method

        if (uploader == null) {
            return "redirect:/login";
        }

        Song song = new Song();
        song.setTitle(songDto.getTitle());
        song.setArtist(songDto.getArtist());
        song.setYoutubeUrl(songDto.getYoutubeUrl());
        song.setLyrics(songDto.getLyrics());
        song.setSpotifyUrl(songDto.getSpotifyUrl());
        song.setMp3Url(songDto.getMp3Url());
        song.setUser(uploader);

        songRepository.save(song);

        return "redirect:/songs";
    }
}