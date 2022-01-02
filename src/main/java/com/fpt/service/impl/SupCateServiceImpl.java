package com.fpt.service.impl;

import com.fpt.dto.SupCategoryDTO;
import com.fpt.entity.SupCategory;
import com.fpt.mapper.SupCateMapper;
import com.fpt.repo.SupCategoryRepo;
import com.fpt.service.SupCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupCateServiceImpl implements SupCateService {

    @Autowired
    SupCategoryRepo supCategoryRepo;

    @Autowired
    SupCateMapper supCateMapper;

    @Override
    public List<SupCategoryDTO> findAll() {
        List<SupCategory> supCategory = supCategoryRepo.findAll();
        return supCategory.stream().map(supCate -> supCateMapper.entityToDTO(supCate)).collect(Collectors.toList());
    }
}
