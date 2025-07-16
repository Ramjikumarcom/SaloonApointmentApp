package com.saloonproj.bookinservice.OtherDTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {
    private String payment_link_url;

    private String payment_link_id;
}
