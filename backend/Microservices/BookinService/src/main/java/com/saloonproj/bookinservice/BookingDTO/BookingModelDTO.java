package com.saloonproj.bookinservice.BookingDTO;

import com.saloonproj.bookinservice.domain.BookingStatus;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookingModelDTO {

    private Long bookingId;

    private Long saloonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> servicesIds;

    private BookingStatus status=BookingStatus.PENDING;

    private  int totalBookedServices;

    private  int totalPrice;
}
