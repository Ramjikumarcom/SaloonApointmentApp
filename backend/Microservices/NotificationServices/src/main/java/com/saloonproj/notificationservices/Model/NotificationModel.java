package com.saloonproj.notificationservices.Model;

import com.saloonproj.notificationservices.OtherDTO.BookingModelDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  notificationId;

    private String type;
    private  String description;
    private  Boolean isRead=false;

    private Long userId;

    private Long bookingId;

    private  Long saloonId;

    private LocalDateTime createdAt;

}
