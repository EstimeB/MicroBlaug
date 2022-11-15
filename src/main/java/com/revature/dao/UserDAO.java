package com.revature.dao;

import com.revature.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User findUserByUsernameEmailAndPassword(String username, String password) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()) {
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
    public User registerNewAccount(String username, String email, String password, String passwordSecondTime) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()){
            return null;
        }
    }
}