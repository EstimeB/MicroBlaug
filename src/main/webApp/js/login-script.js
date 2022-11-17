//const baseUrl = "http://localhost:8080";

const username = document.getElementById("username");
const password = document.getElementById("password");

const loginButton = document.getElementById("login-btn");

loginButton.addEventListener("click", (event) => {
  event.preventDefault();

  fetch(`http://localhost:8080/login`, {
    method: "POST",
    body: `{"username":"${username.value}","password":"${password.value}"}`,
    credentials: "include",
  })
    .then((res) => {
      if (res.status === 200) {
        window.location.href = 'post/nav.html';
      }else{
        alert('Invalid Login Information');
      }
    })
});

 let attempt = 3;
 function validate() {
     if (username == "john_doe" && password == "pass1234") {
         window.location = "placeholder.html";
         return false;
     } else {
         attempt--;
         alert("Invalid login information. You have " + attempt + " attempts remaining;");
         if (attempt == 0) {
             username.disabled = true;
             password.disabled = true;
             loginButton.disabled = true;
             return false;
         }
     }
 }

const signupUsername = document.getElementById("signup-username");
const signupEmail = document.getElementById("signup-email");
const SignupPassword = document.getElementById("signup-password");
const SignupPasswordConf = document.getElementById("signup-repeat-password");

//check to see if the SignupPassword and SignupPasswordConf are the same
const signupButton = document.getElementById("signup-submit-btn");

signupButton.addEventListener("click", (event) => {
  event.preventDefault();

  fetch(`http://localhost:8080/login`, {
    method: "POST",
    body: `{"username":"${signupUsername.value}", "email":"${signupEmail.value}", "password":"${SignupPassword.value}"}`,
    credentials: "include",
  })
    .then((res) => {
      if (res.status === 200) {
        window.location.href = 'post/profile.html';
      }else{
        alert('Invalid Login Information');
      }
    })
});

let rememberMeCheck;
let guestButton;

rememberMeCheck = document.getElementById("login-remember-me");
guestButton = document.getElementById("guest-login-btn");
