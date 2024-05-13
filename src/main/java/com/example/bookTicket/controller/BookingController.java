package com.example.bookTicket.controller;

import com.example.bookTicket.DTO.BookingDTO;
import com.example.bookTicket.DTO.BookingResponseDTO;
import com.example.bookTicket.service.BookingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping("/bookTicket")
    public ResponseEntity<BookingResponseDTO> bookTicket(@RequestBody BookingDTO bookingDTO) {
        return bookingService.bookTicket(bookingDTO);
    }
    @GetMapping("/bookingDetails/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingDetails(@PathVariable("bookingId") Long bookingId) {
        return bookingService.getBookingDetails(bookingId);
    }
    @PutMapping("/cancelTicket")
    public  ResponseEntity<String> cancelTicket(@RequestBody Long bookingId) {
        return bookingService.cancelTicket(bookingId);
    }
}
