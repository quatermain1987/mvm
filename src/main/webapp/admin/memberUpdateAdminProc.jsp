<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${updateResult==1}">
<script>
alert("회원정보가 성공적으로 수정되었습니다.");
location.href="memberAdmin.do";
</script>
</c:if>
<c:if test="${updateResult==0}">
<script>
	alert("회원정보 수정에 실패했습니다! \n 다시 시도해주세요.");
	history.back();
</script>
</c:if>