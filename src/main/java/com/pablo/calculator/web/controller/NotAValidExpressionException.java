package com.pablo.calculator.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "The given parameter is not a valid expression")
public class NotAValidExpressionException extends RuntimeException {

}
