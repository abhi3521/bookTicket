package com.example.bookTicket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HallDTO {
    private String hallName;
    private Integer noOfSeats;
}
