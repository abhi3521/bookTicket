package com.example.bookTicket.repository;

import com.example.bookTicket.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m from Movie m where m.title = :title")
    Optional<Movie> findByTitle(@Param("title") String title);
}
