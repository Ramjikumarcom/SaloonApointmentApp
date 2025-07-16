package com.saloonproj.paymentservice.PaymentService;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.saloonproj.paymentservice.Domain.PaymentMethod;
import com.saloonproj.paymentservice.Domain.PaymentOrderStatus;
import com.saloonproj.paymentservice.OtherDto.BookingModelDTO;
import com.saloonproj.paymentservice.OtherDto.UserDto;
import com.saloonproj.paymentservice.PaymentModelDto.PaymentModelDto;
import com.saloonproj.paymentservice.payload.PaymentLinkResponse;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
 PaymentLinkResponse createOrder(UserDto userDto, BookingModelDTO bookingModelDTO, PaymentMethod paymentMethod) throws RazorpayException, StripeException;


 PaymentModelDto getpaymentById(Long paymentId) throws Exception;

 PaymentModelDto getpaymentByOrderPaymentId(String paymentId);

 PaymentLink createRazorPaymentLink(
         UserDto userDto,Long amount,long orderId) throws RazorpayException;
 String createStripePaymentLink(
         UserDto userDto,Long amount,long orderId
 ) throws StripeException;

 Boolean proceedPayment(
         PaymentModelDto paymentModelDto,String paymentId,String paymentLinkId
 ) throws RazorpayException;
}
