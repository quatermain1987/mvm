<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
boolean loginResult=(boolean)request.getAttribute("loginResult");
System.out.println("loginProc.jsp(loginResult)=>"+loginResult);
response.getWriter().write(String.valueOf(loginResult==true));//입력받은 인자 값을 지정된 Number 객체 형으로 변환하여 반환
System.out.println(String.valueOf(loginResult==true));
%>