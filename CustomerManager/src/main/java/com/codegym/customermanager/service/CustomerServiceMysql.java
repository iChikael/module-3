package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceMysql extends DBContext implements CustomerService{
    private static final String SELECT_ALL_CUSTOMER = "SELECT * FROM customer";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM customer where id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE `customer` SET `name` = ?, `address` = ?, `email` = ?, `createat` = ?, `image` = ?, `customer_type` = ? WHERE (`id` = ?);";
    private static final String DELETE_CUSTOMER = "DELETE FROM `customer` WHERE (`id` = ?);";
    private static final String INSERT_CUSTOMER = "INSERT INTO `customer` (`name`, `address`, `email`, `createat`, `image`, `customer_type`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_CUSTOMERS_BY_KW_AND_FILTER_PAGGING_ALL = "SELECT SQL_CALC_FOUND_ROWS  *   FROM customer where `name` like ?  limit ?,?;";
    private static final String FIND_CUSTOMERS_BY_KW_AND_FILTER_PAGGING = "SELECT SQL_CALC_FOUND_ROWS  *   FROM customer where `name` like ? and customer_type = ?  limit ?,?;";


    private int noOfRecords;


    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(SELECT_ALL_CUSTOMER);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                Customer customer = getCustomerFromResultSet(rs);
                customers.add(customer);
            }
//            preparedStatement.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public List<Customer> findByKwAndFilter_Pagging(int offset, int limit, String kw, int customerType) {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement;
            if (customerType == -1) {
                preparedStatement = connection.prepareStatement(FIND_CUSTOMERS_BY_KW_AND_FILTER_PAGGING_ALL);
                preparedStatement.setString(1,'%' + kw + '%');
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, limit);
            }else{
                preparedStatement =  connection.prepareStatement(FIND_CUSTOMERS_BY_KW_AND_FILTER_PAGGING);
                preparedStatement.setString(1,'%' + kw + '%');
                preparedStatement.setInt(2, customerType);
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, limit);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = getCustomerFromResultSet(rs);
                customers.add(customer);
            }

            rs = preparedStatement.executeQuery("select FOUND_ROWS()");
            while (rs.next()) {
                noOfRecords = rs.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    private Customer getCustomerFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String image = rs.getString("image");
        java.sql.Date date = rs.getDate("createat");

        long idCustomerType = rs.getLong("customer_type");

        Date createAt = new Date(date.getTime());

        //public Customer(long id, String name, String email,
        //                    String address, String image, Date createAt)
        Customer customer = new Customer(id, name, email, address, image, createAt, idCustomerType);
        return customer;
    }


    @Override
    public void save(Customer customer) {
        Connection connection = getConnection();


//        "INSERT INTO `customer` (`name`, `address`, `email`, `createat`,
//        `image`, `customer_type`) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setDate(4, new java.sql.Date(customer.getCreateAt().getTime()));
            preparedStatement.setString(5, customer.getImage());
            preparedStatement.setLong(6, customer.getCustomerType());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Customer findById(long id) {

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Luôn lấy theo column label
                Customer customer = getCustomerFromResultSet(rs);
                return customer;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public void update(long id, Customer customer) {
        Connection connection = getConnection();

        try {
//            UPDATE `customer` SET `name` = ?, `address` = ?, `email` = ?,
//            `createat` = ?, `image` = ?, `customer_type` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setDate(4, (Date) customer.getCreateAt());
            preparedStatement.setString(5, customer.getImage());

            preparedStatement.setLong(6, customer.getCustomerType());
            preparedStatement.setLong(7, customer.getId());

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
    public long maxId() {
        return 0;
    }
}
