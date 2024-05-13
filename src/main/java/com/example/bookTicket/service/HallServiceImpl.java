package com.example.bookTicket.service;

import com.example.bookTicket.DTO.HallDTO;
import com.example.bookTicket.entity.Hall;
import com.example.bookTicket.entity.Seat;
import com.example.bookTicket.repository.HallRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    HallRepository hallRepository;

    @Override
    public ResponseEntity<String> addHall(Hall hall) {
        hallRepository.save(hall);
        return ResponseEntity.ok("Hall added Successfully.");
    }
}
