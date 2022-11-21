package com.revature.model;

import java.sql.Date;
import java.util.Objects;

public class Comment {

    private int commentId;
    private String commentMessage;

    private Date commentDate;
    private int postId;
    private int userId;

    public Comment() {
    }

    public Comment(int commentId, String commentMessage, Date commentDate, int postId, int userId) {
        this.commentId = commentId;
        this.commentMessage = commentMessage;
        this.commentDate = commentDate;
        this.postId = postId;
        this.userId = userId;
    }

    public Comment(int comment_id, String comment_message, int post_id, int user_id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && postId == comment.postId && userId == comment.userId && Objects.equals(commentMessage, comment.commentMessage) && Objects.equals(commentDate, comment.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentMessage, commentDate, postId, userId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentMessage='" + commentMessage + '\'' +
                ", commentDate=" + commentDate +
                ", postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}