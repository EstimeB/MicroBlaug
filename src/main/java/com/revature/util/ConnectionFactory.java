package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {
        //Database credentials should not be hard coded use system environment
        //variables. 20:53 (restart application after)
        String url = "url";
        String username = "db";
        String password = "password";

        //Instantiating connection object
        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;

    }

}
