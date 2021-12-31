package com.fpt.repo;

import com.fpt.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Page<Customer> findPageByClassId(Integer id, Pageable pageable);
    @Query(value = "select code from Customer order by code desc limit 1", nativeQuery = true)
    String findLastCode();

    @Query(value = "select id from Customer order by id desc limit 1", nativeQuery = true)
    Integer findLastId();

}
