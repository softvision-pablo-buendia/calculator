package com.pablo.calculator.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping
    public String getCalculator() {
        return "Calculator available";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam String number1, @RequestParam String number2, @RequestParam String expression) {
        return "10";
    }
}
