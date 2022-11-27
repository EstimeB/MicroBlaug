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
        // setItem that mark a user is logged in, will be retrieved to hide/display nav links
        window.sessionStorage.setItem("user", "loggedIn");
        
        window.location.href = 'home.html';
      }else{
        alert('Invalid Login Information');
      }
    })
});

// let attempt = 3;
// function validate() {
//     if (username == "${username.value}" && password == "${password.value}") {
//         window.location = "dashboard.html";
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

const signupUsername = document.getElementById("signup-username");
const signupEmail = document.getElementById("signup-email");
const SignupPassword = document.getElementById("signup-password");
const SignupPasswordConf = document.getElementById("signup-repeat-password");

//check to see if the SignupPassword and SignupPasswordConf are the same
const signupButton = document.getElementById("signup-submit-btn");

signupButton.addEventListener("click", (event) => {
  event.preventDefault();

  fetch(`http://localhost:8080/signup`, {
    method: "POST",
    body: `{"username":"${signupUsername.value}", "email":"${signupEmail.value}", "password":"${SignupPassword.value}"}`,
    credentials: "include"
  })
    .then((res) => {
      if (res.status === 201) {
        window.location.href = '/MicroBlaug/src/main/webApp/html/profile.html';
      }else{
        alert('Invalid Signup Information');
      }
    })
});

let rememberMeCheck;

rememberMeCheck = document.getElementById("login-remember-me");
