package com.saloonproj.reviewservice.Review_service_Repo;

import com.saloonproj.reviewservice.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepoSitory extends JpaRepository<ReviewModel ,Long> {
    List<ReviewModel>findBySaloonId(Long saloonId);
}
