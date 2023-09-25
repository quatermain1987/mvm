<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM</title>
    <link rel="stylesheet" href="admin/css/noticeContentMain.css">
    <link rel="stylesheet" href="admin/css/noticeContent.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
    
    <jsp:include page="../common/header.jsp"/>
    
    <!-- <div class="banner"></div> -->

        <main>
            <div class="inner">
                <c:if test="${user_no == 1 }">
                	<aside id="aside">
	                    <ul id="navbar">
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
                	<aside id="aside">
	                    <ul id="navbar">
	                        <li><a href="/Movivum/main.do">&nbsp&nbsp<i class="fas fa-solid fa-angle-left"></i>&nbsp&nbspMovivum</a></li>
	                    </ul>
	                </aside>
                </c:if>

                <section id="section">

                    <article id="article">
                        <h1 class="h1"><a href="#">공지사항</a></h1>
                        <div class="tb_wrap">
                            <table class="tb1">
                                <tr class="row1">
                                    <td class="col1">제목</td>
                                    <td class="col_line"><div></div></td>
                                    <td class="col2">${article.notice_subject}</td>
                                </tr>
                                <tr class="row2">
                                    <td class="col1">날짜</td>
                                    <td class="col_line"><div></div></td>
                                    <td class="col2"><fmt:formatDate value="${article.notice_date }" pattern="yy.MM.dd hh시 mm분"/></td>
                                </tr>
                                <tr class="row3">
                                    <td colspan="3">
                                        <div id="notice"><pre>${article.notice_content}</pre></div>
                                    </td>
                                </tr>
                                <tr class="row4">
                                    <td colspan="3">
                                        <c:if test="${user_no == 1 }">
                                        	<input type="button" class="btn" value="글수정" onclick="document.location.href='/Movivum/noticeUpdateForm.do?num=${article.notice_no}&pageNum=${pageNum}'">
											<input type="button" class="btn" value="글삭제" onclick="document.location.href='/Movivum/noticeDeleteForm.do?num=${article.notice_no}&pageNum=${pageNum}'">
                                        </c:if>
										<input type="button" class="btn" value="글목록" onclick="document.location.href='/Movivum/notice.do?pageNum=${pageNum}'">
                                    </td>
                                </tr>
                            </table>
                            <div class="tb2_wrap">
                                <table class="tb2">
                                    <c:if test="${nextCheck }">
	                                    <tr class="row1">
	                                        <td><a href="/Movivum/noticeContent.do?num=${article.notice_no+1 }&pageNum=1"><i class="fas fa-solid fa-angle-up"></i>&nbsp;&nbsp;[공지사항] 다음 글</a></td>
	                                    </tr>
                                    </c:if>
                                    <c:if test="${preCheck }">
	                                    <tr class="row2">
                                        <td><a href="/Movivum/noticeContent.do?num=${article.notice_no-1 }&pageNum=1"><i class="fas fa-solid fa-angle-down"></i>&nbsp;&nbsp;[공지사항] 이전 글</a></td>
                                    </tr>
                                    </c:if>
                                    
                                </table>
                            </div>
                        </div>
                    </article>
                    
                </section>
            </div> 
        </main>

	<jsp:include page="../common/footer.jsp"/>	

	<script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="admin/js/main.js"></script>
</body>
</html>