package com.example.tunelyrics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private String title;
    private  String artist;
    private String youtubeUrl;
    private String lyrics;
    private  String spotifyUrl;
    private String mp3Url;
}
