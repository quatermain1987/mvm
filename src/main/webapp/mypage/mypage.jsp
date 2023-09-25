<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./mypage/css/mypage.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="./mypage/js/getAchieve.js"></script>
<script src="./mypage/js/mypage.js"></script>
<script src="https://kit.fontawesome.com/5ff835e817.js"	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<title>MOVIVUM</title>
</head>
<style>
</style>
<body>
	<input type="hidden" id="userNo" value="${sessionScope.userNo}" />
	<!-- 페이지 업버튼 -->
	<a href="#" class="pageup_btn">Page up</a>
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 네비게이터 -->
	<!-- 배너영역 -->
	<section class="banner">
		<div class="image_wrapper">
			<c:if test="${empty pic}">
				<img src="img/User_cicrle_light.svg" alt="유저이미지" width="150" id="banner_user_image"/>
			</c:if>
			<c:if test="${not empty pic}">
				<%-- <input type="hidden" name="pic" id="pic" value="${pic}">
				<canvas id="userPic" width="150" height="150"></canvas> --%>
				<img src="${pic}" alt="유저이미지" width="150" id="banner_user_image"/>
			</c:if>
			<div>
				<p id="nickname">
					<strong> ${nickname }</strong>
				</p>
				<p id="intro_self">
					<c:choose>
						<c:when test="${introduce==null}">여기에 자기소개해 보세요</c:when>
						<c:otherwise>${introduce }</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>
	</section>

			
		<!-- 메인영역 -->
		<main>
			<aside class="sidebar">
				<ul>
					<li><a href="#tab1" class="selected"><span
							class="material-symbols-rounded">home</span>마이홈</a></li>
					<li><a href="#tab2"><span class="material-symbols-rounded">favorite</span>보고싶어요</a></li>
					<li><a href="#tab3"><span class="material-symbols-rounded">chat</span>내
							리뷰 보기</a></li>
					<li><a href="#tab4"><span class="material-symbols-rounded">bolt</span>도전과제</a></li>
					<li><a href="#tab5"><span class="material-symbols-rounded">confirmation_number</span>선호장르</a></li>
					<li><a href="#tab6"><span class="material-symbols-rounded">mail</span>문의게시판</a></li>
					<li><a href="#tab7"><span class="material-symbols-rounded">settings</span>회원정보수정</a></li>
				</ul>

				<!-- <ul>
				<li><a href="#tab1" class="selected"><img src="img/Home_light.svg"/>마이홈</a></li>
				<li><a href="#tab2"><img src="img/Favorite_light.svg"/>보고싶어요</a></li>
				<li><a href="#tab3"><img src="img/Chat_light.svg"/>내 리뷰 보기</a></li>
				<li><a href="#tab4"><img src="img/lightning_light.svg"/>도전과제</a></li>
				<li><a href="#tab5"><img src="img/Ticket_use_light.svg"/>선호장르</a></li>
				<li><a href="#tab6"><img src="img/Message_light.svg"/>문의게시판</a></li>
				<li><a href="#tab7"><img src="img/Setting_line_light.svg"/>회원정보수정</a></li>
			</ul> -->
			</aside>
			
			
			<article>
				<div id="loadingPage">
					<span></span>
					<span></span>
					<span></span>
				</div>
				<div class="load_article">
				</div>
			</article>
		</main>
		
		
		<jsp:include page="footer.jsp"></jsp:include>
		
		
		<!-- 알림 팝업 -->
		<div class="popup_section" id="popup_notice">
			<div class="popup_body">
				<div class="success-checkmark">
				  <div class="check-icon">
				    <span class="icon-line line-tip"></span>
				    <span class="icon-line line-long"></span>
				    <div class="icon-circle"></div>
				    <div class="icon-fix"></div>
				  </div>
				</div>
				<div class="popup_content">정보가 수정되었습니다</div>
			</div>
		</div>
		<div class="popup_section" id="popup_check">
			<div class="popup_body">
				<div class="popup_content">본인 확인을 위해 비밀번호를 입력해주세요</div>
				<form action="#" class="popup_input">
					<input type="password" name="popup_check_input" id="popup_check_input">
					<div class="popup_button_section">
						<input type="submit" value="확인" onclick="popupCheckSuccess(event)">
						<input type="reset" value="처음화면으로" onclick="popupCheckCancle(event)">
					</div>
				</form>
			</div>
		</div>
		<div class="popup_section" id="popup_delete">
			<div class="popup_body">
				<div class="popup_content">정말 탈퇴하시겠어요? 같이 하고 싶은게 많아요
				<br>그래도 탈퇴하고 싶다면 "탈퇴할래요"를 입력해주세요</div>
				<form action="myprofiledelete.do" class="popup_input">
					<input type="text"  name="popup_delete_input" id="popup_delete_input" placeholder="탈퇴할래요">
					<div class="popup_button_section">
						<input type="submit" value="제출">
						<input type="reset" value="취소" onclick="popupDeleteCancle(event)">
					</div>
				</form>
			</div>
		</div>
</body>
</html>