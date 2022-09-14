package com.example.EjercicioDocker2.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class NotFoundException extends RuntimeException{

    int httpCode;

    Date timeStamp;

    public NotFoundException(String message, int httpCode){
        super(message);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}