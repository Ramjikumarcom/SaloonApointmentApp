package com.saloonproj.paymentservice.PaymentModelDto;

import com.saloonproj.paymentservice.Domain.PaymentMethod;
import com.saloonproj.paymentservice.Domain.PaymentOrderStatus;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModelDto {

    private Long paymentId;


    private Long Amount;


    private PaymentOrderStatus paymentOrderStatus=PaymentOrderStatus.PENDING;


    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    private Long userId;

    private  Long bookingId;

    private Long saloonId;
}
