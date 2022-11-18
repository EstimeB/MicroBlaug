


getUserPosts();

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
            alert('Your Post Has Successfully Been Created');
            document.location.reload();
        } else {
            alert('Your Post Could not be Created');
        }
    });
};

//event listeners, once triggered will execute the functions
createNewPostBtn.addEventListener('click', showPostForm);
submitCreatePostFormBtn.addEventListener('click', postFormHandler);

//to open delete feature modal and render post data associated with the id
// passed in retrieved from database
const openDelModal = async (event) => {
    if (event.target.hasAttribute('data-id')) {
        const id = event.target.getAttribute('data-id');
        console.log(id);
        await fetch(`${baseUrl}/post/${id}`, {
            method: 'GET',
            credentials: 'include'
        }).then((res) => {
            return res.json();
        }).then((post) => {
            const id = document.getElementById('postid');
            const pt = document.getElementById('cpostTitle');
            const pd = document.getElementById('cpostDescription');
            const conDelBtn = document.getElementById('conDel');
            const delCancelBtn = document.getElementById('delCancel');

            conDelBtn.setAttribute('data-id', `${post.id}`);

            id.innerHTML = post.id;
            pt.innerHTML = post.postTitle;
            pd.innerHTML = post.postDescription;

            document.getElementById('modal1').style.display = 'block';

            delCancelBtn.addEventListener('click', cancelDeletion);
            conDelBtn.addEventListener('click', deletePost);
        });
    }
}

//will abort the deletion process and close the modal
const cancelDeletion = () => {
    document.getElementById('modal1').style.display = 'none';
    document.location.reload();
}

//will find and delete all data associated with the id passed in
const deletePost = async (event) => {
    if (event.target.hasAttribute('data-id')) {
        const id = event.target.getAttribute('data-id');
        console.log(id);
        await fetch(`${baseUrl}/deletePost/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        }).then((res) => {
            if (res.ok) {
                alert('Your Post Has Successfully Been Deleted');
                document.location.reload();
            } else {
                alert('Failed to delete post');
            }
        });
    }
}