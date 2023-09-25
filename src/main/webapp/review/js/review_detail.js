"use strict"

//영화번호 받아오기
const mov_no=document.querySelector(".mov_no").value;

//글쓰기 유효성검사
  const form = document.querySelector("#comment_form");
  const textarea = document.querySelector("#comment");

  form.addEventListener("submit", (event) => {
    if (textarea.value.trim() === "") {
      event.preventDefault(); // 
      alert("댓글을 입력해주세요.");
    }
  });

//댓글_로그인 알림
function alertLogin() {
 alert("로그인이 필요합니다.");
  // window.location.href = ""; 로그인 경로
}

//댓글_예외처리
const replyContent = document.getElementById('reply_content');
if (!replyContent) {
}
else {
  const replyTextarea = document.createElement('textarea');  
  replyTextarea.value = replyContent.innerText; 
  replyTextarea.id = 'reply_content_textarea'; 

 	const updateBtn = document.getElementById('update_btn');
	let replyUpdateBtn = document.getElementById('reply_update_btn');
	
	try {
  	replyUpdateBtn.addEventListener('click', () => {
    replyContent.parentNode.replaceChild(replyTextarea, replyContent); 
    replyUpdateBtn.style.display = 'none'; 
    updateBtn.style.display = 'block'; 
  	});
   } catch (error) {
  		console.log('replyUpdateBtn is null:');
	}
}

//댓글_수정버튼(비동기)
$(document).ready(function() {
  $("#update_btn").click(function() {
	  if (!validate()) {
      	return;
      	}
    var formData = $("#updateform").serializeArray();

    var reply_no = $("#reply_no").val();
    var user_no = $("#reply_user_no").val();
    formData.push({name: "reply_no", value: reply_no});
    formData.push({name: "user_no", value: user_no});

    var newReplyContent = $("#reply_content_textarea").val();
    var index = formData.findIndex(function(data) {
      return data.name === "reply_content";
    });
    if (index > -1) {
      formData[index].value = newReplyContent;
    }

    $.ajax({
      url: "review/replyUpdate.jsp",
      type: "POST",
      data: formData,
      success: function(response) {
        if (response.result === "success") {
          alert("수정되었습니다.");
          location.reload();
        }
      },
    });
  });
});

//댓글_컨텐츠 유효성검사
function validate() {
  let content = $("#reply_content_textarea").val().trim();
  if (content === "") {
    alert("댓글을 입력해주세요.");
    return false;
  }
  return true;
}


/*공유 버튼*/
const shareCilp = document.querySelector('.clipBoard');
const shareFace = document.querySelector('.facebook');
const shareTwitter = document.querySelector('.twitter');
const shareKakao = document.querySelector('.kakao');

//(1)클립보드API
shareCilp.addEventListener('click',()=>{
    const page = navigator.clipboard.writeText(window.location.href);
    /*alert('클립보드에 복사되었습니다!');*/
});

//(2-1)Facebook
const reviewUrl = encodeURI(window.location.href);
shareFace.addEventListener('click',()=>{
    window.open("http://www.facebook.com/sharer/sharer.php?u="+reviewUrl);
})

//(2-2)Twitter
shareTwitter.addEventListener('click',()=>{
const urlForParam = new URL(window.location);
const urlParams = urlForParam.searchParams;
const text = `${movieData.title}의 리뷰`;
//-> ${무비제목} 넘기기
window.open("https://twitter.com/intent/tweet?text=" + text + "&reviewUrl=" +  reviewUrl)
});

//(3)카카오 API키 : a929839c842db32041a572a04c154040
	//let like_count = document.querySelector('.like_count').textContent;
	//console.log(like_count);
Kakao.init('a929839c842db32041a572a04c154040');
// SDK 초기화 여부를 판단 -> true 나와야함
console.log(Kakao.isInitialized());

const kakao = document.querySelector(".kakao");
const likeCnt = Number(document.querySelector(".like_count").innerHTML);
const replyCnt = Number(document.querySelector(".commnet_count").innerHTML);

function sendLink(movieData) {
  Kakao.Link.sendDefault({
    objectType: 'feed',
    content: {
      title: `${movieData.title}의 리뷰`,
      description: `MOVIVUM에서 ${movieData.title}의 자세한 리뷰를 확인해보세요!`,
      imageUrl:
          `https://image.tmdb.org/t/p/w500/${movieData.poster_path}`,
      link: {
          webUrl: `http://localhost:8090/Movivum/review_detail.do?mov_no=${mov_no}&review_no=${review_no}`,
      },
    },
    social: {
      likeCount: likeCnt, //좋아요 수
      commentCount: replyCnt, //댓글 수
    },
    buttons: [
      {
        title: 'Web에서 확인',  //버튼 
        link: {
          webUrl: `http://localhost:8090/Movivum/review_detail.do?mov_no=${mov_no}&review_no=${review_no}`,
        },
      },
    ],
  })
}

