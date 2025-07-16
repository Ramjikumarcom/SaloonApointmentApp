package com.saloonproj.bookinservice;

import com.saloonproj.bookinservice.BookingModel.BookingModel;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BookinServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookinServiceApplication.class, args);
    }

     @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
     }

     @Bean
    public BookingModel bookingModel() {
        return new BookingModel();
     }

}
