    package com.SaloonProj.saloonapointment;

import com.SaloonProj.saloonapointment.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

    @SpringBootApplication
public class SaloonApointMentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaloonApointMentApplication.class, args);
    }

    @Bean
    public User userBean() {
        return new User();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
