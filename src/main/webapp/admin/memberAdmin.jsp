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
 <link rel="stylesheet" href="/Movivum/admin/css/memberAdmin.css?ver=3.0">
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
       <section id="user_list" class="content">
           <div class="inner">
               <h2 class="title">회원 정보 수정</h2>
               <div class="wrap">
               <div class="top">
                   <h3 class="subtitle">전체 회원 수 ${pgList.count}</h3>
                   <form name="searchForm" id="searchForm" action="memberAdmin.do">
                       <select name="searchOption" id="searchOption">
                           <option disabled selected>분류</option>
                           <option value="user_name">이름</option>
                           <option value="user_email">이메일</option>
                           <option value="user_nickname">닉네임</option>
                       </select>
                       <div class="search_wrap">
                           <input type="text" name="searchText" id="searchText" placeholder="검색어를 입력해주세요">
                           <button type="button" name="searchBtn" id="searchBtn"><img src="/Movivum/admin/img/search-outline.svg" alt=""></button>
                       </div>
                   </form>
               </div>
               <div class="table">
                   <div class="userinfo_wrap">
                   <ul class="categories">
                       <li class="num">No</li>
                       <li class="user_name">이름</li>
                       <li class="user_nickname">닉네임</li>
                       <li class="user_email">이메일</li>
                       <li class="user_reportcount">신고횟수</li>                       
					   <li class="user_regdate">가입일</li>
                       <li class="user_status">비고</li>
                   </ul>
                   <c:if test="${pgList.count==0}">
					<div class="not_exist">
                        <p>회원 정보를 찾을 수 없습니다.</p>
                    </div>
				   </c:if>
                   <c:if test="${pgList.count>0}">  
                   <c:set var="number" value="${pgList.number}"/>
                    <c:forEach var="entry" items="${userMap}">
				  	<c:set var="u" value="${entry.key}" />
				  	<c:set var="reportcount" value="${entry.value}" />
                    <a href="/Movivum/memberDetailAdmin.do?number=${number}&pageNum=${pgList.currentPage}&user_no=${u.user_no}">
	                    <ul class="userinfo">
   							<li>
   								<c:out value="${number}"/>  
   								<c:set var="number" value="${number-1}"/>
   							</li>
	                        <li>${u.user_name}</li>
	                        <li>${u.user_nickname}</li>
	                        <li>${u.user_email}</li>
	                        <li>${reportcount}</li>
	                        <li><fmt:formatDate value="${u.user_regdate}" timeStyle="medium" pattern="yyyy.MM.dd"/></li>
	                        <c:if test="${u.user_status=='1'}"><li class="status1">활동중</li></c:if>
                        	<c:if test="${u.user_status=='0'}"><li class="status-1">회원탈퇴</li></c:if>
                        	<c:if test="${u.user_status=='-1'}"><li class="status0">활동정지</li></c:if>
	                    </ul>
	                    </a>
                    </c:forEach>
                   </c:if>
               	</div>
               </div>
               <div class="pagenation">
                <c:if test="${pgList.startPage > pgList.blockSize}">
					<a href="/Movivum/memberAdmin.do?pageNum=${pgList.startPage-pgList.blockSize}&searchOption=${searchOption}&searchText=${searchText}"><div class="previcon"><img src="/Movivum/admin/img/arrow-left.svg" alt=""></div></a>
				</c:if>
				<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					<a href="/Movivum/memberAdmin.do?pageNum=${i}&searchOption=${searchOption}&searchText=${searchText}">
						<c:if test="${pgList.currentPage==i}"><b>${i}</b></c:if>
						<c:if test="${pgList.currentPage!=i}">${i}</c:if>
					</a>
				</c:forEach>
				<c:if test="${pgList.endPage < pgList.pageCount}">
					<a href="/Movivum/memberAdmin.do?pageNum=${pgList.startPage+pgList.blockSize}&searchOption=${searchOption}&searchText=${searchText}"><div class="nexticon"><img src="/Movivum/admin/img/arrow-right.svg" alt=""></div></a>
				</c:if>
              </div>
       </section>
   </div>
   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   <script src="/Movivum/admin/js/memberAdmin.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
</body>
</html>