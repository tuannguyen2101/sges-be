package com.fpt.service;

import com.fpt.dto.OrderDTO;
import com.fpt.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderService{

    public List<OrderDTO> findAll();

    public OrderDTO findById(Integer id);

    public OrderDTO create(OrderDTO orderDTO);

    public OrderDTO update(OrderDTO orderDTO);

    public OrderDTO delete(Integer id);

    public List<OrderDTO> findByAccount(String username);

    Page<Order> findAllByAccountAndStatus(Integer userId, Integer status, Pageable pageable);

    Page<Order> findAllByAccount(Integer userId, Pageable pageable);

    // staff
    Page<Order> sGetAll(Pageable pageable);

    Page<Order> sGetAllByStatus(Integer status, Pageable pageable);
    Page<Order> sGetAllByPayment(Integer payment, Pageable pageable);
    Page<Order> sGetAllByDate(Date dateFrom, Date dateTo, Pageable pageable);
    Page<Order> sGetAllByName(String name, Pageable pageable);
    // status
    Page<Order> sGetAllByStatusAndPayment(Integer status, Integer payment, Pageable pageable);
    Page<Order> sGetAllByStatusAndDate(Integer status, Date dateFrom, Date dateTo, Pageable pageable);
    Page<Order> sGetAllByStatusAndName(Integer status, String name, Pageable pageable);
    // payment
    Page<Order> sGetAllByPaymentAndDate(Integer payment, Date dateFrom, Date dateTo, Pageable pageable);
    Page<Order> sGetAllByPaymentAndName(Integer payment, String name, Pageable pageable);


}
