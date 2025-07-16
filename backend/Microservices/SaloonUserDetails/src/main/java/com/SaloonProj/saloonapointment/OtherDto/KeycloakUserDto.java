package com.SaloonProj.saloonapointment.OtherDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class KeycloakUserDto {
    private String id;

    private String firstName;

    private  String lastName;

    private  String email;

    private  String username;
}
