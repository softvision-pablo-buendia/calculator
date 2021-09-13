package com.pablo.calculator.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "The given parameter is not a number")
public class NotAValidNumberException extends RuntimeException {

}
