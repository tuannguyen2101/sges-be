package com.fpt.controller.guest;

import com.fpt.dto.SupCategoryDTO;
import com.fpt.entity.SupCategory;
import com.fpt.service.SupCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/guest/supcate")
public class SupCateController {

    @Autowired
    SupCateService supCateService;

    @GetMapping
    public ResponseEntity<List<SupCategoryDTO>> findAll() {
        try {
            List<SupCategoryDTO> supCategories = supCateService.findAll();
            return new ResponseEntity<>(supCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
