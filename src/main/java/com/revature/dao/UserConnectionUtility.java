package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserConnectionUtility {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://35.184.83.14/postgres";
        String username = "postgres";
        String password = "password"; // change to environment variables

        return DriverManager.getConnection(url, username, password);
    }
}
