<!--  
	작성자 : 박진아
	작성일자 : 2023.03.06
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
    <title>회원가입</title>
    <link rel="stylesheet" href="/Movivum/main/css/join.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
  </head>
<body>
  <header id="header">
    <div class="inner">
        <div class="wrap">
            <nav id="gnb">
                <div class="left"><a href="/Movivum/main.do" class="logo">MOVIVUM</a></div>
               <div class="right"><a href="/Movivum/login.do" name="login" class="login"><span class="material-symbols-outlined login_icon">face</span></a></div>
            </nav>
        </div>
    </div>
</header>
<div class="container">
      <div class="swiper join_swiper">
        <div class="slide-captions"></div>
        <div class="swiper-wrapper">
          <!-- step1 -->
          <div class="swiper-slide slide-1" data-title="회원가입" data-subtitle="이메일을 인증해주세요.">
            <form name="email_form" id="email_form" method="post">
              <!-- 이메일 -->
              <div class="join_row">
                <input type="text" name="user_email" id="user_email" placeholder="email@gmail.com">
                <div class="tip emailCheck alert">
                  <div class="tip_icon"><img src="main/img/alert-circle-outline.svg" alt=""></div>
                  <p class="tip_text">올바른 이메일 형식이 아닙니다.</p>
                </div>
              </div>
              <div class="join_row">
                <ul>
                  <li><input type="text" name="email_auth" id="email_auth" placeholder="인증키를 입력해주세요."></li>
                  <li><button type="button" class="sendEmail">인증키 받기</button></li>
                </ul>
                <div class="tip emailAuthCheck alert">
                  <div class="tip_icon"><img src="main/img/alert-circle-outline.svg" alt=""></div>
                  <p class="tip_text"></p>
                </div>
              </div>
            </form>
          </div>
          <!-- step2 -->
          <div class="swiper-slide slide-2" data-title="회원가입" data-subtitle="이름과 생년월일을 입력해주세요.">
            <form name="namebirth_form" id="namebirth_form">
              <!-- 이름 -->
              <div class="join_row">
                  <input type="text" name="user_name" id="user_name" placeholder="이름을 입력해주세요." required>
                  <div class="tip nameCheck">
                    <div class="tip_icon"><img src="main/img/help-circle-outline.svg" alt=""></div>
                    <p class="tip_text">이름은 최소 2글자 이상 입력해주세요.</p>
                  </div>
              </div>
              <!-- 생년월일 -->
              <div class="join_row">
                  <input type="text" name="user_birth" id="user_birth" minlength="8" maxlength="8" placeholder="생년월일을 입력해주세요(ex.19900101)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required>
                  <div class="tip birthCheck">
                    <div class="tip_icon"><img src="main/img/help-circle-outline.svg" alt=""></div>
                    <p class="tip_text">14세 미만은 회원가입할 수 없습니다.</p>
                  </div>
              </div>
              <ul class="checker">
                <li>
                  <input type="checkbox" name="checkAll" id="checkAll"><label for="checkAll">전체 약관에 동의합니다.</label>
                </li>
                <li>
                  <input type="checkbox" name="checkService" class="normal" id="checkService"><label for="checkService">서비스 이용약관에 동의합니다 (필수)</label>
                </li>
                <li>
                  <input type="checkbox" name="checkTerms" class="normal" id="checkTerms"><label for="checkTerms">개인정보 수집 및 이용에 대해 동의합니다 (필수)</label>
                </li>
              </ul>
            </form>
          </div>
          <!-- step3 -->
          <div class="swiper-slide slide-3" data-title="회원가입" data-subtitle="비밀번호를 입력해주세요.">
            <form action="" id="password_form" name="password_form">
                <!-- 비밀번호 -->
                <div class="join_row">
                  <input type="password" name="user_password" id="user_password" class="pwd" placeholder="비밀번호를 입력해주세요.">
                  <div class="tip pwdCheck">
                    <div class="tip_icon"><img src="main/img/help-circle-outline.svg" alt=""></div>
                    <p class="tip_text">영문,숫자,특수문자 중 2가지 이상을 사용하여 8~16자 입력해주세요.</p>
                  </div>
                </div>
              <!-- 비밀번호 확인 -->
              <div class="join_row">
                <input type="password" name="password_check" id="password_check" class="pwd" placeholder="비밀번호를 한 번 더 입력해주세요.">
                <div class="tip pwdCheck2 alert">
                  <div class="tip_icon"><img src="main/img/alert-circle-outline.svg" alt=""></div>
                  <p class="tip_text">비밀번호가 일치하지 않습니다.</p>
                </div>
              </div>
            </form>
          </div>
          <!-- step4 -->
          <div class="swiper-slide slide-4"data-title="회원가입" data-subtitle="닉네임과 프로필 사진을 설정해주세요.">
            <form action="" id="nickpic_form" name="nickpic_form">
              <!-- 프로필 이미지 선택 -->
              <div class="user_pic">
                <div class="pic"></div>
              </div>
            <!-- 닉네임 -->
            <div class="join_row">
              <input type="text" name="user_nickname" id="user_nickname" placeholder="닉네임을 입력해주세요.">
            </div>
          </form>
          </div>
          <!-- step5 -->
          <div class="swiper-slide slide-5" data-title="회원가입" data-subtitle="좋아하는 장르를 선택해주세요.">
            <form name="genre_form" id="genre_form"> 
              <div class="wrap">
                <button type="button" class="box" name="genre" value="27">
                    <div class="icon"><img src="main/img/icon/ghost.png" alt=""></div>
                    <span class="icon_title">공포</span>
                </button>
                <button type="button" class="box" name="genre" value="10749">
                    <div class="icon"><img src="main/img/icon/hearts.png" alt=""></div>
                    <span class="icon_title">로맨스</span>
                </button>
                <button type="button" class="box" name="genre" value="878">
                    <div class="icon"><img src="main/img/icon/ufo.png" alt=""></div>
                    <span class="icon_title">SF</span>
                </button>
                <button type="button" class="box" name="genre" value="80">
                    <div class="icon"><img src="main/img/icon/crime-scene.png" alt=""></div>
                    <span class="icon_title">범죄</span>
                </button>
                <button type="button" class="box" name="genre" value="37">
                    <div class="icon"><img src="main/img/icon/western.png" alt=""></div>
                    <span class="icon_title">서부</span>
                </button>
                <button type="button" class="box" name="genre" value="14">
                    <div class="icon"><img src="main/img/icon/magic-wand.png" alt=""></div>
                    <span class="icon_title">판타지</span>
                </button>
                <button type="button" class="box" name="user_genre" value="9648">
                    <div class="icon"><img src="main/img/icon/question-mark.png" alt=""></div>
                    <span class="icon_title">미스터리</span>
                </button>
                <button type="button" class="box" name="user_genre" value="35">
                    <div class="icon"><img src="main/img/icon/smile.png" alt=""></div>
                    <span class="icon_title">코미디</span>
                </button>
                <button type="button" class="box" name="user_genre" value="53">
                    <div class="icon"><img src="main/img/icon/theatre-curtains.png" alt=""></div>
                    <span class="icon_title">스릴러</span>
                </button>
                <button type="button" class="box" name="user_genre" value="10752">
                    <div class="icon"><img src="main/img/icon/swords.png" alt=""></div>
                    <span class="icon_title">전쟁</span>
                </button>
                <button type="button" class="box" name="user_genre" value="18">
                    <div class="icon"><img src="main/img/icon/emotions.png" alt=""></div>
                    <span class="icon_title">드라마</span>
                </button>
                <button type="button" class="box" name="user_genre" value="36">
                    <div class="icon"><img src="main/img/icon/history.png" alt=""></div>
                    <span class="icon_title">역사</span>
                </button>
                <button type="button" class="box" name="user_genre" value="99">
                    <div class="icon"><img src="main/img/icon/documentary.png" alt=""></div>
                    <span class="icon_title">다큐멘터리</span>
                </button>
                <button type="button" class="box" name="user_genre" value="16">
                    <div class="icon"><img src="main/img/icon/cat.png" alt=""></div>
                    <span class="icon_title">애니</span>
                </button>
                <button type="button" class="box" name="user_genre" value="28">
                    <div class="icon"><img src="main/img/icon/megaphone.png" alt=""></div>
                    <span class="icon_title">액션</span>
                </button>
                <button type="button" class="box" name="user_genre" value="10402">
                    <div class="icon"><img src="main/img/icon/theatre-curtains.png" alt=""></div>
                    <span class="icon_title">뮤지컬</span>
                </button>
              </div>
          </form>
          </div>
          <!-- step6 -->
          <div class="swiper-slide slide-6" data-title="회원가입" data-subtitle="가입완료">
            <div class="wrapper success">
              <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52"> <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none"/> <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8"/></svg>
              <p class="welcome_txt">환영합니다!</p>			
            </div>
          	<div class="wrapper fail">
              <p class="fail_emoji">;&#40;</p>
              <div class="fail_txt">
                <p>가입에 실패했습니다.</p>
              </div>
          	</div>
          </div>
          </div>
        <div class="swiper-button-prev">이전</div>
        <div class="swiper-button-next">다음</div>
        <div class="swiper-pagination"></div>
      </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
  <script src="/Movivum/main/js/join.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/validate.js/0.13.1/validate.min.js"></script>
</body>
</html>