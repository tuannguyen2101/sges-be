package com.fpt.service;

import com.fpt.dto.response.CustomerDTO;
import com.fpt.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    CustomerDTO findById(Integer id);

    CustomerDTO create(CustomerDTO dto);

    CustomerDTO update(CustomerDTO dto);

    CustomerDTO delete(Integer id);

    Page<Customer> findByClassId(Integer classId, Integer n, Integer s);

}
