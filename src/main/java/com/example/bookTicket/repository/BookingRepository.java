package com.example.bookTicket.repository;

import com.example.bookTicket.DTO.BookShowInterface;
import com.example.bookTicket.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT b.id, b.booking_status, b.seat_no, s.show_start_time, s.show_end_time, " +
            "m.title,  s.show_language, h.hall_name " +
            "FROM booking b " +
            "JOIN shows s on b.show_id = s.id " +
            " JOIN movie m ON s.movie_id = m.id " +
            "JOIN hall h ON h.id = s.hall_id " +
            "WHERE b.id = :bookingId ",
            nativeQuery = true)
    Optional<BookShowInterface> findBookingWithShowDetails(@Param("bookingId") Long bookingId);
    @Query(value = "SELECT b from Booking b where b.id = :bookingId")
    Optional<Booking> findByBookingId(@Param("bookingId") Long bookingId);
}
