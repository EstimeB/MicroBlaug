package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.exception.LoginException;
import com.revature.exception.SignupException;
import com.revature.exception.UserUnsuccessfullyAddedException;
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

    public void signup(User user) throws SQLException {
        user.setUsername(user.getUsername().strip());
        user.setEmail(user.getEmail().strip());
        user.setPassword(user.getPassword().strip());

        if (user.getUsername().length() == 0) {
            throw new SignupException("Must include username");
        }

        if (user.getEmail().length() == 0) {
            throw new SignupException("Must include email");
        }

        if (user.getPassword().length() == 0) {
            throw new SignupException("Must include password");
        }

        int recordsAdded = userDAO.registerNewAccount(user);

        if (recordsAdded != 1) {
            throw new UserUnsuccessfullyAddedException("User could not be added");
        }
    }

    public User findUserById(int userId) throws SQLException {
        User user = userDAO.findUserById(userId);
        return user;
    }

}
