package com.revature.dao;

import com.revature.model.UserComments;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCommentsDao {
    public List<UserComments> getAllCommentsAndUsersForPosts(int postID) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM UserComments\n" +
                    "where post_id = ?\n" +
                    "order by comment_id desc");

            pstmt.setInt(1, postID);

            ResultSet rs = pstmt.executeQuery();

            List<UserComments> userComments = new ArrayList<>();

            while (rs.next()) {
                userComments.add(
                        new UserComments(
                                rs.getInt("comment_id"),
                                rs.getString("comment_message"),
                                rs.getDate("comment_date"),
                                rs.getInt("post_id"),
                                rs.getInt("userid"),
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("avatar")
                        )
                );
            }
            return userComments;
        }
    }
}
