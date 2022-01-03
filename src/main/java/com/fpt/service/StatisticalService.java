package com.fpt.service;

import java.util.List;

public interface StatisticalService {
    public List<Object> getAllNameAndQuantity();

    public List<Object> getTotalSalaryByYear();

    public List<Object> getTotalSalaryByMonthInYear(Integer year);

    public List<Integer> getAllYears();

    // Thống kê doanh thu trong tuần hiện tại
    public List<Object> getSalesByWeek();

    // Thống kê doanh thu trong ngày hiện tại
    public List<Object> getSalesByDay();

    //Thống kê top 10 sản phẩm bán chạy
    public List<Object> getTopTenProductSold();

    //Thống kê doanh thu tháng hiện tại
    public List<Object> getSalesByCurrentMonth();
}
