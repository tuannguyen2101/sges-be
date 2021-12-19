package com.fpt.service;

import java.util.List;

public interface StatisticalService {
    public List<Object> getAllNameAndQuantity();

    public List<Object> getTotalSalaryByYear();

    public List<Object> getTotalSalaryByMonthInYear(Integer year);

    public List<Integer> getAllYears();
}
