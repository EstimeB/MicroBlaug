package com.revature.service;

import com.revature.dao.PostDao;
import com.revature.exception.PostUnsuccessfullyCreated;
import com.revature.model.Post;
import com.revature.exception.PostNotFoundException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();

    public Post createPost(String postTitle, String postDescription, int userId) throws SQLException {

        if (postTitle.length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title");
        }
        if(postDescription.length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description");
        }
        return postDao.createPost(postTitle, postDescription, userId);
    }
    public Post getPostsById(int id) throws SQLException {
        Post post = postDao.getPostsById(id);

        if (post == null) {
            throw new PostNotFoundException("Post with id "+id+" was not found");
        } else {
            return post;
        }
    }
    public List<Post> getAllPostsBelongingToUser(int userId) throws SQLException {
        return postDao.getAllPostsBelongingToUser(userId);
    }
    public List<Post> getAllPosts() throws SQLException {
        return postDao.getAllPosts();
    }

    public HashMap getPostComments(int postId) throws SQLException {
        return postDao.getPostComments(postId);
    }

    public Post updatePost(int id, String postTitle, String postDescription, int userId) throws SQLException {
        if (postTitle.length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title");
        }
        if(postDescription.length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description");
        }
        return postDao.updatePost(id, postTitle, postDescription, userId);
    }
    public int deletePost(int id) throws SQLException {
        int deletePost = postDao.deletePost(id);

        if (deletePost == 0) {
            throw new PostNotFoundException("Post with id "+id+" was not found");
        } else {
            return deletePost;
        }
    }

}
