package com.fpt.controller.staff;

import com.fpt.dto.ProductDTO;
import com.fpt.dto.ProductDetailDTO;
import com.fpt.entity.ProductDetail;
import com.fpt.mapper.ProductDetailMapper;
import com.fpt.repo.ProductDetailRepo;
import com.fpt.service.CategoryService;
import com.fpt.service.ProductService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/staff/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "", params = {"page", "status"})
    public ResponseEntity<List<ProductDTO>> findAll(@RequestParam("page") Optional<Integer> page,
        @RequestParam("status") Integer status,
        @RequestParam(value = "cateId", required = false) Integer cateId,
        @RequestParam(value = "nameQuery") String nameQuery
    ) {
        List<ProductDTO> products;
        if (status == -1 || cateId == -1) {
            if (status != -1) {
                products = this.productService
                    .findAllByStatusAndName(page.orElse(0), status, nameQuery);
            }
            else if (cateId != -1){
                products = this.productService
                    .findAllByCateAndName(page.orElse(0), cateId, nameQuery);
            }
            else products = this.productService.findAllByName(page.orElse(0), nameQuery);
        }
        else {
            products = this.productService
                .findAllUsingFilter(page.orElse(0), cateId, status, nameQuery);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        ProductDTO dto = this.productService.findById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        if (dto.getId() != -1 || this.categoryService.findById(dto.getCategoryId()) == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(this.productService.create(dto), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) {
        if (this.productService.findById(dto.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.productService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable("id") Integer id) {
        ProductDTO dto = this.productService.findById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.productService.delete(dto), HttpStatus.OK);
    }

    private final ProductDetailRepo productDetailRepo;
    private final ProductDetailMapper productDetailMapper;

    @PostMapping("/add-list-detail")
    public List<ProductDetail> addListDetail(
        @RequestBody List<ProductDetailDTO> productDetailDTOS) {
        List<ProductDetail> productDetails = productDetailDTOS.stream()
            .map(productDetailDTO -> productDetailMapper.cvrToEntity(productDetailDTO))
            .collect(Collectors.toList());
        if (!productDetails.isEmpty()) {
            return productDetailRepo.saveAll(productDetails);
        }
        return new ArrayList<>();
    }

}











