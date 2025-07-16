package com.SaloonProj.saloonapointment.Payload;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse {

    private  String message;
    private  String error;
    private LocalDateTime timestamp;


}
