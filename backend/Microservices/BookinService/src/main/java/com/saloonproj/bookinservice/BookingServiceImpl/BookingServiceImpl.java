package com.saloonproj.bookinservice.BookingServiceImpl;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.BookingModel.BookingModel;
import com.saloonproj.bookinservice.BookingModel.SaloonReport;
import com.saloonproj.bookinservice.BookingService.BookingService;
import com.saloonproj.bookinservice.OtherDTO.*;
import com.saloonproj.bookinservice.bookingRepository.BookingRepository;
import com.saloonproj.bookinservice.domain.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    @Autowired
    public final BookingRepository bookingRepository;


    @Autowired
    public final ModelMapper modelMapper;


    @Autowired
    public  final BookingModel bookingModel;


    @Override
    public BookingModelDTO createBooking(BookingRequest booking,
                                         UserDto userDto,
                                         SaloonServiceDto saloonServiceDto,
                                         Set<ServiceOfferingDto> serviceOfferingDtoSet) throws Exception {

//        calculating total duration
        int totalDuration=serviceOfferingDtoSet.stream().mapToInt((ServiceOfferingDto)->
                ServiceOfferingDto.getDuration()).sum();


        LocalDateTime bookingStartTime= booking.getStartTime();
        LocalDateTime bookingEndTime= booking.getStartTime().plusMinutes(totalDuration);

        // checking slot is avalable or not
        Boolean isSlotAvailable=isTimeSlotAvailable(saloonServiceDto,bookingStartTime,bookingEndTime);

        int totalPrice=serviceOfferingDtoSet.stream().mapToInt((serviceOfferingDto)->
                serviceOfferingDto.getPrice()).sum(); // getting total price

        // getting totalServices
        List<BookingModel> totalBookedModel=bookingRepository.findByCustomerId(bookingModel.getCustomerId());
        int totalBookedServices=totalBookedModel.size();

        Set<Long>idList=serviceOfferingDtoSet.stream().map(serviceOfferingDto->
                serviceOfferingDto.getOfferId()).collect(Collectors.toSet());

        bookingModel.setCustomerId(userDto.getUserId());
        bookingModel.setSaloonId(saloonServiceDto.getSaloonId());
        bookingModel.setStatus(BookingStatus.PENDING);
        bookingModel.setStartTime(bookingStartTime);
        bookingModel.setEndTime(bookingEndTime);
        bookingModel.setTotalPrice(totalPrice);
        bookingModel.setTotalBookedServices(totalBookedServices);
        bookingModel.setServicesIds(idList);

        BookingModel bookingModelUpdated = bookingRepository.save(bookingModel);


        return modelMapper.map(bookingModelUpdated, BookingModelDTO.class) ;
    }

    public  Boolean isTimeSlotAvailable(SaloonServiceDto saloonServiceDto ,
                                        LocalDateTime bookingStartTime,LocalDateTime bookingEndTime) throws Exception {

        List<BookingModelDTO>existingBookings=getBookingBySaloonId(saloonServiceDto.getSaloonId());


        LocalDateTime saloonOpenTime=saloonServiceDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime saloonCloseTime=saloonServiceDto.getCloseTime().atDate(bookingEndTime.toLocalDate());
// checking is booking is with working hours
        if(bookingStartTime.isBefore(saloonOpenTime)||bookingEndTime.isAfter(saloonCloseTime)){
            throw  new Exception("Booking time must be within working hours");

        }

        for (BookingModelDTO existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime=existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime=existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime)&&
                    bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Given Slot is not Available ,Choose different Slot !!");
            }

            if(bookingStartTime.isEqual(existingBookingStartTime)||
                    bookingEndTime.isEqual(existingBookingEndTime)){
                throw new Exception("Given Slot is not Available ,Choose different Slot !!");
            }

        }
        return true;
    }


    @Override
    public List<BookingModelDTO> getBookingsByCustomerId(Long customerId) {
        List<BookingModel> bookingModelByCustomerId=bookingRepository.findByCustomerId(customerId);

        return bookingModelByCustomerId.stream().map((
                bookingModel)->modelMapper.map(
                bookingModel,BookingModelDTO.class
        )).collect(Collectors.toList());
    }

    @Override
    public List<BookingModelDTO> getBookingBySaloonId(Long saloonId) {
        List <BookingModel> bookingModelBySaloon=bookingRepository.findBySaloonId(saloonId);

        return bookingModelBySaloon.stream().map((
                bookingModel)->modelMapper.map(
                bookingModel,BookingModelDTO.class
        )).collect(Collectors.toList());
    }

    @Override
    public BookingModelDTO getBookingByBookingId(Long bookingId) throws Exception {
      BookingModel  bookingModel=bookingRepository.findById(bookingId).orElseThrow(()->new Exception("booking of this id is not found"));

      return modelMapper.map(bookingModel,BookingModelDTO.class);
    }

    @Override
    public BookingModelDTO updateBookingStatus(Long bookingId, BookingStatus status) throws Exception {
        BookingModel  bookingModel=bookingRepository.findById(bookingId).orElseThrow(()->new Exception("booking of this id is not found"));
        bookingModel.setStatus(status);
        bookingRepository.save(bookingModel);
        return modelMapper.map(bookingModel,BookingModelDTO.class);
    }
    @Override
    public List<BookingModelDTO> getBookingByDate(LocalDate date, Long saloonId) {
         List<BookingModelDTO>AllBoookingsBySaloon=getBookingBySaloonId(saloonId);

         if(date==null)return AllBoookingsBySaloon;

       return AllBoookingsBySaloon.stream().filter((booking)->isSameDate(booking.getStartTime(),date)||
                isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());

    }

    private boolean isSameDate(LocalDateTime startTime, LocalDate date) {

    return  startTime.toLocalDate().isEqual(date);
    }

    @Override
    public SaloonReport getSaloonReport(Long saloonId) {
        List<BookingModelDTO>bookingsBySaloon=getBookingBySaloonId(saloonId);

        Double totalEarnings=bookingsBySaloon.stream().
                mapToDouble((bookingPrice)->bookingPrice.getTotalPrice()).sum();

        Integer totalBookings=bookingsBySaloon.size();

        List<BookingModelDTO>cancelBookings=bookingsBySaloon.stream().filter((bookings)
                ->bookings.getStatus().equals(BookingStatus.CANCELLED)).collect(Collectors.toList());


        Double totalRefund=cancelBookings.stream().mapToDouble((bookingModelDTO ->
                bookingModelDTO.getTotalPrice())).sum();


        SaloonReport saloonReport=new SaloonReport();

        saloonReport.setTotalEarnings(totalEarnings);
        saloonReport.setTotalBookings(totalBookings);
        saloonReport.setTotalRefund(totalRefund);

        saloonReport.setCanceledBookings(cancelBookings.size());
        saloonReport.setSaloonId(saloonId);
       saloonReport.setSaloonName("ROJ bEAUTY PARLOR");

        return saloonReport;
    }

    @Override
    public BookingModelDTO bookingSuccess(PaymentModelDto paymentModelDto) throws Exception {

        BookingModelDTO bookingModelDTO=getBookingByBookingId(paymentModelDto.getBookingId());
        bookingModelDTO.setStatus(BookingStatus.CONFIRMED);

        BookingModel bookingModel1=modelMapper.map(bookingModelDTO,BookingModel.class);
        bookingRepository.save(bookingModel1);
        return bookingModelDTO;
    }
}



