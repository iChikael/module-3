package com.codegym.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceMySQL implements ProductService {
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM product";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM product where id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE `product` SET `name` = ?, `quantity` = ?, `price` = ?, `description` = ? WHERE (`id` = ?);";
    private static final String DELETE_CUSTOMER = "DELETE FROM `product` WHERE (`id` = ?);";
    private static final String INSERT_CUSTOMER = "INSERT INTO `product` (`name`, `quantity`, `price`, `description`) VALUES (?, ?, ?, ?, )";
    private String jdbcURL = "jdbc:mysql://localhost:3306/c12_products?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

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
        String quantity = rs.getString("quantity");
        String price = rs.getString("price");
        String description = rs.getString("description");


        //public Customer(long id, String name, String email,
        //                    String address, String image, Date createAt)
        Product product = new Product(id, name, quantity, price, description);
        return product;
    }

    @Override
    public void save(Product product) {
        Connection connection = getConnection();


        //INSERT INTO customer` (`name`, `address`, `email`, `createat`, `image`) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getQuantity());
            preparedStatement.setString(3, product.getPrice());

            preparedStatement.setString(5, product.getDescription());

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
            preparedStatement.setString(2, product.getQuantity());
            preparedStatement.setString(3, product.getPrice());
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
        return 0;
    }
}
