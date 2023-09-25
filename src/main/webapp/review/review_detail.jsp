<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/Movivum/review/css/review_detail.css" />
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
	
	<!-- 아이콘 관련 -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
	
	<!-- 아래 스크립트들은 상단에 위치할 것 -->
	<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	
	<title>MOVIVUM | REVIEW_DETAIL</title>
</head>
<body>
    <!-- 헤더 -->
    <jsp:include page="../common/header.jsp" />
    <input type="hidden" name="mov_no" class="mov_no" value="${review.mov_no}" />
    <section id="review" class="content">
        <c:if test="${not empty review}">
            <div class="inner">
                <div class="wrap">
                    <div class="top_wrap">
                        <div class="user">
                            <div class="avartar_wrap">
                                <img src="${review.user_pic}" />
                            </div>
                            <span class="nickname" id="review_nickname">${review.user_nickname}</span>
                        </div>
                        <span class="movie_title"></span>
                        <div class="movie_info">
                            <!-- 장르, 개봉일 -->
                        </div>
                        <span class="grade">
                            <span class="star">
                                <i class="fas fa-regular fa-star"></i> 평점
                            </span>${review.grade_point}
                        </span>
                    </div>
                    <div class="movie_poster">
                        <!-- 영화포스터 -->
                    </div>
                    <div class="review">
                        ${review.review_content}
                    </div>
                    <div class="bottom_wrap">
                        <div class="count">
                            <span class="like">좋아요</span>
                            <span class="like_count">${review.recomm_count}</span>&nbsp;
                            <span class="forcount_comment">댓글</span>
                            <span class="commnet_count">${count}</span>
                        </div>
                        <span class="review_date">
                            <fmt:formatDate value="${review.review_date}" pattern="yy-MM-dd" />
                        </span>
                  	</div>
                 </c:if>  
    	</section>
    <section id="metadata">
        <div class="inner">
            <form class="likeForm" id="likeForm" name="likeForm">
                <input type="hidden" name="review_no" value="${review.review_no}" />
                <input type="hidden" name="user_no" value="${user_no}" />
                <c:if test="${not empty user_no || user_no ne 0}">
                	<c:if test="${likeColor==0}">
		                <button class="like_button InactivedBtn" type="button" id="like_button">
		                    <span class="material-symbols-outlined">
		                        <i class="fa-solid fa-thumbs-up"></i>
		                    </span>&nbsp;좋아요
		                </button>
	                </c:if>
	                <c:if test="${likeColor==1}">
		                <button class="like_button ActivedBtn" type="button" id="like_button">
		                    <span class="material-symbols-outlined">
		                        <i class="fa-solid fa-thumbs-up"></i>
		                    </span>&nbsp;좋아요
		                </button>
	                </c:if>
                </c:if>
                <c:if test="${empty user_no || user_no eq 0}">
		            <button class="like_button" type="button" onclick="alertLogin()">
		                <span class="material-symbols-outlined">
		                    <i class="fa-solid fa-thumbs-up"></i>
		                </span>&nbsp;좋아요
		            </button>
              </c:if>
            </form>
            <div class="share_dropdown">
                <button class="share_button">
                    <span class="material-symbols-outlined">
                        <i class="fa-solid fa-share"></i>
                    </span>
                    &nbsp;공유
                </button>
                <ul class="share_ul">
                    <li class="clipBoard">링크복사</li>
                    <li class="facebook">페이스북</li>
                    <li class="twitter">트위터</li>
                    <li class="kakao">카카오톡</li>
                </ul>
            </div>
            <c:if test="${user_no != 1 }">
	            <button class="claim_button1">
	                <span class="material-symbols-outlined">
	                    <i class="fa-solid fa-circle-exclamation"></i>
	                </span>&nbsp;신고
	            </button>
             </c:if>
             <c:if test="${user_no == 1 }">
                  <button class="report_clear"><a href="/Movivum/reportClear.do?report_no=${report_no }&review_no=${review.review_no }&condition=1&reply_no=0">신고 처리하기(삭제)</a></button>
                  <button class="report_clear"><a href="/Movivum/reportClear.do?report_no=${report_no }&review_no=${review.review_no }&condition=0&reply_no=0">신고 처리하기(유지)</a></button>
             </c:if>
        </div>
    </section>

    <!-- 댓글 -->
    <section id="reply"> 
        <div class="inner">
            <!-- 페이징  -->
            <div class="paging">
                <c:if test="${pgList.startPage>pgList.blockSize}">
                    <a
                        href="/Movivum/review_detail.do?pageNum=${pgList.startPage-pgList.blockSize}&review_no=${review.review_no}&mov_no=${review.mov_no}"><i
                            class="fas fa-solid fa-angle-left"></i></a>
                </c:if>
                <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
                    <a href="/Movivum/review_detail.do?pageNum=${i}&review_no=${review.review_no}&mov_no=${review.mov_no}">
                        <c:if test="${pgList.currentPage==i}">
                            <font color="#B983FF"><b>[${i}]</b></font>
                        </c:if>
                        <c:if test="${pgList.currentPage!=i}">[${i}]</c:if>
                    </a>
                </c:forEach>
                <c:if test="${pgList.endPage<pgList.pageCount}">
                    <a href="/Movivum/review_detail.do?pageNum=${i}&review_no=${review.review_no}&mov_no=${review.mov_no}"><i
                            class="fas fa-solid fa-angle-right"></i></a>
                </c:if>
            </div>
            <c:forEach var="reply" items="${replyList}">
                <div class="commnet_wrap">
                    <div class="top_wrap">
                        <div class="user">
                            <div class="avartar_wrap">
                             <img src="${reply.user_pic}" />
                            </div>
                            <span class="nickname" id="reply_nickname">${reply.user_nickname}</span>
                        </div>
                    </div>
                    <div class="commnet_content" id="reply_content">
                        ${reply.reply_content}
                    </div>
                    <c:if test="${myreply > 0 && user_no == reply.user_no}">
  						<div class="bottom_wrap">
    					    <div class="update_wrap">
                                <form method="post" id="updateform" name="updateform" action="/Movivum/replyUpdate.do">
                                    <input type="hidden" name="user_no" id="reply_user_no" value="${user_no}" />
                                    <input type="hidden" name="reply_no" id="reply_no" value="${reply.reply_no}" />
                                    <input type="hidden" name="reply_content" id="reply_content"
                                        value="${reply.reply_content}" />
                                    <input type="hidden" name="user_no" id="user_no" value="${user_no}" />
                                    <input type="hidden" name="review_no" value="${review.review_no}" id="review_no" />
                                    <input type="hidden" name="mov_no" value="${review.mov_no}" id="mov_no" />
                                    <button id="reply_update_btn" type="button">댓글 수정</button>
                                    <button id="update_btn" type="button" style="display:none;">수정완료</button>
                                </form>
                            </div>
                            <div class="delete_wrap">
                                <form method="post" id="deleteform" name="deleteform" action="/Movivum/replyDelete.do" onsubmit="return confirm('정말 삭제하시겠습니까?')">
                                    <input type="hidden" name="user_no" value="${user_no}" />
                                    <input type="hidden" name="reply_no" value="${reply.reply_no}" />
                                    <input type="hidden" name="review_no" value="${review.review_no}" />
                                    <input type="hidden" name="mov_no" value="${review.mov_no}" />
                                    <button id="reply_delete_btn">댓글 삭제</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                <!-- report button -->
               <c:if test="${user_no != reply.user_no}">
                 <div class="bottom_wrap"> 
                       <c:if test="${user_no != 1 }">
                       		<button class="claim_button2">부적절한 댓글 신고하기</button>
                       </c:if>
                       <c:if test="${user_no == 1 && !empty report_no}">
                       		<button class="report_clear"><a href="/Movivum/reportClear.do?report_no=${report_no }&condition=1&reply_no=${reply.reply_no}&review_no=${review.review_no}">신고 처리하기(삭제)</a></button>
                  			<button class="report_clear"><a href="/Movivum/reportClear.do?report_no=${report_no }&condition=0&reply_no=${reply.reply_no}&review_no=${review.review_no}">신고 처리하기(유지)</a></button>
                       </c:if>
                 </div>
               </c:if>
               <!-- report button -->  
               <!-- 신고 모달 -->
                 <div class="modal2 hidden">
                 <!-- <div class="modal hidden"> -->
                     <div class="modal_overlay"></div>
                     <div class="modal_content">
                         <div class="modal_header">
                             <h3 class="title">정말로 댓글을 신고하시겠습니까?</h3>
                             <button class="close_btn2">
                                 <i class="fa-regular fa-circle-xmark"></i>
                             </button>
                         </div>
                         <form id="claimForm" action="/Movivum/reportInsert.do" method="post">
                             <div class="modal_body">
                                 	<input type="hidden" name="user_no" value="${reply.user_no}">
	                                 <input type="hidden" name="review_no" value="${review.review_no }">
	                                 <input type="hidden" name="report_type" value="2">
	                                 <input type="hidden" name="reply_no" value="${reply.reply_no }">
                                     <select id="claim" name ="report_option">
                                         <option value="" selected disabled>신고사유를 선택하세요.</option>
                                         <option value="1">욕설 및 불쾌함을 주는 표현</option>
                                         <option value="2">상업적인 광고</option>
                                         <option value="3">허위사실 적시 및 명예훼손</option>
                                         <option value="4">방해목적의 반복적인 게시</option>
                                         <option value="5" >기타사유</option>
                                     </select>
                                     <input type="text" id="claimReason"
                                         name="report_content"
                                         maxlength="9"
                                         placeholder="신고사유를 10자 이내로 기재해주세요."
                                         >
                             			</div>
                             <button type="submit"
                                     class="save_btn">신고
                             </button>
                         </form>
                     </div>        
                 </div>
                <!--  -->
      
            </c:forEach>
                <div class="write_comment">
                    <form class="comment_form" id="comment_form" name="comment" action="/Movivum/replayWrite.do" method="POST">
                        <input type="hidden" name="review_no" value="${review.review_no}" />
                        <input type="hidden" name="user_no" value="${user_no}" />
                        <input type="hidden" name="mov_no" value="${review.mov_no}" />
                        <label for="comment">댓글 작성하기</label>
                        <textarea id="comment" name="comment" rows="4" placeholder="자유롭게 감상을 나누어 보세요!"></textarea>
                        <c:if test="${not empty user_no && user_no ne 0}">
                            <button class="reply_btn" id="reply_btn">작성</button>
                        </c:if>
                         <c:if test="${empty user_no || user_no eq 0}">
                           <button class="reply_btn" type="button" onclick="alertLogin()">작성</button>
                         </c:if>
                    </form>
                </div>
        	</div>
    </section>
    <!-- 클립보드 alert -->
    <div class="clipBoard_alert">
        <p>클립보드에 복사되었습니다!</p>
    </div>
    <!-- 신고 모달 -->
    <div class="modal1 hidden">
                 <!-- <div class="modal hidden"> -->
                     <div class="modal_overlay"></div>
                     <div class="modal_content">
                         <div class="modal_header">
                             <h3 class="title">정말로 리뷰를 신고하시겠습니까?</h3>
                             <button class="close_btn1">
                                 <i class="fa-regular fa-circle-xmark"></i>
                             </button>
                         </div>
                         <form id="claimForm" action="/Movivum/reportInsert.do" method="post">
                             <div class="modal_body">
                                 	<input type="hidden" name="user_no" value="${review.user_no}">
	                                 <input type="hidden" name="review_no" value="${review.review_no }">
	                                 <input type="hidden" name="report_type" value="1"><!-- review -->
	                                 <input type="hidden" name="reply_no" value="0">
                                     <select id="claim" name ="report_option">
                                         <option value="" selected disabled>신고사유를 선택하세요.</option>
                                         <option value="1">욕설 및 불쾌함을 주는 표현</option>
                                         <option value="2">상업적인 광고</option>
                                         <option value="3">허위사실 적시 및 명예훼손</option>
                                         <option value="4">방해목적의 반복적인 게시</option>
                                         <option value="5" >기타사유</option>
                                     </select>
                                     <input type="text" id="claimReason"
                                         name="report_content"
                                         maxlength="9"
                                         placeholder="신고사유를 10자 이내로 기재해주세요."
                                         >
                             			</div>
                             <button type="submit"
                                     class="save_btn">신고
                             </button>
                         </form>
                     </div>        
                 </div>
    <!-- scroll top Btn -->
    <button class="scroll_btn" type="button" onclick="clickme()">
        <i class="fa-solid fa-angle-up"></i>
    </button>

    <!-- 푸터 -->
    <jsp:include page="../common/footer.jsp"/>
   	<script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
    <script src=/Movivum/review/js/review_detail.js?ver=1.1"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

</body>

</html>