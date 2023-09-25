
//모든 변수와 함수들은 페이지를 불러올때마다 변수를 중복실행하게 되므로 ready안에 위치해야함
$(function() {
	let count = 0;
	var userNo = document.getElementById("userNo").value;
	//리뷰정보를 받기위한 db와 비동기 통신
	function getAchieve(sendData) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: 'post',           // 타입 (get, post, put 등등)
				url: 'mypage/getAchieve.jsp',	// 요청할 서버url
				async: true,            // 비동기화 여부 (default : true)
				data: {achieve:sendData, userNo:userNo},			// 보낼 데이터
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

	//3.xhr객체를 래핑해서 xhr객체가 전송하고 응답받는 값을 이 코드에서만(IIFE방식의 함수라 이 블럭외에서 값을 사용할 수 없음) 볼수있게 가져오기
	(function() {
		const OriginalXMLHttpRequest = XMLHttpRequest;
		XMLHttpRequest = function() {
			const xhr = new OriginalXMLHttpRequest();
			xhr.addEventListener('readystatechange', function() {
				if (this.readyState === 4) {
					if (this.status == 200){
						console.log('Request:', this._url, this._method,"요청후 서버 응답성공");
					}
					//console.log('Response:', this.responseText);

					//만인의 연인 조건(myhome.do로 이동)
					if (this._url === 'myhome.do' && this.status === 200) {//myhome.do요청은 예시임 나중에 바꿔야함
						//0번 매개변수는 achieve테이블의 achieve_no 칼럼입니다
						getAchieve(0).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("mypage/img/happy_light.svg","즐거운 나의집")
								console.log("즐거운 나의집",result)
							}
						})
					}//뉴비 조건 
					if (this._url === 'grade.do' && this._url === 'write.do' && this._url === 'wish.do' && this.status === 200) {
						getAchieve(1).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("뉴비")
								console.log("뉴비",result)
							}
						})
					}
					/*
					//'myhome.do'요청을 보냈고 응답이 성공이 었다면
					if (this._url === 'myhome.do' && this.status === 200) {//myhome.do요청은 예시임 나중에 바꿔야함
						//0번 매개변수는 achieve테이블의 achieve_no 칼럼입니다
						getAchieve(2).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("만인의 연인")
								console.log("만인의 연인",result)
							}
						})
					}//'myhome.do'요청을 보냈고 응답이 성공이 었다면
					if (this._url === 'myhome.do' && this.status === 200) {//myhome.do요청은 예시임 나중에 바꿔야함
						//0번 매개변수는 achieve테이블의 achieve_no 칼럼입니다
						getAchieve(3).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("만인의 연인")
								console.log("만인의 연인",result)
							}
						})
					}//'myhome.do'요청을 보냈고 응답이 성공이 었다면
					if (this._url === 'myhome.do' && this.status === 200) {//myhome.do요청은 예시임 나중에 바꿔야함
						//0번 매개변수는 achieve테이블의 achieve_no 칼럼입니다
						getAchieve(4).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("만인의 연인")
								console.log("만인의 연인",result)
							}
						})
					}//'myhome.do'요청을 보냈고 응답이 성공이 었다면
					if (this._url === 'myhome.do' && this.status === 200) {//myhome.do요청은 예시임 나중에 바꿔야함
						//0번 매개변수는 achieve테이블의 achieve_no 칼럼입니다
						getAchieve(5).then(function(result) {//비동기통신으로 해당테이블값을 참조해서 true값을 응답받으면
							if (result.achieve>0){
								createPopupMessage("만인의 연인")
								console.log("만인의 연인",result)
							}
						})
					}*/
				}
			});
			xhr.open = function(method, url) {
				this._method = method;
				this._url = url;
				OriginalXMLHttpRequest.prototype.open.apply(this, arguments);
			};
			return xhr;
		};
	})();


	function createPopupMessage(img,msg) {
		// 새로운 요소를 생성하고, 위치와 크기 정보를 설정합니다.
		var newBox = $(`<div class="achievebox">${msg}</div>`);
		var newImg = $(`<img src="${img}"/>`);
		newBox.css({
			'position': 'fixed',
			'bottom': -300 + 'px',
			'right': 0 + 'px',
			'width': 300 + 'px',
			'height': 200 + 'px',
			'background-color': 'var(--thema-light-gray-color)',
			'color':'white',
			'border-radius': 10+'px',
			'box-shadow': '0 4px 16px 0 rgba(0,0,0,0.3)',
			'font-size':'15px',
			'align-items': 'center',
			'display':'flex',
			'align-content': 'center'
		});
		newImg.css({
			'filter': 'invert(1)',
			'width': 100 + 'px',
			'height': 100 + 'px',
		})
		
		newBox.animate({'bottom':0 +'px'},500)
		// 새로운 요소를 body에 추가합니다.
		$('body').append(newBox);
		$('.achievebox').prepend(newImg);

		// 요소의 애니메이션 설정
		setTimeout(function() {
	    	newBox.animate({
	    		'bottom':-300 +'px',
	    	}, 500, function() {
				// 애니메이션이 끝난 후, 새로운 요소를 제거합니다.
				$(this).remove();
			});
		}, 3000);//팝업 지속시간
	}
	
})

