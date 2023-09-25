<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <link rel="stylesheet" href="common/css/header.css">
    <script src="common/js/header.js"></script>
    
<!-- header -->
<header id="header">
     <div class="inner">
         <div class="wrap">
             <nav id="gnb">
                 <div class="left"><a href="main.html" class="logo">MOVIVUM</a></div>
                <div class="middle">
                     <input type="text" name="search" class="search" placeholder="영화 이름을 검색해보세요."><span class="material-symbols-outlined search_icon">search</span>
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
                <a href="/Movivum/login.do" name="login" class="login"><span class="material-symbols-outlined login_icon">face</span></a>
                </c:if>
                </div>
             </nav>
         </div>
     </div>
 </header>