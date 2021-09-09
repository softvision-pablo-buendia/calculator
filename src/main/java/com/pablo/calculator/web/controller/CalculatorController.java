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
        double operand1 = 0;
        double operand2 = 0;
        try {
            operand1 = Double.parseDouble(number1);
            operand2 = Double.parseDouble(number2);
        } catch (NumberFormatException e) {
            throw new NotAValidNumberException();
        }

        double result = getResult(expression, operand1, operand2);

        return Double.toString(result);
    }

    private double getResult(String expression, double operand1, double operand2) {
        switch (expression) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
        }
        throw new NotAValidExpressionException();
    }
}
