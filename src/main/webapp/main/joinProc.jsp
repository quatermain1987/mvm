<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
response.getWriter().print(session.getAttribute("loginResult"));
%>