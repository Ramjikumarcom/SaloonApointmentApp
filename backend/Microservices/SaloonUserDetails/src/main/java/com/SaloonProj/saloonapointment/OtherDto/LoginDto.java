package com.SaloonProj.saloonapointment.OtherDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Data Transfer Objects(DTO)
public class LoginDto {
    private String email;

    private String password;
}
