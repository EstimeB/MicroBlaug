package com.revature.controller;

import com.revature.dto.Message;
import com.revature.exception.PostNotFoundException;
import com.revature.exception.PostUnsuccessfullyCreated;
import com.revature.model.Post;
import com.revature.model.User;
import com.revature.service.PostService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;

public class PostController implements Controller {

    private PostService postService = new PostService();

    public void mapEndPoints(Javalin app) {

        //create post ...
        app.post("/dashboard", (ctx) -> {

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            Post post = ctx.bodyAsClass(Post.class);

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    Post createdPost = postService.createPost(post.getPostTitle(), post.getPostDescription(), user.getUser_id());
                    ctx.status(201); // created
                    ctx.json(createdPost);
                } catch (IllegalArgumentException | PostUnsuccessfullyCreated e) {
                    ctx.result(e.getMessage());
                    ctx.status(400); //Bad Request
                }
            }

        });

        //get post by post id
        app.get("/post/{id}", (ctx) -> {

            String postId = ctx.pathParam("id");

            try {
                int pId = Integer.parseInt(postId);
                Post post = postService.getPostsById(pId);
                ctx.json(post);
                ctx.status(200);
            } catch (NumberFormatException e) {
                ctx.result("Id " + postId + " must be a valid integer");
                ctx.status(400); // 400 BAD REQUEST
            } catch (PostNotFoundException e) {
                ctx.result(e.getMessage());
                ctx.status(404);
            }

        });

        //get post by user id ...
        app.get("/userPosts", (ctx) ->{

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401);
            } else {
                try{
                List<Post> posts = postService.getAllPostsBelongingToUser(user.getUser_id());
                ctx.json(posts);
                ctx.status(200);
                } catch (NumberFormatException e) {
                    ctx.result("Id "+user.getUser_id()+" must be a valid int!");
                    ctx.status(400);
                }
            }

        });

        //get all posts
        app.get("/posts", (ctx) -> {
            List<Post> posts = postService.getAllPosts();
            ctx.json(posts);
        });

        // get all related comments for specific post Id
        app.get("/postcomments/{postId}", (ctx) -> {
            String postId = ctx.pathParam("postId");
            int pId = Integer.parseInt(postId);
            HashMap postcomments = postService.getPostComments(pId);
            ctx.json(postcomments);
        });

        //update post
        app.put("/updatePost/{id}", (ctx) -> {
            //method is currently creating new posts
            //need to work in having it get existing post then update it
            String postId = ctx.pathParam("id");

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            Post post = ctx.bodyAsClass(Post.class);

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    int pId = Integer.parseInt(postId);
                    Post updatePost = postService.updatePost(pId,
                            post.getPostTitle(), post.getPostDescription(), user.getUser_id());
                    ctx.json(updatePost);
                    ctx.status(201);
                } catch (IllegalArgumentException | PostUnsuccessfullyCreated e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        });

        //delete post ...
        app.delete("/deletePost/{id}", (ctx) -> {
            String postId = ctx.pathParam("id");

            HttpSession httpSession = ctx.req().getSession();
            User user = (User) httpSession.getAttribute("user_info");

            if (user == null) {
                ctx.json(new Message("Not logged in!"));
                ctx.status(401); // Unauthorized
            } else {
                try {
                    int pId = Integer.parseInt(postId);
                    int deletePost = postService.deletePost(pId);
                    ctx.json(deletePost);
                    ctx.status(200);
                } catch (PostNotFoundException e) {
                    ctx.result(e.getMessage());
                    ctx.status(404);
                }
            }

        });

    }
}
