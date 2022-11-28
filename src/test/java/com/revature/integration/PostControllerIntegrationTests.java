package com.revature.integration;

import com.revature.controller.PostController;
import com.revature.controller.UserAuthenticationController;
import com.revature.util.ConnectionFactory;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PostControllerIntegrationTests {

    public Connection con;
    public Javalin app;

//    @BeforeEach
//    public void setup() throws SQLException {
//        con = ConnectionFactory.createConnection();
//        app = Javalin.create();
//        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
//        userAuthenticationController.mapEndPoints(app);
//        PostController postController = new PostController();
//        postController.mapEndPoints(app);
//    }

    @AfterEach
    public void clearDb() throws SQLException {
        con.close();
    }

    @Test
    public void createPostPositiveTest() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("postTitle", "Friday");
            requestJson.put("postDescription", "Friday is the best day of the week.");

            Response response = client.post("/dashboard/createPost", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(201);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Your Post Has Successfully Been Created!\"}");
        });
    }

    @Test
    public void createPostTestPostTitleIsNull() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            //post title is left out of the request
            requestJson.put("postDescription", "Friday is the best day of the week.");

            Response response = client.post("/dashboard/createPost", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"You Must Have a Post Title!\"}");
        });
    }

    @Test
    public void createPostTestPostDescriptionIsNull() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("postTitle", "Friday");
            //post description is left out of the request

            Response response = client.post("/dashboard/createPost", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"You Must Have a Post Description!\"}");
        });
    }

    @Test
    public void getPostTest() {
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get("/posts");
            int actualResponseStatusCode = response.code();

            assertThat(actualResponseStatusCode).isEqualTo(200);
        });
    }

    @Test
    public void getUserPostTest() {
        JavalinTest.test(app, (server, client) -> {
            Response response = client.get("/dashboard/userPosts");
            int actualResponseStatusCode = response.code();

            assertThat(actualResponseStatusCode).isEqualTo(200);
        });
    }

//    @Test
//    public void getPostByIdTest() {
//        JavalinTest.test(app, (server, client) -> {
//            Map<String, Object> requestJson = new HashMap<>();
//            requestJson.put("id", "41");
//            Response response = client.get("/dashboard/post/{id}", requestJson);
//            int actualResponseStatusCode = response.code();
//            String responseBodyJson = response.body().string();
//
//            assertThat(actualResponseStatusCode).isEqualTo(200);
//            assertThat(responseBodyJson).isEqualTo("{\"id\":41,\"postTitle\":\"How to master your time\",\"postDescription\":\"The secret to time management is simple: Jedi time tricks.\",\"userId\":\"46\",\"postDateCreated\":\"null\"}");
//        });
//    }

    @Test
    public void updatePostTest() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("postTitle", "Friday(1)");
            requestJson.put("postDescription", "Friday is the best day of the week that is for sure.");

            Response response = client.put("/dashboard/createPost", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(201);
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Your Post Has Successfully Been Updated!\"}");
        });
    }

    @Test
    public void deletePostTest() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("id", "65");
            Response response = client.delete("/dashboard/deletePost/{id}", requestJson);

            int actualResponseStatusCode = response.code();
            assertThat(actualResponseStatusCode).isEqualTo(200);
        });
    }
}