//영화정보 fetch
const urlParams = new URL(location.href).searchParams;
const review_no = urlParams.get('review_no');
const url ='https://api.themoviedb.org/3/movie/';
const api_key ='?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko';

fetch(url+mov_no+api_key)
  .then((response) => response.json())
  .then((data) => {
    const movieData = data;
    const poster = document.createElement("img");
    poster.src = `https://image.tmdb.org/t/p/w500/${movieData.poster_path}`;
    const title = document.createElement("h2");
    title.className = 'title';
    const date = document.createElement("span");
    const genre = document.createElement("span");
    title.textContent = movieData.title;
    date.textContent = movieData.release_date.substr(0, 4) + " ";
    let results ="";
    for (let i = 0; i < movieData.genres.length; i++) {
      results += movieData.genres[i].name + " ";  
    }
    genre.textContent = results;

document.querySelector(".movie_poster").appendChild(poster);
let mov_title = document.querySelector(".movie_title").appendChild(title);
const info = document.querySelector(".movie_info");
info.appendChild(date);
info.appendChild(genre);

//카카오톡 공유시 sendLink()함수 호출
kakao.addEventListener("click", () => sendLink(movieData));
})
  .catch((error) => {
console.error('Error:', error);
})

console.log(mov_no);
console.log(review_no);


/*신고버튼(모달)*/
$(function(){
  $('.claim_button1').click(function(){
      $('.modal1').fadeIn();
  })
  $('.close_btn1','').click(function(){
      $('.modal1').fadeOut();
  })
})
$(function(){
  $('.claim_button2').click(function(){
      $('.modal2').fadeIn();
  })
  $('.close_btn2','').click(function(){
      $('.modal2').fadeOut();
  })
})

/*신고_옵션 중 기타*/
$(document).ready(function() {
    $('#claim').change(function() {
        var result = $('#claim option:selected').val();
        if (result == '5') {
            $('#claimReason').show();
        } else {
            $('#claimReason').hide();
        }
	});

/*공유 드롭다운*/
const shareDropdown = document.querySelector('.share_dropdown');
const shareUl = shareDropdown.querySelector('.share_ul');

shareDropdown.addEventListener('mouseover', function() {
    shareUl.style.opacity = '1';
});
shareDropdown.addEventListener('mouseleave', function() {
    shareUl.style.opacity = '0';
});

/*클립보드 alert*/
$('.clipBoard').click(function(){
    $('.clipBoard_alert').animate(
            {'opacity':'1',
            'bottom':'50%',}
            ,700,"easeOutBounce").fadeOut(1300).show();
    });

/*스크롤 상단 이동 버튼*/
function clickme() {
    window.scrollTo({top:0, left:0, behavior:'smooth'});
}
});

/*좋아요 버튼*/
const likeBtn = document.querySelector('.like_button');
const likeIcon = $('.fa-thumbs-up'); // likeIcon을 jQuery 객체로 변경
/*
likeBtn.addEventListener('click',function(){
    likeIcon.toggleClass('ActivedBtn'); // likeIcon 변수를 $likeIcon으로 변경
});
*/
$(document).on('click','.ActivedBtn',function(){
	$(this).removeClass('ActivedBtn');
	$(this).addClass('InactivedBtn');
	alert('좋아요 취소되었습니다.');
})
$(document).on('click','.InactivedBtn',function(){
	$(this).removeClass('InactivedBtn');
	$(this).addClass('ActivedBtn');
	alert('좋아요 반영되었습니다.');
})



/*reviewLike() 전역사용 위해 분리*/
$(document).ready(function() {
  $('.like_button').click(function() {
    reviewLike($(this));
  })
});

function reviewLike() {
  $.ajax({
    url: "/Movivum/review_like.do",
    type: "POST",
    cache: false,
    dataType: "json",
    data: $('#likeForm').serialize(),
    success: function(data) {
	let likeCount = $(".like_count");
      if (likeIcon.hasClass('ActivedBtn')) {
	     likeCount.html(data.like);
      } else {
		  let newCount = parseInt(likeCount.html())-1;
		  likeCount.html(newCount < 0? 0: newCount);
      }
      likeBtn.toggleClass('ActivedBtn');
    },
    error: function(request, status, error) {  
      alert("요청 처리 실패");
    }
  })
};