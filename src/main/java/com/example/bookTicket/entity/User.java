package com.example.bookTicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NonNull
    @Column(unique = true)
    private String email;
    private String phoneNo;
}
