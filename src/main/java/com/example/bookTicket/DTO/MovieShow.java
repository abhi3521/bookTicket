package com.example.bookTicket.DTO;

import com.example.bookTicket.enums.Genre;
import com.example.bookTicket.enums.Language;
import lombok.Data;

import java.util.List;

@Data
public class MovieShow {
    private String title;
    private String showStartTime;
    private String showEndTime;
    private List<Genre> genre;
    private String language;
    private int availableSeats;
}
