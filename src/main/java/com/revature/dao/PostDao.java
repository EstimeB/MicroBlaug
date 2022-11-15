package com.revature.dao;

import com.revature.model.Post;
import com.revature.util.ConnectionFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    //post (C)
    public int createPost(Post createdPost) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement
                        ("INSERT INTO posts (postTitle, postDescription, userId) VALUES (?, ?, ?)");
            pstmt.setString(1, createdPost.getPostTitle());
            pstmt.setString(2, createdPost.getPostDescription());
            pstmt.setInt(3, createdPost.getUserId());

            int numOfRecordCreated = pstmt.executeUpdate();

            return numOfRecordCreated;

        }
    }

    //get (R)
    public Post getPostsById(int id) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                return new Post(rs.getInt("id"), rs.getString("postTitle"),
                        rs.getString("postDescription"), rs.getInt("userId"));
            } else {
                //if no record associated with the id is found
                return null;
            }
        }
    }
    public List<Post> getAllPostsBelongingToUser(int userId) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts WHERE userId = ?");

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            List<Post> userPosts = new ArrayList<>();

            while (rs.next()) {

                Post post = new Post(rs.getInt("id"), rs.getString("postTitle"),
                        rs.getString("postDescription"), rs.getInt("userId"));

                userPosts.add(post);
            }
            return userPosts;
        }
    }
    public List<Post> getAllPosts() throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()){
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts");

            ResultSet rs = pstmt.executeQuery();

            List<Post> allPosts = new ArrayList<>();

            while (rs.next()) {


                Post post = new Post(rs.getInt("id"), rs.getString("postTitle"),
                        rs.getString("postDescription"), rs.getInt("userId"));

                allPosts.add(post);
            }
            return allPosts;
        }
    }

    //update (U)
    public int updatePost(Post updatePost) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE posts SET postTitle = ?," +
                    " postDescription = ?\n" +
                    "WHERE id = ?");
            pstmt.setString(1, updatePost.getPostTitle());
            pstmt.setString(2, updatePost.getPostDescription());

            int numOfRecordUpdated = pstmt.executeUpdate();

            return numOfRecordUpdated;
        }
    }

    //delete (D)
    public int deletePost(int id) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM posts \n" +
                    "WHERE id = ?;\n");

            pstmt.setInt(1, id);

            int numOfRecordDeleted = pstmt.executeUpdate();

            return numOfRecordDeleted;
        }
    }

}
