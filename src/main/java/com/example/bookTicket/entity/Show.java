package com.example.bookTicket.entity;

import com.example.bookTicket.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String showStartTime;
    private String showEndTime;
    private Long movieId;
    private Long hallId;
    @Enumerated(EnumType.STRING)
    private Language showLanguage;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();

}
