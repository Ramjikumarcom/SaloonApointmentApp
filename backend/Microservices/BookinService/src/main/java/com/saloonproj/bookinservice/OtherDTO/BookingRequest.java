package com.saloonproj.bookinservice.OtherDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Long> serviceids;

}
