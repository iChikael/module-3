package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(long id);

    void update(long id, Product product);

    void remove(long id);

    long getMaxId();

    long maxId();
}
