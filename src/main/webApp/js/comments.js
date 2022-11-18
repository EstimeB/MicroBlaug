// Comment overall div
const mainDiv = document.getElementById('display');
const writeComment = document.createElement('div');
writeComment.setAttribute('id', 'writeComment');
writeComment.classList.add('inline');
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
commentInput.setAttribute('class', 'new-comment');
commentInput.setAttribute('placeholder', 'Enter here...');
commentInput.setAttribute('name', 'comment');
writeComment.appendChild(commentInput);

// Comment Button
const saveCommentButton =  document.createElement('button');
saveCommentButton.setAttribute('id', 'saveCommentButton')
saveCommentButton.setAttribute('type', 'submit');
writeComment.appendChild(saveCommentButton);
saveCommentButton.innerHTML = 'Save';

// close comments section (return to home page)
const spanClose = document.createElement('span');
spanClose.classList.add('time-right');
spanClose.innerHTML = 'close';
const closeButton = document.createElement('button');
closeButton.setAttribute('id', 'close-button')
const closeImage = document.createElement('img');
closeImage.setAttribute('src', '../images/close.jpg');
writeComment.appendChild(spanClose);
spanClose.appendChild(closeButton);
closeButton.appendChild(closeImage);

spanClose.addEventListener('click', () => {
    window.location.href='/home.html';
});


fetch(`${baseUrl}/comments`, {
    method: 'GET',
    credentials: 'include'
}).then((res) => {
    return res.json();
}).then((data) => {

    for (data of data) {

    // Data from database
    var postId = data.postId;
    const commentMessage = data.commentMessage;
    const commentDate = data.commentDate;
    var userId = data.userId;
    var commentId = data.commentId;

    // container div displaying messages received
    const commentsDisplay = document.createElement('div');
    commentsDisplay.classList.add('container');
    mainDiv.appendChild(commentsDisplay);

    // avatar
    const avatar = document.createElement('img');
    avatar.setAttribute('src', '../images/avatar-generic.jpg');
    avatar.setAttribute('alt', 'avatar placeholder img');
    commentsDisplay.appendChild(avatar);

    // Displaying the comments
    const commentDisplay = document.createElement('input');
    commentDisplay.setAttribute('class', 'input-comment');
    commentDisplay.setAttribute('id', `comment-${commentId}`);
    commentDisplay.setAttribute('disabled', 'true');
    commentDisplay.setAttribute('placeholder', `${commentMessage}`);
    commentDisplay.style.border = 'none';
    commentDisplay.style.width = '400px';
    commentsDisplay.appendChild(commentDisplay);

    // hidden update comments button (revealed when user updates field)
    const updateButton = document.createElement('button');
    updateButton.setAttribute('class', 'update-button');
    updateButton.innerHTML = "Update";
    commentsDisplay.appendChild(updateButton);
    updateButton.style.display = 'none';

    updateButton.addEventListener('click', () => {
        //console.log(commentDisplay.value + postId + userId);

        fetch(`${baseUrl}/comment`, {
            method: 'PUT',
            body: `{
                "commentId": "${commentId}",
                "commentMessage":"${commentDisplay.value}",
                "postId": "${postId}",
                "userId": "${userId}"
            }`,
            credentials: 'include'
        }).then((res) => {
            if (res.status === 201) {
                document.location.reload();
                alert('Updated successfully');

            } else {
                alert('Could not update. Contact Admin.');
            }
        });
    });

    // Displaying the time
    const timeDisplay = document.createElement('span');
    timeDisplay.classList.add('time-right');
    timeDisplay.innerHTML = `${commentDate}`;
    commentsDisplay.appendChild(timeDisplay);

    // delete comments button
    const spanDelete = document.createElement('span');
    spanDelete.classList.add('time-right');
    const deleteButton = document.createElement('button');
    deleteButton.setAttribute('id', 'delete-button')
    const deleteImage = document.createElement('img');
    deleteImage.setAttribute('src', '../images/delete-icon.jpg');
    commentsDisplay.appendChild(spanDelete);
    spanDelete.appendChild(deleteButton);
    deleteButton.appendChild(deleteImage);

    deleteButton.addEventListener('click', () => {
        console.log(commentId)
        fetch(`${baseUrl}/comment/${commentId}`, {
            method: 'DELETE',
            credentials: 'include'
        }).then((res) => {
            if (res.status === 200) {
                document.location.reload();
                alert('Deleted successfully');

            } else {
                alert('Comment was not deleted. Contact Admin.');
            }
        });
    });

    // edit comments button
    const spanEdit = document.createElement('span');
    spanEdit.classList.add('time-right');
    var editCommentButton = document.createElement('button');
    editCommentButton.setAttribute('id','edit-button');
    editCommentButton.setAttribute('data-id', `${commentId}`);
    const editImage = document.createElement('img');
    editImage.setAttribute('src', '/images/update.jpg');
    commentsDisplay.appendChild(spanEdit);
    spanEdit.appendChild(editCommentButton);
    editCommentButton.appendChild(editImage);

    editCommentButton.addEventListener('click', () => {
        commentDisplay.removeAttribute('disabled', 'false');
        commentDisplay.style.borderWidth = '1px';
        commentDisplay.style.borderColor = 'rgb(22, 165, 36)';
        updateButton.style.display = 'inline-block';
    });

    };

    saveCommentButton.addEventListener('click', () => {
        //console.log(commentInput.value);

        fetch(`${baseUrl}/comment`, {
            method: 'POST',
            body: `{
                "commentMessage":"${commentInput.value}",
                "postId": "${postId}",
                "userId": "${userId}"
            }`,
            credentials: 'include'
        }).then((res) => {
            if (res.status === 201) {
                document.location.reload();
                alert('Saved successfully');

            } else {
                alert('Comment was not saved. Contact Admin.');
            }
        });
    });

});




   
    
    
    
    