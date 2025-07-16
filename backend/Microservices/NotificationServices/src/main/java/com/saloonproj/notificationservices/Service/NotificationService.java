package com.saloonproj.notificationservices.Service;

import com.saloonproj.notificationservices.ModelDTO.NotificationModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    NotificationModelDTO createNotification(NotificationModelDTO notificationModelDTO) throws Exception;
    List<NotificationModelDTO>getAllNotificationByUserId(Long userId);
    List<NotificationModelDTO>getAllNotificationBySaloonId(Long SaloonId);
    NotificationModelDTO markNotificationAsAread(Long notificationId) throws Exception;
}
