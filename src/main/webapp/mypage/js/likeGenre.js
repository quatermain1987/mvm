$(function() {

		genreNoArray=[];
	for (var i = 0; i < likeGenreArray.length; i++) {
		$(`.likebox[value="${likeGenreArray[i]}"]`).addClass('checked');
	}

	$('.likebox').click(function() {
		if (likeGenreArray.includes($(this).val())) {//이미 배열에 있으면 제거
			likeGenreArray.splice(likeGenreArray.indexOf($(this).val()), 1)
			$(this).removeClass('checked');
			console.log(likeGenreArray)
		} else if (likeGenreArray.length < 3) {//없으면 추가
			likeGenreArray.push($(this).val());
			$(this).addClass('checked');
			console.log(likeGenreArray)
		}
	})
	$('.submit_button').click(function() {
		likeGenreArray.forEach(function(item){
			switch(item){
				case "액션": genreNoArray.push(28); break;
				case "애니": genreNoArray.push(12); break;
				case "코미디": genreNoArray.push(35); break;
				case "범죄": genreNoArray.push(80); break;
				case "다큐멘터리": genreNoArray.push(99); break;
				case "드라마": genreNoArray.push(18); break;
				case "뮤지컬": genreNoArray.push(10402); break;
				case "판타지": genreNoArray.push(14); break;
				case "역사": genreNoArray.push(36); break;
				case "공포": genreNoArray.push(27); break;
				case "미스터리": genreNoArray.push(9648); break;
				case "로맨스": genreNoArray.push(10748); break;
				case "SF": genreNoArray.push(878); break;
				case "스릴러": genreNoArray.push(53); break;
				case "전쟁": genreNoArray.push(10752); break;
				case "서부": genreNoArray.push(37); break;
			}
		})
			if (genreNoArray[0]!= undefined)
				document.querySelector("#genre1").value = genreNoArray[0];
			else document.querySelector("#genre1").value = 0
			if (genreNoArray[1]!= undefined)
				document.querySelector("#genre2").value = genreNoArray[1];
			else document.querySelector("#genre2").value = 0
			if (genreNoArray[2]!= undefined)
				document.querySelector("#genre3").value = genreNoArray[2];
			else document.querySelector("#genre3").value = 0
		console.log(genreNoArray)
		/*
	//비동기로 선호장르 업데이트
	function updateLikegenre() {
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: 'post',           // 타입 (get, post, put 등등)
				url: 'likeGenreedit.do',	// 요청할 서버url
				async: true,            // 비동기화 여부 (default : true)
				data: { genre1:genreNoArray[0],genre2:genreNoArray[1],genre3:genreNoArray[2] },	// 보낼 데이터
				dataType: 'json',
				success: function(result) { // 결과 성공 콜백함수
					resolve(result);
					console.log('통신성공', result)
				},
				error: function(error) { // 결과 에러 콜백함수
					console.log('통신실패',error)
					reject();
				}
			})
		})
	}*/
	})

})