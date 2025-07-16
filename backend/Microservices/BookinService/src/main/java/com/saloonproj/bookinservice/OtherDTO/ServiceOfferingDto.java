package com.saloonproj.bookinservice.OtherDTO;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ServiceOfferingDto {

    private Long offerId;


    private String name;


    private String description;


    private  int price;


    private  int duration; // in minnute


    private  Long saloonId;


    private  Long CategoryId;

    private  String image;
}
