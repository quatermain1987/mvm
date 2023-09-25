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
<link rel="stylesheet" href="./mypage/css/myhome.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Document</title>
</head>
<body>
	<section>
		<h2>내 리뷰 보기</h2>
		<div class="wrap_review">
			<c:if test="${not empty reviewList}">
				<c:forEach var="review" items="${reviewList}">
					<article class="review_card"
						<%-- onclick="createClone(event,'http://localhost:8090/Movivum/review_detail.do?review_no=${reviewList.review_no}')" --%>
						onclick="location.href='http://localhost:8090/Movivum/review_detail.do?review_no=${review.review_no}'"
						>
						<!-- 리뷰1행 -->
						<div class="user_wrap">
							<span class=""> <span>
								<img class="user_picture" src="${review.userPic}"></span>
								<span class="nickname">${nickname}</span>
							</span>
						</div>
						<!-- 리뷰2행 -->
						<div class="grade_wrap">
							<span><b>${review.movTitle}</b></span> <span class="star"><i
								class="fas fa-regular fa-star"></i>&nbsp;${review.movGrade}</span>
						</div>
						<!-- 리뷰3행 -->
						<div class="content_wrap">${review.reviewContent}</div>
						<!-- 리뷰4행 -->
						<div class="bottom_wrap">
							<span><i class="fa-regular fa-thumbs-up"></i>&nbsp;${review.recommCount}</span>
							<span><fmt:formatDate value="${review.reviewDate}"
									pattern="yyyy.MM.dd" /></span>
						</div>
					</article>
				</c:forEach>
			</c:if>

			<c:if test="${empty reviewList}">
				<div class="review_container">
					<h1>작성한 리뷰가 없어요 XD</h1>
					<br> <a href="#"><u>지금 작성하러 가기</u></a>
				</div>
			</c:if>
			<!-- <article class="review_card">임시</article>
            <article class="review_card">임시</article> -->
		</div>
	</section>

	<section>
		<h2>도전과제</h2>
		<div class="summary_container">
			<div>
				<p class="achievements_total_sum">${count_num}</p>
				<p>달성개수</p>
			</div>
			<hr class="divider_line">
			<div>
				<p class="achievements_total_rate">${count_rate}</p>
				<p>달성률</p>
			</div>
		</div>
	</section>

	<section>
		<h2>선호장르</h2>
		<div class="likewrap">
			<c:forEach var="LikeGenre" items="${userLikeGenreList}">
				<c:choose>
					<c:when test="${LikeGenre=='액션'}">
						<button type="button" class="likebox" name="user_genre" value="28">
							<div class="icon">
								<img src="mypage/img/icon/megaphone.png" alt="">
							</div>
							<span class="icon_title">액션</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='모험'}">
					모험
					</c:when>
					<c:when test="${LikeGenre=='애니메이션'}">
						<button type="button" class="likebox" name="user_genre" value="16">
							<div class="icon">
								<img src="mypage/img/icon/cat.png" alt="">
							</div>
							<span class="icon_title">애니</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='코미디'}">
						<button type="button" class="likebox" name="user_genre" value="35">
							<div class="icon">
								<img src="mypage/img/icon/smile.png" alt="">
							</div>
							<span class="icon_title">코미디</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='범죄'}">
						<button type="button" class="likebox" name="genre" value="80">
							<div class="icon">
								<img src="mypage/img/icon/crime-scene.png" alt="">
							</div>
							<span class="icon_title">범죄</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='다큐멘터리'}">
						<button type="button" class="likebox" name="user_genre" value="99">
							<div class="icon">
								<img src="mypage/img/icon/documentary.png" alt="">
							</div>
							<span class="icon_title">다큐멘터리</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='드라마'}">
						<button type="button" class="likebox" name="user_genre" value="18">
							<div class="icon">
								<img src="mypage/img/icon/emotions.png" alt="">
							</div>
							<span class="icon_title">드라마</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='가족'}">
						<button type="button" class="likebox" name="user_genre" value="10402">
							<div class="icon">
								<img src="mypage/img/icon/theatre-curtains.png" alt="">
							</div>
							<span class="icon_title">뮤지컬</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='판타지'}">
						<button type="button" class="likebox" name="genre" value="14">
							<div class="icon">
								<img src="mypage/img/icon/magic-wand.png" alt="">
							</div>
							<span class="icon_title">판타지</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='역사'}">
						<button type="button" class="likebox" name="user_genre" value="36">
							<div class="icon">
								<img src="mypage/img/icon/history.png" alt="">
							</div>
							<span class="icon_title">역사</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='공포'}">
						<button type="button" class="likebox" name="genre" value="27">
							<div class="icon">
								<img src="mypage/img/icon/ghost.png" alt="">
							</div>
							<span class="icon_title">공포</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='음악'}">
					음악
					</c:when>
					<c:when test="${LikeGenre=='미스터리'}">
						<button type="button" class="likebox" name="user_genre" value="9648">
							<div class="icon">
								<img src="mypage/img/icon/question-mark.png" alt="">
							</div>
							<span class="icon_title">미스터리</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='로맨스'}">
						<button type="button" class="likebox" name="genre" value="10749">
							<div class="icon">
								<img src="mypage/img/icon/hearts.png" alt="">
							</div>
							<span class="icon_title">로맨스</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='SF'}">
						<button type="button" class="likebox" name="genre" value="878">
							<div class="icon">
								<img src="mypage/img/icon/ufo.png" alt="">
							</div>
							<span class="icon_title">SF</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='TV영화'}">
					tv영화
					</c:when>
					<c:when test="${LikeGenre=='스릴러'}">
						<button type="button" class="likebox" name="user_genre" value="53">
							<div class="icon">
								<img src="mypage/img/icon/theatre-curtains.png" alt="">
							</div>
							<span class="icon_title">스릴러</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='전쟁'}">
						<button type="button" class="likebox" name="user_genre" value="10752">
							<div class="icon">
								<img src="mypage/img/icon/swords.png" alt="">
							</div>
							<span class="icon_title">전쟁</span>
						</button>
					</c:when>
					<c:when test="${LikeGenre=='서부'}">
						<button type="button" class="likebox" name="genre" value="37">
							<div class="icon">
								<img src="mypage/img/icon/western.png" alt="">
							</div>
							<span class="icon_title">서부</span>
						</button>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>

	</section>
</body>

</html>