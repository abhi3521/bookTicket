package com.example.bookTicket.DTO;

import lombok.*;

import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    @NonNull
    private Long userId;
    @NonNull
    private Long showId;
    @NonNull
    @Positive
    private Integer noOfSeatToBook;
}
