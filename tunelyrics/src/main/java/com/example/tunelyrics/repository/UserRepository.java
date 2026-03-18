package com.example.tunelyrics.repository;

import com.example.tunelyrics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);

    //to list users by role ADMIN or UPLOADER
    List <User> findAllByRole(String role);

}
