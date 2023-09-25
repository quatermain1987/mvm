<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIVUM</title>
<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>
	<!-- header -->
	<header id="header">
		<div id="fix">
			<div class="inner">
				<div class="wrap">
					<nav id="gnb">
						<div class="left">
							<a href="main.html" class="logo">MOVIVUM</a>
						</div>
						<div class="middle">
							<form action="/Movivum/search.do" name="search_form">
								<input type="text" name="search" class="search"
									placeholder="영화 이름을 검색해보세요."><span
									class="material-symbols-outlined search_icon">search</span>
							</form>
						</div>
						<div class="right">
							<c:if test="${!empty sessionScope.user_no}">
								<div class="mymenu">
									<button type="button" class="mymenu_btn">
										<div class="mymenu_pic">
											<img src="${sessionScope.user_pic}" alt="">
										</div>
									</button>
									<div class="mymenu_sub">
										<ul>
											<li><a href="#">마이페이지</a></li>
											<li><a href="#">공지사항</a></li>
											<li><a href="#">문의사항</a></li>
											<li><a href="/Movivum/logout.do">로그아웃</a></li>
										</ul>
									</div>
								</div>
							</c:if>
							<c:if test="${empty sessionScope.user_no}">
								<a href="/Movivum/login.do" name="login" class="login"><span
									class="material-symbols-outlined login_icon">face</span></a>
							</c:if>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
<script type="text/javascript">
	$('#search_icon').on('click',function(){
		document.search_form.submit();
	})
</script>
<script src="./js/header.js"></script>
</body>
</html>