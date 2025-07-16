package com.SaloonProj.saloonapointment.OtherDto;

import com.SaloonProj.saloonapointment.EnumData.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Data Transfer Objects(DTO)
public class SignupDto {
    private  String firstName;

    private String LastName;

    private String email;

    private String password;

    private  String username;

    private UserRole role;

}
