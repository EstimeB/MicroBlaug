# MicroBlaug Application

## API Integration Test - Comments Feature

### `POST /login`

Note: Users have to logged in in order to create, update and delete comments. So we add the login and logout feature here to start.
### Request
Request Body
```json
{
  "username": "marypoppins",
  "password": "password1111"
}
```
### Response
#### Response Body (Success)
We expect to see results similar to what we see below.
```json
{
  "user_id": 103,
  "username": "marypoppins",
  "email": "android92@gmail.net",
  "password": "password1111"
}

```
#### Response Body (Fail)
User is not in the database.
```json
{
    "message": "Invalid login"
}
```
#### Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 OK | Success |
| 400 Bad Request | Failure |

---

### `GET /comments`

Note : All comments for Posts. User does not have to be logged in as all comments will be shown regardless.
### Request
Request Body (none)
### Response
#### Response Body (Success)
We expect to see results similar to what we see below.
```json
[{
        "commentId": 51,
        "commentMessage": "But is it worth it?",
        "commentDate": 1669276800000,
        "postId": 2,
        "userId": 4
    },
    {
        "commentId": 50,
        "commentMessage": "It is not so bad. They get paid a lot of money",
        "commentDate": 1669276800000,
        "postId": 2,
        "userId": 1
    }
]

```
#### Response Body (Fail)
```json

```
#### Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 OK | Success |
| 400 Bad Request | Failure |

---
### `POST /comment`

Note: Users must be logged in to write comments. We must provide the post-ID and the user-ID in the header of the Javascript so we know who is writing comments, and for what post in particular.
### Request
Request Body
```json
{
  "commentMessage": "what an interesting article",
  "postId": 1,
  "userId": 103
}
```
### Response
#### Response Body (Success)
Must be logged in.
```json
{
"message": "Comment successfully created"
}

```
#### Response Body (Fail)
Not logged in
```json
{
    "message": "Not logged in!"
}
```
#### Status Codes
| Status Code | Description |
| :---------- | :---------- |
| 200 OK | Success |
| 400 Bad Request | Failure |
| 404 Unauthorized | Failure |

---
### `DELETE /comment`

Users must be logged in to DELETE comments

#### Response Body (Fail)
Logged in
```json
{
    "message": "Comment successfully deleted"
}
```
Not logged in
```json
{
    "message": "Not logged in!"
}
```
#### Status Codes
| Status Code      | Description |
|:-----------------| :---------- |
| 204 Not Content  | Success |
| 400 Bad Request  | Failure |
| 404 Unauthorized | Failure |

---
### `PUT /comment`

Users must be logged in to UPDATE comments
### Request
Request Body
```json
  {
  "commentId": 16,
  "commentMessage": "we are updating the comment",
  "postId": 1,
  "userId": 103
}
```
### Response
#### Response Body (Fail)
Logged in
```json
{
    "message": "Comment successfully updated"
}
```
Not logged in
```json
{
    "message": "Not logged in!"
}
```
#### Status Codes
| Status Code      | Description |
|:-----------------| :---------- |
| 201 OK           | Success |
| 400 Bad Request  | Failure |
| 404 Unauthorized | Failure |