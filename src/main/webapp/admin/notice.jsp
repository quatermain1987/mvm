<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Hashtable" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM</title>
    <link rel="stylesheet" href="admin/css/main.css">
    <link rel="stylesheet" href="admin/css/notice.css">
    <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="admin/js/main.js"></script>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
    <jsp:include page="../common/header.jsp"/>
    
    <section id="admin" class="content">
        <div class="inner">
            <div class="wrap">
                <c:if test="${user_no == 1 }">
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
                </c:if>
                <c:if test="${user_no != 1 }">
                	<aside class="lnb_wrap">
	                    <ul id="lnb">
	                        <li><a href="/Movivum/main.do">&nbsp&nbsp<i class="fas fa-solid fa-angle-left"></i>&nbsp&nbspMovivum</a></li>
	                    </ul>
	                </aside>
                </c:if>
                <div class="box">
                    <div id="title">공지사항 (${pgList.count })</div>
                    <c:if test="${pgList.count==0 }">
                    	등록된 게시물이 없습니다.
                    </c:if>
                    <c:if test="${0<pgList.count }">
                    <div id="tableBox">
                    	<table class="table">
							<thead>
	                            <tr>
	                                <td>글번호</td>
	                                <td>제목</td>
	                                <td>작성일</td>
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
	                                	<a href="/Movivum/noticeContent.do?num=${article.notice_no }&pageNum=${pgList.currentPage }">${article.notice_subject }</a>
	                                </td>
	                                <td>
	                                	<fmt:formatDate value="${article.notice_date }" pattern="yy.MM.dd"/>
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
									<a href="/Movivum/notice.do?pageNum=${pgList.startPage-pgList.blockSize }"><i class="fas fa-solid fa-angle-left"></i></a>
								</c:if>
								&nbsp;&nbsp;
								<!-- 현재블럭 -->
								<c:forEach var="i" begin="${pgList.startPage }" end="${pgList.endPage }">
									<a href="/Movivum/notice.do?pageNum=${i }">
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
									<a href="/Movivum/notice.do?pageNum=${pgList.startPage+pgList.blockSize }"><i class="fas fa-solid fa-angle-right"></i></a>
								</c:if>
							
	                        <c:if test="${user_no == 1 }">
	                        	<button class="write"><a href="/Movivum/noticeWriteForm.do">글쓰기</a></button>
	                        </c:if>
						</div>
					</div>
                </div>
            </div>
        </div>
    </section>

	<jsp:include page="../common/footer.jsp"/>
</body>
</html>