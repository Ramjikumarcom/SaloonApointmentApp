package com.SaloonProj.categoryservice.Payload;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response {

    private String message;

    private String path;

    private LocalDateTime localDateTime;
}
