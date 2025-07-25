package com.saloonproj.bookinservice.OtherDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingSlotDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
