// Logo - Header div
const navbarIdonHtmlPage = document.getElementById('navbar');
const topLevelDiv = document.createElement('div');
topLevelDiv.classList.add('header');
topLevelDiv.setAttribute('id', 'nav-container');
navbarIdonHtmlPage.appendChild(topLevelDiv);

// project logo image in Navigation
const projectName = document.createElement('span');
const logoImage = document.createElement('img');
logoImage.setAttribute('src', '/images/micro-blue-logo-2000-500.png');
logoImage.setAttribute('id', 'logo-image');
logoImage.setAttribute('alt', 'Microblaug logo-image');
topLevelDiv.appendChild(projectName);
projectName.appendChild(logoImage);

// // Navbar Links Div
const navLinksDiv = document.createElement('div');
navLinksDiv.setAttribute('id', 'menu-links-div');
navLinksDiv.classList.add('header-right');
topLevelDiv.appendChild(navLinksDiv);

// // Navbar Links
const link_1 = document.createElement('a');
link_1.setAttribute('href', '/home.html');
navLinksDiv.appendChild(link_1 );
link_1 .innerHTML = 'Home';

const link_2 = document.createElement('a');
link_2.setAttribute('href', '/dashboard.html');
navLinksDiv.appendChild(link_2);
link_2.innerHTML = 'Dashboard';

const link_3 = document.createElement('a');
link_3.setAttribute('href', '/profile.html');
link_3.innerHTML = 'Profile';
navLinksDiv.appendChild(link_3);

const link_4 = document.createElement('a');
link_4.setAttribute('href', '/logout');
link_4.innerHTML = 'Log Out';
navLinksDiv.appendChild(link_4);
