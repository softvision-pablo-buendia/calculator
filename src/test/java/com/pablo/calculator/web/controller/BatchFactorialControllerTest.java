package com.pablo.calculator.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.calculator.web.model.FactorialDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BatchFactorialController.class)
class BatchFactorialControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    FactorialDto factorialDto;

    List<Integer> results;

    @BeforeEach
    public void setUp() {
        factorialDto = FactorialDto
                .builder()
                .numbers(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5")))
                .build();

        results = new ArrayList<>(Arrays.asList(1, 2, 6, 24, 120));
    }

    @Test
    void testCalculator() throws Exception {
        mockMvc.perform(get("/batch-calculator")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Batch calculator available"));
    }

    @Test
    void testCalculation() throws Exception {
        String factorialDtoJson = objectMapper.writeValueAsString(factorialDto);
        mockMvc.perform(get("/batch-calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(factorialDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(results)));

    }
}