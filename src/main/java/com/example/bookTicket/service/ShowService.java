package com.example.bookTicket.service;

import com.example.bookTicket.DTO.MovieShow;
import com.example.bookTicket.DTO.MovieShowInterface;
import com.example.bookTicket.entity.Show;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ShowService {
    public ResponseEntity<String> addShow(Show show);
    ResponseEntity<List<MovieShowInterface>> getMoviesWithShowTime();
    ResponseEntity<List<MovieShow>> getShowByMovieTitle(String title);
}
