async function getPosts() {
    let url = 'http://127.0.0.1:5500/db/db.json';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderPosts() {
    let posts = await getPosts();
    let html = '';
    posts.forEach(post => {
        let htmlSegment = ` <div class="card" style="width: 25rem; margin-bottom: 30px;">
                                <img src="${post.imageUrl}" class="card-img-top" alt="..." >
                                <div class="card-body">
                                <h5 class="card-title">${post.title}</h5>
                                <p class="card-text">${post.message}</p>
                                <button type="button" onclick="updateCounter${post.id}()" class="likeButton btn btn-light"><span><svg id="heart-like" class="text-danger" xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-chat-heart-fill" viewBox="0 0 16 16">
                                    <path d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15Zm0-9.007c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132Z"/>
                                </svg></span>
                                    <span class="likeNumber badge text-bg-light">${post.likes}</span>
                                </button>
                                </div>
                            </div>`;

        html += htmlSegment;
    }); 

    let container = document.querySelector('.container');
    container.innerHTML = html;
}

renderPosts();


function updateCounter1(){
        let getSpecificPost = document.getElementsByClassName("likeNumber")[0];
        let oldCounterValue = Number(getSpecificPost.innerHTML);
        getSpecificPost.innerText = oldCounterValue + 1;
}

function updateCounter2(){
    let getSpecificPost = document.getElementsByClassName("likeNumber")[1];
    let oldCounterValue = Number(getSpecificPost.innerHTML);
    getSpecificPost.innerText = oldCounterValue + 1;
}

function updateCounter3(){
    let getSpecificPost = document.getElementsByClassName("likeNumber")[2];
    let oldCounterValue = Number(getSpecificPost.innerHTML);
    getSpecificPost.innerText = oldCounterValue + 1;
}

