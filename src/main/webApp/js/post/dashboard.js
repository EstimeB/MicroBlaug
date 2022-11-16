// will allow to connect and communicate with the back end API
const baseUrl = 'http://localhost:8080';

//get el and store them in vars
const postForm = document.querySelector('#container');
const createNewPostBtn = document.querySelector('#cnBtn');
const postTitleInputEl = document.querySelector('#pTitle');
const postDescriptionEl = document.querySelector('#pDescription');
const submitCreatePostFormBtn = document.querySelector('#createPostBtn');

//will render signed-in user posts titles
async function getUserPosts() {
    fetch(`${baseUrl}/101/posts`, {
        method: 'GET',
    }).then((res) => {
        return res.json();
    }).then((posts) => {
        let innerDiv = document.querySelector('#innerContainer');
        for (post of posts) {
            let h3 = document.createElement('h3');
            h3.innerHTML = post.postTitle;
            innerDiv.appendChild(h3);
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
    const res = await fetch(`${baseUrl}/dashboard`, {
        method: 'POST',
        body:  `{"postTitle":"${postTitleInputEl.value}","postDescription":"${postDescriptionEl.value}"}`,
    })
    if (res.ok) {
        document.location.reload();
    } else {
        alert('Your Post Could not be Created');
    }
};

getUserPosts();
//event listeners, once triggered will execute the functions
createNewPostBtn.addEventListener('click', showPostForm);
submitCreatePostFormBtn.addEventListener('click', postFormHandler);