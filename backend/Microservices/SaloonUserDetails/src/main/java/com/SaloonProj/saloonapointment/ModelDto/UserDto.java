package com.SaloonProj.saloonapointment.ModelDto;


import com.SaloonProj.saloonapointment.EnumData.UserRole;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Data Transfer Objects(DTO)
public class UserDto {
  private Long userId;

    private String fullName;


    private String userName;

    private String phone;

    private String email;


    private UserRole role;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    private String password;


}
