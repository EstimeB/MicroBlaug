package com.revature.model;

import java.sql.Date;
import java.util.Objects;

public class UserComments {
    private int commentId;
    private String commentMessage;
    private Date commentDate;
    private int postId;
    private int userId;
    private int userid;
    private String username;

    private String email;
    private String password;
    private String avatar;

    public UserComments() {
    }

    public UserComments(int commentId, String commentMessage, Date commentDate, int postId, int userId, int userid, String username, String email, String password, String avatar) {
        this.commentId = commentId;
        this.commentMessage = commentMessage;
        this.commentDate = commentDate;
        this.postId = postId;
        this.userId = userId;
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComments that = (UserComments) o;
        return commentId == that.commentId && postId == that.postId && userId == that.userId && userid == that.userid && Objects.equals(commentMessage, that.commentMessage) && Objects.equals(commentDate, that.commentDate) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentMessage, commentDate, postId, userId, userid, username, email, password, avatar);
    }

    @Override
    public String toString() {
        return "UserComments{" +
                "commentId=" + commentId +
                ", commentMessage='" + commentMessage + '\'' +
                ", commentDate=" + commentDate +
                ", postId=" + postId +
                ", userId=" + userId +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}