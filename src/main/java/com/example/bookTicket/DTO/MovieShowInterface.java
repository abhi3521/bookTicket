package com.example.bookTicket.DTO;

import com.example.bookTicket.enums.Genre;
import com.example.bookTicket.enums.Language;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface MovieShowInterface {
    String getTitle();
    String getShow_start_time();
    String getShow_end_time();
    String getGenre();
    String getShow_language();
    Integer getAvailable_seat();
    Integer getBooked_seat();

}
