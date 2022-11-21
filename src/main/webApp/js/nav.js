// Logo - Header div
const navbarIdonHtmlPage = document.getElementById('navbar');
const topLevelDiv = document.createElement('div');
topLevelDiv.classList.add('header');
topLevelDiv.setAttribute('id', 'nav-container');
navbarIdonHtmlPage.appendChild(topLevelDiv);

// project logo image in Navigation
const projectName = document.createElement('span');
const logoImage = document.createElement('img');
logoImage.setAttribute('src', '/MicroBlaug/src/main/webApp/images/micro-blue-logo-2000-500.png');
logoImage.setAttribute('id', 'logo-image');
logoImage.setAttribute('alt', 'Microblaug logo-image');
projectName.appendChild(logoImage);
topLevelDiv.appendChild(projectName);

// // Navbar Links Div
const navLinksDiv = document.createElement('div');
navLinksDiv.setAttribute('id', 'menu-links-div');
navLinksDiv.classList.add('header-right');
topLevelDiv.appendChild(navLinksDiv);

// // Navbar Links
const link_1 = document.createElement('a');
link_1.setAttribute('href', '/MicroBlaug/src/main/webApp/html/home.html');
navLinksDiv.appendChild(link_1 );
link_1 .innerHTML = 'Home';

const link_2 = document.createElement('a');
link_2.setAttribute('href', '/MicroBlaug/src/main/webApp/html/post/dashboard.html');
navLinksDiv.appendChild(link_2);
link_2.innerHTML = 'Dashboard';

const link_3 = document.createElement('a');
link_3.setAttribute('href', '/MicroBlaug/src/main/webApp/html/profile.html');
link_3.innerHTML = 'Profile';
navLinksDiv.appendChild(link_3);

const link4 = document.createElement('a');
link4.setAttribute('href', '/MicroBlaug/src/main/webApp/html/login-signup.html');
navLinksDiv.appendChild(link4);
link4.innerHTML = 'Logout';
link4.addEventListener("click", (event) => {
  event.preventDefault();
  fetch(`http://localhost:8080/logout`, {
    method: "POST",
    credentials: "include",
  })
    .then((res) => {
      if (res.status === 200) {
        window.location.href = '/MicroBlaug/src/main/webApp/html/login-signup.html';
      }else{
        alert('failed to logout');
      }
    })
});

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
