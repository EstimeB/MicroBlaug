# MicroBlaug API Documentation

# Profile Feature Endpoints 


`POST/profileview`

Used to send requests to the backend for displaying user 
information on page load. This only occurs if the user is logged in and their credentials are stored 
as JSession IDs. On page load their "Username, Password, First Name, Last Name, Email and Interest" will be displayed in a paragraph tag. 
### Request
 
#### Body
```json
  {
    "username": "Test609",
    "password": "Test123",

  }
```

### Response
```json
  {
    "username": "Test609",
    "password": "Test123",
    "firstname": "Thomas",
    "lastname": "Jackson",
    "email": "Hackerrage@gmail.com",
    "interest": "Scuba Diving"
  }
```

`POST/profileupdate`

This is primarily used as an endpoint for the user to be able 
to update their information present in the database. Users can update 
5 fields. "Password", "Email", "Interest", "Username", "Firstname" and "Lastname".
If users put in invalid credentials for the password and username then they will 
receive an alert saying "Invalid Credentials".


### Request
```json
  {
    "username": "Test609",
    "password": "Test123",
    "firstname": "Halle",
    "lastname": "Berry",
    "email": "revstar@yahoo.com",
    "interest": "Dog Watching"
  }
```


### Response
Response Body (if not logged in)
```json
{
  "message": "Invalid Credentials!"
}
```
Response Body (if successful)
```json
  {
  "username": "Test609",
  "password": "Test123",
  "firstname": "Halle",
  "lastname": "Berry",
  "email": "revstar@yahoo.com",
  "interest": "Dog Watching"
}
```
Status Codes

| Status Code      | Description                                                                                                                                                   |
|:-----------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 200 OK           | The users was able to successfully update their information on the database, an alert will pop up saying "Update Success".                                    |
| 400 Unauthorized | Client side error, the user did not include valid credentials or left 1 or more fields blank. The browser will display an alert saying "Invalid Credentials". |

`GET/profiledelete`

After the user logs in,  and they navigate to the profile edit page, 
they will be able to delete their profile by clicking the "delete" button 
on the right hand side. The user will then be navigated back to the login/signup page immediately, and will
also be logged out of their session. 

### Request


Request Body
```json
{
  "username":"test609",
  "password":"test123"
}
```
### Response
Response Body 
```json
{
  "message": "Deleted!"
}
```

Status Codes

| Status Code | Description                                                                                                                                                                       |
|:------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 200 OK      | The users information is successfully deleted from the database. Their "Username, Password, First Name, Last Name, Interest and Email" are all gone, and they must sign up again. |


