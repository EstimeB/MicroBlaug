package com.revature.controller;

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




    public void mapEndPoints( Javalin app){


        //INSERT PROFILE Endpoint
        app.post("/profilecreate",(ctx) -> {
            System.out.println("Create Endpoint Reached");
            Connection connection = ConnectionFactory.createConnection();
            Profile p = ctx.bodyAsClass(Profile.class);
            PreparedStatement pstmt = connection.prepareStatement("insert into userprofiles (interest, firstname , lastname) values (? , ? , ?)");
            pstmt.setString(1, p.getInterest());
            pstmt.setString(2, p.getFirstname());
            pstmt.setString(3, p.getLastname());
            int numberOfRecordsUpdated = pstmt.executeUpdate();
            ctx.result(numberOfRecordsUpdated + " record(s) updated");
            });

//        app.get("/current-profile-user", (ctx) -> {
//            Connection connection = ConnectionFactory.createConnection();
//            PreparedStatement pstmt1 = connection.prepareStatement("SELECT * FROM users ORDER BY user_id DESC LIMIT 1");
//            ResultSet rs1 = pstmt1.executeQuery();
//            if(rs1.next()) {
//                String username = rs1.getString("username");
//                String password = rs1.getString("password");
//                Profile p1 = new Profile(password, username);
//                System.out.println(p1.getUsername());
//                System.out.println(p1.getPassword());
//                ctx.json(p1);
//                ctx.status(200);
//            }
//
//        });
        //VIEW PROFILE Endpoint (Getting data from two different tables in DB)
        app.post("/profileview", (ctx) -> {
               System.out.println("Query Endpoint Accessed");
                Connection connection = ConnectionFactory.createConnection();
                PreparedStatement pstmt1 = connection.prepareStatement("SELECT * FROM users LEFT JOIN userprofiles on users.user_id = userprofiles.user_id WHERE username = ? and password = ?");
                String u = uName;
                String pw = uPass;
                System.out.println(u);
                System.out.println(pw);
                pstmt1.setString(1, u);
                pstmt1.setString(2, pw);
                System.out.println("***");
                ResultSet rs1 = pstmt1.executeQuery();
             if(rs1.next()) {
                 String username = rs1.getString("username");
                 String email = rs1.getString("email");
                 String password = rs1.getString("password");
                 String firstname = rs1.getString("firstname");
                 String lastname = rs1.getString("lastname");
                 String interest = rs1.getString("interest");
                 Profile p1 = new Profile(interest, firstname, lastname, password, email, username );
//                 System.out.println(p.getInterest());
//                 System.out.println(p.getFirstname());
//                 System.out.println(p.getLastname());
//                 System.out.println(p.getPassword());
//                 System.out.println(p.getEmail());
//                System.out.println(p.getUsername());
                  ctx.json(p1);
                  ctx.status(200);
                   }else{
                   ctx.result("User not found.");
                   ctx.status(400);
                   }
                   });


    //UPDATE ENDPOINT.
        app.post("/profileupdate", (ctx) -> {
            System.out.println("Updated Endpoint Accessed");
            Connection connection = ConnectionFactory.createConnection();
            Profile p = ctx.bodyAsClass(Profile.class);
            PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE userprofiles\n" +
                        "SET firstname =  ? FROM users WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");
            PreparedStatement pstmt1 = connection.prepareStatement(
                    "UPDATE userprofiles\n" +
                            "SET lastname =  ? FROM users WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");
            PreparedStatement pstmt2 = connection.prepareStatement(
                    "UPDATE users\n" +
                            "SET email =  ? FROM userprofiles WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");

            pstmt.setString(1, p.getFirstname());
            pstmt.setString(2, p.getUsername());
            pstmt.setString(3, p.getPassword());

            pstmt1.setString(1, p.getLastname());
            pstmt1.setString(2, p.getUsername());
            pstmt1.setString(3, p.getPassword());

            pstmt2.setString(1, p.getEmail());
            pstmt2.setString(2, p.getUsername());
            pstmt2.setString(3, p.getPassword());

            int numberOfRecordsUpdated = pstmt.executeUpdate();
            int numberOfRecordsUpdated1 = pstmt1.executeUpdate();
            int numberOfRecordsUpdated2 = pstmt2.executeUpdate();

            if(numberOfRecordsUpdated < 1){
            ctx.status(400);
            ctx.result("Invalid username");
            }else {
            ctx.status(200);
            ctx.result(numberOfRecordsUpdated + " record(s) updated");
                   }
            });


        //DELETE ENDPOINT
        app.post("/profiledelete", (ctx) -> {
        System.out.println("Deleted Endpoint Accessed");
        Connection connection = ConnectionFactory.createConnection();
        Profile p = ctx.bodyAsClass(Profile.class);

        PreparedStatement pstmt = connection.prepareStatement("DELETE from userprofiles where password = ? and username = ? ");
        pstmt.setString(1, p.getPassword());
        pstmt.setString(2, p.getUsername());
        int numberOfRecordsUpdated = pstmt.executeUpdate();
        ctx.result(numberOfRecordsUpdated + " record(s) deleted.");
            if( numberOfRecordsUpdated < 1){
                ctx.status(400);
                ctx.json("Invalid information");
            }else {
                ctx.status(200);
                ctx.result(numberOfRecordsUpdated + " record(s) updated");
            }
            });

            }
    }
