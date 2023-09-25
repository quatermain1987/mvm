<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/Movivum/mypage/css/likegenre.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Document</title>
</head>
<body>
	<!-- likeGenreList객체를 직접 자바스크립트에 전역변수로 생성 -->
	<script>
		likeGenreArray = [];
	</script>
	<c:forEach items="${likeGenreList}" var="likeGenre">
		<script>
			likeGenreArray.push("${likeGenre}");
		</script>
	</c:forEach>
	<section>
		<h2>선호장르</h2>
		<form class="likewrap" action="likeGenreedit.do" id="likeform" method="post" >
			<button type="button" class="likebox" name="user_genre"
				value="액션">
				<div class="icon">
					<img src="mypage/img/icon/megaphone.png" alt="">
				</div>
				<span class="icon_title">액션</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="애니">
				<div class="icon">
					<img src="mypage/img/icon/cat.png" alt="">
				</div>
				<span class="icon_title">애니</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="코미디">
				<div class="icon">
					<img src="mypage/img/icon/smile.png" alt="">
				</div>
				<span class="icon_title">코미디</span>
			</button>
			<button type="button" class="likebox" name="genre" value="범죄">
				<div class="icon">
					<img src="mypage/img/icon/crime-scene.png" alt="">
				</div>
				<span class="icon_title">범죄</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="다큐멘터리">
				<div class="icon">
					<img src="mypage/img/icon/documentary.png" alt="">
				</div>
				<span class="icon_title">다큐멘터리</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="드라마">
				<div class="icon">
					<img src="mypage/img/icon/emotions.png" alt="">
				</div>
				<span class="icon_title">드라마</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="뮤지컬">
				<div class="icon">
					<img src="mypage/img/icon/theatre-curtains.png" alt="">
				</div>
				<span class="icon_title">뮤지컬</span>
			</button>
			<button type="button" class="likebox" name="genre" value="판타지">
				<div class="icon">
					<img src="mypage/img/icon/magic-wand.png" alt="">
				</div>
				<span class="icon_title">판타지</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="역사">
				<div class="icon">
					<img src="mypage/img/icon/history.png" alt="">
				</div>
				<span class="icon_title">역사</span>
			</button>
			<button type="button" class="likebox" name="genre" value="공포">
				<div class="icon">
					<img src="mypage/img/icon/ghost.png" alt="">
				</div>
				<span class="icon_title">공포</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="미스터리">
				<div class="icon">
					<img src="mypage/img/icon/question-mark.png" alt="">
				</div>
				<span class="icon_title">미스터리</span>
			</button>
			<button type="button" class="likebox" name="genre" value="로맨스">
				<div class="icon">
					<img src="mypage/img/icon/hearts.png" alt="">
				</div>
				<span class="icon_title">로맨스</span>
			</button>
			<button type="button" class="likebox" name="genre" value="SF">
				<div class="icon">
					<img src="mypage/img/icon/ufo.png" alt="">
				</div>
				<span class="icon_title">SF</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="스릴러">
				<div class="icon">
					<img src="mypage/img/icon/theatre-curtains.png" alt="">
				</div>
				<span class="icon_title">스릴러</span>
			</button>
			<button type="button" class="likebox" name="user_genre" value="전쟁">
				<div class="icon">
					<img src="mypage/img/icon/swords.png" alt="">
				</div>
				<span class="icon_title">전쟁</span>
			</button>
			<button type="button" class="likebox" name="genre" value="서부">
				<div class="icon">
					<img src="mypage/img/icon/western.png" alt="">
				</div>
				<span class="icon_title">서부</span>
			</button>
			<input type="hidden" id="genre1" name="genre1" value="">
			<input type="hidden" id="genre2" name="genre2" value="">
			<input type="hidden" id="genre3" name="genre3" value="">
			<button class="submit_button">**제출**</button>
		</form>
	</section>
</body>

<script src="/Movivum/mypage/js/likeGenre.js"></script>
</html>