fetch(`${baseUrl}/comments`, {
    method: 'GET',
    credentials: 'include'
}).then((res) => {
    return res.json();
}).then((data) => {
    
    for (data of data) {

    // Data from database
    const postId = data.postId;
    const commentMessage = data.commentMessage;
    const commentDate = data.commentDate;
    const userId = data.userId;

    const mainDiv = document.getElementById('display');
    console.log(mainDiv);
    const commentsDisplay = document.createElement('div');
    commentsDisplay.classList.add('container');
    mainDiv.appendChild(commentsDisplay);

    // avatar 
    const avatar = document.createElement('img');
    avatar.setAttribute('src', '/images/avatar-generic.jpg');
    avatar.setAttribute('alt', 'avatar placeholder img');
    commentsDisplay.appendChild(avatar);


    // Displaying the messages
    const messageDisplay = document.createElement('p');
    messageDisplay.innerHTML = `${commentMessage}`;
    commentsDisplay.appendChild(messageDisplay);

    // Displaying the time
    const timeDisplay = document.createElement('span');
    timeDisplay.classList.add('time-right');
    timeDisplay.innerHTML = `${commentDate}`;
    commentsDisplay.appendChild(timeDisplay);

    };
});


   
    
    
    
    