package com.fpt.repo;

import com.fpt.entity.Account;
import com.fpt.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT order FROM Order order WHERE order.account =:account and status != 3")
    public List<Order> findByAccount(@Param("account") Account account);

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId and status = :status", nativeQuery = true)
    Page<Order> findAllByAccountAndStatus(Integer userId, Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
    Page<Order> findAllByAccount(Integer userId, Pageable pageable);

    // staff

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    Page<Order> sGetAll(Pageable Pageable);

    @Query(value = "SELECT * FROM orders WHERE status = :status", nativeQuery = true)
    Page<Order> sGetAllByStatus(@Param("status") Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE payment = :payment", nativeQuery = true)
    Page<Order> sGetAllByPayment(@Param("payment") Integer payment, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE name like %:name%", nativeQuery = true)
    Page<Order> sGetAllByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE status = :status and payment = :payment", nativeQuery = true)
    Page<Order> sGetAllByStatusAndPayment(@Param("status") Integer status, @Param("payment") Integer payment, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE status = :status and name like %:name%", nativeQuery = true)
    Page<Order> sGetAllByStatusAndName(@Param("status") Integer status, @Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE payment = :payment and name like %:name%", nativeQuery = true)
    Page<Order> sGetAllByPaymentAndName(@Param("payment") Integer payment, @Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE status = :status and payment = :payment and name like %:name%", nativeQuery = true)
    Page<Order> sGetAllByStatusAndPaymentAndName(@Param("status") Integer status, @Param("payment") Integer payment, @Param("name") String name, Pageable pageable);

}
