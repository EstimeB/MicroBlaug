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
navLinksDiv.setAttribute('id', 'navbar-right');
topLevelDiv.appendChild(navLinksDiv);


// Nav Links
const link1 = document.createElement('a');
link1.classList.add('active');
link1.setAttribute('href', '/MicroBlaug/src/main/webApp/html/home.html');
navLinksDiv.appendChild(link1);
link1.innerHTML = 'Home';

const link5 = document.createElement('a');
navLinksDiv.appendChild(link5);
const avatarImg = document.createElement('img');
avatarImg.setAttribute('src', '/images/avatar-generic.jpg');
link5.setAttribute('href', '/MicroBlaug/src/main/webApp/html/login-signup.html');
link5.appendChild(avatarImg);
link5.innerHTML = 'Login';

//retrieve session item set when a user is logged in
const sessonId = window.sessionStorage.getItem("user");

// when a user is logged in a session item is set, that means a user is logged in
// will hide/display links if item was truly set/is found
if (sessonId === 'loggedIn') {
    link5.style.display = 'none';
    const link2 = document.createElement('a');
    link2.setAttribute('href', '/MicroBlaug/src/main/webApp/html/post/dashboard.html');
    navLinksDiv.appendChild(link2);
    link2.innerHTML = 'Dashboard';

    const link3 = document.createElement('a');
    link3.setAttribute('href', '/MicroBlaug/src/main/webApp/html/profile.html');
    navLinksDiv.appendChild(link3);
    link3.innerHTML = 'Profile';

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
                    //will clear the session storage
                    sessionStorage.clear();
                    window.location.href = '/MicroBlaug/src/main/webApp/html/login-signup.html';
                }else{
                    alert('failed to logout');
                }
            })
    });

}