import com.revature.controller.CommentController;
import com.revature.controller.Controller;
import com.revature.controller.PostController;
import com.revature.controller.UserAuthenticationController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
            config.plugins.enableCors((cors) -> {
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("127.0.0.1:63342");
                    it.allowHost("localhost:63342");
                    it.allowHost("127.0.0.1:5500");
                    it.allowHost("localhost:5500");
                });
            });
        });

        Controller[] controllers = { new PostController(), new CommentController()};

        for (Controller c : controllers) {
            c.mapEndPoints(app);
        }

        app.start(8080);
    }
}
