package com.saloonproj.bookinservice.bookingRepository;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.BookingModel.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingModel,Long> {

    List<BookingModel>findByCustomerId(Long customerId);

    List<BookingModel>findBySaloonId(Long saloonId);

}
