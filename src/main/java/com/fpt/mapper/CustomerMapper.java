package com.fpt.mapper;

import com.fpt.dto.response.CustomerDTO;
import com.fpt.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    @Autowired
    ModelMapper mapper;

    public Customer DtoToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        mapper.map(dto, customer);
        return customer;
    }

    public CustomerDTO EntityToDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        mapper.map(customer, dto);
        return dto;
    }
}
