package com.pablo.calculator.web.model;

import java.math.BigInteger;
import java.util.List;

public class FactorialRunnable implements Runnable {

    private final Integer factorial;
    private List<BigInteger> factorials;

    public FactorialRunnable(Integer factorial, List<BigInteger> factorials) {
        this.factorial = factorial;
        this.factorials = factorials;
    }

    @Override
    public void run() {
        factorials.add(getFactorial());
    }

    private BigInteger getFactorial() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= factorial; i++){
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        return result;
    }
}
