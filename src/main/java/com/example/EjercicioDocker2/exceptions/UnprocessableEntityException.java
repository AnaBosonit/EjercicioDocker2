package com.example.EjercicioDocker2.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Data
public class UnprocessableEntityException extends RuntimeException{
    int httpCode;
    Date timeStamp;

    public UnprocessableEntityException(String message, int httpCode){
        super(message);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}