let attempt = 3;

const baseURL = "http://localhost:8080";
const username = document.getElementById("username-input");
const password = document.getElementById("password-input");
const loginButton = document.getElementById("login-btn");

loginButton.addEventListener(`click`, (event) => {
    event.preventDefault();
    // console.log(username.value);
    // console.log(password.value);
    fetch(`${baseURL}/login`, {
        method: 'POST',
        body: `{"username": ${username.value}, "password": ${password.value}}`,
        credentials: "include"
    }).then((res) => {
        return res.json;
    }).then((responseBody) => {
        console.log(responseBody);
    })
});

// function validate() {
//     if (username == "john_doe" && password == "pass1234") {
//         window.location = "placeholder.html";
//         return false;
//     } else {
//         attempt--;
//         alert("Invalid login information. You have " + attempt + " attempts remaining;");
//         if (attempt == 0) {
//             username.disabled = true;
//             password.disabled = true;
//             loginButton.disabled = true;
//             return false;
//         }
//     }
// }



let rememberMeCheck;
let guestButton;


rememberMeCheck = document.getElementById("login-remember-me");
guestButton = document.getElementById("guest-login-btn");