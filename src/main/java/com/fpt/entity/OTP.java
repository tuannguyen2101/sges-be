package com.fpt.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Random;


@Entity
@Data
public class OTP {
    private final static Integer EXPIRED_TIME = 5 * 60 * 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Long issueAt;
    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account account;

    public OTP(Account account) {
        this.account = account;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(String.valueOf(random.nextInt(10)));
        } while (sb.length() < 5);
        code = sb.toString();
        issueAt = System.currentTimeMillis();
    }

    public OTP() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(String.valueOf(random.nextInt(10)));
        } while (sb.length() < 5);
        this.code = sb.toString();
        this.issueAt = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return this.issueAt + EXPIRED_TIME < System.currentTimeMillis();
    }
}



