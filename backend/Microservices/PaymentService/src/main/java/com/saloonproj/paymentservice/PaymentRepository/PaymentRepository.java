package com.saloonproj.paymentservice.PaymentRepository;

import com.saloonproj.paymentservice.Model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentModel,Long> {

    PaymentModel findByPaymentLinkId(String paymentLinkId);
}
