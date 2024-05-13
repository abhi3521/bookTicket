package com.example.bookTicket.repository;

import com.example.bookTicket.DTO.BookShowInterface;
import com.example.bookTicket.DTO.MovieShow;
import com.example.bookTicket.DTO.MovieShowInterface;
import com.example.bookTicket.entity.Seat;
import com.example.bookTicket.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "SELECT \n" +
            "    s.show_start_time, \n" +
            "    s.show_end_time, \n" +
            "    COUNT(DISTINCT CASE WHEN t.status = 'AVAILABLE' THEN t.seat_no END) as available_seat, \n" +
            "    COUNT(DISTINCT CASE WHEN t.status = 'BOOKED' THEN t.seat_no END) as booked_seat,\n" +
            "    m.title, \n" +
            "    GROUP_CONCAT(DISTINCT mg.genre) AS genre, \n" +
            "    s.show_language \n" +
            "FROM \n" +
            "    shows s  \n" +
            "JOIN \n" +
            "    movie m ON s.movie_id = m.id  \n" +
            "JOIN \n" +
            "    movie_genre mg ON m.id = mg.movie_id  \n" +
            "JOIN \n" +
            "    seat t on t.show_id = s.id  \n" +
            "WHERE \n" +
            "    STR_TO_DATE(s.show_start_time, '%d/%m/%Y %H:%i:%s') > (:currentDateTime)  \n" +
            "    AND (t.status = 'AVAILABLE' OR t.status = 'BOOKED') \n" +
            "GROUP BY \n" +
            "    s.id",
            nativeQuery = true)
    List<MovieShowInterface> findUpcomingShowsWithMovieDetails(@Param("currentDateTime") LocalDateTime currentDateTime);

    @Query(value = "SELECT s.show_start_time, s.show_end_time, s.available_seat, " +
            "m.title, GROUP_CONCAT(mg.genre) AS genre, s.show_language " +
            "FROM shows s " +
            " JOIN movie m ON s.movie_id = m.id " +
            "JOIN movie_genre mg ON m.id = mg.movie_id " +
            "WHERE STR_TO_DATE(s.show_start_time, '%d/%m/%Y %H:%i:%s') > (:currentDateTime) " +
            "AND m.title = :title " +
            "GROUP BY s.id",
            nativeQuery = true)
    List<MovieShow> findUpcomingShowsByMovieTitle(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("title") String title);

    @Query(value = "SELECT s.show_start_time, s.show_end_time, " +
            "m.title,  s.show_language, h.hall_name " +
            "FROM shows s " +
            " JOIN movie m ON s.movie_id = m.id " +
            "JOIN hall h ON h.id = s.hall_id " +
            "WHERE s.id = :showId ",
            nativeQuery = true)
    Optional<BookShowInterface> findShowWithMovieDetails(@Param("showId") Long showId);



}
