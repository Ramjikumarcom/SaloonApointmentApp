package com.saloonproj.paymentservice.OtherDto;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
