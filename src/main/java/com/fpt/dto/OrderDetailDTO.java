package com.fpt.dto;

import com.fpt.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Integer id;

    private Integer orderId;

    private Double price;

    private Integer productId;

    private String size;

    private String color;

    private Integer quantity;

}
