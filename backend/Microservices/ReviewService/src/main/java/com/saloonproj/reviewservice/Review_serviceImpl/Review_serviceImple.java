package com.saloonproj.reviewservice.Review_serviceImpl;

import com.saloonproj.reviewservice.OtherDTO.ReviewRequest;
import com.saloonproj.reviewservice.OtherDTO.SaloonServiceDto;
import com.saloonproj.reviewservice.OtherDTO.UserDto;
import com.saloonproj.reviewservice.Review_Service.ReviewService;
import com.saloonproj.reviewservice.Review_service_Repo.ReviewRepoSitory;
import com.saloonproj.reviewservice.model.ReviewModel;
import com.saloonproj.reviewservice.modelDTO.ReviewModelDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Review_serviceImple implements ReviewService {

    private final ReviewRepoSitory reviewRepoSitory;

    private final ModelMapper modelMapper;

    @Override
    public ReviewModelDTO createReview(ReviewRequest reviewRequest, UserDto userDto, SaloonServiceDto saloonServiceDto) {

        ReviewModel reviewModel=new ReviewModel();
        reviewModel.setReviewText(reviewRequest.getReviewText());
        reviewModel.setRating(reviewRequest.getRating());
        reviewModel.setCreatedAt(LocalDateTime.now());
        reviewModel.setUserId(userDto.getUserId());
        reviewModel.setSaloonId(saloonServiceDto.getSaloonId());

        reviewRepoSitory.save(reviewModel);
        ReviewModelDTO reviewModelDTO=modelMapper.map(reviewModel,ReviewModelDTO.class);

        return reviewModelDTO;
    }

    @Override
    public List<ReviewModelDTO> getReviewBySaloonId(Long saloonId) {

        List<ReviewModel>reviewModels= reviewRepoSitory.findBySaloonId(saloonId);
        List<ReviewModelDTO>reviewModelDTOS=reviewModels.stream().map(
                reviewModel -> modelMapper.map(reviewModel,ReviewModelDTO.class)
        ).collect(Collectors.toList());
        return reviewModelDTOS;
    }

   public ReviewModel findReviewById(Long reviewId) throws Exception {
       return reviewRepoSitory.findById(reviewId).orElseThrow(()->new Exception("review not found ...."));
    }
    @Override
    public ReviewModelDTO updateReview(ReviewRequest reviewRequest, Long reviewId, Long userId) throws Exception {
        ReviewModel reviewModel=findReviewById(reviewId);
        if(!reviewModel.getUserId().equals(userId)){
            throw  new Exception("you don't have  permition to update this review");
        }
        reviewModel.setReviewText(reviewRequest.getReviewText());
        reviewModel.setRating(reviewRequest.getRating());
        reviewRepoSitory.save(reviewModel);
        return modelMapper.map(reviewModel,ReviewModelDTO.class);
    }

    @Override
    public String deleteReview(Long reviewId, Long userId) throws Exception {
        ReviewModel reviewModel=findReviewById(reviewId);
        if(!reviewModel.getUserId().equals(userId)){
            throw  new Exception("you don't have  permition to delete this review");
        }

        reviewRepoSitory.delete(reviewModel);
        return "review Deleted Succesfully";
    }
}
