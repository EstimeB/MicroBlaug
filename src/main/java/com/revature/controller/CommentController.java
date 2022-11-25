package com.revature.controller;

import com.revature.dto.Message;
import com.revature.exception.CommentUnsuccessfullyAddedException;
import com.revature.exception.CommentUnsuccessfullyUpdatedException;
import com.revature.model.Comment;
import com.revature.model.User;
import com.revature.service.CommentService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class CommentController implements Controller {

    private final CommentService commentService = new CommentService();

    public void mapEndPoints(Javalin app) {


        // get all comments in the system

        app.get("/comments", (ctx) -> {
            List<Comment> AllComments = commentService.getAllComments();

            ctx.json(AllComments);
        });

        // find a comment by postId

        app.get("/comment/{post_id}", (ctx) -> {
            String pId = ctx.pathParam("post_id");

            try {
                int postId = Integer.parseInt(pId);

                List<Comment> comment = commentService.findCommentByPostId(postId);

                ctx.json(comment);
                ctx.status(200);
            } catch (NumberFormatException e) {
                ctx.result("Id " + pId + " must be a valid int!");
                ctx.status(400);
            }

        });

        // create a new comment

        app.post("/comment", (ctx) -> {

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            Comment comment  = ctx.bodyAsClass(Comment.class);

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    commentService.createNewComment(comment);
                    ctx.json(comment);
                    ctx.result("comment successfully added!");
                    ctx.status(201);
                } catch (IllegalArgumentException | CommentUnsuccessfullyAddedException e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        });

        // delete an existing comment by comment_id

        app.delete("/comment/{comment_id}", (ctx) -> {

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            String commentToDelete = ctx.pathParam("comment_id");

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    int commentId = Integer.parseInt(commentToDelete);

                    int deletedComment = commentService.findAndDeleteCommentById(commentId);

                    ctx.json(deletedComment);
                    ctx.result("comment was deleted!");
                    ctx.status(204);
                } catch (NumberFormatException e) {
                    ctx.result("The comment Id " + commentToDelete + " must be a valid integer!");
                    ctx.status(400);
                }
            }
        });

        // update an existing comment by comment_id

        app.put("/comment", (ctx) -> {

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            Comment commentToUpdate  = ctx.bodyAsClass(Comment.class);

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    commentService.findAndUpdateCommentId(commentToUpdate);

                    ctx.json(commentToUpdate);
                    ctx.result("comment successfully updated!");
                    ctx.status(201);
                } catch (IllegalArgumentException | CommentUnsuccessfullyUpdatedException e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        });
    }
}
