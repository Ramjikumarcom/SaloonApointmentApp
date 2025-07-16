package com.saloonproj.reviewservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long reviewId;
    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private Long saloonId;

    @Column(nullable = false)
    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;


}
