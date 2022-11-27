package com.revature.dao;

import com.revature.model.Comment;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentDao {

    public List<Comment> getAllComments() throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {

            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM comments\n" +
                    "ORDER BY comment_id DESC");

            ResultSet rs = pstmt.executeQuery();

            List<Comment> allComments = new ArrayList<>();

            while (rs.next()) {
                int commentId = rs.getInt("comment_id");
                String commentMessage = rs.getString("comment_message");
                Date commentDate = rs.getDate("comment_date");
                int postId = rs.getInt("post_id");
                int userId = rs.getInt("userid");

                Comment u = new Comment(commentId, commentMessage, commentDate, postId, userId );

                allComments.add(u);
            }
            return allComments;
        }
    }

    public List<Comment> findCommentByPostId(int postId) throws SQLException {

        // try with resources to close connection once sql executed
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "SELECT * FROM comments \n" +
                    "WHERE post_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, postId);

            ResultSet rs = pstmt.executeQuery();

            List<Comment> comments = new ArrayList<>();

            while (rs.next()) {
               comments.add(
                        new Comment(
                                rs.getInt("comment_id"),
                                rs.getString("comment_message"),
                                rs.getDate("comment_date"),
                                rs.getInt("post_Id"),
                                rs.getInt("userid")
                        )
                );
            }
            return comments;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int createNewComment(Comment comment) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "INSERT INTO comments (comment_message, userid, post_id) \n" +
                    "VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, comment.getCommentMessage());
            pstmt.setInt(2, comment.getUserId());
            pstmt.setInt(3, comment.getPostId());


            return pstmt.executeUpdate();
    }
}

    public int findAndDeleteCommentById(int commentId) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "DELETE FROM comments \n" +
                    "WHERE comment_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, commentId);

            return pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findAndUpdateCommentId(Comment comment) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "UPDATE comments \n" +
                    "SET comment_message = ? \n" +
                    "WHERE comment_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, comment.getCommentMessage());
            pstmt.setInt(2, comment.getCommentId());


            pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
