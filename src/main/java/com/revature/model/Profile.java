package com.revature.model;

public class Profile {
    private int user_id;
    private String username;
    private String email;
    private String password;

    private String interest;

    private String firstName;

    private String lastName;

    public Profile() {
    }

    public Profile(String username, String email, String password, String interest, String firstName, String lastName) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.interest = interest;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
