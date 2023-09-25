<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,org.json.simple.JSONObject, org.json.simple.JSONArray, model.mypage.ReviewDAO, model.mypage.ReviewGenreDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./mypage/css/myreview.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="./mypage/js/myreview.js"></script>
<title>Document</title>

</head>
<body>
	<!-- js로 값을 넘겨주기위한 hidden -->
	<input type="hidden" id="userNo" value="${userNo}">

	<div class="review_summary">
		<h2>내 리뷰 보기</h2>

		<div class="summary_container">
			<div>
				<p class="review_total_sum">${count}</p>
				<p>작성한 리뷰</p>
			</div>
			<hr class="divider_line">
			<div>
				<p class="review_total_rate">${average}</p>
				<p>별점 평균</p>
			</div>
			<hr class="divider_line">
			<div class="review_rate">
				<p id="review_rate_1">${genreList[0].avgGradePoint}</p>
				<p id="review_genre_1">${genreList[0].genreName}</p>
			</div>
			<div class="review_rate">
				<p id="review_rate_2">${genreList[1].avgGradePoint}</p>
				<p id="review_genre_2">${genreList[1].genreName}</p>
			</div>
			<div class="review_rate">
				<p id="review_rate_3">${genreList[2].avgGradePoint}</p>
				<p id="review_genre_3">${genreList[2].genreName}</p>
			</div>
			
            <div class="chart_section" style="width: 0px; height: 0px; display:none; margin:10px">
            	<%-- <div>
	            	<c:forEach var="genreList" items="${genreList}">
	            	${genreList.avgGradePoint}
	            	${genreList.genreName}
	            	</c:forEach>
            	</div> --%>
				<!--차트가 그려질 부분-->
				<canvas id="myChart"></canvas>
				<%
			    JSONArray jsonArray= new JSONArray();
				
				List<ReviewGenreDTO> genreList = (List<ReviewGenreDTO>) request.getAttribute("genreList");
				for (ReviewGenreDTO item : genreList) {
			        JSONObject jsonObject = new JSONObject();
					jsonObject.put(item.getGenreName(), item.getAvgGradePoint());
					jsonArray.add(jsonObject);
					System.out.println("값넣어짐");
				}

			    String jsonList = jsonArray.toString();
				System.out.println(jsonList);
			    %>
			<script type="text/javascript">
			var genreList = JSON.parse('<%=jsonList%>');
			console.log(genreList);// 우선 컨텍스트를 가져옵니다. 
			var labelsArray=[]; 
			var dataArray=[];
			var backgroundColorArray=[]; 
			
			for (var i = 0; i < genreList.length; i++) {
			    var jsonObject = genreList[i];
			    for (var key in jsonObject) {
			    	labelsArray.push(key);
			    	dataArray.push(jsonObject[key]);
			        backgroundColorArray.push(getRandomColor());
			    }
			}
			var ctx = document.getElementById("myChart").getContext('2d');
			/*
			- Chart를 생성하면서, 
			- ctx를 첫번째 argument로 넘겨주고, 
			- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
			*/
			var myChart = new Chart(ctx, {
			    type: 'doughnut',
			    data: {
			        labels: labelsArray,
			        datasets: [{
			            label: '# of Votes',
			            data: dataArray,
			            backgroundColor: backgroundColorArray,
			            borderWidth: 1
			        }]
			    },
			    options: {
			        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
			        scales: {
			            x: { grid: { display: false } },
			            y: { grid: { display: false } }
			        },
			        legend : {
			            display: false // 차트 제목 출력하지 않음
			        }
			    }
			});	
			//16진수 무작위 색 뽑기
			function getRandomColor() {
			    var letters = '0123456789ABCDEF';
			    var color = '#';
			    for (var i = 0; i < 6; i++) {
			        color += letters[Math.floor(Math.random() * 16)];
			    }
			    return color;
			}
			</script>
			</div>
		</div>
	</div>

	<div class="wrap_search">
		<div class="search_box">
			<label for="search_text"><i class="fas fa-search"></i></label> <input
				type="search" placeholder="영화, 내용, 작성일 검색" id="search_text">
		</div>

	</div>

	<div class="wrap_review">
		<!-- 동적생성될부분 -->
	</div>


</body>
</html>