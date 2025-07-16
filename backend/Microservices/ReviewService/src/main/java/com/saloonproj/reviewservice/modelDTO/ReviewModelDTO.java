package com.saloonproj.reviewservice.modelDTO;


import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewModelDTO {

    private Long reviewId;

    private String reviewText;

    private double rating;

    private Long saloonId;

    private Long userId;

    private LocalDateTime createdAt;
}
