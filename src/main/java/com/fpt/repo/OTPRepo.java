package com.fpt.repo;

import com.fpt.entity.Account;
import com.fpt.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OTPRepo extends JpaRepository<OTP, Integer> {
    @Query("FROM OTP o WHERE o.account = :account")
    Optional<OTP> findByUser(@Param("account") Account account);
}
