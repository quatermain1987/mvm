<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>MUVIVUM | All Review</title>
 <link rel="stylesheet" href="/Movivum/review/css/review_all.css">
 <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
 <link rel="stylesheet"
  href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  </head>
		 <body>
              <!-- 헤더 -->
 				<jsp:include page="../common/header.jsp" />
                <section class="banner">
                    <div class="inner">
                        <!-- 영화포스터 및 정보 -->
                        <div class="moive_information">
                           <!-- 영화정보 동적생성 + 영화 평균별점 가져오기--> 
                          <input type="hidden" id="avgrating" value="${avgrating}" />
                        </div>
                    </div>
                </section>
                </header>
                <main>
                    <section class="content">
                        <div class="inner">
                            <div class="wrap_upper">
                                <h2 class="title">모든 리뷰 보기</h2>
                                <form id="selectform" name="selectform" action="/Movivum/review_all.do">
                                <input type="hidden" name="mov_no" id="mov_no" value="${mov_no}">
                                    <div class="review_select">
                                        <select class="review_align">
                                            <option value="fastest" ${select=='fastest'? 'selected' :''}>최신순</option>
                                            <option value="grade" ${select=='grade'? 'selected' :''}>별점순</option>
                                            <option value="popular" ${select=='popular'? 'selected' :''}>인기순</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <div class="wrap_review">
                                <c:choose>
                                    <c:when test="${count == 0}">
                                        <div>등록된 글이 없습니다.</div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="review" items="${reviewList}">
                                       <a href="/Movivum/review_detail.do?review_no=${review.review_no}&mov_no=${mov_no}">
                                            <article class="review_card">
                                                    <!-- 리뷰1행 -->
                                                    <div class="user_wrap">
                                                        <span>
                                                            <span><img class="user_picture" src="1.jpg">${review.user_pic}</span>
                                                            <span class="nickname">${review.user_nickname}</span>
                                                        </span>
                                                    </div>
                                                    <!-- 리뷰2행 -->
                                                    <div class="grade_wrap">
                                                        <span class="mov_title"> </span>
                                                        <span class="star"><i class="fas fa-regular fa-star"></i>${review.grade_point}</span>
                                                    </div>
                                                    <!-- 리뷰3행 -->
                                                    <div class="content_wrap">
                                                    ${review.review_content}
                                                    </div>
                                                    <!-- 리뷰4행 -->
                                                    <div class="bottom_wrap">
                                                        <span><i class="fa-solid fa-thumbs-up"></i>${review.recomm_count}</span>
                                                        <span><fmt:formatDate value="${review.review_date}" pattern="yy-MM-dd" /></span>
                                                    </div>
                                             </article>
                                           </a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- 여기에 번호 -->
                        <div class="paging">
                            <c:if test="${pgList.startPage>pgList.blockSize}">
                                <a
                                    href="/Movivum/review_all.do?pageNum=${pgList.startPage-pgList.blockSize}&mov_no=${mov_no}&select=${select}"><i
                                        class="fas fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
                                <a href="/Movivum/review_all.do?pageNum=${i}&mov_no=${mov_no}&select=${select}">
                                    <c:if test="${pgList.currentPage==i}">
                                        <font color="#B983FF"><b>[${i}]</b></font>
                                    </c:if>
                                    <c:if test="${pgList.currentPage!=i}">[${i}]</c:if>
                                </a>
                            </c:forEach>
                            <c:if test="${pgList.endPage<pgList.pageCount}">
                                <a
                                    href="/Movivum/review_all.do?pageNum=${pgList.startPage+pgList.blockSize}&mov_no=${mov_no}&select=${select}"><i
                                        class="fas fa-solid fa-angle-right"></i></a>
                            </c:if>
                        </div>
                    </section>
                </main>
                <!-- footer -->
				<jsp:include page="../common/footer.jsp"/>
				 <script src="Notice/js/header.js"></script>
                <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
                <script src="/Movivum/review/js/review_all.js"></script>
            </body>
            </html>