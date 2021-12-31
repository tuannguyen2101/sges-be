package com.fpt.controller.guest;

import com.fpt.dto.ProductDTO;
import com.fpt.entity.Customer;
import com.fpt.entity.Product;
import com.fpt.service.CategoryService;
import com.fpt.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/guest/product")
@Slf4j
public class ProdController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

//	@GetMapping("")
//	public ResponseEntity<List<ProductDTO>> findAll () {
//		List<ProductDTO> products = this.productService.findAll();
//		if(products.isEmpty()) {
//			return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
//	}

    @GetMapping("")
    public ResponseEntity<Page<Product>> findAll(@RequestParam("cId") Optional<Integer> cId, @RequestParam("n") Optional<Integer> n, @RequestParam("s") Optional<Integer> s, @RequestParam("p") Optional<String> p, @RequestParam("d") Optional<Integer> d) {

        Integer cateId = cId.orElse(null);

        int number = n.orElse(0);
        int size = s.orElse(10);
        String prop = p.orElse("name");
        int direction = d.orElse(0);

        Pageable pageable = null;
        Page<Product> products = null;

        if (direction == 0) {
            pageable = PageRequest.of(number, size, Sort.by(Sort.Direction.ASC, prop));
        } else {
            pageable = PageRequest.of(number, size, Sort.by(Sort.Direction.DESC, prop));
        }
        try {
            if (cateId != null) {
                products = productService.findByCategoryId(cateId, pageable);
            } else {
                products = productService.findAll(pageable);
            }
            if (products.getTotalElements() > 0) {
                return new ResponseEntity<Page<Product>>(products, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("error: " + e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
//        ProductDTO dto = this.productService.findById(id);
//        if (dto == null) {
//            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<ProductDTO>(dto, HttpStatus.OK);
//    }

}
