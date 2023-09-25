<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIVUM</title>
<link rel="stylesheet" href="common/css/header.css">
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
							<a href="/Movivum/main.do" class="logo">MOVIVUM</a>
						</div>
						<div class="middle">
							<form action="/Movivum/search.do" name="search_form" id="search_form">
								<input type="text" name="search" class="search" id="search_input" 
									placeholder="영화 이름을 검색해보세요." autocomplete="off"><span
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
											<li><a href="/Movivum/mypage.do">마이페이지</a></li>
											<li><a href="/Movivum/notice.do">공지사항</a></li>
											<li><a href="/Movivum/mypage_FAQ.do">문의사항</a></li>
											<li><a href="/Movivum/logout.do">로그아웃</a></li>
											<c:if test="${sessionScope.user_no==1}">
												<li><a href="/Movivum/admin.do">관리자 전용</a></li>
											</c:if>
										</ul>
									</div>
								</div>
							</c:if>
							<c:if test="${empty sessionScope.user_no}">
								<a href="/Movivum/login.do" name="login" class="login"><span
									class="material-symbols-outlined login_icon">face</span></a>
							</c:if>
							<c:if test="${!empty sessionScope.user_no}">
			                    <div class="mymenu">
			                        <button type="button" class="mymenu_btn">
			                            <div class="mymenu_pic">
			                                <img src="${sessionScope.user_pic}" alt="">
			                            </div>
			                        </button>
			                        <div class="mymenu_sub">
			                            <ul>
			                                <li><a href="/Movivum/mypage.do">마이페이지</a></li>
			                                <li><a href="/Movivum/notice.do">공지사항</a></li>
			                                <li><a href="/Movivum/mypage_FAQ.do">문의사항</a></li>
			                                <li><a href="/Movivum/logout.do">로그아웃</a></li>
			                                <c:if test="${sessionScope.user_no==1}">
			                                	<li><a href="/Movivum/admin.do">관리자 전용</a></li>
			                                </c:if>
			                            </ul>
			                        </div>
			                    </div>
			                </c:if>
						</div>
					</nav>
				</div>
                <div id="prevSearch_wrap">
                    <div id="prevSearch">
                        <ul id="PSul">
                        </ul>
                        <div id="delAll_wrap">
                            <h3>이전 검색어 목록</h3>
                            <button id="delAll">전체삭제</button>
                        </div>
                    </div>
                </div>
			</div>
		</div>
	</header>
	<script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="common/js/header.js"></script>
</body>
</html>