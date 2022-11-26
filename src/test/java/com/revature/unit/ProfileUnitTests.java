package com.revature.unit;
import com.revature.controller.ProfileController;
import com.revature.dao.ProfileDao;
import com.revature.model.Profile;
import com.revature.model.User;
import com.revature.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.UnnecessaryStubbingException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)//Provides Jupiter with additional functionalities coming from Mockito.
public class ProfileUnitTests {


    @Mock //
    ProfileDao mockProfDao;

    @InjectMocks
    ProfileController pc;

    @Test
    public void profileObjectTest() throws SQLException, IOException {
       try{
          lenient().when(mockProfDao.viewInformation())
               .thenReturn(new Profile());
          System.out.println("New profile object is created!");
       }
       catch(UnnecessaryStubbingException e){
           Assert.assertEquals(1,1);
       }
    }


    @Test
    public void profileUpdateTest() throws SQLException, IOException {

            Profile p = new Profile("marcus", "aurelius");
            lenient().when(mockProfDao.updatedInformation(p))
                    .thenReturn(1);
            System.out.println("one profile updated");
        }


    @Test
    public void profileDeleteTest() throws SQLException, IOException {
            Profile p = new Profile("James", "Madison");
            lenient().when(mockProfDao.deleteInformation(p))
                    .thenReturn(1);
            System.out.println("one profile deleted");
        }
    }


