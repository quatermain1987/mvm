<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOVIVUM</title>
    <link rel="stylesheet" href="admin/css/main.css">
    <link rel="stylesheet" href="admin/css/notice.css">
    <link rel="stylesheet" href="admin/css/noticeWriteFrom.css">
    <script type="text/javascript" src="admin/js/formCheck.js"></script>
    <script src="https://kit.fontawesome.com/5ff835e817.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
                    <table>
                        <tr>
                            <td><h1>공지사항 작성</h1></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form">
                                    <form method="post" id="form" name="writeform" action="/Movivum/noticeWritePro.do" onsubmit="return writeSave()">
                                        <div><input type="text" name="notice_subject" id="subject" placeholder="공지사항 제목을 입력해주세요."></div>
                                        <div><textarea name="notice_content" cols="50" rows="20" id="content" placeholder="공지사항 내용을 입력해주세요."></textarea></div>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <tr>
                        	<td>
                        		<div>
                                        <button type="submit" form="form" id="write_btn">글쓰기</button>
                                        <button OnClick="window.location='/Movivum/notice.do'">글목록</button>
                      		 	</div>
                        	</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </section>

	<jsp:include page="../common/footer.jsp"/>

</body>
</html>