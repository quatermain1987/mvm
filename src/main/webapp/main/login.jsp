<!--  
	작성자 : 박진아
	작성일자 : 2023.03.24
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
 <head>
 <html lang="ko">
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>로그인</title>
 <link rel="stylesheet" href="/Movivum/main/css/login.css">
 <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
</head>
<body>
<header id="header">
 <div class="inner">
     <div class="wrap">
         <nav id="gnb">
             <div class="left"><a href="/Movivum/main.do" class="logo">MOVIVUM</a></div>
            <div class="right"></div>
         </nav>
     </div>
 </div>
</header>
<div class="container">
   <div class="swiper join_swiper">
     <div class="slide-captions"></div>
     <div class="swiper-wrapper">
       <!-- step1 -->
       <div class="swiper-slide" data-title="로그인" data-subtitle="로그인정보를 입력해주세요.">
         <form name="login_form" id="login_form" method="post" action="loginProcAction.do">
           <!-- 이메일 -->
           <div class="join_row">
             <input type="text" name="user_email" id="user_email" placeholder="email@gmail.com" value="">
             <div class="tip emailCheck alert">
               <div class="tip_icon"><img src="img/alert-circle-outline.svg" alt=""></div>
               <p class="tip_text emailEmpty">이메일을 입력해주세요.</p>
             </div>
             <div class="tip loginCheck alert">
               <div class="tip_icon"><img src="img/alert-circle-outline.svg" alt=""></div>
               <p class="tip_text loginFail">가입되지 않은 이메일 입니다.</p>
             </div>
             
           </div>
           <div class="join_row">
               <input type="password" name="user_password" id="user_password" placeholder="비밀번호를 입력해주세요." value="">
               <div class="tip passwordCheck alert">
                 <div class="tip_icon"><img src="img/alert-circle-outline.svg" alt=""></div>
                 <p class="tip_text">비밀번호를 입력해주세요.</p>
               </div>
             </div>
                <button type="button" class="login_button" onclick="loginCheck()">로그인</button>
         </form>
         <div class="findNjoin">
            <a href="#" class="find_password">비밀번호를 잊어버리셨나요?</a>
            <p class="join">아직 계정이 없으신가요?<a href="/Movivum/join.do" class="join_btn">회원가입</a></p>
        </div>
       </div>
   </div>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="/Movivum/main/js/login.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/validate.js/0.13.1/validate.min.js"></script>
</body>
</html>