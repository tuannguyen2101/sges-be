package com.fpt.service;

import com.fpt.dto.CategoryDTO;
import com.fpt.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Page<Category> findAllByPage(Integer number, Integer size);

    List<CategoryDTO> findAll();

    CategoryDTO findById(Integer id);

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(CategoryDTO dto);

    CategoryDTO delete(Integer id);

    boolean isExist(CategoryDTO dto);
}
