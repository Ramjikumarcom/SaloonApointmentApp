package com.saloonproj.bookinservice.BookingService.client;

import com.saloonproj.bookinservice.OtherDTO.SaloonServiceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

// instance placed in later when i run eureka server
//?->Eureka server name
@FeignClient("SALOONSERVICEAPPOINTMENT")
public interface SaloonServiceFeignClient {
    @GetMapping("/api/saloonService/owner")
    public ResponseEntity<SaloonServiceDto> getSaloonbyOwner(
            @RequestHeader("Authorization")String token) throws Exception;

    @GetMapping("/api/saloonService/find/{saloonId}")
    public ResponseEntity<SaloonServiceDto> findSaloonById(@PathVariable Long saloonId) throws Exception;
}
