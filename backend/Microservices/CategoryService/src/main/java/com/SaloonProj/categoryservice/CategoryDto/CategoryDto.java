package com.SaloonProj.categoryservice.CategoryDto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {

   private  Long categoryId;

    private String name;

    private String image;

    private Long saloonId;
}
