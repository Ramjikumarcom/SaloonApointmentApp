package com.SaloonProj.saloonapointment.ExceptionHandlePackage;

import com.SaloonProj.saloonapointment.Payload.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*
@ControllerAdvice: Global handler for all controller exceptions.

@ExceptionHandler: Declares methods that handle specific exceptions.

WebRequest: Provides request metadata like URL, parameters, etc.

ResponseEntity: Represents the HTTP response including status and body.

ExceptionResponse: A custom POJO (likely with fields: message, details, timestamp).
 */



//This tells Spring that this class globally handles exceptions thrown from any controller in your app.
@ControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse>ExceptionHandler(Exception ex, WebRequest req){

        ExceptionResponse exceptionResponse=new ExceptionResponse(
                ex.getMessage(),

                req.getDescription(false),LocalDateTime.now()
        );
        return  ResponseEntity.status(500).body(exceptionResponse);

    }
}
