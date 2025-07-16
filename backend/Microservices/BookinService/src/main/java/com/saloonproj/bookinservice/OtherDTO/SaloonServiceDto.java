package com.saloonproj.bookinservice.OtherDTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SaloonServiceDto {

  private  Long saloonId;

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
