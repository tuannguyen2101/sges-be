package com.fpt.dto;

import com.fpt.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;
    private String name;
    private Integer status;
    private Integer supCategoryId;
//    private List<Product> products;

}
