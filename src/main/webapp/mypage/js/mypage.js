
$(window).on('load', function(){
	$(".load_article").load("myhome.do", function(response, status, xhr) {
				$(".load_article").css("display", "none");
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
				}
			})
	
	$('.sidebar ul li a').hover(function() {//호버 시
		if ($(this).attr("href") == "#tab1") {
			$(':root').css('--thema-screen-lignt', '#5c16c5');//상단 스크린 빛
		}
		else if ($(this).attr("href") == "#tab2") {
			$(':root').css('--thema-screen-lignt', '#F1263A');
		}
		else if ($(this).attr("href") == "#tab3") {
			$(':root').css('--thema-screen-lignt', '#E6CF26');
		}
		else if ($(this).attr("href") == "#tab4") {
			$(':root').css('--thema-screen-lignt', '#2F8D9C');
		}
		else if ($(this).attr("href") == "#tab5") {
			$(':root').css('--thema-screen-lignt', '#0DCD76');
		}
		else if ($(this).attr("href") == "#tab6") {
			$(':root').css('--thema-screen-lignt', '#D1F321');
		}
		else if ($(this).attr("href") == "#tab7") {
			$(':root').css('--thema-screen-lignt', '#FCF4F9');
		}
	},function(){//호버해제시
		if (location.href=="http://localhost:8090/Movivum/mypage.do#tab1" || location.href=="http://localhost:8090/Movivum/mypage.do"){
			$(':root').css('--thema-screen-lignt', '#5c16c5');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab2"){
			$(':root').css('--thema-screen-lignt', '#F1263A');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab3"){
			$(':root').css('--thema-screen-lignt', '#E6CF26');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab4"){
			$(':root').css('--thema-screen-lignt', '#2F8D9C');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab5"){
			$(':root').css('--thema-screen-lignt', '#0DCD76');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab6"){
			$(':root').css('--thema-screen-lignt', '#D1F321');//상단 스크린 빛
		}
		else if (location.href=="http://localhost:8090/Movivum/mypage.do#tab7"){
			$(':root').css('--thema-screen-lignt', '#FCF4F9');//상단 스크린 빛
		}
	})
			
	$('.sidebar ul li a').click(function() {
		$('.sidebar ul li a').removeClass('selected');
		$(this).addClass('selected');
		$(".pageup_btn").hide();//페이지 업 버튼 기본숨김
		$("article>section").css("height", "auto")//마이리뷰에서 늘어난 높이 재수정
		$("#loadingPage").css("display", "flex");
		$(".load_article").css("display", "none",function(){console.log("hide실행됨")});
		$(':root').css('--thema-screen-lignt', '--accent-purple-color');

		if ($(this).attr("href") == "#tab1") {
			$(".load_article").load("myhome.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#5c16c5');//상단 스크린 빛
				}
			})
		}
		if ($(this).attr("href") == "#tab2") {
			$(".load_article").load("wish.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#F1263A');//상단 스크린 빛
				}
			})
		}
		if ($(this).attr("href") == "#tab3") {
			$(".load_article").load("myreview.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#E6CF26');
				}
			})
		}
		if ($(this).attr("href") == "#tab4") {
			$(".load_article").load("myachieve.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#2F8D9C');
				}
			})
		}
		if ($(this).attr("href") == "#tab5") {
			$(".load_article").load("likeGenre.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#0DCD76');
				}
			})
		}
		if ($(this).attr("href") == "#tab6") {
			$(".load_article").load("mypage/mypage_FAQ.jsp", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#D1F321');
				}
			})
		}
		if ($(this).attr("href") == "#tab7") {
			$(".load_article").load("myprofile.do", function(status, xhr) {
				if (status == "error") {
					console.log("Error: " + xhr.status + " " + xhr.statusText);
				} else {
					setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
					setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
					$(':root').css('--thema-screen-lignt', '#FCF4F9');
				}
			});
			popupCheck();
		}
	})

	//숨기고 있다가 스크롤하면 페이지 업버튼 생성
	$(".pageup_btn").hide();
	$(window).scroll(function() {
		if ($('.sidebar ul li a[class="selected"]').attr("href") == "#tab3"
		|| $('.sidebar ul li a[class="selected"]').attr("href") == "#tab4"
		|| $('.sidebar ul li a[class="selected"]').attr("href") == "#tab6") {
			$(".pageup_btn").show();
		}
	})
	

})//실시간 이미지 경로수정
$(function() {
  	var pic = new Image();
  
  	pic.src = document.getElementById("banner_user_image").src
  	function checkImage() {//이미지 경로뒤에 쓸모없는 값을 새로 붙이면 새파일을 로드하는 것으로 착각하고 새롤 로드함
	  	var newSrc = document.getElementById("banner_user_image").src + '?' + new Date().getTime();
	    document.getElementById("banner_user_image").src = newSrc;
	    pic.src = newSrc;
	}
  
	pic.onload = function() {
    // 이미지 파일이 존재함
    console.log("이미지 불러오기 성공");
    	if (location.href=="http://localhost:8090/Movivum/mypage.do#tab1" || location.href=="http://localhost:8090/Movivum/mypage.do") {
			$(".load_article").load("myhome.do", function(status, xhr) {
			if (status == "error") {
				console.log("Error: " + xhr.status + " " + xhr.statusText);
			} else {
				setTimeout(function(){$(".load_article").fadeIn()}, 100);//최소로딩시간
				setTimeout(function(){$("#loadingPage").fadeOut()}, 100);//최소로딩시간
				$(':root').css('--thema-screen-lignt', '#5c16c5');//상단 스크린 빛
			}
		})
	}
  }
  
  pic.onerror = function() {
    // 이미지 파일이 존재하지 않음
    setTimeout(checkImage, 1000);
    console.log("이미지 불러오기 재시도");
  }
})
/* 자식요소에서 mypage.js(root)의 js를 참조하기 위해서 load 이벤트 밖에 선언함 */
//비밀번호 체크 팝업 생성
function popupCheck(){
	$("#popup_check").css('display', 'flex').animate({'bottom': 0 + 'px'}, 500)
}
//비밀번호 체크 팝업 취소
function popupCheckCancle(e){
	e.preventDefault();
	window.location.href = "http://localhost:8090/Movivum/mypage.do";
}
//비밀번호 체크 팝업 성공
function popupCheckSuccess(e){
	e.preventDefault();
	//비밀번호 원래 비동기로 가져와야함
	getPasswd().then(function(result){
		if (document.getElementById("popup_check_input").value == result.passwd){
			$("#popup_check").animate({'bottom': -120 + 'vh'}, 300)
			.fadeOut()
			document.getElementById("popup_check_input").value = ""
		}
	})
}
//회원탈퇴 팝업 생성
function popupDelete(e){
	e.preventDefault();
	$("#popup_delete").css('display', 'flex').animate({'bottom': 0 + 'px'}, 500)
}
//회원탈퇴 팝업 문자열 체크
function memberDelete(e){
	if (document.getElementById("popup_delete_input").value !== "탈퇴할래요"){
		e.preventDefault();
	}else 
		{document.getElementById("popup_delete_input").value = ""}
}
//회원탈퇴 취소
function popupDeleteCancle(e){
	e.preventDefault();
	$("#popup_delete").animate({'bottom': -120 + 'vh'}, 300)
	.fadeOut()
}
//알림팝업창 생성(정보수정완료 알림 같은거)
$(document).ready(function(){
	let popupContent = null;
	const name = "popupContent=";
	const cookies = document.cookie.split(';');
	for(let i = 0; i < cookies.length; i++) {
	    let cookie = cookies[i];
	    while (cookie.charAt(0) == ' ') {
	        cookie = cookie.substring(1);
	    }
	    if (cookie.indexOf(name) == 0) {
	        popupContent = decodeURIComponent(cookie.substring(name.length, cookie.length));
	        break;
	    }
	}
	
	if (popupContent !== null) {
		$("#popup_notice").css('display', 'flex')
		.animate({'bottom': 0 + 'px'}, 500,function(){
			$(".check-icon").hide();
		  	setTimeout(function () {
		    	$(".check-icon").show();
		    }, 10);
		})
		$("#popup_notice>.popup_body> .popup_Content").text(popupContent);
	}
	//알림팝업창 닫기(정보수정완료 알림 같은거)
	if (popupContent !== null){
		$(".popup_section").click(function() {
			$("#popup_notice").animate({'bottom': -120 + 'vh'}, 300)
			.fadeOut(); //페이드아웃 효과
			document.cookie = "popupContent" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
			popupContent=null;
		});
	}
})


