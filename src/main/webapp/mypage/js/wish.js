var userNo = document.getElementById("userNo").value;//hidden으로 userNo 전달받음
console.log('userNo : '+userNo)
//모든 변수와 함수들은 페이지를 불러올때마다 변수를 중복실행하게 되므로 ready안에 위치해야함
$(function() {
	let sendData;
	let reviewGlobal;//ajax밖에서도 응답받은 값을 사용할수있게 전역 변수 선언
	//리뷰카드용 변수
	//let row = 2;//현재 노출된 행
	//const card_row = 1 //한행당 보여줄 카드 수
	//let card_num = 3;
	//let card_num = $("article[class=review_card]").length; //현재 리뷰카드 갯수
	//let max_row = Math.ceil(card_num / card_row); //확장 가능한 최대 행


	//리뷰정보를 받기위한 db와 비동기 통신
	function reviewGet(sendData) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: 'post',        // 타입 (get, post, put 등등)
				url: 'mypage/getWishList.jsp',	// 요청할 서버url
				async: true,            // 비동기화 여부 (default : true)
				data: sendData,			// 보낼 데이터
				dataType: 'json',
				  success: function(result) { // 결과 성공 콜백함수
                resolve();
                reviewGlobal = result;
                console.log('통신성공', result)
                if (reviewGlobal.length > 0) {
					console.log('생성됨')
                    createReview();//받은 값 만큼 리뷰카드 생성
                } else {
                    noneReview();
                }
            },
				error: function(error) { // 결과 에러 콜백함수
					console.log(error)
					reject();
				}
			})
		})
	}

	//리뷰카드 생성
	function createReview() {
  if (typeof reviewGlobal === 'undefined') {
    alert('reviewGlobal값이', reviewGlobal);
  } else {
    const url = 'https://api.themoviedb.org/3/movie/';
    const api_key = '?api_key=ce8ce557f85eb5b4175ee66f08002107&language=ko';

    for (let i = 0; i < reviewGlobal.length; i++) {
      fetch(url + reviewGlobal[i].mov_no + api_key)
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          let results = "";
          for (let i = 0; i < data.genres.length; i++) {
            results += data.genres[i].name + "・";
          }
          let overview = data.overview ? data.overview : "등록 된 줄거리가 없습니다.";
          let card = `
            <div class="wrap"> 
              <div class="movie_wrap">
              	<a href="/Movivum/detail.do?mov_no=${data.id}&userNo=${userNo}">
                <div class="movie_poster">
                  <img src="${'https://image.tmdb.org/t/p/w342/' + data.poster_path}" class="moive_img"/>
                </div>
              </div>
              <div class="middle_wrap">
                <div class="moive_info">
                  <h4 class="title">${data.title}</h4>
                  <div>${data.release_date.substr(0, 4)}</div>
                  <div>${results.slice(0, -1)}</div>
                </div>
                <div class="overview">
                  ${overview}
                  </div>
                </a>
                <div class="button">
                  <form method="post" name="wishform" action="mywishDelete.do" id="wishform"  onsubmit="return confirm('정말 취소하시겠습니까?')">
                     <input type="hidden" name="mov_no" value="${data.id}" />
                     <input type="hidden" name="userNo" value="${userNo}" />
                  <button id="cancelbtn${i}"><i class="fa-regular fa-heart"></i>보고싶어요 취소</button>
                  </form>
                </div>
              </div>
            </div>
          `;
          $(".wish_wrap").append(card);
        });
    }
  }
}
	
	
	//검색된 보고싶어요 없을때
	function noneReview() {
		$(".wish_wrap").append(`<div class="empty_movie_wrap">
            <h3>보고싶은 영화가 없어요 XD</h3><br>
    </div>`)
	}


	//리뷰카드를 생성하기위한 비동기 통신
	sendData = { "userNo": userNo, "startNum": 1,};//전달해줄 값sendData = { "userNo": userNo, "startNum":card_num, "endNum":((row+1)*card_row)+(card_num)};//전달해줄 값
	reviewGet(sendData);
	
	
})//끝 