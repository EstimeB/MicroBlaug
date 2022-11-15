package com.revature.controller;

import com.revature.exception.PostNotFoundException;
import com.revature.exception.PostUnsuccessfullyCreated;
import com.revature.model.Post;
import com.revature.service.PostService;
import io.javalin.Javalin;

import java.util.List;

public class PostController implements Controller {

        private PostService postService = new PostService();

        public void mapEndPoints(Javalin app) {

            //create post
            app.post("/dashboard", (ctx) -> {
                Post post = ctx.bodyAsClass(Post.class);
                try {
                    Post createdPost = postService.createPost(post);
                    ctx.json(createdPost);
                    ctx.status(200);
                } catch (IllegalArgumentException | PostUnsuccessfullyCreated e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            });
            //get by id
            app.get("/posts/{id}", (ctx) -> {
                String postId = ctx.pathParam("id");

                try {
                    int pId = Integer.parseInt(postId);
                    Post post = postService.getPostsById(pId);
                    ctx.json(post);
                } catch (NumberFormatException e) {
                    ctx.result("Id " + postId + " must be a valid integer");
                    ctx.status(400); // 400 BAD REQUEST
                } catch (PostNotFoundException e) {
                    ctx.result(e.getMessage());
                    ctx.status(404);
                }
            });

            //get post by user id
            app.get("/{userId}/posts", (ctx) ->{
                String userId = ctx.pathParam("userId");

                try {
                    int uId = Integer.parseInt(userId);
                    List<Post> posts = postService.getAllPostsBelongingToUser(uId);
                    ctx.json(posts);
                    ctx.status(200);
                } catch (NumberFormatException e) {
                    ctx.result("Id "+userId+" must be a valid int!");
                    ctx.status(400);
                }
            });

            //get all posts
            app.get("/posts", (ctx) -> {
                List<Post> posts = postService.getAllPosts();

                ctx.json(posts);
            });

            //update post
            app.put("/posts/{id}", (ctx) -> {
                //method is currently creating new posts
                //need to work in having it get existing post then update it
                Post updatePost = ctx.bodyAsClass(Post.class);
                try {
                    postService.updatePost(updatePost);
                    ctx.json(updatePost);
                    ctx.status(200);
                } catch (IllegalArgumentException | PostUnsuccessfullyCreated e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            });

            //delete post
            app.delete("/posts/{id}", (ctx) -> {
                String postId = ctx.pathParam("id");

                try {
                    int pId = Integer.parseInt(postId);
                    int deletePost = postService.deletePost(pId);
                    ctx.json(deletePost);
                } catch (NumberFormatException e) {
                    ctx.result("Id " + postId + " must be a valid integer");
                    ctx.status(400); // 400 BAD REQUEST
                } catch (PostNotFoundException e) {
                    ctx.result(e.getMessage());
                    ctx.status(404);
                }
            });

        }
}