//리뷰페이지 전환 애니메이션
function createClone(e,href) {
  var boxOffset = $(e.currentTarget).offset();//e.currentTarget을 쓰는이유는 보블링(부모태그의 기능이 하위태그들도 전부 상속되는 문제)
  var boxWidth = $(e.currentTarget).width();
  var boxHeight = $(e.currentTarget).height();
  disableScroll();

  // 새로운 요소를 생성하고, 위치와 크기 정보를 설정합니다.
  var newBox = $('<div class="box"></div>');
  newBox.css({
    'position': 'absolute',
    'top': boxOffset.top + 'px',
    'left': boxOffset.left + 'px',
    'width': boxWidth + 'px',
    'height': boxHeight + 'px',
    'background-color': $(e.currentTarget).css('background-color'),
    'border-radius': $(e.currentTarget).css('border-radius'),
    'box-shadow': '0 4px 16px 0 rgba(0,0,0,0.3)',
    'transform': 'scale(1.02)'
  });

  // 새로운 요소를 body에 추가합니다.
  $('body').append(newBox);

  // 요소의 애니메이션 설정
  newBox.animate({
    'width': $(window).width() + 'px',
    'height': document.body.offsetHeight+window.scrollY + 'px',
    'top': 0 + 'px',
    'left': 0 + 'px',
  }, 500, function () {
    // 스크롤 중지 이벤트 핸들러 해제
    $(window).off('scroll', disableScroll);
    $('body').css('overflow', '');
    location.href=href
    // 애니메이션이 끝난 후, 새로운 요소를 제거합니다.
    //$(this).remove(); 제거할필요없어서 일단 주석처리
  });

  	function disableScroll() {
    // 스크롤 중지
    $('body').css('overflow', 'hidden');
  }
}

//패스워드 비동기로 가져오기
function getPasswd() {
	var userNo = document.getElementById("userNo").value;
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: 'post',           // 타입 (get, post, put 등등)
				url: 'mypage/getPasswd.jsp',	// 요청할 서버url
				async: true,            // 비동기화 여부 (default : true)
				data: {userNo:userNo},			// 보낼 데이터
				dataType: 'json',
				success: function(result) { // 결과 성공 콜백함수
					resolve(result);
					console.log('통신성공', result)
				},
				error: function(error) { // 결과 에러 콜백함수
					console.log(error)
					reject();
				}
			})
		})
	}