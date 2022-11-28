package com.revature.dao;

import com.revature.model.Post;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostDao {

    //post (C)
    public void createPost(Post post, int uid) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
                PreparedStatement pstmt = connection.prepareStatement
                        ("INSERT INTO posts (postTitle, postDescription, userId) VALUES (?, ?, ?)");
                pstmt.setString(1, post.getPostTitle());
                pstmt.setString(2, post.getPostDescription());
                pstmt.setInt(3, uid);

            pstmt.executeUpdate();
        }
    }

    //get (R)
    public Post getPostsById(int id) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts WHERE id = ?");

                pstmt.setInt(1, id);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {

                    return new Post(rs.getInt("id"), rs.getString("postTitle"),
                            rs.getString("postDescription"), rs.getInt("userId"), rs.getDate("postDateCreated"), rs.getString("postimage"));
                } else {
                    //if no record associated with the id is found
                    return null;
                }
        }
    }

    public List<Post> getAllPostsBelongingToUser(int userId) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts WHERE userId = ?");

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            List<Post> userPosts = new ArrayList<>();

            while (rs.next()) {

                Post post = new Post(rs.getInt("id"), rs.getString("postTitle"),
                        rs.getString("postDescription"), rs.getInt("userId"), rs.getDate("postDateCreated"));

                userPosts.add(post);
            }
            return userPosts;
        }
    }

    public List<Post> getAllPosts() throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM posts");

            ResultSet rs = pstmt.executeQuery();

            List<Post> allPosts = new ArrayList<>();

            while (rs.next()) {
                Post post = new Post(rs.getInt("id"), rs.getString("postTitle"), rs.getString("postDescription"), rs.getInt("userId"), rs.getDate("postDateCreated"), rs.getString("postimage"));

                allPosts.add(post);
            }
            return allPosts;
        }
    }

    // getting related comments to specific Posts

    public HashMap getPostComments(int postId) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()){
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM comments \n" +
                    "LEFT JOIN posts \n" +
                    "ON posts.id = comments.post_id WHERE post_id=?");

            pstmt.setInt(1, postId);

            ResultSet rs = pstmt.executeQuery();

            HashMap postComments = new HashMap();

            while (rs.next()) {
                int id = rs.getInt("id");
                String postTitle = rs.getString("postTitle");
                String postDescription = rs.getString("postDescription");
                int postuserId = rs.getInt("userId");
                int commentId = rs.getInt("comment_id");
                String commentMessage = rs.getString("comment_message");
                int postid = rs.getInt("post_id");
                int commentUserid = rs.getInt("user_id");
                Date commentDate = rs.getDate("comment_date");

                postComments.put("id", id);
                postComments.put("postTitle", postTitle);
                postComments.put("postDescription", postDescription);
                postComments.put("postuserId", postuserId);
                postComments.put("commentId", commentId);
                postComments.put("commentMessage", commentMessage);
                postComments.put("postid", postid);
                postComments.put("commentUserid", commentUserid);
                postComments.put("commentDate", commentDate);
            }
            return postComments;
        }
    }

    //update (U)
    public Post updatePost(Post post) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE posts SET postTitle = ?," +
                    " postDescription = ?" +
                    "WHERE id = ?");
            pstmt.setString(1, post.getPostTitle());
            pstmt.setString(2, post.getPostDescription());
            pstmt.setInt(3, post.getId());

            pstmt.executeUpdate();

            return new Post(post.getId(), post.getPostTitle(), post.getPostDescription(), post.getUserId());
        }
    }

    //delete (D)
    public int deletePost(int id) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM posts \n" +
                    "WHERE id = ?;\n");

            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        }
    }
}
