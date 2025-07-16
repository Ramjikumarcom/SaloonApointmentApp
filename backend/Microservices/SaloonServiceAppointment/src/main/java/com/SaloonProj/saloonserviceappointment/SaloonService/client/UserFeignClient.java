package com.SaloonProj.saloonserviceappointment.SaloonService.client;

import com.SaloonProj.saloonserviceappointment.SaloonServiceDto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
//SALOONSERVICEAPPOINTMENT
@FeignClient("SALOONAPOINTMENT")
public interface UserFeignClient {
    @GetMapping("/api/users/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws Exception;


    @GetMapping("/api/users/user/profile")
    public ResponseEntity<UserDto> getUserByEmail(@RequestHeader("Authorization") String jwt) throws Exception;

}
