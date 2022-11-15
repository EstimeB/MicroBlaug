package com.revature.model;

import java.awt.*;
import java.util.Objects;

public class Post {

    private int id;
    private String postTitle;
    private String postDescription;
    private int userId;

    public Post(){}

    public Post(int id, String postTitle, String postDescription, int userId) {
        this.id = id;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && userId == post.userId && postTitle.equals(post.postTitle) && postDescription.equals(post.postDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postTitle, postDescription, userId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", userId=" + userId +
                '}';
    }
}
