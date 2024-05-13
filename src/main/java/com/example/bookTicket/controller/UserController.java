package com.example.bookTicket.controller;

import com.example.bookTicket.entity.User;
import com.example.bookTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/healthCheck")
    public String healthCheck(){
        return "System is up";
    }
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody(required = true) User user) {
        return userService.addUser(user);
    }
}
