package com.revature.controller;

import com.revature.exception.CommentUnsuccessfullyAddedException;
import com.revature.exception.CommentUnsuccessfullyUpdatedException;
import com.revature.model.Comment;
import com.revature.service.CommentService;
import io.javalin.Javalin;

import java.util.List;

public class CommentController implements Controller {

    private final CommentService commentService = new CommentService();


    public void mapEndPoints(Javalin app) {

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
            Comment comment  = ctx.bodyAsClass(Comment.class);

            try {
                commentService.createNewComment(comment);

                ctx.result("comment successfully added!");
                ctx.status(201);
            } catch (IllegalArgumentException | CommentUnsuccessfullyAddedException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }

        });

        // delete an existing comment by comment_id

        app.delete("/comment/{comment_id}", (ctx) -> {
            String commentToDelete = ctx.pathParam("comment_id");

            try {
                int commentId = Integer.parseInt(commentToDelete);

                int deletedComment = commentService.findAndDeleteCommentById(commentId);

                ctx.json(deletedComment);
                ctx.result("comment successfully deleted!");
                ctx.status(200);
            } catch (NumberFormatException e) {
                ctx.result("The comment Id " + commentToDelete + " must be a valid integer!");
                ctx.status(400);
            }

        });

        // update an existing comment by comment_id

        app.put("/comment", (ctx) -> {
            Comment commentToUpdate  = ctx.bodyAsClass(Comment.class);

            try {

                commentService.findAndUpdateCommentId(commentToUpdate);

                ctx.json(commentToUpdate);
                ctx.result("comment successfully updated!");
                ctx.status(201);
            } catch (IllegalArgumentException | CommentUnsuccessfullyUpdatedException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }

        });

    }

}