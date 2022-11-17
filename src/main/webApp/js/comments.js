// Comment overall div
const mainDiv = document.getElementById('display');
const writeComment = document.createElement('div');
writeComment.setAttribute('id', 'writeComment');
// writeComment.classList.add('form-inline');
writeComment.classList.add('container');
mainDiv.appendChild(writeComment);

// Comment Label
const label =  document.createElement('label');
label.setAttribute('for', 'comment');
writeComment.appendChild(label);
label.innerHTML = 'Write a Comment:';

// Comment Input
const commentInput =  document.createElement('input');
commentInput.setAttribute('type', 'text') 
commentInput.setAttribute('id', 'comment');
commentInput.setAttribute('placeholder', 'Enter here...');
commentInput.setAttribute('name', 'comment');
mainDiv.append(commentInput);

// Comment Button
const saveCommentButton =  document.createElement('button');
saveCommentButton.setAttribute('id', 'saveCommentButton')
saveCommentButton.setAttribute('type', 'submit');
mainDiv.append(label);
saveCommentButton.innerHTML = 'Save';



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

    // container div displaying messages received
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


   
    
    
    
    