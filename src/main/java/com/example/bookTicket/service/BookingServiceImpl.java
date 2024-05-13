package com.example.bookTicket.service;

import com.example.bookTicket.DTO.BookShowInterface;
import com.example.bookTicket.DTO.BookingDTO;
import com.example.bookTicket.DTO.BookingResponseDTO;
import com.example.bookTicket.entity.Booking;
import com.example.bookTicket.entity.Seat;
import com.example.bookTicket.enums.BookingStatus;
import com.example.bookTicket.enums.SeatStatus;
import com.example.bookTicket.repository.BookingRepository;
import com.example.bookTicket.repository.SeatRepository;
import com.example.bookTicket.repository.ShowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ShowRepository showRepository;
    @Autowired
    SeatRepository seatRepository;
    private static final Object lock = new Object();

    @Override
    @Transactional
    @Modifying
    public ResponseEntity<BookingResponseDTO> bookTicket(BookingDTO bookingDTO) {
        try {
            //userId, showId
            //Booking - bookingId
            //Show - startTime, endTime, language, seats, Movie, Hall
            //Movie - title
            //Hall - hallName
            List<Seat> seats = seatRepository.findAvailableSeatsByShowId(bookingDTO.getShowId());
            if (bookingDTO.getNoOfSeatToBook() > seats.size()) {
                throw new RuntimeException("Only " + seats.size() + " seats available");
            }
            boolean seatProcessed = false;
            List<Seat> availableSeats = seats.stream().filter(s -> s.getStatus().equals(SeatStatus.AVAILABLE)).toList();
            List<Seat> bookedSeats = new ArrayList<>();
            synchronized (lock) {
                for (int i = 0; i < bookingDTO.getNoOfSeatToBook(); i++) {
                    availableSeats.get(i).setStatus(SeatStatus.BOOKED);
                    bookedSeats.add(availableSeats.get(i));
                }
                seatRepository.saveAll(bookedSeats);
                seatProcessed = true;
            }

            if (seatProcessed) {

                Booking booking = new Booking();
                booking.setShowId(bookingDTO.getShowId());
                booking.setUserId(bookingDTO.getUserId());
                List<String> seatNumbers = bookedSeats.stream()
                        .map(seat -> String.valueOf(seat.getSeatNo()))
                        .toList();
                String seatNumbersString = String.join(",", seatNumbers);
                booking.setSeatNo(seatNumbersString);
                booking.setBookingStatus(BookingStatus.CONFIRMED);
                bookingRepository.save(booking);
                Long bookingId = booking.getId();
                Optional<BookShowInterface> optionalBookShowInterface = showRepository.findShowWithMovieDetails(bookingDTO.getShowId());
                if (optionalBookShowInterface.isPresent()) {
                    BookShowInterface bookShowInterface = optionalBookShowInterface.get();
                    BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
                    bookingResponseDTO.setBookingId(bookingId);
                    bookingResponseDTO.setSeatNo(booking.getSeatNo());
                    bookingResponseDTO.setStartTime(bookShowInterface.getShow_start_time());
                    bookingResponseDTO.setEndTime(bookShowInterface.getShow_end_time());
                    bookingResponseDTO.setTitle(bookShowInterface.getTitle());
                    bookingResponseDTO.setLanguage(bookShowInterface.getShow_language());
                    bookingResponseDTO.setHallName(bookShowInterface.getHall_name());
                    bookingResponseDTO.setBookingStatus(BookingStatus.CONFIRMED.toString());
                    return ResponseEntity.ok(bookingResponseDTO);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Unable to book tickets");
    }

    @Override
    public ResponseEntity<BookingResponseDTO> getBookingDetails(Long bookingId) {
        Optional<BookShowInterface> optionalBookShowInterface = bookingRepository.findBookingWithShowDetails(bookingId);
        if(optionalBookShowInterface.isPresent()) {
            BookShowInterface bookShowInterface = optionalBookShowInterface.get();
            BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
            bookingResponseDTO.setBookingId(bookingId);
            bookingResponseDTO.setSeatNo(bookShowInterface.getSeat_no());
            bookingResponseDTO.setStartTime(bookShowInterface.getShow_start_time());
            bookingResponseDTO.setEndTime(bookShowInterface.getShow_end_time());
            bookingResponseDTO.setTitle(bookShowInterface.getTitle());
            bookingResponseDTO.setLanguage(bookShowInterface.getShow_language());
            bookingResponseDTO.setHallName(bookShowInterface.getHall_name());
            bookingResponseDTO.setBookingStatus(bookShowInterface.getBooking_status());
            return ResponseEntity.ok(bookingResponseDTO);
        }
        throw new RuntimeException("Booking details not found");
    }

    @Override
    public ResponseEntity<String> cancelTicket(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findByBookingId(bookingId);
        if(bookingOptional.isEmpty()) {
            return new ResponseEntity("Booking not found with booking id " + bookingId, HttpStatus.NOT_FOUND);
        }

        Booking booking = bookingOptional.get();
        if(booking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
            return new ResponseEntity("Booking already cancelled with booking id " + bookingId, HttpStatus.CONFLICT);
        }
        String[] seatNoArray = booking.getSeatNo().split(",");
        List<Integer> seatNo = Arrays.stream(seatNoArray).map(i -> Integer.parseInt(i)).toList();
        List<Seat> seats = seatRepository.findBookedSeatsByShowId(booking.getShowId(), seatNo);
        for(Seat seat : seats) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        seatRepository.saveAll(seats);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return ResponseEntity.ok("Ticket cancelled for booing id " + bookingId + " with seat No : "+ booking.getSeatNo() );
    }
}
