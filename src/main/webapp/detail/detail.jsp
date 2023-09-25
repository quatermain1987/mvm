<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <!DOCTYPE html>
 <html lang="kr">
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="/Movivum/detail/css/detail.css?ver=1.1">
<link rel="stylesheet" href="/Movivum/include/css/header.css">
<link rel="stylesheet" href="/Movivum/include/css/footer.css">
 
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
   <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
   <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
   <!-- footer icon -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
            <title>MOVIVUM | DETAIL</title>
        </head>

        <body>
        	<!-- 헤더 -->
        	<jsp:include page="../common/header.jsp"/>
            <!-- 스틸컷 -->
              <section id="stillcut">
            	<div class="stillcut_wrap"> 
                <!-- 스틸컷이미지 -->
                <div class="stillcut_img">
                <span class="bg"></span>
           	 	</div>
           	 	</div>	
       			 </section>
            <!-- 영화 메인 -->
            <section id="main" class="content">
                <div class="inner">
                    <div class="wrap">
                        <div class="movie_poster">
                            <!-- 이미지 -->
                        </div>
                        <div class="middle_wrap">
                            <div class="moive_info">
                                <!-- 영화정보 -->
                            </div>
                            <div class="rating">
                                <form name="gradeform" id="gradeform" method="post" action="/Movivum/grade.do">
                                    <p>평가하기</p>
                                    <input type="hidden" name="mov_no" value="${mov_no}" />
                                    <input type="hidden" name="user_no" value="${user_no}" id="user_no" />
                                    <fieldset>
                                        <input type="radio" name="reviewStar" value="0" id="rate0" ${myGradePoint==0
                                            ? 'checked' :''}>
                                        <label for="rate0">&nbsp;&nbsp;&nbsp;</label>
                                        <input type="radio" name="reviewStar" value="5" id="rate1" ${myGradePoint==5
                                            ? 'checked' :''}>
                                        <label for="rate1"><i class="fa-solid fa-star"></i></label>
                                        <input type="radio" name="reviewStar" value="4" id="rate2" ${myGradePoint==4
                                            ? 'checked' :''}>
                                        <label for="rate2"><i class="fa-solid fa-star"></i></label>
                                        <input type="radio" name="reviewStar" value="3" id="rate3" ${myGradePoint==3
                                            ? 'checked' :''}>
                                        <label for="rate3"><i class="fa-solid fa-star"></i></label>
                                        <input type="radio" name="reviewStar" value="2" id="rate4" ${myGradePoint==2
                                            ? 'checked' :''}>
                                        <label for="rate4"><i class="fa-solid fa-star"></i></label>
                                        <input type="radio" name="reviewStar" value="1" id="rate5" ${myGradePoint==1
                                            ? 'checked' :''}>
                                        <label for="rate5"><i class="fa-solid fa-star"></i></label>
                                        <input type="radio" name="reviewStar" value="0" id="rate6" ${myGradePoint==0
                                            ? 'checked' :''}>
                                        <label for="rate6">&nbsp;&nbsp;&nbsp;</label>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="button">
                                <c:if test="${empty myReview}">
                                    <c:if test="${not empty user_no && user_no ne 0}">
                                        <button type="button" class="buttonall" id="openbtn"><i class="fa-solid fa-comment"></i>리뷰쓰기</button>
                                    </c:if>
                                    <c:if test="${empty user_no || user_no eq 0}">
                                        <button class="buttonall" onclick="alertLogin()"><i class="fa-solid fa-comment"></i>리뷰쓰기</button>
                                    </c:if>
                                </c:if>
                                <c:if test="${not empty myReview}">
                                    <button type="button" class="buttonall" id="openbtn2"><i class="fa-solid fa-comment"></i>수정하기</button>
                                </c:if>
                                <form method="post" name="wishform" action="/Movivum/wish.do" id="wishform">
                                    <input type="hidden" name="mov_no" value="${mov_no}" />
                                    <input type="hidden" name="user_no" value="${user_no}" />
                                    <c:if test="${getHasWishList > 0}">
                                        <button id="wish" type="button" class="selected"><i class="fa-solid fa-heart"></i></button>
                                    </c:if>
                                    <c:if test="${getHasWishList == 0}">
                                        <c:if test="${empty user_no || user_no eq 0}">
                                            <button type="button" class="active" onclick="alertLogin()"><i
                                                    class="fa-solid fa-heart"></i></button>
                                        	</c:if>
                                        <c:if test="${not empty user_no && user_no ne 0}">
                                            <button id="wish" type="button" class="active"><i class="fa-solid fa-heart"></i></button>
                                        </c:if>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                        <div class="bottom_wrap">
                           <p> 평균 <i class="fa-solid fa-star"></i>${avgrating}</p>
                            <div style="width: 300px; height: 300px;">
                                <!--차트가 그려질 부분-->
                                <canvas id="myChart"></canvas>
                                <input type="hidden" id="grade1" name="grade1" value="${grade1}" />
                                <input type="hidden" id="grade2" name="grade2" value="${grade2}" />
                                <input type="hidden" id="grade3" name="grade3" value="${grade3}" />
                                <input type="hidden" id="grade4" name="grade4" value="${grade4}" />
                                <input type="hidden" id="grade5" name="grade5" value="${grade5}" />
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 리뷰작성시 보이는 리뷰 클래스-->
            <c:if test="${not empty myReview}">
                <section id="review_update">
                    <div class="inner">
                        <div class="wrap">
                                <div class="review_update_content"><a
                                href="/Movivum/review_detail.do?mov_no=${myReview.mov_no}&review_no=${myReview.review_no}">${myReview.review_content}</a></div>
                            <div class="review_button">
                                <form method="post" name="deleteform" action="/Movivum/delete.do" id="deleteform">
                                    <input type="hidden" name="mov_no" value="${mov_no}" />
                                    <input type="hidden" name="user_no" value="${user_no}" />
                                    <input type="hidden" name="review_no" value="${myReview.review_no}" />
                                    <button id="deletebtn" type="button"><i class="fa-solid fa-comment"></i>삭제</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </c:if>
            <c:if test="${empty myReview}">
                <section id="empty_review" class="empty_review">
                    <div class="inner">
                        <div class="wrap"></div>
                    </div>
                </section>
            </c:if>
            <!-- 영화정보 -->
            <section id="infomation" class="content">
                <div class="inner">
                    <div class="wrap">
                        <h2>영화정보</h2>
                        <a href="detail/info.jsp?mov_no=${mov_no}">더보기</a>
                    </div>
                    <div class="moive_content">
                        <!-- 상세정보 내용 -->
                    </div>
                </div>
            </section>
            <!-- 출연진 -->
            <section id="moive_cast" class="content">
                <div class="inner">
                    <h2>출연진</h2>
                    <div class="swiper">
                        <div class="swiper-wrapper">
                        </div>
                        <div class="swiper-btn-wrap">
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 트레일러 -->
            <section id="trailer" class="content">
                <div class="inner">
                    <h2>트레일러</h2>
                    <div class="swiper">
                        <div class="swiper-wrapper">
                        </div>
                        <div class="swiper-btn-wrap">
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 리뷰 -->
            <section id="review" class="content">
                <div class="inner">
                    <c:if test="${review_count == 0}">
                        <div class="wrap">
                            <h2>리뷰</h2>
                            <span class="review_more"></span>
                            <div class="review_none">
                                <p>등록된 리뷰가 없습니다.</p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${review_count > 0}">
                        <div class="wrap">
                            <h2>리뷰</h2>
                            <a href="/Movivum/review_all.do?mov_no=${mov_no}">더보기</a>
                        </div> 
                        <c:forEach items="${reviewList}" var="review" varStatus="loop">
                           <c:if test="${loop.index < 5}">
                        <div id="review_allwrap">
                         <a href="/Movivum/review_detail.do?mov_no=${review.mov_no}&review_no=${review.review_no}">
                                        <div class="review_wrap">
                                            <div class="review_top">
                                                <div class="user_wrap">
                                                    <div class="user_picture_wrap">
                                                        <img src="${review.user_pic}" class="user_picture">
                                                    </div>
                                                    <span class="nickname">${review.user_nickname}</span>
                                                </div>
                                                <c:choose>
                                                    <c:when test="${review.grade_point == 0}">
                                                        <div>&nbsp;</div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="grade">
                                                            <i class="fa-solid fa-star"></i>${review.grade_point}
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="reivew_content">
                                                ${review.review_content}
                                            </div>
                                            <div class="review_bottom">
                                                <div class="recomm_wrap">
                                                    <i class="fa-solid fa-thumbs-up"></i>${review.recomm_count}
                                                </div>
                                                <span><fmt:formatDate value="${review.review_date}" pattern="yy-MM-dd" /></span>  
                                            </div>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
              	</div> 
            </section>
             <!-- footer -->
             <jsp:include page="../common/footer.jsp"/>
            <!-- 글쓰기 모달 -->
            <div class="blackbg"></div>
            <div class="write_box_write">
                <div class="inner">
                    <div class="wrap">
                        <div class="topwrap">
                            <span class="write_title"></span>
                            <button class="write_closebtn" type="button"><i class="fas fa-duotone fa-xmark"></i></button>
                        </div>
                        <form method="post" name="writeform" id="writeform" action="/Movivum/write.do"> 
                            <input type="hidden" name="mov_no" value="${mov_no}">
                            <input type="hidden" name="user_no" value="${user_no}">
                            <textarea maxlength="10000" placeholder="이 작품에 대한 생각을 자유롭게 표현해주세요." class="write_content" id="newWrite"
                                style="height: 352px;" name="review_content"></textarea>
                            <button type="button" class="write_savebtn">저장</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- 수정용 모달 -->
            <div class="blackbg"></div>
            <div class="write_box_update">
                <div class="inner">
                    <div class="wrap">
                        <div class="topwrap">
                            <span class="update_title"></span>
                            <button class="write_closebtn2" type="button"><i
                                    class="fas fa-duotone fa-xmark"></i></button>
                        </div>
                        <form method="post" name="updateform" id="updateform" action="/Movivum/update.do">
                            <input type="hidden" name="mov_no" value="${mov_no}">
                            <input type="hidden" name="user_no" value="${user_no}">
                            <textarea maxlength="10000" placeholder="이 작품에 대한 생각을 자유롭게 표현해주세요." class="write_content" id="reWrite"
                                style="height: 352px;" name="review_content">
                     </textarea>
                            <button type="button" class="update_savebtn">저장</button>
                        </form>
                    </div>
                </div>
            </div>
            <script src="/Movivum/detail/js/detail.js?ver=1.1%>"></script>
            <script src="Notice/js/header.js"></script>
        </body>
        </html>