package com.SaloonProj.categoryservice;

import com.SaloonProj.categoryservice.CategoryModel.CategoryModel;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){return  new ModelMapper();}

    @Bean
    public CategoryModel categoryModel(){return  new CategoryModel();}

}
