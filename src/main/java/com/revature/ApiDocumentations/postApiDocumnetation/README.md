# MicroBlaug Application

# Post API Documentation

## Post Feature

`GET /posts`

Used to get and display all posts from the database, from all users.

### Response
```json
[
  {
    "id": 0,
    "postTitle": "monday21",
    "postDescription": "tHERE WIll be no CQ today!!! ...",
    "userId": 103,
    "postDateCreated": [2022,11,18]
  },
  {
    "id": 18,
    "postTitle": "Monday's title",
    "postDescription": "Monday's description",
    "userId": 60,
    "postDateCreated": [2022,11,21]
  },
  {
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple: Jedi time tricks.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
  }
]
```
Status Codes

|Status Code|Description |
|:----------|:------------|
|200 OK|Successfully retrieved posts|

`GET /dashboard/userPosts`

Used for user to a list of their posts. The server will get the previously generated HTTP Session that contains the user credentials to determine if a user is logged in and identify who the user is.
### Request
Headers

|Key |Value |Description |
|:---|:----|:------------|
|Cookie |`JSESSIONID=<session id>`|Used to identify which Http Session the client is associated with|

### Response
Response Body (if not logged in)
```json
{
  "message": "Not logged in!"
}
```
Response Body (if successful)
```json
[
  {
    "id": 19,
    "postTitle": "Post Feature",
    "postDescription": "Things are now working as they should.",
    "userId": 46,
    "postDateCreated": [2022,11,21]
  },
  {
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple: Jedi time tricks.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
  }
]
```
Status Codes

|Status Code |Description |
|:-----------|:-----------|
|200 OK|Successfully retrieved user posts|
|401 Unauthorized|User is not logged|

`POST /dashboard/createPost`

Allows a logged-in user to successfully create a post.

### Request
Headers

|Key |Value |Description |
|:---|:----|:------------|
|Cookie |`JSESSIONID=<session id>`|Used to identify which Http Session the client is associated with|

Request Body
```json
{
  "postTitle":"How to master your time",
  "postDescription":"The secret to time management is simple: Jedi time tricks."
}
```
### Response
Response Body (if not logged in)
```json
{
  "message": "Not logged in!"
}
```
Response Body (with no title)

`You Must Have a Post Title!`

Response Body (with no description)

`You Must Have a Post Description!`

Response Body (if successful)
```json
{
    "message": "Your Post Has Successfully Been Created!"
}
```
Newly Created Post
```json
{
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple: Jedi time tricks.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
}
```
Status Codes

|Status Code |Description |
|:----------|:------------|
|201 Created|Your Post Has Successfully Been Created|
|401 Unauthorized|User is not logged in|
|400 Bad Request|You Must Have a Post Title/You Must Have a Post Description|


`GET /dashboard/post/{id}`

Used to select individual Post.

### Request
`{{baseUrl}}/dashboard/post/41`

### Response
Response Body (if value passed in is NaN)

`Id a must be a valid integer!`

Response Body
```json
{
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple: Jedi time tricks.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
}
```
Status Codes

|Status Code |Description |
|:-----------|:-----------|
|200 OK|Successfully retrieved post|
|400 Bad Request|Id a must be a valid integer!|
|404 Not Found|Post with id 41 was not found|

`PUT /dashboard/updatePost`

Used by logged-in users to update their posts.
### Request
Headers

|Key |Value |Description |
|:---|:----|:------------|
|Cookie |`JSESSIONID=<session id>`|Used to identify which Http Session the client is associated with|

Request Body
```json
{
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
}
```
### Response
Response Body (if not logged in)
```json
{
  "message": "Not logged in!"
}
```
Response Body (with no title)

`You Must Have a Post Title!`

Response Body (with no description)

`You Must Have a Post Description!`

Response Body (if successful)
```json
{
    "message": "Your Post Has Successfully Been Updated!"
}
```
Newly Updated Post
```json
{
    "id": 41,
    "postTitle": "How to master your time",
    "postDescription": "The secret to time management is simple.",
    "userId": 46,
    "postDateCreated": [2022,11,23]
}
```
Status Codes

|Status Code |Description |
|:----------|:------------|
|201 Created|Your Post Has Successfully Been Updated|
|401 Unauthorized|User is not logged in|
|400 Bad Request|You Must Have a Post Title/You Must Have a Post Description|

`DELETE /dashboard/deletePost/{id}`

Used by logged-in users to deleted their posts.
### Request
Headers

|Key |Value |Description |
|:---|:----|:------------|
|Cookie |`JSESSIONID=<session id>`|Used to identify which Http Session the client is associated with|


`{{baseUrl}}/dashboard/deletePost/50`

### Response 
Response Body (if not logged in)
```json
{
  "message": "Not logged in!"
}
```
Response Body (if value passed in is NaN)

`Id a must be a valid integer!`

Response Body (id not found)

`Post with id 50 was not found!`

Response Body (if successful)

`Post with Id 50 has been deleted!`

Status Codes

|Status Code|Description|
|:---------|:-----------|
|200 OK|Post with Id 50 has been deleted|
|401 Unauthorized|User is not logged in|
|400 Bad Request|Id a must be a valid integer!|
|404 Not Found|Post with id 50 was not found|