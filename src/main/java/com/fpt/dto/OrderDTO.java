package com.fpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private Integer accountId;

    private Date createDate;

    private String address;

    private String name;

    private String email;

    private String phone;

    private Double transportFee;

    private Double tienHang;

    private Double tongThanhToan;

    private Integer status;

    private Integer payment;

}
