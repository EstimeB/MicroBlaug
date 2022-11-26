package com.revature.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Post {

    private int id;
    private String postTitle;
    private String postDescription;
    private int userId;
    private LocalDate postDateCreated;

    public Post(){}
    public Post(String postTitle, String postDescription) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
    }
    public Post(String postTitle, String postDescription, int userId) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
    }
    public Post(int id, String postTitle, String postDescription, int userId) {
        this.id = id;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
    }
    public Post(int id, String postTitle, String postDescription, int userId, LocalDate postDateCreated) {
        this.id = id;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.userId = userId;
        this.postDateCreated = postDateCreated;
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

    public LocalDate getPostDateCreated() {
        return postDateCreated;
    }

    public void setPostDateCreated(LocalDate postDateCreated) {
        this.postDateCreated = postDateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && userId == post.userId && postTitle.equals(post.postTitle) && postDescription.equals(post.postDescription) && Objects.equals(postDateCreated, post.postDateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postTitle, postDescription, userId, postDateCreated);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", userId=" + userId +
                ", postdateCreated=" + postDateCreated +
                '}';
    }
}
