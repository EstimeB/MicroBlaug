package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserConnectionUtility {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://34.134.127.27/postgres";
        String username = "postgres";
        String password = "revstarProject02"; // change to environment variables

        return DriverManager.getConnection(url, username, password);
    }
}
