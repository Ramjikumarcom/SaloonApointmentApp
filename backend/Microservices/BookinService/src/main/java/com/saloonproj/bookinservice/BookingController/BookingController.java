package com.saloonproj.bookinservice.BookingController;

import com.saloonproj.bookinservice.BookingDTO.BookingModelDTO;
import com.saloonproj.bookinservice.BookingModel.SaloonReport;
import com.saloonproj.bookinservice.BookingService.BookingService;
import com.saloonproj.bookinservice.BookingService.client.OfferFeignClient;
import com.saloonproj.bookinservice.BookingService.client.PaymentFeignClient;
import com.saloonproj.bookinservice.BookingService.client.SaloonServiceFeignClient;
import com.saloonproj.bookinservice.BookingService.client.UserFeignClient;
import com.saloonproj.bookinservice.OtherDTO.*;
import com.saloonproj.bookinservice.domain.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    public final BookingService bookingService;

    public final SaloonServiceFeignClient saloonServiceFeignClient;

    public final UserFeignClient userFeignClient;

    public final OfferFeignClient offerFeignClient;

    private final PaymentFeignClient paymentFeignClient;

    @PostMapping("/create")
     public ResponseEntity<PaymentLinkResponse>createBooking(
             @RequestParam Long saloonId,
             @RequestParam PaymentMethod paymentMethod,
             @RequestBody BookingRequest bookingRequest,
             @RequestHeader("Authorization")String token
             ) throws Exception {
        UserDto userDto = userFeignClient.getUserByEmail(token).getBody();


        SaloonServiceDto saloonServiceDto = saloonServiceFeignClient.findSaloonById(saloonId).getBody();

        Set<ServiceOfferingDto>serviceOfferingDtoSet = offerFeignClient.
                getServicesByIds(bookingRequest.getServiceids()).getBody();

      BookingModelDTO bookingModelDTO=  bookingService.createBooking(bookingRequest,userDto,
              saloonServiceDto,serviceOfferingDtoSet);
        if(serviceOfferingDtoSet==null)throw  new Exception("no services avaliable");
        if (serviceOfferingDtoSet.isEmpty()){
              throw  new Exception("service not found...");
          }
    PaymentLinkResponse paymentLinkResponse=  paymentFeignClient.createPaymentLink(
         bookingModelDTO,paymentMethod,token).getBody();

          return new  ResponseEntity<>(paymentLinkResponse,HttpStatus.CREATED);
     }
     @GetMapping("/cutomerId")
    public ResponseEntity<Set<BookingModelDTO>>getBookingsByCustomerId(
            @RequestHeader("Authorization") String token
     ) throws Exception {
         UserDto userDto = userFeignClient.getUserByEmail(token).getBody();
         if(userDto==null||userDto.getUserId()==null)throw  new Exception("User Not Found...");
         List<BookingModelDTO> bookingModelDTOS=  bookingService.getBookingsByCustomerId(userDto.getUserId());

      Set<BookingModelDTO> bookingModelDTOSet= new HashSet<>();

      bookingModelDTOSet.addAll(bookingModelDTOS);
      return new ResponseEntity<>(bookingModelDTOSet,HttpStatus.OK);

     }

    @GetMapping("/saloonId")

    public ResponseEntity<Set<BookingModelDTO>>getBookingsBySaloonId(
            @RequestHeader("Authorization")String token
    ) throws Exception {

        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.getSaloonbyOwner(token).getBody();

        if(saloonServiceDto==null||saloonServiceDto.getSaloonId()==null)throw new Exception("Saloon not fount to this Token ...");
        List<BookingModelDTO> bookingModelDTOS=  bookingService.getBookingBySaloonId(saloonServiceDto.getSaloonId());

        Set<BookingModelDTO> bookingModelDTOSet= new HashSet<>();

        bookingModelDTOSet.addAll(bookingModelDTOS);
        return new ResponseEntity<>(bookingModelDTOSet,HttpStatus.OK);

    }



    @GetMapping("/bookingId/{bookingId}")

    public ResponseEntity<BookingModelDTO>getBookingsByBookingId(
            @PathVariable Long bookingId
    ) throws Exception {

        BookingModelDTO bookingModelDTOS=  bookingService.getBookingByBookingId(bookingId);

        return new ResponseEntity<>(bookingModelDTOS,HttpStatus.OK);

    }


    @PutMapping("/updateBookingStatus/{bookingId}/status")
    public ResponseEntity<BookingModelDTO>updateBookingStatus(
            @PathVariable Long bookingId, @RequestParam BookingStatus bookingStatus
            ) throws Exception {

        BookingModelDTO bookingModelDTOS=  bookingService.updateBookingStatus(bookingId, bookingStatus);

        return new ResponseEntity<>(bookingModelDTOS,HttpStatus.OK);

    }

    @GetMapping("/slots/saloon/{saloonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDto> >getBookedSlots(
            @PathVariable Long saloonId,@RequestParam(required = false) LocalDate date
    ) throws Exception {

        List<BookingModelDTO> bookingModelDTOS=  bookingService.getBookingByDate(date,saloonId);

        List<BookingSlotDto>slotsDtos=bookingModelDTOS.stream()
                .map((bookingModelDTO ->
                {
                   BookingSlotDto bookingSlotDto= new BookingSlotDto();
                   bookingSlotDto.setStartTime(bookingModelDTO.getStartTime());
                   bookingSlotDto.setEndTime(bookingModelDTO.getEndTime());
                   return bookingSlotDto;
                })).collect(Collectors.toList());

        return new ResponseEntity<>(slotsDtos,HttpStatus.OK);

    }



    @GetMapping("/report")
    public ResponseEntity<SaloonReport>getBookedSlotsReport(

            @RequestHeader("Authorization")String token
    ) throws Exception {

        SaloonServiceDto saloonServiceDto=saloonServiceFeignClient.getSaloonbyOwner(token).getBody();

        if(saloonServiceDto==null||saloonServiceDto.getSaloonId()==null)throw new Exception("Saloon not fount to this Token ...");
        SaloonReport saloonReport= bookingService.getSaloonReport(saloonServiceDto.getSaloonId());

        return new ResponseEntity<>(saloonReport,HttpStatus.OK);

    }
}
