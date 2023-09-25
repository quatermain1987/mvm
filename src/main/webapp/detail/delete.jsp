<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



  <c:if test="${check==1}">
 
  <% 
	int mov_no=Integer.parseInt(request.getParameter("mov_no"));
	response.sendRedirect("detail.do?mov_no="+mov_no);
	%>
  </c:if>
   <c:if test="${check==0}">
    <script>
            alert("에러발생");
            history.go(-1);
    </script>
   </c:if>

