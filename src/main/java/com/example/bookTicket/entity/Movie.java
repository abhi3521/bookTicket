package com.example.bookTicket.entity;

import com.example.bookTicket.enums.Genre;
import com.example.bookTicket.enums.Language;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Genre> genre = new ArrayList<>();

    @ElementCollection(targetClass = Language.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private List<Language> language = new ArrayList<>();

}
