import com.revature.controller.*;
import io.javalin.Javalin;

import java.io.IOException;

public class
Main {
    public static void main(String[] args) throws IOException {
        Javalin app = Javalin.create((config) -> {
            config.plugins.enableCors((cors) -> {
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("127.0.0.1:63342");
                    it.allowHost("localhost:63342");

                    it.allowHost("localhost:5500");
                    it.allowHost("127.0.0.1:5500");
                    it.allowCredentials = true;


                });
            });
        });


        Controller[] controllers = { new UserAuthenticationController(), new PostController(), new CommentController(), new ProfileController(), new UserCommentsController()};


        for (Controller c : controllers) {
            c.mapEndPoints(app);
        }

        app.start(8080);
    }
}
