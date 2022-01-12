package com.fpt.service;

import com.fpt.dto.OrderDTO;
import com.fpt.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
