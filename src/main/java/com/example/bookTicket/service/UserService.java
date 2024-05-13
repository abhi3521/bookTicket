package com.example.bookTicket.service;

import com.example.bookTicket.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    ResponseEntity<String> addUser(User user);
    ResponseEntity<List<User>> getAllUser();
    ResponseEntity<User> getUserById(Long id);
}
