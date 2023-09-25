<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM</title>
    <link rel="stylesheet" href="admin/css/main.css">
    <link rel="stylesheet" href="admin/css/admin.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="admin/js/main.js"></script>
</head>
<body>
    <!-- <header id="header">
        <div class="inner">
            <div class="wrap">
                <nav id="gnb">
                    <div class="left"><a href="main.html" class="logo">MOVIVUM</a></div>
                   <div class="middle">
                        <input type="text" name="search" class="search" placeholder="영화 이름을 검색해보세요."><span class="material-symbols-outlined search_icon">search</span>
                    </div>
                   <div class="right"><a href="#" name="login" class="login"><span class="material-symbols-outlined login_icon">face</span></a></div>
                </nav>
            </div>
        </div>
    </header> -->
    <jsp:include page="../common/header.jsp"/>
    

    <section id="admin" class="content">
        <div class="inner">
            <div class="wrap">
                <aside class="lnb_wrap">
                    <ul id="lnb">
                        <li><a href="/Movivum/admin.do">&nbsp&nbsp<i class="fas fa-solid fa-check"></i>&nbsp&nbsp오늘의 주요 현황</a></li>
						<!-- <li><a href="#">&nbsp&nbsp<i class="fas fa-solid fa-pen"></i>&nbsp&nbsp회원 정보 수정</a></li> -->
						<li><a href="/Movivum/memberAdmin.do">&nbsp&nbsp<i class="fas fa-solid fa-user"></i>&nbsp&nbsp회원 관리</a></li>
						<li><a href="/Movivum/notice.do">&nbsp&nbsp<i class="fas fa-solid fa-bullhorn"></i>&nbsp&nbsp공지사항</a></li>
						<li><a href="#">&nbsp&nbsp<i class="fas fa-solid fa-comments"></i>&nbsp&nbsp문의내역</a></li>
						<li><a href="/Movivum/report.do">&nbsp&nbsp<i class="fas fa-solid fa-xmark"></i>&nbsp&nbsp신고내역</a></li>
                    </ul>
                </aside>
                <div class="box">
                    <div id="title">오늘의 주요 현황</div>
                    <div id="count_box">
                        <div class="count"><div><i class="fas fa-solid fa-house"></i></div><span id="visitCount">${paegHit }</span><br>방문자수</div>
                        <div class="count"><div><i class="fas fa-solid fa-user"></i></div><span>${regCount }</span><br>회원가입</div>
                        <div class="count"><div><i class="fas fa-solid fa-user"></i></div><span>${rejectCount }</span><br>회원탈퇴</div>
                        <div class="count"><div><i class="fas fa-solid fa-file-lines"></i></div><span>${documentCount }</span><br>게시물 등록</div>
                    </div>
                    <div id="table_box">
                    	<div class="table_box">
	                        <div id="title">문의내역</div>
	                        <c:if test="${qnaCount==0 }">
		                    	오늘 등록된 문의내역이 없습니다.
		                    </c:if>
		                    <c:if test="${0 < qnaCount }">
		                    <c:set var="number" value="${qnaCount }"/>
	                        <table class="table">
	                            <thead>
	                                <tr>
	                                    <td>No</td>
	                                    <td>제목</td>
	                                    <td>이름/닉네임</td>
	                                </tr>
	                            </thead>
		                    <c:forEach var="article" items="${qnaList }">
	                            <tbody>
	                                <tr>
	                                    <td>
	                                    	<c:out value="${number }"/>
											<c:set var="number" value="${number-1 }"/>
										</td>
	                                    <td>
	                                    	<a href="/Movivum/qnaContent.do?num=${article.qna_no }">${article.qna_subject }</a>
	                                    </td>
	                                    <td>${article.user_name }/${article.user_nickname }</td>
	                                </tr>
	                            </c:forEach>
	                            </tbody>
	                        </table>
	                        </c:if>
	                    </div>
	                    <div class="table_box">
	                        <div id="title">신고내역</div>
	                        <c:if test="${reportCount==0 }">
		                    	오늘 등록된 신고 내역이 없습니다.
		                    </c:if>
		                    <c:if test="${0 < reportCount }">
		                    <c:set var="number" value="${reportCount }"/>
	                        <table class="table">
	                            <thead>
	                                <tr>
	                                    <td>No</td>
	                                    <td>신고옵션</td>
	                                    <td>이름/닉네임</td>
	                                </tr>
	                            </thead>
	                        <c:forEach var="article" items="${reportList }">
	                            <tbody>
	                                <tr>
	                                    <td>
	                                    	<c:out value="${number }"/>
											<c:set var="number" value="${number-1 }"/>
	                                    </td>
	                                    <td>
	                                    	<c:if test="${article.report_option==1 }">
											<a href="/Movivum/review_detail.do?review_no=${article.review_no }&user_no=${article.user_no }">욕설비방</a>
											</c:if>
											<c:if test="${article.report_option==2 }">
											<a href="/Movivum/review_detail.do?review_no=${article.review_no }&user_no=${article.user_no }">광고</a>
											</c:if>
											<c:if test="${article.report_option==3 }">
											<a href="/Movivum/review_detail.do?review_no=${article.review_no }&user_no=${article.user_no }">명예훼손</a>
											</c:if>
											<c:if test="${article.report_option==4 }">
											<a href="/Movivum/review_detail.do?review_no=${article.review_no }&user_no=${article.user_no }">반복적 게시</a>
											</c:if>
											<c:if test="${article.report_option==5 }">
											<a href="/Movivum/review_detail.do?review_no=${article.review_no }&user_no=${article.user_no }">기타</a>
											</c:if>
	                                    </td>
	                                    <td>${article.user_name }/${article.user_nickname }</td>
	                                </tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
	                        </c:if>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<jsp:include page="../common/footer.jsp"/>
    <!-- <footer id="footer">
        <div class="inner">
            <nav class="wrap">
                <div class="left_footer">
                    <div class="logo"><a href="main.html">MOVIVUM</a></div>
                    <ul class="sns">
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </div>
                <div class="right_footer">
                    <ul id="footer_gnb">
                        <li><a href="#">서비스이용약관</a></li>
                        <li><a href="#">개인정보처리방침</a></li>
                        <li><a href="#">이용약관</a></li>
                        <li><a href="#">고객센터</a></li>
                        <li><a href="#">채용정보</a></li>
                    </ul>
                    <p>고객센터 | cs@watcha.co.kr /02-515-9985 (유료)</p>
                    <p>제휴 및 대외 협력 | https://watcha.team/contact</p>
                    <p>주식회사 아무개 | 대표 홍길동 | 서울특별시 서초구 강남대로 343 신덕빌딩 3층</p>
                    <p>© 2023 by MOVIVUM, Inc. All rights reserved.</p>
                </div>
            </nav>
        </div>
    </footer> -->
</body>
</html>