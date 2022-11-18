package com.revature.controller;

import com.revature.dto.Message;
import com.revature.model.Profile;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfileController implements Controller{

    public void mapEndPoints( Javalin app){

        //InsertEndpoint
        //I want to insert values into the users profile table, but I cannot do that because I need a user_id value. I cannot
        //get the user ID value from the user so I cannot populate the user profiles table.
        app.post("/profilecreate",(ctx) -> {
            System.out.println("Create Endpoint Reached");
            Connection connection = ConnectionFactory.createConnection();
            Profile p = ctx.bodyAsClass(Profile.class);
            PreparedStatement pstmt = connection.prepareStatement("insert into userprofiles (interest, firstname , lastname) values (? , ? , ?)");
            pstmt.setString(1, p.getInterest());
            pstmt.setString(2, p.getFirstName());
            pstmt.setString(3, p.getLastName());
            int numberOfRecordsUpdated = pstmt.executeUpdate();
            ctx.result(numberOfRecordsUpdated + " record(s) updated");
        });

             //ViewCurrentEndpoint (Getting data from two different tables in DB)
            //If you try to view someone that does not exist, you will get a 400 error (This works for now!),
            app.post("/profileview", (ctx) -> {
            System.out.println("Query Endpoint Accessed");
            Connection connection = ConnectionFactory.createConnection();
            User u = ctx.bodyAsClass(User.class);
            PreparedStatement pstmt1 = connection.prepareStatement("SELECT * FROM users WHERE username = ? and password = ? ");
            pstmt1.setString(1, u.getUsername());
            pstmt1.setString(2, u.getPassword());
            ResultSet rs1 = pstmt1.executeQuery();
                //Getting password from user object
                // Getting username from user object

                String password = rs1.getString("password");
        if (rs1.next()) {
            String username = rs1.getString("username");
            String email = rs1.getString("email");
            //String password = rs1.getString("password");
           //Make sure constructor is having the appropriate values passed in for the appropriate variables.
            //ctx.json(new User(username, email, password));
            ctx.status(200);
        } else {
            ctx.status(400);
            ctx.result("Sorry, not found in our database.");
        }

        System.out.println("***");
        pstmt1.setInt(1, u.getUser_id());
        //This query is difficult
        PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM userprofiles join users on users.user_id = userprofiles.user_id WHERE user_id = ? ;");
                ///Something up is here, Join
                ResultSet rs2 = pstmt2.executeQuery();

                if (rs2.next()) {
                    String interest = rs2.getString("interest");
                    String firstname = rs2.getString("firstname");
                    String lastname = rs2.getString("lastname");
                    //Make sure constructor is having the appropriate values passed in for the appropriate variables.
                    ctx.json(new Profile(interest, firstname,lastname));
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
       // pstmt.setString(1, p.getUsername());
      //  pstmt.setString(2, p.getEmail());
        pstmt.setString(3, p.getInterest());
        pstmt.setString(4, p.getFirstName());
        pstmt.setString(5, p.getLastName());
       // pstmt.setString(6, p.getPassWord());
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
      //  pstmt.setString(1, p.getPassWord());
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
