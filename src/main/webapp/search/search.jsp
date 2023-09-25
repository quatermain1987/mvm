<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM</title>
    <link rel="stylesheet" href="search/css/search.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>
	
	<%-- <jsp:include page="Header.jsp" flush="false"/> --%>
    <jsp:include page="../common/header.jsp" flush="false"/>
    
    <div class="banner"></div>

        <main>
            <div class="inner" id="movie_wrap">
                <div class="movie_swiper_wrap">
                    <div class="swiper mySwiper">
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-wrapper">
                        <!-- script에서 동적으로 생성한 영화 리스트 -->
                        </div>
                    </div>
                </div>
                <div class="movie_list_for">&nbsp; 영화 〉</div>
                <div class="movie_list_wrap">
                <!-- script에서 동적으로 생성한 영화 리스트 -->
                </div>
                <div class="forMore">
                    <button class="more"> 더보기 ∨ </button>
                </div>
            </div>
        </main>
        
	<%-- <%@ include file="../common/footer.jsp"%> --%>
	<jsp:include page="../common/footer.jsp"/>
        
    <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <script src="search/js/search.js"></script>
</body>
</html>