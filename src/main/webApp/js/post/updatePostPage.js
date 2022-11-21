


//to open update feature modal and render post data associated with the id
// // passed in retrieved from database
const openUpdatePostModal = async (event) => {
    if (event.target.hasAttribute('data-id')) {
        const id = event.target.getAttribute('data-id');
        console.log(id);
        await fetch(`${baseUrl}/post/${id}`, {
            method: 'GET',
            credentials: 'include'
        }).then((res) => {
            return res.json();
        }).then((post) => {
            console.log(post);
            const id = document.getElementById('pstid');
            const pt = document.getElementById('pstTitle');
            const pd = document.getElementById('pstDescription');
            const ui = document.getElementById('userid');
            const conUpdateBtn = document.getElementById('conUpdate');
            const cancelUpdateBtn = document.getElementById('cancelUpdate');

            conUpdateBtn.setAttribute('data-id', `${post.id}`);
            conUpdateBtn.setAttribute('class', `${post.userId}`);

            id.innerHTML = `Post Id: ${post.id}`;
            pt.innerHTML = post.postTitle;
            pd.innerHTML = post.postDescription;
            ui.innerHTML = `User Id: ${post.userId}`;

            document.getElementById('modal2').style.display = 'block';

            cancelUpdateBtn.addEventListener('click', cancelUpdate);
            conUpdateBtn.addEventListener('click', updatePost);
        });
    }
}

//will abort the update process and close the modal
const cancelUpdate = () => {
    document.getElementById('modal1').style.display = 'none';
    document.location.reload();
}

const upostTitleInputEl = document.querySelector('#pstTitle');
const upostDescriptionEl = document.querySelector('#pstDescription');

//will find and update data associated with the id passed in
const updatePost = async  (event) => {
    const id = event.target.getAttribute('data-id');
    if (id != null) {
        await fetch(`${baseUrl}/updatePost/${id}`, {
            method: 'PUT',
            body: `{"postTitle":"${upostTitleInputEl.value}","postDescription":"${upostDescriptionEl.value}"}`,
            credentials: 'include'
        }).then((res) => {
            console.log(upostTitleInputEl);
            if (res.ok) {
                alert('Your Post Has Successfully Been Updated');
                document.location.reload();
            } else {
                alert('Your Post Could not be Updated');
            }
        });
    }
}