package com.SaloonProj.saloonapointment.UserController;

import com.SaloonProj.saloonapointment.OtherDto.LoginDto;
import com.SaloonProj.saloonapointment.OtherDto.SignupDto;
import com.SaloonProj.saloonapointment.Response.AuthResponse;
import com.SaloonProj.saloonapointment.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody SignupDto req
            ) throws Exception {
        System.out.println(req);

        AuthResponse res=authService.signup(req);

        return  new ResponseEntity<>(res, HttpStatus.CREATED);
    }




    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginDto req
    ) throws Exception {
        AuthResponse res=authService.login(req.getEmail(),req.getPassword());

        return  new ResponseEntity<>(res, HttpStatus.CREATED);
    }



    @GetMapping("/acces-token/refresh-token/{refreshToken}")
    public ResponseEntity<AuthResponse> getAccessToken(
            @PathVariable String  refreshToken
    ) throws Exception {
        AuthResponse res=authService.getAccesTokenFromRefreshToken(refreshToken);

        return  new ResponseEntity<>(res, HttpStatus.CREATED);
    }


}
