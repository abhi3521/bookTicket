package com.example.bookTicket.controller;

import com.example.bookTicket.DTO.HallDTO;
import com.example.bookTicket.entity.Hall;
import com.example.bookTicket.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    HallService hallService;

    @PostMapping("/addHall")
    public ResponseEntity<String> addHall(@RequestBody Hall hall) {
        return hallService.addHall(hall);
    }
}
