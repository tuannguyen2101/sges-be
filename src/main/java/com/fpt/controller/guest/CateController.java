package com.fpt.controller.guest;

import com.fpt.dto.CategoryDTO;
import com.fpt.entity.Category;
import com.fpt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/guest/category")
public class CateController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> catetegories = this.categoryService.findAll();
        if (catetegories.isEmpty()) {
            return new ResponseEntity<List<CategoryDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CategoryDTO>>(catetegories, HttpStatus.OK);
    }

    @GetMapping("/supcate/{id}")
    public ResponseEntity<List<Category>> findBySupCategoryId(@PathVariable("id") Integer id) {
        try {
            List<Category> categories = categoryService.findBySupCategoryId(id);
            return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
