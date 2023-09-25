var userNo = document.getElementById("userNo").value;//hidden으로 userNo 전달받음
//모든 변수와 함수들은 페이지를 불러올때마다 변수를 중복실행하게 되므로 ready안에 위치해야함
$(function() {
	let sendData;
	let reviewGlobal;//ajax밖에서도 응답받은 값을 사용할수있게 전역 변수 선언
	//리뷰카드용 변수
	let row = 2;//현재 노출된 행
	const card_row = 3 //한행당 보여줄 카드 수
	let card_num = $("article[class=review_card]").length; //현재 리뷰카드 갯수
	let max_row = Math.ceil(card_num / card_row); //확장 가능한 최대 행
	let query = "";//검색어

	//리뷰정보를 받기위한 db와 비동기 통신
	function reviewGet(sendData) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: 'post',           // 타입 (get, post, put 등등)
				url: 'mypage/getReview.jsp',	// 요청할 서버url
				async: true,            // 비동기화 여부 (default : true)
				data: sendData,			// 보낼 데이터
				dataType: 'json',
				success: function(result) { // 결과 성공 콜백함수
					resolve();
					reviewGlobal = result;//사용할수있게 전역변수에 저장
					console.log('통신성공', result)
					if (sendData.endNum > 0) {
						createReview();//받은 값 만큼 리뷰카드 생성
						if (card_num == 0) {//생성된 카드가 하나도 없으면
							noneReview();
						}
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
		if (typeof reviewGlobal === 'undefined') {// globalVariable의 값이 undefined일때		
			alert('reviewGlobal값이', reviewGlobal);
		} else {// globalVariable의 값이 undefined아닐때
			for (let i = 0; i < reviewGlobal.length; i++) {
				//날짜에 시간이 포함되서 형식변경
				const reviewDate = new Date(reviewGlobal[i].reviewDate);
				let reviewDateform = reviewDate.toLocaleDateString('ko-KR',
					{ year: 'numeric', month: '2-digit', day: '2-digit' }).split(' ').join('').slice(0, -1);
/*data-url="${reviewGlobal[i].url}"*/
				$(".wrap_review").append(`<article class="review_card"  onclick="createClone(event,'http://localhost:8090/Movivum/review_detail.do?review_no=${reviewGlobal[i].review_no}'); ">
            <!-- 리뷰1행 -->
            <div class="user_wrap">
                <span class="">
                    <span><img class="user_picture" src="${reviewGlobal[i].userPic}"></span>
                    <span class="nickname">${reviewGlobal[i].userNickname}</span>
                </span>
            </div>
            <!-- 리뷰2행 -->
            <div class="grade_wrap">
            	<span><b class="movieName">${reviewGlobal[i].movTitle}</b></span>
                <span class="star"><i class="fas fa-regular fa-star"></i>&nbsp;${reviewGlobal[i].movGrade}</span>
            </div>
            <!-- 리뷰3행 -->
            <div class="content_wrap">
                ${reviewGlobal[i].reviewContent}
            </div>
            <!-- 리뷰4행 -->
            <div class="bottom_wrap">
                <span><i class="fa-regular fa-thumbs-up"></i>&nbsp;${reviewGlobal[i].recommCount}</span>
                <span>${reviewDateform}</span>
            </div>
        </article>`);
			}
		}
		//카드 생성될때마다 card_num,max_row다시 계산 필요
		card_num = $("article[class=review_card]").length;
		max_row = Math.ceil(card_num / card_row);
		delete window.reviewGlobal;
	}

	//리뷰초기화
	function initReview() {
		$(".wrap_review").empty();
		row = 2;//현재 노출된 행
		card_num = $("article[class=review_card]").length; //현재 리뷰카드 갯수
		max_row = Math.ceil(card_num / card_row); //확장 가능한 최대 행
		$(".wrap_review").height("750px");
		console.log('initReview()호출됨')
	}

	//검색된 리뷰가 없을때
	function noneReview() {
		$(".wrap_review").append(`<div class="review_container">
            <h1>리뷰가 없어요 XD</h1><br>
            <a href="#"><u>지금 작성하러 가기</u></a>
    </div>`)
	}

	//리뷰카드를 생성하기위한 비동기 통신
	sendData = { "userNo": userNo, "startNum": 1, "endNum": 9, "query": query };//전달해줄 값sendData = { "userNo": userNo, "startNum":card_num, "endNum":((row+1)*card_row)+(card_num)};//전달해줄 값
	reviewGet(sendData);
	//검색시
	$("#search_text").keyup(function() {
		query = $("#search_text").val();
		sendData = { "userNo": userNo, "startNum": 1, "endNum": 9, "query": query };
		reviewGet(sendData).then(initReview());
					
		console.log(query);
	})

	initReview()//처음페이지 로딩후 초기화필요
	//무한스크롤(리뷰 카드 갯수 만큼 보여지는 범위 확장)
	$(window).scroll(function() {
		if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 400//(현재 화면높이+현재Y스크롤 값) > (최대 문서높이-여유값)
			&& row < max_row && location.href == "http://localhost:8090/Movivum/mypage.do#tab3")
			 {
			row += 1;
			$(".wrap_review").height(( parseInt($(".review_card").css("height")) + parseInt($(".review_card").css("margin-bottom"))) *row);
			
			if (card_num % 3 == 0) {//열이 가득차면(다음 리뷰글이 있을수있으므로) 다음 열을 비동기통신으로 가져오기
				//비동기 통신
				sendData = { "userNo": userNo, "startNum": card_num + 1, "endNum": card_num + 3, "query": query };//전달해줄 값
				reviewGet(sendData);//통신 요청
			}
		}
	})
	$(".summary_container").click(function(){
			if ($(".chart_section").is(":hidden")){
			    $(this).css('height', 'auto')
			}
			if ($(".chart_section").is(":visible")){
			    $(this).animate({'height': '150px'}, 200)
			} 
		$(".chart_section").toggle(500).animate({'width': '300px','height': '300px'}, 500)
	})
})