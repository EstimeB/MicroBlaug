package com.revature.controller;

import com.revature.dao.ProfileDao;
import com.revature.dto.LoginCredentials;
import com.revature.dto.Message;
import com.revature.model.Profile;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;
import org.eclipse.jetty.security.UserAuthentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ProfileController extends UserAuthenticationController implements Controller{

    UserAuthenticationController loggedIn = new UserAuthenticationController();
    private ProfileDao profileDao = new ProfileDao();
    public void mapEndPoints( Javalin app){
        app.post("/profileview", (ctx) -> {
            Profile p1 = profileDao.viewInformation();
            if (p1 != null) {
                System.out.println(p1.getAvatar());
                ctx.json(p1);
                ctx.status(200);
            } else {
                ctx.result("User not found.");
                ctx.status(400);
            }

        });



    //UPDATE ENDPOINT.
        app.post("/profileupdate", (ctx) -> {
            Profile p = ctx.bodyAsClass(Profile.class);
            if(profileDao.updatedInformation(p) < 1){
                System.out.println("O records updated");
            ctx.status(400);
            ctx.result("Invalid username");
            }else {
                System.out.println("one record updated");
            ctx.status(200);
            ctx.result(profileDao.updatedInformation(p)+ " record(s) updated");
                   }
            });


        //DELETE ENDPOINT
        app.post("/profiledelete", (ctx) -> {
          Profile p = ctx.bodyAsClass(Profile.class);
             if(profileDao.deleteInformation(p)< 1){
                System.out.println("0 records deleted");
                ctx.status(400);
                ctx.json("Invalid information");
            }else {
                ctx.status(200);
                ctx.result(profileDao.deleteInformation(p) + " record(s) deleted");
                    }
                });
            }
    }
