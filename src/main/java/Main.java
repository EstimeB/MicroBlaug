import com.revature.controller.Controller;
import com.revature.controller.PostController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
            config.plugins.enableCors((cors) -> {
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("127.0.0.1:63342");
                    it.allowHost("localhost:63342");
                });
            });
        });

        Controller[] controllers = { new PostController()};

        for (Controller c : controllers) {
            c.mapEndPoints(app);
        }

        app.start(7080);
    }
}
