package com.revature.model;
//represents data as objects from user and db.
public class Profile {

    private String username;
    private String email;
    private String passWord;
    private String interest;
    private String firstName;
    private String lastName;

    public Profile() {
    }
//Step 4
    public Profile(String username, String email, String passWord, String interest, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.passWord = passWord;
        this.interest = interest;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
