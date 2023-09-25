<!--  
  작성자 : 박진아
  작성일자 : 2023.03.06
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>MOVIVUM</title>
 <link rel="stylesheet" href="/Movivum/main/css/main.css">
 <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
 <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>
 <header id="header">
     <div class="inner">
         <div class="wrap">
             <nav id="gnb">
                 <div class="left"><a href="main.html" class="logo">MOVIVUM</a></div>
                <div class="middle">
                     <input type="text" name="search" class="search" placeholder="영화 이름을 검색해보세요.">
                     <a href="/Movivum/search.do" class="search_btn"><img src="main/img/search-outline.svg" class="search_icon"></a>
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
                                	<li>
                                		<a href="/Movivum/admin.do">관리자 전용</a>
                                	</li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.user_no}">
                <a href="/Movivum/login.do" name="login" class="login"><img src="main/img/face_icon.svg" class="login_icon"></a>
                </c:if>
                </div>
             </nav>
         </div>
     </div>
 </header>
 <%-- <jsp:include page="../common/header.jsp" flush="false"/> --%>
 <section id="trailer" class="content">
     <div class="inner">
        <div class="backdrop"></div>
        <div class="backdropMask"></div>
        <div class="logo_wrap">
            <div class="backdropLogo"><img src="https://www.themoviedb.org/t/p/original/24dIhRKjLnYRanA2Mo0ycZfObUp.png" alt=""></div>
            <button type="button" class="playbtn">
                <div class="btnicon"><img src="main/img/play.svg"></div>
            </button>
        </div>       
     </div>
 </section>

 <!--트레일러 슬라이드 -->
 <section id="trailer_list" class="content">
    <div class="inner">
        <h2 class="title">관련 영화</h2>
        <div class="swiper" id="trailer_swiper">
            <div class="swiper-wrapper" id="trailer_wrap">
            </div>
        </div>
    </div>
</section>

 <!-- main content -->
 <section id="popular" class="content">
     <div class="inner">
         <h2 class="title">영화 인기순</h2>
         <div class="swiper">
             <div class="swiper-wrapper" id="popular_wrap">
             </div>
             <div class="swiper-btn-wrap">
                 <div class="swiper-button-next"></div>
                 <div class="swiper-button-prev"></div>
             </div>
         </div>
     </div>
 </section>
 <section id="banner" class="content">
     <div class="inner">
         <span class="bg"></span>
         <div class="pic"></div>
     </div>
 </section>

 <section id="recommend" class="content">
     <div class="inner">
         <h2 class="title">영화 추천순</h2>
         <div class="swiper">
             <div class="swiper-wrapper" id="recommend_wrap">
             </div>
             <div class="swiper-button-next"></div>
             <div class="swiper-button-prev"></div>
         </div>
     </div>
 </section>
 <section id="recent" class="content">
     <div class="inner">
         <h2 class="title">영화 최신순</h2>
         <div class="swiper">
             <div class="swiper-wrapper" id="now_playing_wrap">
             </div>
             <div class="swiper-button-next"></div>
             <div class="swiper-button-prev"></div>
         </div>
     </div>
 </section>
 
 <!-- footer -->
 <footer id="footer">
     <div class="inner">
         <nav class="wrap">
             <div class="box">
                 <ul id="footer_gnb">
                     <li><a href="#">서비스이용약관</a></li>
                     <li><a href="#">개인정보처리방침</a></li>
                     <li><a href="#">이용약관</a></li>
                     <li><a href="#">고객센터</a></li>
                     <li><a href="#">채용정보</a></li>
                 </ul>
                 <div class="info">
                     <p>고객센터 | cs@movivum.co.kr /02-515-9985 (유료)</p>
                     <p>제휴 및 대외 협력 | https://movivum.team/contact</p>
                     <p>주식회사 아무개 | 대표 홍길동 | 서울특별시 서초구 강남대로 343 신덕빌딩 3층</p>
                     <p><a href="main.html">MOVIVUM</a> © 2023 by MOVIVUM, Inc. All rights reserved.</p>
                 </div>
             </div>
             <div class="box">
                 <ul class="sns">
                     <li><a href="#"><i class="xi-kakaotalk"></i></a></li>
                     <li><a href="#"><i class="xi-instagram"></i></a></li>
                     <li><a href="#"><i class="xi-share-alt"></i></a></li>
                 </ul>
             </div>

         </nav>
     </div>
 </footer>

 <!-- 비디오모달창 -->
 <div class="blackbg"></div>
 <div class="trailer_video">
     <div class="inner">
        <button class="closebtn" type="button"><img src="img/close-outline.svg" alt=""></button>
        <iframe src="https://www.youtube.com/embed/ly3QrgEZaQY?controls=0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture;" allowfullscreen></iframe></div>
     </div>
 </div>
 <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
 <script src="/Movivum/main/js/main.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
</body>
</html>