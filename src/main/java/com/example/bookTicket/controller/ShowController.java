package com.example.bookTicket.controller;

import com.example.bookTicket.DTO.MovieShow;
import com.example.bookTicket.DTO.MovieShowInterface;
import com.example.bookTicket.DTO.ShowDTO;
import com.example.bookTicket.entity.Show;
import com.example.bookTicket.enums.Language;
import com.example.bookTicket.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody ShowDTO showDTO) {
        Show show = new Show();
        show.setShowStartTime(showDTO.getShowStartTime());
        show.setShowEndTime(showDTO.getShowEndTime());
        show.setHallId(showDTO.getHallId());
        show.setMovieId(showDTO.getMovieId());
        show.setShowLanguage(Language.valueOf(showDTO.getShowLanguage().toUpperCase()));
        return showService.addShow(show);
    }
    @GetMapping("/allShow")
    ResponseEntity<List<MovieShowInterface>> getMoviesWithShowTime() {
        return showService.getMoviesWithShowTime();
    }
}


