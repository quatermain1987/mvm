<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>MOVIVUM</title>
<link rel="stylesheet" href="admin/css/noticeWriteFrom.css">
<link rel="stylesheet" href="admin/css/main.css">
</head>
<body>
	<div id="delete_pro">
		<form method="POST" name="delForm" id="delForm" action="/Movivum/noticeDeletePro.do">
			<div>
				게시물을 정말 삭제 하시겠습니까? 
				<input type="hidden" name="num" value="${num }">
				<input type="hidden" name="pageNum" value="${pageNum }">
			</div>
		</form>
		
		<button type="submit" form="delForm">글삭제</button>
		<button onclick="document.location.href='/Movivum/notice.do?pageNum=${pageNum }'">글목록</button>
	</div>
</body>
</html>
