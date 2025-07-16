package com.saloonproj.reviewservice.Review_Service;

import com.saloonproj.reviewservice.OtherDTO.ReviewRequest;
import com.saloonproj.reviewservice.OtherDTO.SaloonServiceDto;
import com.saloonproj.reviewservice.OtherDTO.UserDto;
import com.saloonproj.reviewservice.modelDTO.ReviewModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ReviewService {

    ReviewModelDTO createReview(
            ReviewRequest reviewRequest,
            UserDto userDto,
            SaloonServiceDto saloonServiceDto
    );

    List<ReviewModelDTO>getReviewBySaloonId(Long saloonId);

    ReviewModelDTO updateReview(
            ReviewRequest reviewRequest,
            Long reviewId,
            Long userId
    ) throws Exception;

    String deleteReview(Long reviewId,Long userId) throws Exception;
}
