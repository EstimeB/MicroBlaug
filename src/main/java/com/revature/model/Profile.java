package com.revature.model;
//represents data as objects from user and db.
public class Profile {

    private String interest;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String username;

    private String newPassword;
    public Profile() {

    }

    public Profile(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public Profile(String interest, String firstname, String lastname, String password, String email, String username, String newPassword) {

        this.interest = interest;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.username = username;
        this.newPassword = newPassword;
    }

    public Profile(String interest, String firstname, String lastname, String password, String email, String username) {
        this.interest = interest;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    };

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
