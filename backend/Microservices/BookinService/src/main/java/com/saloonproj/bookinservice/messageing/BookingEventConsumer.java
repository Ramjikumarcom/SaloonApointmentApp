package com.saloonproj.bookinservice.messageing;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.BookingService.BookingService;
import com.saloonproj.bookinservice.OtherDTO.PaymentModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventConsumer {
    private final BookingService bookingService;
    @RabbitListener(queues = "booking-queue")
    public BookingModelDTO bookingUpdateListener(PaymentModelDto paymentModelDto) throws Exception {
      return  bookingService.bookingSuccess(paymentModelDto);
    }
}
