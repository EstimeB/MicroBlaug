const baseUrl = 'http://localhost:7080';

const postForm = document.querySelector('#container');
const createNewPostBtn = document.querySelector('#cnBtn');
const postTitleInputEl = document.querySelector('#pTitle');
const postDescriptionEl = document.querySelector('#pDescription');
const submitCreatePostFormBtn = document.querySelector('#createPostBtn');

function showPostForm() {
    postForm.style.visibility = "";
    createNewPostBtn.style.visibility = "hidden";
}

createNewPostBtn.addEventListener('click', showPostForm);

// submitCreatePostFormBtn.addEventListener('click', () => {
//     fetch(`${baseUrl}/dashboard`, {
//         method: 'POST',
//         body: `{"postTitle":"${postTitleInputEl.value}","postDescription":"${postDescriptionEl.value}"}`,
//     })
// })

//to be moved to home page

fetch(`${baseUrl}/posts`, {
    method: 'GET',
}).then((res) => {
    return res.json();
}).then((posts) => {
    //need to create el instead and append iv container
    for (post of posts) {
        let postTle = document.querySelector('.postTitle');
        let postBody  = document.querySelector('.postBody');

        postTle.innerHTML = post.postTitle;
        postBody.innerHTML = post.postDescription;
    }
})
