package com.saloonproj.reviewservice.ReviewController;

import com.saloonproj.reviewservice.OtherDTO.ReviewRequest;
import com.saloonproj.reviewservice.OtherDTO.SaloonServiceDto;
import com.saloonproj.reviewservice.OtherDTO.UserDto;
import com.saloonproj.reviewservice.Review_Service.ReviewService;
import com.saloonproj.reviewservice.client.SaloonServiceFeignClient;
import com.saloonproj.reviewservice.client.UserFeignClient;

import com.saloonproj.reviewservice.modelDTO.ReviewModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    private final UserFeignClient userFeignClient;

    private final SaloonServiceFeignClient saloonServiceFeignClient;

    @PostMapping("/saloon/{saloonId}")
    public ResponseEntity<ReviewModelDTO>createReview(
            @RequestHeader("Authorization")String token,
            @PathVariable Long saloonId,
            @RequestBody ReviewRequest reviewRequest
            ) throws Exception {

        UserDto userDto=userFeignClient.getUserByEmail(token).getBody();
        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.findSaloonById(saloonId).getBody();

       ReviewModelDTO reviewModelDTO= reviewService.createReview(reviewRequest,userDto,saloonServiceDto);
        return new ResponseEntity<>(reviewModelDTO, HttpStatus.CREATED);
    }

    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<List<ReviewModelDTO>>getReviewBySaloon(
            @RequestHeader("Authorization")String token,
            @PathVariable Long saloonId
    ) throws Exception {

        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.findSaloonById(saloonId).getBody();

        assert saloonServiceDto != null;
        List<ReviewModelDTO> reviewModelDTOs= reviewService.getReviewBySaloonId(saloonServiceDto.getSaloonId());
        return new ResponseEntity<>(reviewModelDTOs, HttpStatus.OK);
    }


    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ReviewModelDTO>updateReviewByUser(
            @RequestHeader("Authorization")String token,
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest reviewRequest
    ) throws Exception {

        UserDto userDto=userFeignClient.getUserByEmail(token).getBody();
        if (userDto==null||userDto.getUserId()==null)throw  new Exception("you don't have permition to delete");
       ReviewModelDTO reviewModelDTOS= reviewService.updateReview(reviewRequest,reviewId,userDto.getUserId());
        return new ResponseEntity<>(reviewModelDTOS, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String>deleteReview(
            @RequestHeader("Authorization")String token,
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest reviewRequest
    ) throws Exception {

        UserDto userDto=userFeignClient.getUserByEmail(token).getBody();
        if (userDto==null||userDto.getUserId()==null)throw  new Exception("you don't have permition to delete");

        String status=reviewService.deleteReview(reviewId,userDto.getUserId());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
