package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    List<Customer> findByKwAndFilter_Pagging(int offset, int limit, String kw, int customerType);


    int getNoOfRecords();
    void save(Customer customer);

    Customer findById(long id);

    void update(long id, Customer customer);

    void remove(long id);

    long maxId();

}
