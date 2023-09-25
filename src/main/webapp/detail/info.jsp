<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/Movivum/detail/css/info.css?ver=1.1">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>MOVIVUM | MOVIE_INFORMATION</title>
</head>

<body>
 	<!-- 헤더 -->
 	<jsp:include page="../common/header.jsp"/>
    <section #="basic_info" class="content">
        <div class="inner">
            <a href="javascript:window.history.back();"><span class="material-symbols-outlined">arrow_back</span></a>
            <h2 class="title">기본정보</h2>
            <div class="wrap">
                <ul class="info_wrap">
                <!-- 상세정보 -->
                </ul>
            </div>
        </div>
    </section>
 	<!-- 푸터 -->
 	 <jsp:include page="../common/footer.jsp"/>
 	 <script src="Notice/js/header.js"></script>
    <script src="/Movivum/detail/js/info.js"></script>
    
</body>
</html>