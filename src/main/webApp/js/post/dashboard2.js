


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