package com.codegym.demo.service;

import com.codegym.demo.model.ProductType;

import java.util.List;

public interface ProductTypeService {
List<ProductType> findAll();
ProductType findbyId(long id);
}
