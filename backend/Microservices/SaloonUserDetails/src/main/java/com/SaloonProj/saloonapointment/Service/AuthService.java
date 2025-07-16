package com.SaloonProj.saloonapointment.Service;

import com.SaloonProj.saloonapointment.OtherDto.SignupDto;
import com.SaloonProj.saloonapointment.Response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse login(String username,String password) throws Exception;

    AuthResponse signup(SignupDto req) throws Exception;

    AuthResponse getAccesTokenFromRefreshToken(String refreshToken) throws Exception;
}
