package com.revature.service;

import com.revature.dao.CommentDao;
import com.revature.model.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService {

    private CommentDao commentDao = new CommentDao();

    public List<Comment> findCommentByPostId(int postId) throws SQLException {
        return commentDao.findCommentByPostId(postId);
    }

    public void createNewComment(Comment comment) throws SQLException {
        commentDao.createNewComment(comment);
    }

    public int findAndDeleteCommentById(int commentId) throws SQLException {
        return commentDao.findAndDeleteCommentById(commentId);
    }

    public void findAndUpdateCommentId(Comment comment) throws SQLException {
        commentDao.findAndUpdateCommentId(comment);
    }
}
