package com.SaloonProj.categoryservice.CategoryModel;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    private String image;

    @Column(nullable = false)
    private Long saloonId;


}
