package com.saloonproj.reviewservice.OtherDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewRequest {
    private String reviewText;
    private double rating;
}
