const baseUrl = 'http://localhost:8080';


async function getPosts() {
    let url = `${baseUrl}/posts`;
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function getComments() {
    let url = `${baseUrl}/comment/1`;
    try {
        let res = await fetch(url, {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET',
                'Access-Control-Allow-Headers': 'Content-Type, Authorization'
            },
            mode: 'cors',
            cache: 'default',
            
            });
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}



async function renderPosts() {
    let posts = await getPosts();
    let html = '';
    posts.forEach(post => {
        let htmlSegment = ` <div id="card" class="card">
                                <img src="${post.postImage}" class="card-img-top" alt="..." >
                                <div class="card-body">
                                    <h5 class="card-title">${post.postTitle}</h5>
                                    <p class="card-text">${post.postDescription}</p><hr>
                                    <div id="feature-buttons">
                                    <button id="like-button" type="button" onclick="updateCounter()" class="likeButton btn btn-light">
                                        <span><svg id="heart-like" class="text-danger" xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-chat-heart-fill" viewBox="0 0 16 16">
                                            <path d="M8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6-.097 1.016-.417 2.13-.771 2.966-.079.186.074.394.273.362 2.256-.37 3.597-.938 4.18-1.234A9.06 9.06 0 0 0 8 15Zm0-9.007c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132Z"/>
                                        </svg>
                                        </span>
                                        <span class="likeNumber badge text-bg-light">${post.postLikes}</span>
                                    </button>
                                    <button id="comments-button${post.postId}" type="button" class="btn btn-light accordion-button-adapted collapsed" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
                                        <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                                        <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
                                        </svg>
                                        <span class="badge text-bg-light">COMMENTS</span>
                                    </button>
                                    <button id="share-button" type="button" class="btn btn-primary">                                 
                                    <svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-share" viewBox="0 0 16 16">
                                    <path d="M13.5 1a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zM11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5zm-8.5 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3zm11 5.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3z"/>
                                    </svg>
                                        <span class="badge">SHARE</span>
                                    </button>
                                    </div id="comments-section">
                                    <div id="accordion" class="accordion-item">
                                    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                                        <div class="accordion-body">
                                            <div class="input-group mb-3">
                                            <span class="input-group-text" id="basic-addon1">Write a Comment:</span>
                                            <input type="text" class="form-control" placeholder="" aria-label="add-comment" aria-describedby="basic-addon1">
                                            <span class="input-group-text" id="basic-addon2">Save</span>
                                            
                                            <div id="previous-comments">${renderPreviousComments()}</div>
                                            </div>
                                        </div>
                                     </div>
                                    </div>
                                    
                                </div>
                            </div>`;

        html += htmlSegment;
    }); 

    let container = document.querySelector('.container');
    container.innerHTML = html;
}

renderPosts();



async function renderPreviousComments() {
    let comments = await getComments();
    let html = '';
    comments.forEach(comment => {
        let CommentsSegment = `<div class="list-group">
                                <a class="list-group-item list-group-item-action" aria-current="true">
                                    <div class="d-flex w-100 justify-content-between">
                                        <p class="mb-1">${comment.commentMessage}</p>
                                        <small>3 days ago</small>
                                    </div>
                                </div>`;

        html += CommentsSegment;
    }); 

    let previousComments = document.getElementById('previous-comments');
    previousComments.innerHTML = html;
}













// function updateCounter1(){
//         let getSpecificPost = document.getElementsByClassName("likeNumber")[0];
//         let oldCounterValue = Number(getSpecificPost.innerHTML);
//         getSpecificPost.innerText = oldCounterValue + 1;
// }

// function updateCounter2(){
//     let getSpecificPost = document.getElementsByClassName("likeNumber")[1];
//     let oldCounterValue = Number(getSpecificPost.innerHTML);
//     getSpecificPost.innerText = oldCounterValue + 1;
// }

// function updateCounter3(){
//     let getSpecificPost = document.getElementsByClassName("likeNumber")[2];
//     let oldCounterValue = Number(getSpecificPost.innerHTML);
//     getSpecificPost.innerText = oldCounterValue + 1;
// }


