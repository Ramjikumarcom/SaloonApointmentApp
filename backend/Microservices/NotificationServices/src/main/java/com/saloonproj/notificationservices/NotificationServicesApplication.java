package com.saloonproj.notificationservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class NotificationServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServicesApplication.class, args);
    }

     @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
     }
}
