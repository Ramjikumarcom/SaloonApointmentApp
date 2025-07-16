package com.saloonproj.notificationservices.client;

import com.saloonproj.notificationservices.OtherDTO.BookingModelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BOOKINGSERVICE")
public interface BookingFeignClient {


    @GetMapping("/api/bookings/bookingId/{bookingId}")
    public ResponseEntity<BookingModelDTO> getBookingsByBookingId(
            @PathVariable Long bookingId
    ) throws Exception;

}
