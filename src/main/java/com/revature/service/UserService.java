package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.exception.LoginException;
import com.revature.exception.SignupException;
import com.revature.model.User;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO = new UserDAO();
    public User login(String username, String password) throws SQLException {
        User user = userDAO.findUserByUsernameAndPassword(username, password);

        if (user == null) {
            throw new LoginException("Invalid login");
        }

        return user;
    }

    public User signup(String username, String email, String password) throws SQLException {
        User user = userDAO.registerNewAccount(username, email, password);

        if (user == null) {
            throw new SignupException("Invalid Signup");
        }

        return user;
    }

}
