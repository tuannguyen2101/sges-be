package com.fpt.controller.staff;

import com.fpt.dto.CategoryDTO;
import com.fpt.entity.Category;
import com.fpt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/staff/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Page<Category>> findAll(@RequestParam("n") Optional<Integer> n, @RequestParam("s") Optional<Integer> s) {
        Page<Category> catetegories = categoryService.findAllByPage(n.orElse(0), s.orElse(10));
        if (catetegories.isEmpty()) {
            return new ResponseEntity<Page<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Category>>(catetegories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) {
        CategoryDTO dto = this.categoryService.findById(id);
        if (dto == null) {
            return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryDTO>(dto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        if (this.categoryService.isExist(dto) || dto.getId() != -1) {
            return new ResponseEntity<CategoryDTO>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<CategoryDTO>(this.categoryService.create(dto), HttpStatus.OK);
    }


    @PutMapping("")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto) {
        if (this.categoryService.findById(dto.getId()) == null) {
            return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryDTO>(this.categoryService.update(dto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Integer id) {
        CategoryDTO dto = this.categoryService.findById(id);
        if (dto == null) {
            return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryDTO>(this.categoryService.delete(dto), HttpStatus.OK);
    }

}
