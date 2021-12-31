package com.fpt.service.impl;

import com.fpt.dto.response.CustomerDTO;
import com.fpt.entity.Customer;
import com.fpt.mapper.CustomerMapper;
import com.fpt.repo.CustomerRepo;
import com.fpt.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    final
    CustomerRepo customerRepo;

    final CustomerMapper mapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper mapper) {
        this.customerRepo = customerRepo;
        this.mapper = mapper;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }

    @Override
    public CustomerDTO findById(Integer id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.map(mapper::EntityToDto).orElse(null);
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer customer = customerRepo.save(mapper.DtoToEntity(dto));
        return mapper.EntityToDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getId())) {
            Customer customer = customerRepo.save(mapper.DtoToEntity(dto));
            return mapper.EntityToDto(customer);
        }
        return null;
    }

    @Override
    public CustomerDTO delete(Integer id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.deleteById(id);
            return mapper.EntityToDto(customer.get());
        }
        return null;
    }

    @Override
    public Page<Customer> findByClassId(Integer classId, Integer n, Integer s) {
        Page<Customer> customerPage = customerRepo.findPageByClassId(classId, PageRequest.of(n, s));
        if (!customerPage.isEmpty()) {
            return customerPage;
        }
        return null;
    }
}
