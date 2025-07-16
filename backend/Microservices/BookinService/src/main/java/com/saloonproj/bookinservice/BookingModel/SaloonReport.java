package com.saloonproj.bookinservice.BookingModel;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class SaloonReport {
    private long saloonId;

    private String saloonName;

    private Double totalEarnings;

    private  Integer totalBookings;

    private  Integer canceledBookings;

    private  Double totalRefund;

}
