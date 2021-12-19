package com.fpt.service.impl;

import com.fpt.repo.ProductRepo;
import com.fpt.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    ProductRepo productRepo;
    @Override
    public List<Object> getAllNameAndQuantity() {
        return productRepo.getAllNameAndQuantity();
    }

    @Override
    public List<Object> getTotalSalaryByYear() {
        return productRepo.getTotalSalaryByYear();
    }

    @Override
    public List<Object> getTotalSalaryByMonthInYear(Integer year) {
        return productRepo.getTotalSalaryByMonth(year);
    }

    @Override
    public List<Integer> getAllYears() {
        return productRepo.getAllYears();
    }
}
