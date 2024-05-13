package com.example.bookTicket.service;

import com.example.bookTicket.DTO.BookingDTO;
import com.example.bookTicket.DTO.BookingResponseDTO;
import org.springframework.http.ResponseEntity;

public interface BookingService {
    ResponseEntity<BookingResponseDTO> bookTicket(BookingDTO bookingDTO);
    ResponseEntity<BookingResponseDTO> getBookingDetails(Long bookingId);
    ResponseEntity<String> cancelTicket(Long bookingId);
}
