package com.pablo.calculator.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "The customer with the given id was not found")
public class CustomerNotFoundException extends RuntimeException {
}
