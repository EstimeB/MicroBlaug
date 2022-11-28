


getUserPosts();

//handle the visibility of the 'create new post' button and the form
function showPostForm() {
    postForm.style.visibility = '';
    createNewPostBtn.style.visibility = 'hidden';
}

//sending data collected from input el from the browser to the back end to create a new post
const postFormHandler = async (event) => {
    event.preventDefault();
    const res = await fetch(`${baseUrl}/dashboard/createPost`, {
        method: 'POST',
        body:  `{"postTitle":"${postTitleInputEl.value}","postDescription":"${postDescriptionEl.value}"}`,
        credentials: 'include'
    })
    if (res.ok) {
        alert('Your Post Has Successfully Been Created!');
        document.location.reload();
    } else {
        alert('You Must Have a Post Title and a Post Description!');
        alert('Your Post Could not be Created!');
    }
};

//event listeners, once triggered will execute the functions
createNewPostBtn.addEventListener('click', showPostForm);
submitCreatePostFormBtn.addEventListener('click', postFormHandler);

//to open delete feature modal and render post data associated with the id
// passed in retrieved from database
const openDelModal = async (event) => {
    if (postForm.style.visibility === '') {
        postForm.style.visibility = 'hidden';
        createNewPostBtn.style.visibility = '';
    }
    if (event.target.hasAttribute('data-id')) {
        const id = event.target.getAttribute('data-id');
        const res = await fetch(`${baseUrl}/dashboard/post/${id}`, {
            method: 'GET',
            credentials: 'include'
        });
        const post = await res.json();

        const id2 = document.getElementById('postid');
        const pt = document.getElementById('cpostTitle');
        const pd = document.getElementById('cpostDescription');
        const conDelBtn = document.getElementById('conDel');
        const delCancelBtn = document.getElementById('delCancel');
        const uid = document.getElementById('useridD');
        const date = document.getElementById('date2');

        conDelBtn.setAttribute('data-id', `${post.id}`);

        id2.innerHTML = post.id;
        pt.innerHTML = post.postTitle;
        pd.innerHTML = post.postDescription;
        uid.innerHTML = `User Id: ${post.userId}`;
        date.innerHTML = `Date Posted: ${post.postDateCreated}`;

        document.getElementById('modal1').style.display = 'block';

        delCancelBtn.addEventListener('click', cancelDeletion);
        conDelBtn.addEventListener('click', deletePost);
    }
}

//will abort the deletion process and close the modal
const cancelDeletion = () => {
    document.getElementById('modal1').style.display = 'none';
    // document.location.reload();
}

//will find and delete all data associated with the id passed in
const deletePost = async (event) => {
    if (event.target.hasAttribute('data-id')) {
        const id = event.target.getAttribute('data-id');
        const res = await fetch(`${baseUrl}/dashboard/deletePost/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        })
        if (res.ok) {
            alert('Your Post Has Successfully Been Deleted!');
            document.location.reload();
        } else {
            alert('Failed to delete post!');
        }
    }
}