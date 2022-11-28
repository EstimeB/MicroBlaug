package com.revature.controller;

import com.revature.dto.LoginCredentials;
import com.revature.dto.Message;
import com.revature.dto.SignupCredentials;
import com.revature.exception.LoginException;
import com.revature.exception.PostNotFoundException;
import com.revature.exception.UserUnsuccessfullyAddedException;
import com.revature.model.Post;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

public class UserAuthenticationController implements Controller {

    private UserService userService = new UserService();

    public static String uName;
    public static String uPass;
    
    @Override
    public void mapEndPoints(Javalin app) {
            app.post("/login", (ctx) -> {
                //Getting the login credentials from the user. Getting info from request body and put that info into an object.

                LoginCredentials credentials = ctx.bodyAsClass(LoginCredentials.class);

                if (credentials.getUsername() == null ||credentials.getPassword() == null) {
                    ctx.result("username and/or password was not provided");
                    ctx.status(400);
                } else {

                    try {
                        uName = credentials.getUsername();
                        uPass = credentials.getPassword();
                        User user = userService.login(credentials.getUsername(), credentials.getPassword());
                        //Create an HTTPSession and associate the user object with that session
                        //HTTPSessions are used to track which client is sending a particular request
                        HttpSession httpSession = ctx.req().getSession();
                        httpSession.setAttribute("user_info", user);

                        ctx.json(user);
                    } catch (LoginException e) {
                        ctx.req().getSession().invalidate();
                        ctx.status(400);
                        ctx.json(new Message(e.getMessage()));
                    }
                }
            });

            app.post("/logout", (ctx) -> {

//                HttpSession httpSession = ctx.req().getSession();
//                httpSession.invalidate();
                ctx.req().getSession().invalidate();
                ctx.req().logout();
                ctx.json(new Message("User is logged out"));
            });

            app.get("/current-user", (ctx) -> {
                HttpSession httpSession = ctx.req().getSession();
                User loggedInUser = (User) httpSession.getAttribute("user_info");

                if (loggedInUser == null){
                    ctx.json(new Message("User is not logged in."));
                    ctx.status(401);
                } else {
                    ctx.json(loggedInUser);
                }
            });

        app.post("/signup", (ctx -> {
            SignupCredentials credentials = ctx.bodyAsClass(SignupCredentials.class);
            User userToAdd = ctx.bodyAsClass(User.class);

            if (credentials.getUsername() == null || credentials.getPassword() == null) {
                ctx.result("username and/or password was not provided");
                ctx.status(400);
            } else {

                try {
                    uName = credentials.getUsername();
                    uPass = credentials.getPassword();
                    userService.signup(userToAdd);
                    ctx.result("User successfully added");
                    ctx.status(201);

                    User user = userService.login(credentials.getUsername(), credentials.getPassword());
                    HttpSession httpSession = ctx.req().getSession();
                    httpSession.setAttribute("user_info", user);
                    ctx.json(user);
                } catch (IllegalArgumentException | UserUnsuccessfullyAddedException e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        }));

        //get post by post id
        app.get("/getuser", (ctx) -> {

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401);
            } else {
                try {
                    User u = userService.findUserById(user.getUser_id());
                    ctx.json(u);
                    ctx.status(200);
                } catch (NumberFormatException e) {
                    ctx.result("Id " + user.getUser_id() + " must be a valid integer!");
                    ctx.status(400); // 400 BAD REQUEST
                }
            }

        });
    }
}
