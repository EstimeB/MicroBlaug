package com.revature.service;

import com.revature.controller.UserCommentsController;
import com.revature.dao.UserCommentsDao;
import com.revature.model.UserComments;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserCommentsService {

    private final UserCommentsDao userCommentsDao = new UserCommentsDao();

    public List<UserComments> getAllCommentsAndUsersForPosts(int postID) throws SQLException, IOException {
        return userCommentsDao.getAllCommentsAndUsersForPosts(postID);
    }
}
