package com.pablo.calculator.services;

import com.pablo.calculator.domain.Customer;
import com.pablo.calculator.repositories.CustomerRepository;
import com.pablo.calculator.web.exceptions.CustomerNotFoundException;
import com.pablo.calculator.web.mappers.CustomerMapper;
import com.pablo.calculator.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto getCustomerById(UUID id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customerMapper.customerToCustomerDto(customer.get());
        }
        throw new CustomerNotFoundException();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper
                .customerToCustomerDto(repository.save(customerMapper.customerDtoToCustomer(customerDto)));
    }

    @Override
    public void updateCustomer(UUID id, CustomerDto customerDto) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            CustomerDto oldCustomerDto = customerMapper.customerToCustomerDto(customer.get());
            oldCustomerDto.setName(customerDto.getName());

            repository.save(customerMapper.customerDtoToCustomer(oldCustomerDto));
        }
    }

    @Override
    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }
}
