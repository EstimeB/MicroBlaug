package com.revature.service;

import com.revature.dao.CommentDao;
import com.revature.exception.CommentNotFoundException;
import com.revature.exception.PostNotFoundException;
import com.revature.model.Comment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommentService {

    private CommentDao commentDao = new CommentDao();

    public List<Comment> getAllComments() throws SQLException, IOException {
        return  commentDao.getAllComments();
    }

    public List<Comment> findCommentByPostId(int postId) throws SQLException {
        return commentDao.findCommentByPostId(postId);
    }

    public void createNewComment(Comment comment) throws SQLException {
        if (comment.getCommentMessage().length() == 0) {
            throw new IllegalArgumentException("Comments must be filled in");
        } else {
            commentDao.createNewComment(comment);
        }
    }

    public int findAndDeleteCommentById(int commentId) throws SQLException {
        int commentToDelete = commentDao.findAndDeleteCommentById(commentId);
        if (commentToDelete == 0) {
            throw new CommentNotFoundException("Comment with " + commentId + " was not found");
        } else {
            return commentToDelete;
        }
    }

    public void findAndUpdateCommentId(Comment comment) throws SQLException {
        if (comment.getCommentMessage().length() == 0) {
            throw new IllegalArgumentException("Comments must be filled in");
        }
        commentDao.findAndUpdateCommentId(comment);
    }
}
