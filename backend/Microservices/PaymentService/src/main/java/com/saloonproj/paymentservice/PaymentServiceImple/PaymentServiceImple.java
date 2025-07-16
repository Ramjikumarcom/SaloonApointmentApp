package com.saloonproj.paymentservice.PaymentServiceImple;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.saloonproj.paymentservice.Domain.PaymentMethod;
import com.saloonproj.paymentservice.Domain.PaymentOrderStatus;
import com.saloonproj.paymentservice.Messaging.BookingEventProducer;
import com.saloonproj.paymentservice.Messaging.NotificationEvenProducer;
import com.saloonproj.paymentservice.Model.PaymentModel;
import com.saloonproj.paymentservice.OtherDto.BookingModelDTO;
import com.saloonproj.paymentservice.OtherDto.UserDto;
import com.saloonproj.paymentservice.PaymentModelDto.PaymentModelDto;
import com.saloonproj.paymentservice.PaymentRepository.PaymentRepository;
import com.saloonproj.paymentservice.PaymentService.PaymentService;
import com.saloonproj.paymentservice.payload.PaymentLinkResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImple implements PaymentService {

    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private final PaymentRepository payMentRepository;

     private final BookingEventProducer bookingEventProducer;

     private final NotificationEvenProducer notificationEvenProducer;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentModel paymentModel;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSeceret;


    @Override
    public PaymentLinkResponse createOrder(UserDto userDto, BookingModelDTO bookingModelDTO,
                                           PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Long amount = (long) bookingModelDTO.getTotalPrice();


        paymentModel.setAmount(amount);
        paymentModel.setPaymentMethod(paymentMethod);
 bookingModelDTO.setStartTime(LocalDateTime.now());
// bookingModelDTO.setEndTime(LocalDateTime.now()+10);
        paymentModel.setBookingId(bookingModelDTO.getBookingId());

        paymentModel.setSaloonId(bookingModelDTO.getSaloonId());
        paymentModel.setUserId(userDto.getUserId());
        PaymentModel savedPaymentOrder = payMentRepository.save(paymentModel);

        // mapping in dto
        PaymentModelDto paymentModelDto = modelMapper.map(savedPaymentOrder, PaymentModelDto.class);


        // creating link response
        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            // create Razorpay paymentLink
            PaymentLink paymentLink = createRazorPaymentLink(userDto,
                    paymentModelDto.getAmount(),
                    paymentModelDto.getPaymentId());
            String paymentUrl = paymentLink.get("short_url");
            String paymentUrlId = paymentLink.get("id");
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setPayment_link_id(paymentUrlId);

            savedPaymentOrder.setPaymentLinkId(paymentUrlId);

            payMentRepository.save(savedPaymentOrder);
        } else {
// create Stripe PaymentLink
            String paymenturl = createStripePaymentLink(userDto,
                    savedPaymentOrder.getAmount(), savedPaymentOrder.getPaymentId());
            paymentLinkResponse.setPayment_link_url(paymenturl);
        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentModelDto getpaymentById(Long paymentId) throws Exception {
        PaymentModel paymentModel1 = payMentRepository.findById(paymentId).orElseThrow(() -> new Exception("Payment not found from this id"));
        PaymentModelDto paymentModelDto = modelMapper.map(paymentModel1, PaymentModelDto.class);
        return paymentModelDto;
    }

    @Override
    public PaymentModelDto getpaymentByOrderPaymentId(String paymentId) {
        PaymentModel paymentModel1 = payMentRepository.findByPaymentLinkId(paymentId);
        return modelMapper.map(paymentModel1, PaymentModelDto.class);
    }

    @Override
    public PaymentLink createRazorPaymentLink(UserDto userDto,
                                              Long Amount,
                                              long orderId) throws RazorpayException {
        Long amount = Amount * 100;
        RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSeceret);

        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount", Amount);

        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", userDto.getFullName());

        customer.put("email", userDto.getEmail());

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("reminder_enable", true);


        // this url is created in frontend
        paymentLinkRequest.put("callback_url", "http://localhost:5173/payment-success/" + orderId);


        paymentLinkRequest.put("callback_method", "get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

        return paymentLink;
    }

    @Override
    public String createStripePaymentLink(UserDto userDto,
                                          Long amount,
                                          long orderId) throws StripeException {

        Stripe.apiKey = stripeApiKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/payment-success/" + orderId)
                .setCancelUrl("http://localhost:5173/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount * 100).
                                setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Saloon Apointment Booking").build()).build()).build()).build();


        Session session = Session.create(params);


        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentModelDto paymentModelDto,
                                  String paymentId,
                                  String paymentLinkId) throws RazorpayException {
        if (paymentModelDto.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING)) {
            if (paymentModelDto.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {

                RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSeceret);

                Payment payment = razorpayClient.payments.fetch(paymentId);

                Integer amount = payment.get("amount");
                String status = payment.get("status");

                if (status.equals("captured")) {
                    // produce Kafka event
             bookingEventProducer.sentBookingUpdateEvent(paymentModelDto);
             notificationEvenProducer.sentNotification(paymentModelDto.getBookingId(),
                     paymentModelDto.getUserId(),paymentModelDto.getSaloonId());

                    paymentModelDto.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);

                    PaymentModel paymentModel1 = modelMapper.map(paymentModelDto, PaymentModel.class);
                    payMentRepository.save(paymentModel1);

                    return true;

                }
                return false;

            } else {

                paymentModelDto.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
                PaymentModel paymentModel1 = modelMapper.map(paymentModelDto, PaymentModel.class);
                payMentRepository.save(paymentModel1);

                return true;

            }
        }

        return false;
    }
}
