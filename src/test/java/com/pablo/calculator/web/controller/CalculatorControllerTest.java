package com.pablo.calculator.web.controller;

import com.pablo.calculator.web.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class CalculatorControllerTest {

    @MockBean
    CalculatorService calculatorService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void prepareTest() {
        when(calculatorService.calculate(5, 5, "+")).thenReturn(10.0);
        when(calculatorService.calculate(15, 8, "-")).thenReturn(7.0);
        when(calculatorService.calculate(5, 5, "*")).thenReturn(25.0);
        when(calculatorService.calculate(15, 5, "/")).thenReturn(3.0);
    }

    @Test
    void getCalculator() throws Exception {
        mockMvc.perform(get("/calculator")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Calculator available"));
    }

    @Test
    void calculateAddition() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "5")
                .param("number2", "5")
                .param("expression", "+")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("10.0"));

    }

    @Test
    void calculateSubtraction() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "15")
                .param("number2", "8")
                .param("expression", "-")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("7.0"));
    }

    @Test
    void calculateMultiplication() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "5")
                .param("number2", "5")
                .param("expression", "*")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("25.0"));

    }

    @Test
    void calculateDivision() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "15")
                .param("number2", "5")
                .param("expression", "/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("3.0"));
    }

    @Test
    void testFailWithWrongExpression() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "15")
                .param("number2", "5")
                .param("expression", "qwe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testFailWithWrongNumber1() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "qwe")
                .param("number2", "5")
                .param("expression", "+")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFailWithWrongNumber2() throws Exception {
        mockMvc.perform(get("/calculator/calculate")
                .param("number1", "15")
                .param("number2", "qwe")
                .param("expression", "+")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}