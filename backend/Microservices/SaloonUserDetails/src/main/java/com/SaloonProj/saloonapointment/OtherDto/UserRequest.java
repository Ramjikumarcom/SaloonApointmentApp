package com.SaloonProj.saloonapointment.OtherDto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Data Transfer Objects(DTO)
public class UserRequest {

    private String username;

    private Boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private List<Credentials>credentials=new ArrayList<>();

}
