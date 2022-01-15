package com.fpt.service.impl;

import com.fpt.dto.ProductDTO;
import com.fpt.entity.Category;
import com.fpt.entity.Product;
import com.fpt.mapper.ProductMapper;
import com.fpt.repo.CategoryRepo;
import com.fpt.repo.ProductRepo;
import com.fpt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<ProductDTO> findAllUsingFilter(Integer page, Integer cateId, Integer status,
        String nameQuery) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepo.findAllByCategoryIdAndStatusAndNameContains(cateId, status, nameQuery, pageable)
            .stream()
            .map(e -> this.productMapper.cvToDTO(e))
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCateAndName(Integer page, Integer cateId, String nameQuery) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepo.findAllByCategoryIdAndNameContains(cateId, nameQuery, pageable)
            .stream()
            .map(e -> this.productMapper.cvToDTO(e))
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByStatusAndName(Integer page, Integer status, String nameQuery) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepo.findAllByStatusAndNameContains(status, nameQuery, pageable)
            .stream()
            .map(e -> this.productMapper.cvToDTO(e))
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByName(Integer page, String nameQuery) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepo.findAllByNameContains(nameQuery, pageable)
            .stream()
            .map(e -> this.productMapper.cvToDTO(e))
            .collect(Collectors.toList());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public List<ProductDTO> findByStatus(Integer page, Integer status) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepo.findByStatus(status, pageable).stream()
                .map(e -> this.productMapper.cvToDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Product> findByCategoryId(Integer categoryId, Pageable pageable) {
        Optional<Category> cate = this.categoryRepo.findById(categoryId);
        if (cate.isPresent()) {
            return productRepo.findAllByCategoryId(categoryId, pageable);
        }
        return null;
    }

    @Override
    public List<Product> findProductNewByTop() {
        return productRepo.findProductNewByTop();
    }

    @Override
    public List<Product> timSanPhamBanChay() {
        return productRepo.timSanPhamBanChay();
    }

    @Override
    public Page<Product> findByProductName(String name, Pageable pageable) {
        return productRepo.findByProductName(name, pageable);
    }

    @Override
    public Page<Product> findAllByNotCategoryId(Pageable pageable) {
        return productRepo.findAllByNotCategoryId(pageable);
    }

    @Override
    public ProductDTO findById(Integer id) {
        return this.productMapper.cvToDTO(this.productRepo.findById(id).get());
    }

    @Override
    public Product findProductById(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        return product.orElse(null);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        return this.productMapper.cvToDTO(this.productRepo.save(this.productMapper.cvToEntity(dto)));
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        return this.productMapper.cvToDTO(this.productRepo.save(this.productMapper.cvToEntity(dto)));
    }

    @Override
    public ProductDTO delete(ProductDTO dto) {
        this.productRepo.delete(this.productMapper.cvToEntity(dto));
        return dto;
    }

}
