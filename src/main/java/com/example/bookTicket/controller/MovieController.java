package com.example.bookTicket.controller;

import com.example.bookTicket.DTO.MovieDTO;
import com.example.bookTicket.entity.Movie;
import com.example.bookTicket.enums.Genre;
import com.example.bookTicket.enums.Language;
import com.example.bookTicket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true) MovieDTO movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(Arrays.stream(movieDto.getGenre().split(", ")).map(e-> Genre.valueOf(e)).toList());
        movie.setLanguage(Arrays.stream(movieDto.getLanguage().split(", ")).map(e-> Language.valueOf(e)).toList());
        return movieService.addMovie(movie);
    }
}
