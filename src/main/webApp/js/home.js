fetch(`${baseUrl}/posts`, {
    method: 'GET',
    credentials: 'include'
}).then((res) => {
    return res.json();
}).then((data) => {

    for (data of data) {

    // Data from database
    const postId = data.postId;
    const postTitle = data.postTitle;
    const postDescription = data.postDescription;
    const postImage = data.postImage;
    const postLikes = data.postLikes;
    const userId = data.userId;

    const postContent = document.getElementById('container');

    const mainDiv = document.createElement('div');
    mainDiv.classList.add('card');
    postContent.appendChild(mainDiv);

    // image 
    const image = document.createElement('img');
    image.classList.add("card-img-top");
    image.setAttribute(`src`, `${postImage}`);
    mainDiv.appendChild(image);


    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    cardBody.setAttribute('id', 'card-container')
    mainDiv.appendChild(cardBody);

    // post title
    const cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.innerHTML = `${postTitle}`;
    cardBody.appendChild(cardTitle);

    
    // post description
    const cardText = document.createElement('p');
    cardText.innerHTML = `${postDescription}`;
    cardBody.appendChild(cardText);

    const mainButtonDivs = document.createElement('div');
    mainButtonDivs.classList.add('buttons-group');
    cardBody.append(mainButtonDivs);


    // like button
    const likebutton = document.createElement('button');
    likebutton.classList.add('btn');
    likebutton.innerHTML = 'Like';
    mainButtonDivs.appendChild(likebutton);


    // comments button
    const commentsbutton = document.createElement('button');
    commentsbutton.classList.add('btn');
    commentsbutton.setAttribute('id', `comment-btn-${userId}`);
    commentsbutton.setAttribute('type', 'submit');
    commentsbutton.innerHTML = 'Comments';
    mainButtonDivs.appendChild(commentsbutton);
    commentsbutton.addEventListener('click', addComments);
    function addComments(){
        window.location.href="/MicroBlaug/src/main/webApp/html/display.html";
    }


    // share button
    const sharebutton = document.createElement('button');
    sharebutton.classList.add('btn');
    sharebutton.innerHTML = 'Share';
    mainButtonDivs.appendChild(sharebutton);

    };


});
