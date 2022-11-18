package com.revature.controller;

import com.revature.dto.LoginCredentials;
import com.revature.dto.Message;
import com.revature.model.Profile;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;
import io.javalin.Javalin;
import org.eclipse.jetty.security.UserAuthentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileController implements Controller{

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

        //VIEW PROFILE Endpoint (Getting data from two different tables in DB)
        app.post("/profileview", (ctx) -> {
                System.out.println("Query Endpoint Accessed");
                Connection connection = ConnectionFactory.createConnection();
                LoginCredentials credentials = ctx.bodyAsClass(LoginCredentials.class);
                PreparedStatement pstmt1 = connection.prepareStatement("SELECT * FROM userprofiles WHERE username = ? and password = ?");
                pstmt1.setString(1, credentials.getUsername());
                pstmt1.setString(2, credentials.getPassword());
                ResultSet rs1 = pstmt1.executeQuery();
                System.out.println("***");
             if(rs1.next()) {
                 String username = rs1.getString("username");
                 String email = rs1.getString("email");
                 String password = rs1.getString("password");
                 String firstname = rs1.getString("firstname");
                 String lastname = rs1.getString("lastname");
                 String interest = rs1.getString("interest");
                 Profile p = new Profile(interest, firstname, lastname, password, email, username );
                 System.out.println(p.getInterest());
                 System.out.println(p.getFirstname());
                 System.out.println(p.getLastname());
                 System.out.println(p.getPassword());
                 System.out.println(p.getEmail());
                 System.out.println(p.getUsername());
                 ctx.json(p);
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
            String username = p.getUsername();
            String password = p.getPassword();
            PreparedStatement pstmt = connection.prepareStatement(
                "Update userprofiles set interest = ? , firstname =  ? , lastname = ? , username = ? , password = ?, email = ? where username = ?");
            pstmt.setString(1, p.getInterest());
            pstmt.setString(2, p.getFirstname());
            pstmt.setString(3, p.getLastname());
            pstmt.setString(4, p.getUsername());
            pstmt.setString(5, p.getPassword());
            pstmt.setString(6, p.getEmail());
            pstmt.setString(7, p.getUsername());
            int numberOfRecordsUpdated = pstmt.executeUpdate();
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
