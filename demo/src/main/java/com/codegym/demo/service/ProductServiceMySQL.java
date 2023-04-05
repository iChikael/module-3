package com.codegym.demo.service;

import com.codegym.demo.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceMySQL extends DBContext implements ProductService {
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM product where id = ?";
    private static final String UPDATE_PRODUCT = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ?, `description` = ? , `category` = ? WHERE (`id` = ?);";
    private static final String DELETE_PRODUCT = "DELETE FROM `product` WHERE (`id` = ?);";
    private static final String INSERT_PRODUCT = "INSERT INTO `product` (`name`, `price`, `quantity`,`description`, `category`) VALUES ( ?, ?, ?, ?,?)";

    private static final String FIND_PRODUCTS_BY_KW_AND_FILTER_PAGGING_ALL = "SELECT SQL_CALC_FOUND_ROWS  *   FROM product where `name` like ?  limit ?,?;";
    private static final String FIND_PRODUCTS_BY_KW_AND_FILTER_PAGGING = "SELECT SQL_CALC_FOUND_ROWS  *   FROM product where `name` like ? and `category` = ?  limit ?,?;";
    private int totalRows;

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = getProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int price = rs.getInt("price");
        int quantity = rs.getInt("quantity");
        String description = rs.getString("description");
        int type = rs.getInt("category");
        Product product = new Product(id, name, quantity, price, description , type);
        return product;
    }

    @Override
    public void save(Product product) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setLong(5, product.getType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findbyId(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = getProductFromResultSet(rs);
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(long id, Product product) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(5, product.getType());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setLong(6, product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(long id) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public long maxId() {
        return 0;
    }
    @Override
    public List<Product> findByKwAndFilter_Pagging(int offset, int limit, String kw, int productType) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement;
            if (productType == -1) {
                preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_KW_AND_FILTER_PAGGING_ALL);
                preparedStatement.setString(1, '%' + kw + '%');
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, limit);
            } else {
                preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_KW_AND_FILTER_PAGGING);
                preparedStatement.setString(1, '%' + kw + '%');
                preparedStatement.setInt(2, productType);
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, limit);
            }
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("findByKwAndFilter_Pagging(): " + preparedStatement);
            while (rs.next()) {
                Product product = getProductFromResultSet(rs);
                products.add(product);
            }
            rs = preparedStatement.executeQuery("select FOUND_ROWS()");
            System.out.println("findByKwAndFilter_Pagging() - FOUND_ROWS: " + preparedStatement);
            if (rs.next()) {
                this.totalRows = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public int getNoOfRecords() {
        return totalRows;
    }

}
