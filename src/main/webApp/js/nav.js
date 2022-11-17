
// Logo
const logo = document.createElement('a');
logo.setAttribute('id', 'logo');
const navbar = document.getElementById('navbar');
logo.innerHTML = 'MicroBlaug';
navbar.append(logo);


// Navbar Links Div
const navLinksDiv = document.createElement('div');
navLinksDiv.setAttribute('id', 'navbar-right');
navbar.appendChild(navLinksDiv);


// Nav Links
const link1 = document.createElement('a');
link1.classList.add('active');
link1.setAttribute('href', '/MicroBlaug/src/main/webApp/html/home.html');
navLinksDiv.appendChild(link1);
link1.innerHTML = 'Home';

const link2 = document.createElement('a');
link2.setAttribute('href', '/MicroBlaug/src/main/webApp/html/post/dashboard.html');
navLinksDiv.appendChild(link2);
link2.innerHTML = 'Dashboard';

const link3 = document.createElement('a');
link3.setAttribute('href', '/MicroBlaug/src/main/webApp/html/profile.html');
navLinksDiv.appendChild(link3);
link3.innerHTML = 'Profile';

const link4 = document.createElement('a');
link4.setAttribute('href', '/MicroBlaug/src/main/webApp/html/logout.html');
navLinksDiv.appendChild(link4);
link4.innerHTML = 'Logout';

const link5 = document.createElement('a');
navLinksDiv.appendChild(link5);
const avatarImg = document.createElement('img');
avatarImg.setAttribute('src', '/images/avatar-generic.jpg');
link5.setAttribute('href', '/MicroBlaug/src/main/webApp/html/login-signup.html');
link5.appendChild(avatarImg);
link5.innerHTML = 'Login';



// When the user scrolls down 80px from the top of the document, resize the navbar's padding and the logo's font size
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
    document.getElementById("navbar").style.padding = "20px 10px";
    document.getElementById("logo").style.fontSize = "25px";
  } else {
    document.getElementById("navbar").style.padding = "30px 10px";
    document.getElementById("logo").style.fontSize = "35px";
  }
}