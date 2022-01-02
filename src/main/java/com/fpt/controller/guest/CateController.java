package com.fpt.controller.guest;

import com.fpt.dto.CategoryDTO;
import com.fpt.entity.Category;
import com.fpt.mapper.CategoryMapper;
import com.fpt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/guest/category")
public class CateController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper mapper;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = this.categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/supcate/{id}")
    public ResponseEntity<List<CategoryDTO>> findBySupCategoryId(@PathVariable("id") Integer id) {
        try {
            List<Category> categories = categoryService.findBySupCategoryId(id);
            List<CategoryDTO> categoryDTOS = categories.stream().map(category -> mapper.cvToDTO(category)).collect(Collectors.toList());
            return new ResponseEntity<List<CategoryDTO>>(categoryDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
