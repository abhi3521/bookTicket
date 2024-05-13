package com.example.bookTicket.service;

import com.example.bookTicket.entity.Movie;
import com.example.bookTicket.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    ResponseEntity<String> addMovie(Movie movie);
    ResponseEntity<List<Movie>> getAllMovie();
    ResponseEntity<Movie> getMovieById(Long id);
    ResponseEntity<Movie> getMovieByTitle(String title);
}
