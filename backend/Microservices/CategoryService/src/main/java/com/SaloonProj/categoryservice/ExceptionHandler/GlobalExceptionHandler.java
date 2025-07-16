package com.SaloonProj.categoryservice.ExceptionHandler;

import com.SaloonProj.categoryservice.Payload.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> ExceptionHandler(Exception ex, WebRequest req){

        Response Response=new Response(
                ex.getMessage(),
                req.getDescription(false), LocalDateTime.now()
        );
        return ResponseEntity.status(500).body(Response);
    }

}
