
fetch(`${baseUrl}/posts`, {
    method: 'GET',
    credentials: 'include',
    headers: {
        'Content-Type': 'application/json'
      },
    }).then((res) => res.json()).then((res) => {
    console.log(res);

    for (res of res){

        const postId = res.id;
        const postImage = res.postImage;
        const postTitle = res.postTitle;
        const postDescription = res.postDescription;
        const userId = res.userId;


        // create a HTML table
        const table = document.createElement('table');
        const tableBody = document.createElement('tbody');
        const imageContainer = document.createElement('tr');
        const titleContainer = document.createElement('tr');
        const descriptionContainer = document.createElement('tr');
        const groupContainer = document.createElement('tr');
        const td1 = document.createElement('td');
        const td2 = document.createElement('td');
        const td3 = document.createElement('td');
        const commentsContainer = document.createElement('tr');


        // append container to display the table on the frontend
        const homeContainer = document.getElementById('container');
        homeContainer.appendChild(table);
        table.appendChild(tableBody);
                 if (postImage != null) {
                    tableBody.appendChild(imageContainer);
                 }
        tableBody.appendChild(titleContainer);
        tableBody.appendChild(descriptionContainer);
        tableBody.appendChild(groupContainer);
        tableBody.appendChild(commentsContainer);
        groupContainer.appendChild(td1);
        groupContainer.appendChild(td2);
        groupContainer.appendChild(td3);


        // create elements needed
        const imagePost = document.createElement('img');
        const titlePost = document.createElement('h5');
        const descriptionPost = document.createElement('p');
        const commentsCount = document.createElement('p');

        // add html elements inside of the containers
        imageContainer.appendChild(imagePost);
        titleContainer.appendChild(titlePost);
        descriptionContainer.appendChild(descriptionPost);
        td1.appendChild(commentsCount);


        // assign IDs to specific elements
        table.setAttribute('id', `table-${postId}`);
        commentsContainer.setAttribute('id', `comment-container-${postId}`)
        commentsContainer.classList.add('comments-container');


        // assign CLASSES to specific elements
        imageContainer.classList.add('image-container');
        titleContainer.classList.add('title-container');
        descriptionContainer.classList.add('description-container');
        td1.classList.add('group-container');


        // Add response data to the webpage
        imagePost.setAttribute(`src`, `/MicroBlaug/webApp${postImage}`);
        titlePost.innerHTML = `${postTitle}`;
        descriptionPost.innerHTML = `${postDescription}`;

            fetch(`${baseUrl}/user/comments/${postId}`, {
                method: 'GET',
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json'
                  },
                }).then((res2) => res2.json()).then((res2) => {
                console.log(res2);


                const writeAComment = document.createElement('div');
                commentsContainer.appendChild(writeAComment);

                writeAComment.classList.add('comment-inline');

                // write a comment functionality here -- Label
                const label =  document.createElement('label');
                label.setAttribute('for', 'comment');
                writeAComment.appendChild(label);
                label.innerHTML = 'Write a Comment:';

                 // Comment Input
                const commentInput =  document.createElement('input');
                commentInput.setAttribute('id', `write-comment-${postId}`);
                commentInput.setAttribute('type', 'text')
                // commentInput.setAttribute('placeholder', 'Enter here...');
                commentInput.setAttribute('name', 'comment');
                writeAComment.appendChild(commentInput);

                // Comment Button
                const saveCommentButton =  document.createElement('button');
                saveCommentButton.setAttribute('id', `save-comment-${postId}`);
                saveCommentButton.setAttribute('type', 'submit');
                saveCommentButton.innerHTML = 'Save';
                writeAComment.appendChild(saveCommentButton);


                saveCommentButton.addEventListener('click', () => {
                    console.log(commentInput.value);

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

                            alert('Saved successfully');
                            document.location.reload();
                            window.scrollTo(0,500);

                        } else {
                            alert('You must sign up to create comments');
                        }
                    });

                });


                for (responses of res2 ){

                // create HTML for Comments Area
                const viewComments = document.createElement('div');
                const viewComments1 = document.createElement('div');
                const viewComments2 = document.createElement('div');
                const viewComments3 = document.createElement('div');
                const viewComments4 = document.createElement('div');
                const viewComments5 = document.createElement('div');
                const viewComments6 = document.createElement('div');

                // Append to Comments Container

                commentsContainer.appendChild(viewComments);


                viewComments.appendChild(viewComments1);
                viewComments.appendChild(viewComments2);
                viewComments.appendChild(viewComments6);
                viewComments.appendChild(viewComments3);
                viewComments.appendChild(viewComments4);
                viewComments.appendChild(viewComments5);

                // assign IDs

                // assign classes
                viewComments.classList.add('row');
                viewComments1.classList.add('column', 'col-1');
                viewComments2.classList.add('column', 'col-2');
                viewComments3.classList.add('column', 'col-3');
                viewComments4.classList.add('column', 'col-4');
                viewComments5.classList.add('column', 'col-5');
                viewComments6.classList.add('column', 'col-6');

                // Define data received in the response body
                const commentId = responses.commentId;
                const commentMessage = responses.commentMessage;
                const commentDate = responses.commentDate;
                const email = responses.email;
                const postId = responses.postId;
                const userId = responses.userId;
                const picture = responses.avatar;
                const firstName = responses.username;
                const date = new Date(commentDate);
                const convertedDate = date.toLocaleDateString();

                commentsCount.innerHTML = `${res2.length} Comments`;

                // Avatar
                const avatar = document.createElement('img');
                avatar.setAttribute('src', `/MicroBlaug/webApp/${picture}`);
                avatar.setAttribute('alt', 'avatar placeholder img');
                avatar.classList.add('avatar');

                const pTag = document.createElement('p');
                pTag.classList.add('user-id-displayed');
                pTag.innerHTML = `${firstName}`;

                // comments
                const comments = document.createElement('input');
                comments.classList.add('comments');
                comments.setAttribute('id', `update-comment-${postId}`);
                comments.setAttribute('disabled', 'true');
                comments.setAttribute('placeholder', `${commentMessage}`);

// UPDATE button (hidden initially) *****************************************************

                const updateButton = document.createElement('a');
                updateButton.setAttribute('id', `update-icon-${postId}`) ;
                const updateIcon = document.createElement('img');
                updateIcon.setAttribute('src', '/MicroBlaug/webApp/images/publish-icon.jpg');
                updateIcon.setAttribute('alt', 'publishing icon image');
                updateIcon.classList.add('icon-comments');
                updateButton.appendChild(updateIcon);
                updateButton.style.display = 'none';

                updateButton.addEventListener('click', () => {
                    //console.log(comments.value + postId + userId);

                    fetch(`${baseUrl}/comment`, {
                        method: 'PUT',
                        body: `{
                            "commentId": "${commentId}",
                            "commentMessage":"${comments.value}",
                            "postId": "${postId}",
                            "userId": "${userId}"
                        }`,
                        credentials: 'include'
                    }).then((res) => {
                        if ((res.status === 201) && (comments.value != "")) {
                            document.location.reload();
                            alert('Updated successfully');
                        } else if (comments.value == "") {
                            alert('Message field can not be blank');
                        } else {
                            alert('You must sign up to create comments');
                        }
                    });
                });


 // EDIT button     *********************************************************************

                const editCommentButton = document.createElement('a');
                editCommentButton.classList.add('edit-button');
                editCommentButton.setAttribute('id', `edit-icon-${postId}`);
                const editImage = document.createElement('img');
                const dateForComment = document.createElement('p');
                dateForComment.innerHTML = `${convertedDate}`;
                editImage.setAttribute('src', '/MicroBlaug/webApp/images/update.jpg');
                editImage.setAttribute('alt','icon of a publish symbol');
                editImage.classList.add('icon-comments');



                // CANCEL BUTTON -- cancel update


                const cancelButton = document.createElement('a');
                cancelButton.setAttribute('id', `cancel-icon-${postId}`);
                const cancelIcon = document.createElement('img');
                cancelIcon.setAttribute('src', '/MicroBlaug/webApp/images/cancel.jpg');
                cancelIcon.setAttribute('alt', 'publishing icon image');
                cancelIcon.classList.add('icon-comments');
                cancelButton.appendChild(cancelIcon);
                cancelButton.style.display = 'none';

                cancelButton.addEventListener('click', function() {
                    comments.setAttribute('disabled', 'false');
                    comments.classList.remove('comments-update');
                    cancelButton.style.display = 'none';
                    updateButton.style.display = 'none';
                });


                editCommentButton.addEventListener('click', () => {
                    comments.removeAttribute('disabled', 'false');
                    comments.classList.add('comments-update');
                    updateButton.style.display = 'inline-block';
                    cancelButton.style.display = 'inline-block';
                });


  // DELETE button   *******************************************************************

                const deleteButton = document.createElement('a');
                deleteButton.setAttribute('id', `trash-icon-${postId}`)
                const deleteImage = document.createElement('img');
                deleteImage.setAttribute('src', '/MicroBlaug/webApp/images/delete-icon.jpg');
                deleteImage.setAttribute('alt', 'trash can icon image');
                deleteImage.classList.add('icon-comments');



                deleteButton.addEventListener('click', () => {
                    console.log(commentId)
                    fetch(`${baseUrl}/comment/${commentId}`, {
                        method: 'DELETE',
                        credentials: 'include'
                    }).then((res) => {
                        if (res.status === 204) {

                            alert('Deleted successfully');
                            //document.location.reload();


                        } else {
                            alert('You must sign up to create comments');
                        }
                    });
                });

                // append to containers defined already
                viewComments1.appendChild(avatar);
                viewComments1.appendChild(pTag);
                viewComments2.appendChild(comments);
                viewComments2.appendChild(dateForComment);
                viewComments6.appendChild(cancelButton);
                viewComments3.appendChild(updateButton);
                viewComments4.appendChild(editCommentButton);
                editCommentButton.appendChild(editImage);
                deleteButton.appendChild(deleteImage);
                viewComments5.appendChild(deleteButton);
                }
                });
    }
});

//function refreshPage() {
//                var page_y = document.getElementsByTagName("body")[0].scrollTop;
//                window.location.href = window.location.href.split('?')[0] + '?page_y=' + page_y;
//            }
//            window.onload = function () {
//                setTimeout(refreshPage, 35000);
//                if ( window.location.href.indexOf('page_y') != -1 ) {
//                    var match = window.location.href.split('?')[1].split("&")[0].split("=");
//                    document.getElementsByTagName("body")[0].scrollTop = match[1];
//                }
//            }