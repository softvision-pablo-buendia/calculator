package com.pablo.calculator.web.controller;

import com.pablo.calculator.web.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    public final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

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
        if (!(expression.equals("+") || expression.equals("-") || expression.equals("*") || expression.equals("/"))) {
            throw new NotAValidExpressionException();
        }

        double result = calculatorService.calculate(operand1, operand2, expression);

        return Double.toString(result);
    }
}
