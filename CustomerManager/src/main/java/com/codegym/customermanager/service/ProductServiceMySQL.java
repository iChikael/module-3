package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductServiceMySQL extends DBContext implements ProductService{
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM product";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM product where id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE `product` SET `name` = ?, `quantity` = ?, `price` = ?, `description` = ? WHERE (`id` = ?);";
    private static final String DELETE_CUSTOMER = "DELETE FROM `product` WHERE (`id` = ?);";
    private static final String INSERT_CUSTOMER = "INSERT INTO `product` (`name`, `quantity`, `price`, `description`) VALUES (?, ?, ?, ?)";




    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                Product product = getProductFromResultSet(rs);
                products.add(product);
            }
//            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }


    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        String description = rs.getString("description");


        //public Customer(long id, String name, String email,
        //                    String address, String image, Date createAt)
        Product product = new Product(id, name, quantity, price, description);
        return product;
    }

    @Override
    public void save(Product product) {
        Connection connection = getConnection();



        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.setString(4, product.getDescription());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product findById(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                Product product = getProductFromResultSet(rs);
                return product;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void update(long id, Product product) {
        Connection connection = getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void remove(long id) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public long getMaxId() {
        return 0;
    }

    @Override
    public long maxId() {
        List<Product> products = findAll();
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() == o2.getId()) {
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        Product lastProduct = products.get(products.size() - 1);
        return lastProduct.getId();
    }
}
