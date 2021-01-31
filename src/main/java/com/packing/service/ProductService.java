package com.packing.service;

import com.packing.dao.ProductRepository;
import com.packing.model.Product;
import com.packing.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(Product entity) {
        productRepository.save(entity);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.getOne(id);
    }

    @Override
    public void remove(Integer id) {
        productRepository.delete(findById(id));
    }
}
