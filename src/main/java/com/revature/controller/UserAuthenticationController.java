package com.revature.controller;

import com.revature.dto.LoginCredentials;
import com.revature.dto.Message;
import com.revature.exception.LoginException;
import com.revature.exception.UserUnsuccessfullyAddedException;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

public class UserAuthenticationController implements Controller {

    private UserService userService = new UserService();
    private static final String LOGOUT = "logout";
    @Override
    public void mapEndPoints(Javalin app) {
            app.post("/login", (ctx) -> {
                LoginCredentials credentials = ctx.bodyAsClass(LoginCredentials.class);

                if (credentials.getUsername() == null ||credentials.getPassword() == null) {
                    ctx.result("username and/or password was not provided");
                    ctx.status(400);
                } else {

                    try {
                        User user = userService.login(credentials.getUsername(), credentials.getPassword());

                        //Create an HTTPSession and associate the user object with that session
                        //HTTPSessions are used to track which client is sending a particular request
                        HttpSession httpSession = ctx.req().getSession();
                        httpSession.setAttribute("user_info", user);

                        ctx.json(user);
                    } catch (LoginException e) {
                        ctx.status(400);
                        ctx.json(new Message(e.getMessage()));
                    }
                }
            });

            app.post("/logout", (ctx) -> {
                System.out.println("hit it");
                HttpSession httpSession = ctx.req().getSession();
                httpSession.invalidate();
                ctx.req().logout();
                httpSession = ctx.req().getSession();
            });

            app.get("/current-user", (ctx) -> {
                HttpSession httpSession = ctx.req().getSession();
                User loggedInUser = (User) httpSession.getAttribute("user_info");

                if (loggedInUser == null){
                    ctx.json(new Message("User is not logged in."));
                } else {
                    ctx.json(loggedInUser);
                }
            });

        app.post("/signup", (ctx -> {
            User userToAdd = ctx.bodyAsClass(User.class);

            try {
                userService.signup(userToAdd);

                ctx.result("User successfully added");
                ctx.status(201);
            } catch (IllegalArgumentException | UserUnsuccessfullyAddedException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }
        }));
    }
}
