package com.saloonproj.paymentservice.PaymentController;

import com.razorpay.RazorpayException;
import com.saloonproj.paymentservice.Domain.PaymentMethod;
import com.saloonproj.paymentservice.OtherDto.BookingModelDTO;
import com.saloonproj.paymentservice.OtherDto.UserDto;
import com.saloonproj.paymentservice.PaymentModelDto.PaymentModelDto;
import com.saloonproj.paymentservice.PaymentService.PaymentService;
import com.saloonproj.paymentservice.PaymentService.client.UserFeignClient;
import com.saloonproj.paymentservice.payload.PaymentLinkResponse;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentContoller {

    @Autowired
    public  final PaymentService paymentService;
public  final UserFeignClient userFeignClient;
    @PostMapping("/createOrder")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingModelDTO bookingModelDTO,
            @RequestParam PaymentMethod paymentMethod
            ,@RequestHeader("Authorization")String token
            ) throws Exception {

        UserDto userDto=userFeignClient.getUserByEmail(token).getBody();

        PaymentLinkResponse paymentLinkResponse=paymentService.createOrder(
                userDto,bookingModelDTO,paymentMethod
        );
        return new ResponseEntity<>(paymentLinkResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentOrderId}")
    public  ResponseEntity<PaymentModelDto>getPaymentByOrderId(
            @PathVariable long paymentOrderId
    ) throws Exception {
         PaymentModelDto paymentModelDto=paymentService.getpaymentById(paymentOrderId);
         return  new ResponseEntity<>(paymentModelDto,HttpStatus.OK);
    }

    @GetMapping("/paymentOrderId")

    public ResponseEntity<PaymentModelDto>getpaymentByGeneratedId(
            @RequestParam String generatedPaymentId
    ){
        return new ResponseEntity<>( paymentService.getpaymentByOrderPaymentId(generatedPaymentId),HttpStatus.OK);
    }



    @PatchMapping("/proceedPayment")
    public  ResponseEntity<Boolean>proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId

    ) throws Exception {
        PaymentModelDto paymentModelDto=paymentService.getpaymentByOrderPaymentId(paymentId);
        Boolean PaymentStatus=paymentService.proceedPayment(paymentModelDto,paymentId,paymentLinkId);
        return  new ResponseEntity<>(PaymentStatus,HttpStatus.OK);
    }







}
