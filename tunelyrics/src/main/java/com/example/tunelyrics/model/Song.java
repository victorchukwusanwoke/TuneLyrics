package com.example.tunelyrics.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "songs", uniqueConstraints = {@UniqueConstraint(columnNames = {"artist", "title"})}) //to avoid repetaion of titles and artist

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String title;

    @Column(length = 250, nullable = false)
    private String artist;

    @Column(nullable = false)
    private String youtubeUrl;

    @Lob //to overide the maximum character of 255
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String lyrics; //Longtext cause its needs alot of characters

    @Column
    private String spotifyUrl;


    @Column
    private String mp3Url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //uploader


}
