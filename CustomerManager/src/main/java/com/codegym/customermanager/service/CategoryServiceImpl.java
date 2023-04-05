package com.codegym.customermanager.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CategoryServiceImpl extends DBContext implements CategoryService{
    private static final String SP_ADD_CATEGORY = "call spAddCategory(?, ?);";

    @Override
    public void addCategory(String name) {
        Connection connection = getConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(SP_ADD_CATEGORY);

            callableStatement.setString(1, name);
            callableStatement.registerOutParameter(2, Types.BIGINT);

            callableStatement.execute();

            int id = callableStatement.getInt(2);
            System.out.println(id);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
