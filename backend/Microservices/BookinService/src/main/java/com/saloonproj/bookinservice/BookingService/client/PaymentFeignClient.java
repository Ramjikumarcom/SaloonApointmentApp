package com.saloonproj.bookinservice.BookingService.client;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.OtherDTO.PaymentLinkResponse;
import com.saloonproj.bookinservice.OtherDTO.PaymentMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PAYMENTSERVICE")
public interface PaymentFeignClient {

    @PostMapping("/api/payments/createOrder")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingModelDTO bookingModelDTO,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization")String token
    );
}
