package com.fpt.service;

import com.fpt.dto.OrderDetailDTO;
import com.fpt.entity.Order;
import com.fpt.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDTO> findByOrder(Order order);

    OrderDetailDTO create(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO findByOrderId(Integer id);

    List<OrderDetail> findAllByOrderId(Integer id);

}
