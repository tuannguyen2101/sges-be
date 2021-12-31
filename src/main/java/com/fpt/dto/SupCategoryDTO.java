package com.fpt.dto;

import com.fpt.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupCategoryDTO {

    private Integer id;
    private String name;
    private List<Category> categories;

}
