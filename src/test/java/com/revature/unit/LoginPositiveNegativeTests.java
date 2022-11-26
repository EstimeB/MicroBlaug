package com.revature.unit;

import com.revature.dao.UserDAO;
import com.revature.exception.LoginException;
import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Provides Jupiter with additional functionalities coming with Mockito
public class LoginPositiveNegativeTests {

    @Mock // Create a mock object
    UserDAO mockUd;

    @InjectMocks //When we run tests, it will automatically go and instantiate a UserService object and inject any mock dependency
    UserService us;

    @Test
    public void loginPositiveTest() throws SQLException{
        //arrange
        when(mockUd.findUserByUsernameAndPassword(eq("muser123"), eq("password"))).thenReturn(new User(1, "muser123", "muser@aol.com", "password"));
        //act
        User actual = us.login("muser123", "password");
        User expected = new User(1, "muser123",  "muser@aol.com", "password");
        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void loginNegativeTestInvalidCredentials() throws SQLException {
       //arrange
        when(mockUd.findUserByUsernameAndPassword(any(), any()))
                .thenReturn(null);
        //act
        //do nothing because we aren't passing any information in
        //assert
        Assertions.assertThrows(LoginException.class, () -> {;
            us.login("fakey1", "fake123");
        });
    }
}
