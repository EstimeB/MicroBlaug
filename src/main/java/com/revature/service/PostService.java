package com.revature.service;

import com.revature.dao.PostDao;
import com.revature.exception.PostUnsuccessfullyCreated;
import com.revature.model.Post;
import com.revature.exception.PostNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class PostService {

    private PostDao postDao = new PostDao();

    public Post createPost(Post createPost) throws SQLException {
        createPost.setPostTitle(createPost.getPostTitle().strip());
        createPost.setPostDescription(createPost.getPostDescription().strip());

        if (createPost.getPostTitle().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title");
        }
        if(createPost.getPostDescription().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description");
        }
        int createdPostRecord = postDao.createPost(createPost);
        if (createdPostRecord != 1) {
            throw new PostUnsuccessfullyCreated("The Post could not be created");
        }
        return createPost;
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
    public void updatePost(Post updatePost) throws SQLException {
        updatePost.setPostTitle(updatePost.getPostTitle().strip());
        updatePost.setPostDescription(updatePost.getPostDescription().strip());

        if (updatePost.getPostTitle().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Title");
        }
        if(updatePost.getPostDescription().length() == 0) {
            throw new IllegalArgumentException("You Must Have a Post Description");
        }
        int updatedPostRecord = postDao.updatePost(updatePost);
        if (updatedPostRecord != 1) {
            throw new PostUnsuccessfullyCreated("The Post could not be created");
        }
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
