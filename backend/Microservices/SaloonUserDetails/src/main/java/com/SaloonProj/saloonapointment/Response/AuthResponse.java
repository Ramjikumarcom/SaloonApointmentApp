package com.SaloonProj.saloonapointment.Response;

import com.SaloonProj.saloonapointment.EnumData.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AuthResponse {
    private String jwt;

    private String refresh_token;

    private String  message;

    private String title;

    private UserRole role;
}
