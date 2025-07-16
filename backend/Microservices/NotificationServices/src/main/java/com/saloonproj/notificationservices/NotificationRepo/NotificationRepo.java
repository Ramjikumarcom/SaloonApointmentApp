package com.saloonproj.notificationservices.NotificationRepo;

import com.saloonproj.notificationservices.Model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<NotificationModel ,Long> {

    List<NotificationModel> findByUserId(Long userId);
    List<NotificationModel>findBySaloonId(Long salooId);

}
