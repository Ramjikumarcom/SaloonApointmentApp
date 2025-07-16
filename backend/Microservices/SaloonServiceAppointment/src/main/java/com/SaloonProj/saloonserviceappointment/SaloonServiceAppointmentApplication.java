package com.SaloonProj.saloonserviceappointment;

import com.SaloonProj.saloonserviceappointment.Model.Model;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SaloonServiceAppointmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(SaloonServiceAppointmentApplication.class, args);

    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Model model(){return new Model();}

}
