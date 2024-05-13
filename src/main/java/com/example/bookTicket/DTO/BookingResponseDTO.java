package com.example.bookTicket.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private Long bookingId;
    private String startTime;
    private String endTime;
    private String title;
    private String language;
    private String hallName;
    private String seatNo;
    private String bookingStatus;

}
