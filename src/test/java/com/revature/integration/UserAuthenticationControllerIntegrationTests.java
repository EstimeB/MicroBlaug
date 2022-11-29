package com.revature.integration;

import com.revature.controller.UserAuthenticationController;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthenticationControllerIntegrationTests extends UserAuthenticationController  {


    //    public Connection connection;
//    public Javalin app;
//
//    @BeforeEach
//    public void setup() throws SQLException, IOException {
//        connection = UserConnectionUtility.getConnection();
//        UserConnectionUtility.populateH2Database(connection);
//
//        //Start server (for all tests)
//        app = Javalin.create();
//        UserAuthenticationController authController = new UserAuthenticationController();
//        authController.mapEndPoints(app); //map endpoints in UserAuthenticationController to the test Javalin object
//        }
//
//    @AfterEach
//    public void clearDb()throws SQLException, IOException {
//        UserConnectionUtility.clearH2Database(connection);
//        connection.close();
//    }

    //Whitebox Integration (API) test

    @Test
    public void loginAPITestPositive() throws IOException, SQLException {
        //Map endpoint
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);
        //Test
        JavalinTest.test(app, (server, client) -> {
            //Request
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "Mary");
            requestJson.put("password", "mary123");

            Response response = client.post("/login", requestJson);
            //Code
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();
            //Assert
            assertThat(actualResponseStatusCode).isEqualTo(200);
            assertThat(responseBodyJson).isEqualTo("{\"user_id\":68,\"username\":\"Mary\",\"email\":\"mary@mary.com\",\"password\":\"mary123\"}");
        });
    }

    @Test
    public void loginAPITestInvalidCredentials() {
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "fakeusername");
            requestJson.put("password", "fakepassword");

            Response response = client.post("/login", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400); // 400 Bad Request
            assertThat(responseBodyJson).isEqualTo("{\"message\":\"Invalid login\"}");
        });
    }

    @Test
    public void loginAPITestUsernameIsNull(){
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("password", "fakepassword");

            Response response = client.post("/login", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("username and/or password was not provided");
        });
    }

    @Test
    public void loginAPIPasswordIsNull(){
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "fakeusername");

            Response response = client.post("/login", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("username and/or password was not provided");
        });
    }

    @Test
    public void loginAPIUsernameAndPasswordAreNull(){
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();

            Response response = client.post("/login", requestJson);
            int actualResponseStatusCode = response.code();
            String responseBodyJson = response.body().string();

            assertThat(actualResponseStatusCode).isEqualTo(400);
            assertThat(responseBodyJson).isEqualTo("username and/or password was not provided");
        });
    }

    @Test
    public void currentUserApiTestLoggedIn() {
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "Mary");
            requestJson.put("password", "mary123");

            Response loginResponse = client.post("/login", requestJson);
            String cookie = loginResponse.header("Set-Cookie").split(":")[0];
            Response currentUserResponse = client.get("/current-user", (builder) -> {builder.addHeader("Cookie", cookie);});

            assertThat(currentUserResponse.body().string()).isEqualTo("{\"user_id\":68,\"username\":\"Mary\",\"email\":\"mary@mary.com\",\"password\":\"mary123\"}");
        });
    }

    @Test
    public void currentUserApiTestNotLoggedIn() {
        Javalin app = Javalin.create();
        UserAuthenticationController userAuthenticationController = new UserAuthenticationController();
        userAuthenticationController.mapEndPoints(app);

        JavalinTest.test(app, (server, client) -> {
            Response currentUserResponse = client.get("/current-user");

            assertThat(currentUserResponse.code()).isEqualTo(401);
            assertThat(currentUserResponse.body().string()).isEqualTo("{\"message\":\"User is not logged in.\"}");
        });
    }
}
