package com.example.bookTicket.entity;

import com.example.bookTicket.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Integer seatNo;
    @Enumerated(EnumType.STRING)
    private SeatStatus status = SeatStatus.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
}
