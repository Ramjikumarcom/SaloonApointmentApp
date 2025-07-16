package com.saloonproj.bookinservice.BookingService;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.BookingModel.SaloonReport;
import com.saloonproj.bookinservice.OtherDTO.*;
import com.saloonproj.bookinservice.domain.BookingStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface BookingService {

    BookingModelDTO createBooking(BookingRequest booking, UserDto userDto,
                                  SaloonServiceDto saloonServiceDto,
                                  Set<ServiceOfferingDto> serviceOfferingDtoSet) throws Exception;


    List<BookingModelDTO>getBookingsByCustomerId(Long customerId);

    List<BookingModelDTO>getBookingBySaloonId(Long saloonId);

    BookingModelDTO getBookingByBookingId(Long bookingId) throws Exception;

    BookingModelDTO updateBookingStatus(Long bookingId, BookingStatus status) throws Exception;

    List<BookingModelDTO>getBookingByDate(LocalDate date,Long saloonId);

    SaloonReport getSaloonReport(Long saloonId);

    BookingModelDTO bookingSuccess(PaymentModelDto paymentModelDto) throws Exception;


}
