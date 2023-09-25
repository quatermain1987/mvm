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
<link rel="stylesheet" href="./mypage/css/achivement.css">
<script src="https://kit.fontawesome.com/5ff835e817.js"	crossorigin="anonymous"></script>
<title>도전과제</title>
</head>
<body>
	<section class="achievements_summary">
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

	<section class="achievements_latest">
		<h2>최근달성</h2>
		<c:forEach var="achieve" items="${RecentachieveList}">
			<c:choose>
				<c:when test="${achieve.achieveNo ==0}">
					<div id="achievements_everymanslover"
						class="achievements_container">
						<div>
							<img src="mypage/img/happy_light.svg" />
						</div>
						<div>
							<h2>즐거운 나의집</h2>
							<br>
							<P>
								마이페이지에 첫 접속<br>어서오세요 고생많으셨어요
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==1}">
					<div id="achievements_newbie" class="achievements_container">
						<div class="img_container">
							<i class="fa-solid fa-baby"></i>
						</div>
						<div>
							<h2>뉴비</h2>
							<br>
							<P>
								첫 별점, 리뷰, 찜을 작성하셨어요<br>첫걸음부터 차근차근
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>

				</c:when>
				<c:when test="${achieve.achieveNo ==2}">
					<div id="achievements_horrormania" class="achievements_container">
						<div class="img_container">
							<img src="mypage/img/Wow_light.svg" />
						</div>
						<div>
							<h2>호러 매니아</h2>
							<br>
							<P>
								공포/스릴러/범죄/미스터리/추리 리뷰를 작성하셨어요<br>ㅎㄷㄷ
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==3}">
					<div id="achievements_alien" class="achievements_container">
						<div>
							<i class="fab fa-reddit-alien"></i>
						</div>
						<div>
							<h2>외계인</h2>
							<br>
							<P>
								SF장르 영화 리뷰 X개 작성하셨어요<br>빵상 깨랑까랑 빵빵 또로로롱
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==4}">
					<div id="achievements_bully" class="achievements_container">
						<div>
							<img src="mypage/img/thumb_down.svg" />
						</div>
						<div>
							<h2>깍쟁이</h2>
							<br>
							<P>
								영화별점 1점를 X개 부여하셨어요<br>내가 원했던건 이게 아니야!
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==5}">
					<div id="achievements_poomelier" class="achievements_container">
						<div>
							<i class="fa-solid fa-poo"></i>
						</div>
						<div>
							<h2>똥믈리에</h2>
							<br>
							<P>
								별점1점 이하영화에 리뷰X개 작성하셨어요<br>음 바로 이 맛이야!
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div id="achievements_empty" class="achievements_container">
						<div>
							<h1>최근달성한 도전과제가 없어요</h1>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 
        <div id="achievements_empty" class="achievements_container">
            <div>
                <h1>최근달성한 도전과제가 없어요</h1>
            </div>
        </div> -->
	</section>


	<section class="achievements_inprogress">
		<h2>진행중</h2>
		<c:forEach var="achieve" items="${notAchieveList}">
			<c:choose>
				<c:when test="${achieve.achieveNo ==0}">
					<div id="achievements_everymanslover"
						class="achievements_container">
						<div>
							<img src="mypage/img/happy_light.svg" />
						</div>
						<div>
							<h2>만인의 연인</h2>
							<br>
							<P>
								추천을 X개나 받으셨어요<br>모두가 회원님을 사랑해요!
							</P>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==1}">
					<div id="achievements_newbie" class="achievements_container">
						<div class="img_container">
							<i class="fa-solid fa-baby"></i>
						</div>
						<div>
							<h2>뉴비</h2>
							<br>
							<P>
								첫 별점, 리뷰, 찜을 작성하셨어요<br>첫걸음부터 차근차근
							</P>
						</div>
					</div>

				</c:when>
				<c:when test="${achieve.achieveNo ==2}">
					<div id="achievements_horrormania" class="achievements_container">
						<div class="img_container">
							<img src="mypage/img/Wow_light.svg" />
						</div>
						<div>
							<h2>호러 매니아</h2>
							<br>
							<P>
								공포/스릴러/범죄/미스터리/추리 리뷰를 작성하셨어요<br>ㅎㄷㄷ
							</P>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==3}">
					<div id="achievements_alien" class="achievements_container">
						<div>
							<i class="fab fa-reddit-alien"></i>
						</div>
						<div>
							<h2>외계인</h2>
							<br>
							<P>
								SF장르 영화 리뷰 X개 작성하셨어요<br>빵상 깨랑까랑 빵빵 또로로롱
							</P>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==4}">
					<div id="achievements_bully" class="achievements_container">
						<div>
							<img src="mypage/img/thumb_down.svg" />
						</div>
						<div>
							<h2>깍쟁이</h2>
							<br>
							<P>
								영화별점 1점를 X개 부여하셨어요<br>내가 원했던건 이게 아니야!
							</P>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==5}">
					<div id="achievements_poomelier" class="achievements_container">
						<div>
							<i class="fa-solid fa-poo"></i>
						</div>
						<div>
							<h2>똥믈리에</h2>
							<br>
							<P>
								별점1점 이하영화에 리뷰X개 작성하셨어요<br>음 바로 이 맛이야!
							</P>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div id="achievements_empty" class="achievements_container">
						<div>
							<h1>달성한 도전과제가 없어요</h1>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>

	</section>

	<section class="achievements_completed">
		<h2>달성완료</h2>
		<c:forEach var="achieve" items="${achieveList}">
			<c:choose>
				<c:when test="${achieve.achieveNo ==0}">
					<div id="achievements_everymanslover"
						class="achievements_container">
						<div>
							<img src="mypage/img/happy_light.svg" />
						</div>
						<div>
							<h2>만인의 연인</h2>
							<br>
							<P>
								추천을 X개나 받으셨어요<br>모두가 회원님을 사랑해요!
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==1}">
					<div id="achievements_newbie" class="achievements_container">
						<div class="img_container">
							<i class="fa-solid fa-baby"></i>
						</div>
						<div>
							<h2>뉴비</h2>
							<br>
							<P>
								첫 별점, 리뷰, 찜을 작성하셨어요<br>첫걸음부터 차근차근
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>

				</c:when>
				<c:when test="${achieve.achieveNo ==2}">
					<div id="achievements_horrormania" class="achievements_container">
						<div class="img_container">
							<img src="mypage/img/Wow_light.svg" />
						</div>
						<div>
							<h2>호러 매니아</h2>
							<br>
							<P>
								공포/스릴러/범죄/미스터리/추리 리뷰를 작성하셨어요<br>ㅎㄷㄷ
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>

							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==3}">
					<div id="achievements_alien" class="achievements_container">
						<div>
							<i class="fab fa-reddit-alien"></i>
						</div>
						<div>
							<h2>외계인</h2>
							<br>
							<P>
								SF장르 영화 리뷰 X개 작성하셨어요<br>빵상 깨랑까랑 빵빵 또로로롱
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==4}">
					<div id="achievements_bully" class="achievements_container">
						<div>
							<img src="mypage/img/thumb_down.svg" />
						</div>
						<div>
							<h2>깍쟁이</h2>
							<br>
							<P>
								영화별점 1점를 X개 부여하셨어요<br>내가 원했던건 이게 아니야!
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:when test="${achieve.achieveNo ==5}">
					<div id="achievements_poomelier" class="achievements_container">
						<div>
							<i class="fa-solid fa-poo"></i>
						</div>
						<div>
							<h2>똥믈리에</h2>
							<br>
							<P>
								별점1점 이하영화에 리뷰X개 작성하셨어요<br>음 바로 이 맛이야!
							</P>
						</div>
						<div class="achievements_date_div">
							<p class="achievements_date">
								<fmt:formatDate value="${achieve.getDate}"
									pattern="yyyy년MM월dd일에 획득" />
							</p>
							<p>회원님 중 ${achieve.avg*100}%만이 획득하셨어요</p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div id="achievements_empty" class="achievements_container">
						<div>
							<h1>달성한 도전과제가 없어요</h1>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</section>


</body>
</html>