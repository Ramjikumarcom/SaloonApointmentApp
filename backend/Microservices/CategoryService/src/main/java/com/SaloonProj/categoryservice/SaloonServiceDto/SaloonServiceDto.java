package com.SaloonProj.categoryservice.SaloonServiceDto;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
@Data
public class SaloonServiceDto {
 private Long saloonId;// may be in future problem arises here
    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String state;

    private String city;

    private Long ownerId;

    private LocalTime openTime;

    private LocalTime closeTime;
}
