package com.SaloonProj.saloonserviceappointment.ExceptionHandler;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
  private   String message;

    private String error;

    private LocalDateTime timestamp;
}
