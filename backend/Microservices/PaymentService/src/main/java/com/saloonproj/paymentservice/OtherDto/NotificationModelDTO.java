package com.saloonproj.paymentservice.OtherDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class NotificationModelDTO {


        private Long notificationId;

        private String type;

        private  Boolean isRead=false;
  private String description;
        private Long userId;

        private Long bookingId;

        private  Long saloonId;

        private LocalDateTime createdAt;
        private BookingModelDTO bookingModelDTO;

}
