package com.revature.controller;

import com.revature.model.UserComments;
import com.revature.service.UserCommentsService;
import io.javalin.Javalin;
import java.util.List;

public class UserCommentsController implements Controller {

    private final UserCommentsService userCommentsService = new UserCommentsService();


    public void mapEndPoints(Javalin app) {

        // find all comments and posts by postid

        app.get("/user/comments/{post_id}", (ctx) -> {
            String p = ctx.pathParam("post_id");

            try {
                int postID = Integer.parseInt(p);

                List<UserComments> comment = userCommentsService.getAllCommentsAndUsersForPosts(postID);

                ctx.json(comment);
                ctx.status(200);
            } catch (NumberFormatException e) {
                ctx.result("Id " + p + " must be a valid int!");
                ctx.status(400);
            }

        });
    }
}
