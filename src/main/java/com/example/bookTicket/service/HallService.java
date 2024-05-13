package com.example.bookTicket.service;

import com.example.bookTicket.DTO.HallDTO;
import com.example.bookTicket.entity.Hall;
import org.springframework.http.ResponseEntity;

public interface HallService {
    ResponseEntity<String> addHall(Hall hall);
}
