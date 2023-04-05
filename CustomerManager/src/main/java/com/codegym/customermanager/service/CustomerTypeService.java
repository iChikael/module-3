package com.codegym.customermanager.service;


import com.codegym.customermanager.model.CustomerType;

import java.util.List;

public interface CustomerTypeService {
    List<CustomerType> findAll();
    CustomerType findById(long id);


}
