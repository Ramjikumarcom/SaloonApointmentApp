package com.saloonproj.paymentservice.PaymentService.client;

import com.saloonproj.paymentservice.OtherDto.ServiceOfferingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient("SERVICEOFFERING")
public interface OfferFeignClient {
    @GetMapping("/api/service-offering/getallByIds")
    public ResponseEntity<Set<ServiceOfferingDto>> getServicesByIds(@RequestParam Set<Long> serviceIds);
}
