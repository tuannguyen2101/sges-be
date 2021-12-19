package com.fpt.service;

import com.fpt.dto.OrderDetailDTO;
import com.fpt.entity.Order;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDTO> findByOrder(Order order);

    OrderDetailDTO create(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO findByOrderId(Integer id);

}
