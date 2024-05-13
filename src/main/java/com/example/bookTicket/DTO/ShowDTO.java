package com.example.bookTicket.DTO;

import com.example.bookTicket.enums.Language;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {
    private String showStartTime;
    private String showEndTime;
    private Long movieId;
    private Long hallId;
    private String showLanguage;
}
