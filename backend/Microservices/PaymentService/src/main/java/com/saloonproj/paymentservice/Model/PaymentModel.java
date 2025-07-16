package com.saloonproj.paymentservice.Model;

import com.saloonproj.paymentservice.Domain.PaymentMethod;
import com.saloonproj.paymentservice.Domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;
//import com.saloonproj.paymentservice.Domain.PaymentStatus;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @Column(nullable =false)
    private Long Amount;

    @Column(nullable = false)
  private PaymentOrderStatus paymentOrderStatus=PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

//    @Column(nullable = false)
    private String paymentLinkId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private  Long bookingId;

    @Column(nullable = false)
    private Long saloonId;

}
