let attempt = 3;

function validate() {
    let username = document.getElementById("username-input");
    let password = document.getElementById("password-input");
    let loginButton = document.getElementById("login-btn");

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
let rememberMeCheck;
let guestButton;


rememberMeCheck = document.getElementById("login-remember-me");
guestButton = document.getElementById("guest-login-btn");

// class Login {
//     constructor(form, field) {
//         this.form = form;
//         this.fields = fields;
//         this.validateOnSubmit();
//     }
//     validateOnSubmit() {
//         let self = this;

//         this.form.addEventListener("submit", (e) => {
//             e.preventDefault();
//             self.fields.forEach((field) => {
//                 const input = document.querrySelector('#$(field)');
//                 console.log(input.value);
//             })
//         })
//     }
// }

// const form = document.querySelector(".login-input")
// if (form) {
//     const fields = ["username", "password"];
//     const validator = new Login(form, fields);
// }

// // document.addEventListener("DOMContentLoaded", () => {
// //     const loginForm = document.querySelector("#login");
// //     const signUpForm = document.querySelector("#signUp")

// // })
