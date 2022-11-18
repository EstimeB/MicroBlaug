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
    await fetch(`${baseUrl}/userPosts`, {
        method: 'GET',
        credentials: 'include'
    }).then((res) => {
        return res.json();
    }).then((posts, event) => {
        const outerDiv = document.querySelector('#innerContainer');
        for (post of posts) {
            const innerDiv = document.createElement('div');
            var upImg = document.createElement('IMG');
            var delImg = document.createElement('IMG');
            const h3 = document.createElement('h3');
            const iconsDiv = document.createElement('div');

            upImg.setAttribute('src', '../../icons/icons8-update-30.png');
            delImg.setAttribute('src', '../../icons/icons8-trash-26.png');
            delImg.setAttribute('type', 'button');
            upImg.setAttribute('type', 'button');
            delImg.setAttribute('id', 'open-modal');
            upImg.setAttribute('id', 'open-modal1');
            innerDiv.setAttribute('id', 'uPostDiv');
            delImg.setAttribute('data-id', `${post.id}`);
            upImg.setAttribute('data-id', `${post.id}`);

            h3.innerHTML = post.postTitle;


            innerDiv.appendChild(h3);
            iconsDiv.appendChild(upImg);
            iconsDiv.appendChild(delImg);
            innerDiv.appendChild(iconsDiv);
            outerDiv.appendChild(innerDiv);

            innerDiv.style.backgroundColor = 'lightgray';
            //innerDiv.style.marginTop = '1%';
            innerDiv.style.border = 'solid';
            innerDiv.style.borderRadius = '3px';
            innerDiv.style.borderWidth = '1px';
            innerDiv.style.borderColor = 'gray';
            innerDiv.style.boxShadow = '5px 5px 5px rgb(151, 151, 143)';
            innerDiv.style.height = '100%';
            innerDiv.style.width = '70%';
            innerDiv.style.margin = '1% 15%';

            h3.style.marginLeft = '2%'
            h3.style.marginRight = '2%'

            iconsDiv.style.float = 'right';
            iconsDiv.style.marginRight = '15px';
            iconsDiv.style.marginTop = '-4.7%';

            upImg.style.marginRight = '15px';


            upImg.addEventListener('click', openUpdatePostModal);
            delImg.addEventListener('click', openDelModal);
        }

    });
}
