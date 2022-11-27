package com.revature.util;

import java.io.IOException;
import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException, IOException {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("ENV_URL");
            String username = dotenv.get("ENV_USERNAME");
            String password = dotenv.get("ENV_PASSWORD");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
    }

}




