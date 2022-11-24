package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.sun.tools.javac.Main;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {

    //Getting H2 value.
    public static Connection createConnection() throws SQLException, IOException {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("ENV_URL");
            String username = dotenv.get("ENV_USERNAME");
            String password = dotenv.get("ENV_PASSWORD");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        }

    }




