package com.example.EjercicioDocker2.exceptions;


import lombok.Data;

import java.util.Date;

@Data
public class CustomError {

    int httpCode;
    String message;
    Date timeStamp;

    public CustomError(String message, int httpCode){
        setMessage(message);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}
