package com.SaloonProj.saloonserviceappointment.SaloonServiceDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private  Long userId;

    private String fullName;

    private String email;
}
