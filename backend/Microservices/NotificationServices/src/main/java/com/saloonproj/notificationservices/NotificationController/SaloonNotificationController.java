package com.saloonproj.notificationservices.NotificationController;


import com.saloonproj.notificationservices.ModelDTO.NotificationModelDTO;
import com.saloonproj.notificationservices.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications/saloon-owner")
public class SaloonNotificationController {

    private final NotificationService notificationService;
    @GetMapping("/BySaloonId/{saloonId}")
    public ResponseEntity<List<NotificationModelDTO>> getNotificationBySaloonId(
            @PathVariable long saloonId
    ){

        return ResponseEntity.ok(notificationService.getAllNotificationBySaloonId(saloonId));

    }
}
