package com.codegym.customermanager.service;

import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeMySQL extends DBContext implements CustomerTypeService{
    private static final String SELECT_ALL_CUSTOMER_TYPE = "SELECT * FROM customer_type;";
    private static final String FIND_CUSTOMER_TYPE_BY_ID = "SELECT * FROM customer_type where id = ?";

    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypes = new ArrayList<>();

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER_TYPE);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                CustomerType customerType = getCustomerTypeFromResultSet(rs);
                customerTypes.add(customerType);
            }
//            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerTypes;
    }

    private CustomerType getCustomerTypeFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");


        //public Customer(long id, String name, String email,
        //                    String address, String image, Date createAt)
        CustomerType customerType = new CustomerType(id, name);
        return customerType;
    }

    @Override
    public CustomerType findById(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_TYPE_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                CustomerType customerType = getCustomerTypeFromResultSet(rs);
                return customerType;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }
}
