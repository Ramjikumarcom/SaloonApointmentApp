package com.SaloonProj.saloonapointment.ServiceImpl;

import com.SaloonProj.saloonapointment.Model.User;
import com.SaloonProj.saloonapointment.ModelDto.UserDto;
import com.SaloonProj.saloonapointment.OtherDto.SignupDto;
import com.SaloonProj.saloonapointment.Response.AuthResponse;
import com.SaloonProj.saloonapointment.Response.TokenResponse;
import com.SaloonProj.saloonapointment.Service.AuthService;
import com.SaloonProj.saloonapointment.UserRepository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private  final UserRepo userRepo;

    private final  KeyCloakService keyCloakService;

    @Override
    public AuthResponse login(String username, String password) throws Exception {



        TokenResponse tokenResponse=keyCloakService.getLoginToken(
                username,password,"password",null
        );


        AuthResponse authResponse=new AuthResponse();

        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());

        authResponse.setMessage("Login success");
//        authResponse.setRole()
        return authResponse;
    }

    @Override
    public AuthResponse signup(SignupDto req) throws Exception {
        keyCloakService.createuser(req);
        User user=new User();

        user.setEmail(req.getEmail());

        user.setUserName(req.getUsername());
        user.setPassword(req.getPassword());

        user.setRole(req.getRole());

        user.setFullName(req.getFirstName()+req.getLastName());
        user.setCreatedAt(LocalDateTime.now());

        userRepo.save(user);


        TokenResponse tokenResponse=keyCloakService.getLoginToken(
                req.getUsername(),req.getPassword(),"password",null
        );


        AuthResponse authResponse=new AuthResponse();

        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setRole(user.getRole());

        authResponse.setMessage("Register success");

        return authResponse;
    }

    @Override
    public AuthResponse getAccesTokenFromRefreshToken(String refreshToken) throws Exception {


        TokenResponse tokenResponse=keyCloakService.getLoginToken(
               null,null,"refresh_token",refreshToken
        );


        AuthResponse authResponse=new AuthResponse();

        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());

        authResponse.setMessage("Access token recived");
        return authResponse;
    }
}
