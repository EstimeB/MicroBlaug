package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {

        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("ENV_URL");
        String username = dotenv.get("ENV_USERNAME");
        String password = dotenv.get("ENV_PASSWORD");

        //Instantiating connection object
        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;

    }

}
