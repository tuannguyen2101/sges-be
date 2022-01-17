package com.fpt.service.impl;

import com.fpt.dto.OrderDTO;
import com.fpt.entity.Account;
import com.fpt.entity.Order;
import com.fpt.mapper.OrderMapper;
import com.fpt.repo.AccountRepo;
import com.fpt.repo.OrderRepo;
import com.fpt.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<OrderDTO> findAll() {
        return this.orderRepo.findAll().stream()
                .map(entity -> this.orderMapper.cvrToDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Integer id) {
        Optional<Order> entity = this.orderRepo.findById(id);
        if (entity.isPresent()) {
            return this.orderMapper.cvrToDTO(entity.get());
        } else return null;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setCreateDate(new Date());
        System.out.println(orderDTO);
        Order entity = this.orderRepo.save(this.orderMapper.cvrToEntity(orderDTO));
        return this.orderMapper.cvrToDTO(entity);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Optional<Order> entity = this.orderRepo.findById(orderDTO.getId());
        if (entity.isPresent()) {
            return this.orderMapper.cvrToDTO(this.orderRepo.save(this.orderMapper.cvrToEntity(orderDTO)));
        } else return null;
    }

    @Override
    public OrderDTO delete(Integer id) {
        Optional<Order> entity = this.orderRepo.findById(id);
        if (entity.isPresent()) {
            this.orderRepo.delete(entity.get());
            return this.orderMapper.cvrToDTO(entity.get());
        } else return null;
    }

    @Override
    public List<OrderDTO> findByAccount(String username) {
        Optional<Account> account = Optional.ofNullable(this.accountRepo.findByUsername(username));
        if (account.isPresent()) {
            List<Order> list = this.orderRepo.findByAccount(account.get());
            return list.stream()
                    .map(entity -> this.orderMapper.cvrToDTO(entity))
                    .collect(Collectors.toList());
        } else return null;
    }

    @Override
    public Page<Order> findAllByAccountAndStatus(Integer userId, Integer status, Pageable pageable) {
        Optional<Account> account = accountRepo.findById(userId);
        if (account.isPresent()) {
            return orderRepo.findAllByAccountAndStatus(userId, status, pageable);
        }
        return null;
    }

    @Override
    public Page<Order> findAllByAccount(Integer userId, Pageable pageable) {
        Optional<Account> account = accountRepo.findById(userId);
        if (account.isPresent()) {
            return orderRepo.findAllByAccount(userId, pageable);
        }
        return null;
    }

    @Override
    public Page<Order> sGetAll(Pageable pageable) {
        try {
            return orderRepo.sGetAll(pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByStatus(Integer status, Pageable pageable) {
        try {
            return orderRepo.sGetAllByStatus(status, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByPayment(Integer payment, Pageable pageable) {
        try {
            return orderRepo.sGetAllByPayment(payment, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByDate(Date dateFrom, Date dateTo, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Order> sGetAllByName(String name, Pageable pageable) {
        try {
            return orderRepo.sGetAllByName(name, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByStatusAndPayment(Integer status, Integer payment, Pageable pageable) {
        try {
            return orderRepo.sGetAllByStatusAndPayment(status, payment, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByStatusAndDate(Integer status, Date dateFrom, Date dateTo, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Order> sGetAllByStatusAndName(Integer status, String name, Pageable pageable) {
        try {
            return orderRepo.sGetAllByStatusAndName(status, name, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByPaymentAndDate(Integer payment, Date dateFrom, Date dateTo, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Order> sGetAllByPaymentAndName(Integer payment, String name, Pageable pageable) {
        try {
            return orderRepo.sGetAllByPaymentAndName(payment, name, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }

    @Override
    public Page<Order> sGetAllByStatusAndPaymentAndName(Integer status, Integer payment, String name, Pageable pageable) {
        try {
            return orderRepo.sGetAllByStatusAndPaymentAndName(status, payment, name, pageable);
        } catch (Exception e) {
            log.error("error: " + e);
            return null;
        }
    }
}
