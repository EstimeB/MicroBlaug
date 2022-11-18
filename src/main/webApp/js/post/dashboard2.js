


const openModal = async (event) => {
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

const cancelDeletion = () => {
    document.getElementById('modal1').style.display = 'none';
    document.location.reload();
}

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