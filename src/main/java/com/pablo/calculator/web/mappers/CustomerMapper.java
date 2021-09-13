package com.pablo.calculator.web.mappers;

import com.pablo.calculator.domain.Customer;
import com.pablo.calculator.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
