package com.SaloonProj.saloonapointment.OtherDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Data Transfer Objects(DTO)
public class Credentials {

    private String type;

    private String value;

    private boolean temporary;

}
