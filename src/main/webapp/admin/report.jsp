<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.report.reportDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MUVIVUM</title>
<link rel="stylesheet" href="admin/css/main.css">
<link rel="stylesheet" href="admin/css/report.css">
<script src="https://kit.fontawesome.com/5ff835e817.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="admin/js/report.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
	<jsp:include page="../common/header.jsp"/>

	<section id="admin" class="content">
		<div class="inner">
			<div class="wrap">
				<aside class="lnb_wrap">
					<ul id="lnb">
						<li><a href="/Movivum/admin.do">&nbsp&nbsp<i class="fas fa-solid fa-check"></i>&nbsp&nbsp오늘의 주요 현황</a></li>
						<!-- <li><a href="#">&nbsp&nbsp<i class="fas fa-solid fa-pen"></i>&nbsp&nbsp회원 정보 수정</a></li> -->
						<li><a href="/Movivum/memberAdmin.do"">&nbsp&nbsp<i class="fas fa-solid fa-user"></i>&nbsp&nbsp회원 관리</a></li>
						<li><a href="/Movivum/notice.do">&nbsp&nbsp<i class="fas fa-solid fa-bullhorn"></i>&nbsp&nbsp공지사항</a></li>
						<li><a href="#">&nbsp&nbsp<i class="fas fa-solid fa-comments"></i>&nbsp&nbsp문의내역</a></li>
						<li><a href="/Movivum/report.do">&nbsp&nbsp<i class="fas fa-solid fa-xmark"></i>&nbsp&nbsp신고내역</a></li>
					</ul>
				</aside>
				<div class="box">
					<div id="title">신고내역</div>
					<div class="block">
						<span>전체신고수(${pgList.count })</span>
						<!-- 검색어 -->
						<div id="searchBox">
							<form name="test" action="/Movivum/report.do">
								<div>
									<select name="search" id="select" onchange="showValue(this)">
										<option value="">검색</option>
										<option value="user_name">이름</option>
										<option value="user_nickname">닉네임</option>
										<option value="condition_1">처리 중</option>
										<!--  (click) => {inputBoxHidden} -->
										<option value="condition_2">처리완료</option>
										<!--  (click) => {inputBoxHidden} -->
									</select>
								</div>
								<div id="box"></div>
								<div>
									<button type="submit" class="search_btn">검색</button><!--  -->
								</div>
							</form>
						</div>
					</div>
					<c:if test="${pgList.count==0 }">
                    	등록된 게시물이 없습니다.
                    </c:if>
                    <c:if test="${0<pgList.count }">
                    <div id="tableBox">
                    	<table class="table">
							<thead>
								<tr>
									<td>글번호</td>
									<td>유형</td>
									<td>이름/아이디</td>
									<td>신고옵션</td>
									<td>신고내용</td>
									<td>처리상태</td>
								</tr>
							</thead>
							<c:set var="number" value="${pgList.number }"/>
	                        <c:forEach var="article" items="${articleList }">
							<tbody>
								<tr>
									<td>
										<c:out value="${number }"/>
										<c:set var="number" value="${number-1 }"/>
									</td>
									<td>
										<c:if test="${article.report_type==1 }">
										리뷰
										</c:if>
										<c:if test="${article.report_type==2 }">
										댓글
										</c:if>
									</td>
									<td>
										${article.user_name }/${article.user_nickname }
									</td>
									<td>
										<c:if test="${article.report_option==1 }">
										욕설비방
										</c:if>
										<c:if test="${article.report_option==2 }">
										광고
										</c:if>
										<c:if test="${article.report_option==3 }">
										명예훼손
										</c:if>
										<c:if test="${article.report_option==4 }">
										반복적 게시
										</c:if>
										<c:if test="${article.report_option==5 }">
										기타
										</c:if>
										<c:if test="${article.report_option==6 }" >
										댓글신고
										</c:if>
									</td>
									<td>
										<c:if test="${article.report_content==null }">
	 										<a href="/Movivum/review_detail.do?review_no=${article.review_no}&report_no=${article.report_no}">
												리뷰 상세로 가기 <i class="fas fa-solid fa-angle-right"></i>
											</a>
										</c:if>
										<c:if test="${article.report_content!=null }">
	 										<a href="/Movivum/review_detail.do?review_no=${article.review_no}&report_no=${article.report_no}">${article.report_content }</a>
										</c:if>
									</td>
									<td>
										<c:if test="${article.report_condition==1 }">
										처리중
										</c:if>
										<c:if test="${article.report_condition==2 }">
										처리완료
										</c:if>
									</td>
								</tr>
							</tbody>
							</c:forEach>
						</table>
                    </div>
					</c:if>
					<!-- 페이징 처리 -->
							<div id="paging">
								<!-- 이전 페이지 -->
								<c:if test="${pgList.startPage>pgList.blockSize }">
									<a href="/Movivum/report.do?pageNum=${pgList.startPage-pgList.blockSize }&search=${search }&searchtext=${searchtext }"><i class="fas fa-solid fa-angle-left"></i></a>
								</c:if>
								&nbsp;&nbsp;
								<!-- 현재블럭 -->
								<c:forEach var="i" begin="${pgList.startPage }" end="${pgList.endPage }">
									<a href="/Movivum/report.do?pageNum=${i }&search=${search }&searchtext=${searchtext }">
										<c:if test="${pgList.currentPage==i }">
											<font id="pageSelected"><b>&nbsp;&nbsp;${i }&nbsp;&nbsp;</b></font>&nbsp;&nbsp;
										</c:if>
										<c:if test="${pgList.currentPage!=i }">
											${i }&nbsp;&nbsp;
										</c:if>
									</a>
								</c:forEach>
								<!-- 다음블럭 -->
								<c:if test="${pgList.endPage<pgList.pageCount }">
									<a href="/Movivum/report.do?pageNum=${pgList.startPage+pgList.blockSize }&search=${search }&searchtext=${searchtext }"><i class="fas fa-solid fa-angle-right"></i></a>
								</c:if>
							</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="../common/footer.jsp"/>
</body>
</html>