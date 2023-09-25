<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <% 
	int mov_no=Integer.parseInt(request.getParameter("mov_no"));
	response.sendRedirect("detail.do?mov_no="+mov_no);//입력한후 다시 DB 접속
	%>
  