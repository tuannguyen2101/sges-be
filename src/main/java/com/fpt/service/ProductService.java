package com.fpt.service;

import com.fpt.dto.ProductDTO;
import com.fpt.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

//    List<ProductDTO> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNotCategoryId(Pageable pageable);

    List<ProductDTO> findByStatus(Integer page, Integer status);

//    List<ProductDTO> findAllByPaginate(Integer page);

//    List<ProductDTO> findByCategory(Integer id);
    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    ProductDTO findById(Integer id);

    Product findProductById(Integer id);

    List<Product> findProductNewByTop();

    List<Product> timSanPhamBanChay();

    ProductDTO create(ProductDTO dto);

    ProductDTO update(ProductDTO dto);

    ProductDTO delete(ProductDTO dto);


}
