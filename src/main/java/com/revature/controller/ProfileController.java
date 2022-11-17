package com.revature.controller;

import com.revature.dto.Message;
import com.revature.model.Profile;
import com.revature.util.ConnectionFactory;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileController implements Controller{

    public void mapEndPoints( Javalin app){


        //ViewEndpoint
        //If you try to view someone that does not exist, you will get a 400 error,
        app.post("/profileview", (ctx) -> {
        System.out.println("Query Endpoint Accessed");
        Connection connection = ConnectionFactory.createConnection();
        Profile p = ctx.bodyAsClass(Profile.class);
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE password = ?");
        pstmt.setString(1, p.getPassWord());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String userName = rs.getString("username");
            String passWord = rs.getString("password");
            String email = rs.getString("email");
            String interest = rs.getString("interest");
            //Make sure constructor is having the appropriate values passed in for the appropriate variables.
            ctx.json(new Profile(userName, email, passWord, interest, firstName, lastName));
            ctx.status(200);
        } else {
            ctx.status(400);
            ctx.result("Sorry, not found in our database.");
        }
    });

    //UPDATE ENDPOINT.
        app.post("/profileupdate", (ctx) -> {
            System.out.println("Updated Endpoint Accessed");
        Connection connection = ConnectionFactory.createConnection();
        Profile p = ctx.bodyAsClass(Profile.class);
        PreparedStatement pstmt = connection.prepareStatement(
                "Update users set username = ? , email = ? ,interest = ? ,firstname= ? , lastname= ? where password = ?;");
        pstmt.setString(1, p.getUsername());
        pstmt.setString(2, p.getEmail());
        pstmt.setString(3, p.getInterest());
        pstmt.setString(4, p.getFirstName());
        pstmt.setString(5, p.getLastName());
        pstmt.setString(6, p.getPassWord());
        int numberOfRecordsUpdated = pstmt.executeUpdate();
        if( numberOfRecordsUpdated < 1){
            ctx.status(400);
        }else {
            ctx.result(numberOfRecordsUpdated + "record(s) updated");
        }
    });


        //DELETE ENDPOINT
        app.post("/profiledelete", (ctx) -> {
        System.out.println("Deleted Endpoint Accessed");
        Connection connection = ConnectionFactory.createConnection();
        Profile p = ctx.bodyAsClass(Profile.class);
        PreparedStatement pstmt = connection.prepareStatement("DELETE from users where password= ?;");
        pstmt.setString(1, p.getPassWord());
        int numberOfRecordsUpdated = pstmt.executeUpdate();
        ctx.result(numberOfRecordsUpdated + " record(s) deleted.");
            if( numberOfRecordsUpdated < 1){
                ctx.status(400);
                ctx.json(new Message("Invalid Login"));
            }else {
                ctx.result(numberOfRecordsUpdated + "record(s) updated");
            }
    });

    }
}
