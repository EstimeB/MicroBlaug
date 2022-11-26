package com.revature.dao;

import com.revature.model.Profile;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.controller.UserAuthenticationController.uName;
import static com.revature.controller.UserAuthenticationController.uPass;

//Contains code that will interact and query db.
public class ProfileDao {


    public Profile viewInformation() throws SQLException, IOException {
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
        if (rs1.next()) {
            String username = rs1.getString("username");
            String email = rs1.getString("email");
            String password = rs1.getString("password");
            String firstname = rs1.getString("firstname");
            String lastname = rs1.getString("lastname");
            String interest = rs1.getString("interest");
            Profile p1 = new Profile(interest, firstname, lastname, password, email, username);
            return p1;
        }

        return null;
    }


    public int updatedInformation(Profile p) throws SQLException, IOException {
        System.out.println("Update Endpoint Accessed!");
        Connection connection = ConnectionFactory.createConnection();

        PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE userprofiles\n" +
                        "SET firstname =  ? FROM users WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");
        PreparedStatement pstmt1 = connection.prepareStatement(
                "UPDATE userprofiles\n" +
                        "SET lastname =  ? FROM users WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");
        PreparedStatement pstmt2 = connection.prepareStatement(
                "UPDATE users\n" +
                        "SET email =  ? FROM userprofiles WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");

        PreparedStatement pstmt3 = connection.prepareStatement(
                "UPDATE userprofiles\n" +
                        "SET interest =  ? FROM users WHERE users.user_id = userprofiles.user_id and username = ? and password = ? ;");

        pstmt.setString(1, p.getFirstname());
        pstmt.setString(2, p.getUsername());
        pstmt.setString(3, p.getPassword());

        pstmt1.setString(1, p.getLastname());
        pstmt1.setString(2, p.getUsername());
        pstmt1.setString(3, p.getPassword());

        pstmt2.setString(1, p.getEmail());
        pstmt2.setString(2, p.getUsername());
        pstmt2.setString(3, p.getPassword());

        pstmt3.setString(1, p.getInterest());
        pstmt3.setString(2, p.getUsername());
        pstmt3.setString(3, p.getPassword());

        int numberOfRecordsUpdated = pstmt.executeUpdate();
        int numberOfRecordsUpdated1 = pstmt1.executeUpdate();
        int numberOfRecordsUpdated2 = pstmt2.executeUpdate();
        int numberOfRecordsUpdated3 = pstmt3.executeUpdate();

        return numberOfRecordsUpdated;
    }

    public int deleteInformation(Profile p) throws SQLException, IOException {
        Connection connection = ConnectionFactory.createConnection();

        String u = uName;
        String pw = uPass;
        PreparedStatement pstmt = connection.prepareStatement("DELETE from users where username = ? and password = ?");
        pstmt.setString(1, u);
        pstmt.setString(2, pw);

        int numberOfRecordsUpdated = pstmt.executeUpdate();
        System.out.println("Executed!");

        return numberOfRecordsUpdated;
    }

}