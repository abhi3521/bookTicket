package com.example.bookTicket.service;

import com.example.bookTicket.entity.Movie;
import com.example.bookTicket.entity.User;
import com.example.bookTicket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;
    @Override
    public ResponseEntity<String> addMovie(Movie movie) {
        movieRepository.save(movie);
        return new ResponseEntity<>(movie.getTitle() + " added successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Movie>> getAllMovie(){
        List<Movie> movieList =  movieRepository.findAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Movie> getMovieById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Movie> getMovieByTitle(String title){
        Optional<Movie> movie = movieRepository.findByTitle(title);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
