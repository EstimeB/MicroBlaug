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

var link_4 = document.createElement('a');
link_4.setAttribute('id', 'loginLink');
link_4.setAttribute('href', '/MicroBlaug/src/main/webApp/html/login-signup.html');
link_4.innerHTML = 'Login/Signup';
navLinksDiv.appendChild(link_4);

var link_5 = document.createElement('a');
link_5.setAttribute('id', 'logoutLink');
link_5.style.display = 'none';
link_5.setAttribute('href', '/MicroBlaug/src/main/webApp/html/logout.html');
link_5.innerHTML = 'Log Out';
navLinksDiv.appendChild(link_5);
