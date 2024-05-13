package com.example.bookTicket.repository;

import com.example.bookTicket.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT s from Seat s where s.show.id = :showId AND s.status = 'AVAILABLE' ORDER BY s.seatNo ASC")
    List<Seat> findAvailableSeatsByShowId(@Param("showId") Long showId);

    @Query(value = "SELECT s from Seat s where s.show.id = :showId AND s.seatNo IN (:seatNo) ORDER BY s.seatNo ASC")
    List<Seat> findBookedSeatsByShowId(@Param("showId") Long showId, @Param("seatNo") List<Integer> seatNo);
}
