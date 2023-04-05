package com.codegym.demo.service;

import com.codegym.demo.model.Product;
import com.codegym.demo.model.ProductType;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findbyId(long id);

    void update(long id, Product product);

    void remove(long id);

    long maxId();

    List<Product> findByKwAndFilter_Pagging(int i, int limit, String kw, int category);

    int getNoOfRecords();
}
