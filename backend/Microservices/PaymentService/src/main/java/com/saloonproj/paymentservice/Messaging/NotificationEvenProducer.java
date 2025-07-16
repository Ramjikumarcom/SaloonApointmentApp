package com.saloonproj.paymentservice.Messaging;

import com.saloonproj.paymentservice.OtherDto.NotificationModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEvenProducer {

    private  final RabbitTemplate rabbitTemplate;

    public void sentNotification(Long bookingId,Long userId,Long saloonId){

        NotificationModelDTO notificationModelDTO=new NotificationModelDTO();

        notificationModelDTO.setBookingId(bookingId);
        notificationModelDTO.setSaloonId(saloonId);
        notificationModelDTO.setType("BOOKING");
        notificationModelDTO.setDescription("new booking got confirmed");
        notificationModelDTO.setUserId(userId);

        rabbitTemplate.convertAndSend("notification-queue",notificationModelDTO);

    }
}
