package com.saloonproj.notificationservices.NotificationController;

import com.saloonproj.notificationservices.ModelDTO.NotificationModelDTO;
import com.saloonproj.notificationservices.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationContorller {

    private final NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<NotificationModelDTO>createNotification(
            @RequestBody NotificationModelDTO notificationModelDTO
    ) throws Exception {
        notificationModelDTO.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(notificationService.createNotification(notificationModelDTO));
    }


    @GetMapping("/ByUserId/{userId}")
    public ResponseEntity<List<NotificationModelDTO>>getNotificationByUserId(
            @PathVariable long userId
    ){
        return ResponseEntity.ok(notificationService.getAllNotificationByUserId(userId));
    }

    @PutMapping("/mark/{notificationId}")
    public ResponseEntity<NotificationModelDTO>markNotificationAsARead(
            @PathVariable Long notificationId
    ) throws Exception {
        return ResponseEntity.ok(notificationService.markNotificationAsAread(notificationId));
    }




}
