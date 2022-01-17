package com.fpt.service;

import com.fpt.dto.ProductDTO;
import com.fpt.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {


    List<ProductDTO> findAllUsingFilter(Integer page, Integer cateId, Integer status, String nameQuery);

    List<ProductDTO> findAllByCateAndName(Integer page, Integer cateId, String nameQuery);

    List<ProductDTO> findAllByStatusAndName(Integer page, Integer status, String nameQuery);

    List<ProductDTO> findAllByName(Integer page, String nameQuery);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNotCategoryId(Pageable pageable);

    List<ProductDTO> findByStatus(Integer page, Integer status);

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    ProductDTO findById(Integer id);

    Product findProductById(Integer id);

    List<Product> findProductNewByTop();

    List<Product> timSanPhamBanChay();

    Page<Product> findByProductName(String name, Pageable pageable);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(ProductDTO dto);

    ProductDTO delete(ProductDTO dto);


}
