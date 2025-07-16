package com.saloonproj.bookinservice.BookingModel;

import com.saloonproj.bookinservice.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class BookingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    private Long saloonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ElementCollection
    private Set<Long> servicesIds;

    private BookingStatus status=BookingStatus.PENDING;

    private  int totalBookedServices;

    private  int totalPrice;


}
