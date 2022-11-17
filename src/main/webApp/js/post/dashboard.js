// will allow to connect and communicate with the back end API
const baseUrl = 'http://localhost:8080';

//get el and store them in vars
const postForm = document.querySelector('#container');
const createNewPostBtn = document.querySelector('#cnBtn');
const postTitleInputEl = document.querySelector('#pTitle');
const postDescriptionEl = document.querySelector('#pDescription');
const submitCreatePostFormBtn = document.querySelector('#createPostBtn');
const upBtn = document.createElement('button');
const delBtn = document.createElement('button');

//will render signed-in user posts titles
async function getUserPosts() {
    await fetch(`${baseUrl}/userPosts`, {
        method: 'GET',
        credentials: 'include'
    }).then((res) => {
        return res.json();
    }).then((posts) => {
        const outerDiv = document.querySelector('#innerContainer');
        for (post of posts) {
            const innerDiv = document.createElement('div');
            const upImg = document.createElement('IMG');
            const delImg = document.createElement('IMG');
            const h3 = document.createElement('h3');
            const iconsDiv = document.createElement('div');
            upImg.setAttribute('src', '../../icons/icons8-update-30.png');
            delImg.setAttribute('src', '../../icons/icons8-trash-26.png');
            innerDiv.setAttribute('id', 'uPostDiv');
            h3.innerHTML = post.postTitle;
            innerDiv.appendChild(h3);
            upBtn.appendChild(upImg);
            delBtn.appendChild(delImg);
            iconsDiv.appendChild(upImg);
            iconsDiv.appendChild(delImg);
            innerDiv.appendChild(iconsDiv);
            outerDiv.appendChild(innerDiv);

            innerDiv.style.backgroundColor = 'lightgray';
            innerDiv.style.marginTop = '15px';
            innerDiv.style.border = 'solid';
            innerDiv.style.borderRadius = '3px';
            innerDiv.style.borderWidth = '1px';
            innerDiv.style.borderColor = 'gray';
            innerDiv.style.boxShadow = '5px 5px 5px rgb(151, 151, 143)';
            innerDiv.style.height = '100%';
            innerDiv.style.width = '100%';

            h3.style.marginLeft = '2%'
            h3.style.marginRight = '2%'

            iconsDiv.style.float = 'right';
            iconsDiv.style.marginRight = '15px';
            iconsDiv.style.marginTop = '-4.7%';

            upImg.style.marginRight = '15px';
        }
    });
};

//handle the visibility of the 'create new post' button and the form
function showPostForm() {
    postForm.style.visibility = '';
    createNewPostBtn.style.visibility = 'hidden';
}

//sending data collected from input el from the browser to the back end to create a new post
const postFormHandler = async () => {

    await fetch(`${baseUrl}/dashboard`, {
        method: 'POST',
        body:  `{"postTitle":"${postTitleInputEl.value}","postDescription":"${postDescriptionEl.value}"}`,
        credentials: 'include'
    }).then((res) => {
        if (res.ok) {
            document.location.reload();
        } else {
            alert('Your Post Could not be Created');
        }
    });
};

getUserPosts();
//event listeners, once triggered will execute the functions
createNewPostBtn.addEventListener('click', showPostForm);
submitCreatePostFormBtn.addEventListener('click', postFormHandler);