package com.saloonproj.paymentservice.Messaging;

import com.saloonproj.paymentservice.Domain.PaymentOrderStatus;
import com.saloonproj.paymentservice.PaymentModelDto.PaymentModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public  void sentBookingUpdateEvent(PaymentModelDto paymentModelDto){
        rabbitTemplate.convertAndSend("booking-queue",paymentModelDto);
    }
}
