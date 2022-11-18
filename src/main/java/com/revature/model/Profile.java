package com.revature.model;
//represents data as objects from user and db.
public class Profile {

    private String interest;
    private String firstName;
    private String lastName;

    public Profile() {
    }
//Step 4
    public Profile(String interest, String firstName, String lastName) {
        this.interest = interest;
        this.firstName = firstName;
        this.lastName = lastName;
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
