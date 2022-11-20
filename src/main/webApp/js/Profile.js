//const baseUrl = 'http://127.0.0.1:8080'
const submitButtonElement = document.getElementById('submit');
const viewButtonElement = document.getElementById('vbtn');
const deleteButtonElement = document.getElementById('dbtn');

const firstnameinputElement = document.getElementById('fname');
const lastnameinputElement = document.getElementById('lname');
const usernameinputElement = document.getElementById('uname');
const passwordinputElement = document.getElementById('pword');
const emailinputElement = document.getElementById('email');
const interestinputElement = document.getElementById('interest');
const newPassWordinputElement = document.getElementById('p2word');

//Sending post request to profile view with username x and password x on page load
function myFunction(){
    fetch(`http://127.0.0.1:8080/profileview`,{
    method: 'POST',
    credentials: 'include',
    body: `{"username": "asdark1", "password":"yyy"}`
    //body: `{"username": "${username}", "password":"${password}"}`
}).then((res) => {
    return res.json();
}).then((responseBody) => {
    console.log(responseBody)
    const interest = responseBody.interest;
    const firstname = responseBody.firstname;
    const lastname = responseBody.lastname;
    const password = responseBody.password;
    const email = responseBody.email;
    const username = responseBody.username;
    const p1 = document.createElement('p');
    p1.innerHTML = ` Username : ${username}`;
    const p2 = document.createElement('p');
    p2.innerHTML = ` Password : ${password}`;
    const p3 = document.createElement('p');
    p3.innerHTML = ` Firstname : ${firstname}`;

    const p4 = document.createElement('p');
    p4.innerHTML = `Lastname : ${lastname}`;

    const p5 = document.createElement('p');
    p5.innerHTML = `Email : ${email}`;

    const p6 = document.createElement('p');
    p6.innerHTML = `Interest : ${interest}`;

         const userInfoDivElement = document.getElementById("userinfo");
            userInfoDivElement.appendChild(p1);
         userInfoDivElement.appendChild(p2);
            userInfoDivElement.appendChild(p3);
         userInfoDivElement.appendChild(p4);
          userInfoDivElement.appendChild(p5);
             userInfoDivElement.appendChild(p6);
             console.log(12);
            });
 
            }




var count = 1;
function showDiv() {
    
    count++;
    if(count % 2 == 0){
        document.getElementById('form').style.display = "block";
    }else{
        document.getElementById('form').style.display = "none";
    }
 }

//updateButtonElement.addEventListener('click', showDiv());




function updateProf(){ 
fetch(`http://127.0.0.1:8080/profileupdate`,{
         method: 'POST',
         credentials: 'include',
         body: `{"username": "${usernameinputElement.value}","email": "${emailinputElement.value}","interest": "${interestinputElement.value}","firstname":"${firstnameinputElement.value}", "lastname": "${lastnameinputElement.value}","password":"${passwordinputElement.value}", "newPassword": "${newPassWordinputElement.value}"}`
     }).then((res) => {
        if(res.status == 200){
            alert("Update Success!");
    
            window.location.reload();
         }
        
        }) 
        };
        
            function deleteProf(){ 
                    fetch(`http://127.0.0.1:8080/profiledelete`,{
                    method: 'POST',
                    credentials: 'include',
                    body: `{"username": "asdark1","password":"yyy"}`
                    //body: `{"username": "${username}", "password":"${password}"}`
                    }).then((res) => {
                    alert("Deleted!");
                    window.location.href = 'login-signup.html';
                    
                    }) 
                };

    

