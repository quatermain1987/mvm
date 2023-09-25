<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>MOVIVUM | User Admin</title>
 <link rel="stylesheet" href="/Movivum/admin/css/memberDetailAdmin.css">
 <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
 <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>
   <div id="wrapper" class="content">
       <aside id="sidenav" class="content">
           <div class="inner">
               <ul class="lnb">
                   <li><a href="/Movivum/admin.do"><div class="menu_icon"><img src="/Movivum/admin/img/chart.svg" alt=""></div>오늘의 주요 현황</a></li>
                   <li><a href="/Movivum/userAdmin.do"><div class="menu_icon"><img src="/Movivum/admin/img/settings.svg" alt=""></div>회원관리</a></li>
                   <li><a href="/Movivum/notice.do"><div class="menu_icon"><img src="/Movivum/admin/img/megaphone.svg" alt=""></div>공지사항</a></li>
                   <li><a href="#"><div class="menu_icon"><img src="/Movivum/admin/img/mail.svg" alt=""></div>문의내역</a></li>
                   <li><a href="/Movivum/report.do"><div class="menu_icon"><img src="/Movivum/admin/img/ban.svg" alt=""></div>신고내역</a></li>
               </ul>
               <div class="account">
                   <button type="button" class="exit_btn" onclick="location.href='main.do'">
                       <img src="/Movivum/admin/img/exit.svg" alt="">
                   </button>
               </div>
           </div>
       </aside>
       <!-- 본문영역 -->
       <section id="user_detail" class="content">
           <div class="inner">
                <div class="wrap">
                    <form name="userdetailForm" id="userdetailForm" method="post" action="memberUpdateAdmin.do" enctype="multipart/form-data">
                        <div class="row picRow">
                            <div class="pic">
                                <img class="img" src="${user.user_pic}" alt="">
                                <div class="picbtnwrap">
                                    <label for="user_pic" id="picChangebtn"><img src="/Movivum/admin/img/camera.svg" alt=""></label>
                                    <input type="file" name="user_pic" id="user_pic" value="${user.user_pic}" accept=".jpg, .jpeg, .png">
                                </div>
                            </div>
                            
                        </div>
                        <!-- 이메일 -->
                        <div class="row emailRow">
                            <label for="user_email">이메일</label>
                            <input type="text" name="user_email" id="user_email" placeholder="email@gmail.com" value="${user.user_email}" required>
                            <div class="tip emailCheck alert">
                                <div class="tip_icon"><img src="/Movivum/admin/img/alert-circle-outline.svg" alt=""></div>
                                <p class="tip_text">이메일을 입력해주세요.</p>
                            </div>
                        </div>
                        <!-- 닉네임 -->
                        <div class="row nicknameRow">
                            <label for="user_nickname">닉네임</label>
                            <input type="text" name="user_nickname" id="user_nickname" placeholder="닉네임을 입력해주세요." value="${user.user_nickname}" required>
                        </div>
                        <!-- 이름 -->
                        <div class="row nameRow">
                            <label for="user_name">이름</label>
                            <input type="text" name="user_name" id="user_name" placeholder="이름을 입력해주세요." value="${user.user_name}" required>
                            <div class="tip nameCheck">
                                <div class="tip_icon"><img src="/Movivum/admin/img/help-circle-outline.svg" alt=""></div>
                                <p class="tip_text">이름은 최소 2글자 이상 입력해주세요.</p>
                            </div>
                        </div>
                        <!-- 생년월일 -->
                        <div class="row birthRow">
                            <label for="user_birth">생년월일</label>
                            <input type="text" name="user_birth" id="user_birth" minlength="8" maxlength="8" value="${user.user_birth}" placeholder="생년월일을 입력해주세요(ex.19900101)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required disabled>
                            <div class="tip birthCheck">
                                <div class="tip_icon"><img src="/Movivum/admin/img/help-circle-outline.svg" alt=""></div>
                                <p class="tip_text">14세 미만은 회원가입할 수 없습니다.</p>
                            </div>
                        </div>
                        <!-- 비밀번호 -->
                        <div class="row passwordRow">
                            <label for="user_password">비밀번호</label>
                            <input type="password" name="user_password" id="user_password" placeholder="비밀번호를 입력해주세요." value="${user.user_password}" required>
                            <div class="tip pwdCheck">
                                <div class="tip_icon"><img src="/Movivum/admin/img/help-circle-outline.svg" alt=""></div>
                                <p class="tip_text">영문,숫자,특수문자 중 2가지 이상을 사용하여 8~16자 입력해주세요.</p>
                            </div>
                        </div>
                        <!-- 사용자 상태 및 로그인 횟수, 신고 횟수 -->
                        <div class="row othersRow">
                            <div class="box">
                                <label for="user_status" class="user_status_lable">회원상태</label>
                                <select name="user_status" id="user_status">
								  <option value="-1" ${user.user_status == '-1'?'selected' : ''}>활동중지</option>
								  <option value="0" ${user.user_status == '0'?'selected' : ''}>회원탈퇴</option>
								  <option value="1" ${user.user_status == '1'?'selected' : ''}>활동중</option>
								</select>
                            </div>
                            <div class="box">
                                <label for="user_logincount">로그인횟수</label>
                                <input type="text" name="user_logincount" id="user_logincount" value="${user.user_logincount}" disabled>
                            </div>
                            <div class="box">
                                <label for="user_reportcount">신고당한 횟수</label>
                                <input type="text" name="user_reportcount" id="user_reportcount" value="${reportCount}" disabled>
                            </div>
                            <input type="hidden" name="user_no" value="${user.user_no}">
                            <input type="hidden" name="num" value="${num}">
                            <input type="hidden" name="pageNum" value="${pageNum}">
                        </div>
                        <div class="btnwrap">
                            <button type="button" id="cancelbtn" name="cancelbtn" onClick="history.back()">취소</button>
                            <button type="submit" id="confirmbtn" name="confirmbtn">완료</button>
                        </div>
                    </form>
                </div>
            </div>
       </section>
   </div>
   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   <script src="/Movivum/admin/js/memberDetailAdmin.js?ver=2.0"></script>
   <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
</body>
</html>