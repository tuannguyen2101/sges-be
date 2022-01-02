package com.fpt.mapper;

import com.fpt.dto.SupCategoryDTO;
import com.fpt.entity.SupCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupCateMapper {

    @Autowired
    ModelMapper mapper;

    public SupCategoryDTO entityToDTO(SupCategory supCategory) {
        SupCategoryDTO supCategoryDTO = new SupCategoryDTO();
        mapper.map(supCategory, supCategoryDTO);
        return supCategoryDTO;
    }

    public SupCategory dtoToEntity(SupCategoryDTO supCategoryDTO) {
        SupCategory supCategory = new SupCategory();
        mapper.map(supCategoryDTO, supCategory);
        return supCategory;
    }
}
