package com.saloonproj.notificationservices.messaging;

import com.saloonproj.notificationservices.Model.NotificationModel;
import com.saloonproj.notificationservices.ModelDTO.NotificationModelDTO;
import com.saloonproj.notificationservices.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "notification-queue")

    public void setNotificationService(NotificationModelDTO notificationModel) throws Exception {
        notificationService.createNotification(notificationModel);
    }
}
