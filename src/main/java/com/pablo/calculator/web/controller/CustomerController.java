package com.pablo.calculator.web.controller;

import com.pablo.calculator.services.CustomerService;
import com.pablo.calculator.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable UUID customerId) {
        return service.getCustomerById(customerId);
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        return service.saveCustomer(customerDto);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        service.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId) {
        service.deleteCustomer(customerId);
    }
}
