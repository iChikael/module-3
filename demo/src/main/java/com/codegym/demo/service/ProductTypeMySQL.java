package com.codegym.demo.service;

import com.codegym.demo.model.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeMySQL extends DBContext implements ProductTypeService {
    private static final String SELECT_ALL_PRODUCT_TYPE = "SELECT * FROM c12_products.category;";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM c12_products.category where id = ?";

    @Override
    public List<ProductType> findAll() {
        List<ProductType> productTypes = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_TYPE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductType productType = getProductTypeFromResultSet(rs);
                productTypes.add(productType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productTypes;
    }

    private ProductType getProductTypeFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("category");
        ProductType productType = new ProductType( id, name);

        return productType;
    }


    @Override
    public ProductType findbyId(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductType productType = getProductTypeFromResultSet(rs);
                return productType;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
