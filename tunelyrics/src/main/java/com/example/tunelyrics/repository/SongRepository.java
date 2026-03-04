package com.example.tunelyrics.repository;

import com.example.tunelyrics.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    // Find songs by exact title
    List<Song> findByTitle(String title);

    // Search songs by artist (contains, ignoring case)
    List<Song> findByArtistContainingIgnoreCase(String artist);

    // Search songs by lyrics content (contains, ignoring case)
    List<Song> findByLyricsContainingIgnoreCase(String lyrics);

    // Optional: search by multiple fields (title OR artist)
    List<Song> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String title, String artist);
}
