package com.pablo.calculator.web.controller;

import com.pablo.calculator.web.model.FactorialDto;
import com.pablo.calculator.web.model.FactorialRunnable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/batch-calculator")
public class BatchFactorialController {

    @GetMapping
    public String getCalculator() {
        return "Batch calculator available";
    }

    @GetMapping("/calculate")
    public List<BigInteger> calculate(@RequestBody FactorialDto factorialDto) throws InterruptedException {

        List<String> list = factorialDto.getNumbers();

        List<Thread> threads = new ArrayList<>();
        List<BigInteger> factorials = new Vector<>();

        for (String number : list) {
            Integer factorial = Integer.parseInt(number);
            Thread newThread = new Thread(new FactorialRunnable(factorial, factorials));
            newThread.join();
            threads.add(newThread);
        }
        threads.stream().forEach(Thread::run);

        return factorials;
    }
}
