package com.example.bookTicket.service;

import com.example.bookTicket.entity.User;
import com.example.bookTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<String> addUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user.getName() + " added successfully", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList =  userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
