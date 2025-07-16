package com.saloonproj.reviewservice.OtherDTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
  private  Long userId;
    private String fullName;


    private String userName;

    private String phone;

    private String email;

    private String role;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    private String password;


}
