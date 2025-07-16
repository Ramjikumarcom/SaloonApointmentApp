package com.saloonproj.paymentservice.payload;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {
//    private Long paymentId;
    private String payment_link_url;

    private String payment_link_id;
}
