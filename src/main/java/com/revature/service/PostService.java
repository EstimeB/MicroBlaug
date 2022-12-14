package com.revature.service;

import com.revature.dao.PostDao;
import com.revature.model.Post;
import com.revature.exception.PostNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();

    public void createPost(Post createdPost, int uid) throws SQLException, IOException {

        if (createdPost.getPostTitle().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title!");
        }
        if(createdPost.getPostDescription().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description!");
        }
        postDao.createPost(createdPost, uid);
    }
    public Post getPostsById(int id) throws SQLException, IOException {
        Post post = postDao.getPostsById(id);

        if (post == null) {
            throw new PostNotFoundException("Post with id "+id+" was not found!");
        } else {
            return post;
        }
    }
    public List<Post> getAllPostsBelongingToUser(int userId) throws SQLException, IOException {
        return postDao.getAllPostsBelongingToUser(userId);
    }
    public List<Post> getAllPosts() throws SQLException, IOException {
        return postDao.getAllPosts();
    }
    public HashMap getPostComments(int postId) throws SQLException, IOException {
        return postDao.getPostComments(postId);
    }
    public Post updatePost(Post updatedPost) throws SQLException, IOException {
        if (updatedPost.getPostTitle().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title!");
        }
        if(updatedPost.getPostDescription().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description!");
        }
        return postDao.updatePost(updatedPost);
    }
    public void deletePost(int id) throws SQLException, IOException {
        int deletePost = postDao.deletePost(id);

        if (deletePost == 0) {
            throw new PostNotFoundException("Post with id "+id+" was not found!");
        }
    }

}
