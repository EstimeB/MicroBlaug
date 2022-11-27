package IntegrationTests;

import com.revature.controller.ProfileController;
import com.revature.controller.UserAuthenticationController;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileIntegrationTest extends UserAuthenticationController {

    public Connection con;


    //Creating Mock Table.
    @BeforeEach
    public void setup() throws SQLException, IOException {
    }

    //Deleting mock table.
    @AfterEach
    public void clearDb() throws SQLException {
    }

    //INTEGRATION TEST 1.
    @Test
    public void profileViewInvalid() throws IOException, SQLException {
            //Map endpoint
             Javalin app = Javalin.create();
             ProfileController profController = new ProfileController();
             profController.mapEndPoints(app);
            //Test
             JavalinTest.test(app,(server,client) -> {
                 //Request
            Map<String,Object> requestJSON = new HashMap<>();
            requestJSON.put("username", "admin123");
            requestJSON.put("password","password123");
            //Response
            Response response = client.post("/profileview", requestJSON);
            //Code
            int actualResponseCode = response.code();
            String responseBodyJSON = response.body().string();
            //Assert
            assertThat(actualResponseCode).isEqualTo(400);
        });
    }

    //Integration Test 2
    @Test
    public void profileViewValid() throws IOException, SQLException {
        uName = "marypoppins";
        uPass = "password1111";

        Javalin app = Javalin.create();
        ProfileController profController = new ProfileController();
        profController.mapEndPoints(app);
        //Test
        JavalinTest.test(app,(server,client) -> {
            //Request
            Map<String,Object> requestJSON = new HashMap<>();
            requestJSON.put("username", uName);
            requestJSON.put("password", uPass);
            //Response
            Response response = client.post("/profileview");
            //Code
            int actualResponseCode = response.code();
            String responseBodyJSON = response.body().string();
            //Assert
            assertThat(actualResponseCode).isEqualTo(200);
        });
    }


    //INTEGRATION TEST 3.
    @Test
    public void ProfileEditPositive(){
        Javalin app = Javalin.create();
        ProfileController profController = new ProfileController();
        profController.mapEndPoints(app);
        //Test
        JavalinTest.test(app,(server,client) -> {
            //Request
            Map<String,Object> requestJSON = new HashMap<>();
            requestJSON.put("username", "marypoppins");
            requestJSON.put("password","password1111");
            requestJSON.put("firstname","Anwar");
            requestJSON.put("lastname","Darkazanli");
            requestJSON.put("email","Rev@gmail.com");
            requestJSON.put("newPassword","password1111");
            //Response
            Response response = client.post("/profileupdate", requestJSON);
            //Code
            int actualResponseCode = response.code();
            String responseBodyJSON = response.body().string();
            //Assert
            assertThat(actualResponseCode).isEqualTo(200);

        });
    }

 //INTEGRATION TEST 4.
    @Test
    public void ProfileEditNegative(){
        Javalin app = Javalin.create();
        ProfileController profController = new ProfileController();
        profController.mapEndPoints(app);
        //Test
        JavalinTest.test(app,(server,client) -> {
            //Request
            Map<String,Object> requestJSON = new HashMap<>();
            requestJSON.put("username", "marypoppin");
            requestJSON.put("password","password111");
            requestJSON.put("firstname","Anwar");
            requestJSON.put("lastname","Darkazanli");
            requestJSON.put("email","Rev@gmail.com");
            requestJSON.put("newPassword","password111");
            //Response
            Response response = client.post("/profileupdate", requestJSON);
            //Code
            int actualResponseCode = response.code();
            String responseBodyJSON = response.body().string();
            //Assert
            assertThat(actualResponseCode).isEqualTo(400);
        });
    }

}
