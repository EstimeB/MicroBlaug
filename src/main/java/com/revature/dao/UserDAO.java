package com.revature.dao;

import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;

            } else {
                return null;
            }
        }
    }
    public int registerNewAccount(User userToAdd) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "insert into users (username, email, password) values (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, userToAdd.getUsername());
            pstmt.setString(2, userToAdd.getEmail());
            pstmt.setString(3, userToAdd.getPassword());

            int numberOfRecordsUpdated = pstmt.executeUpdate();

            return numberOfRecordsUpdated;

        }
    }
}
