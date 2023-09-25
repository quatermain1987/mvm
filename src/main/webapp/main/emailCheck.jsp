<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.member.MemberDAO" %>
<%
	//MemberDAO에 있는 이메일 중복체크 메서드에 값을 전달 후 결과를 리턴받음	
	String user_email=request.getParameter("user_email");
	MemberDAO memberDao=new MemberDAO();
	boolean check= memberDao.emailCheck(user_email);
	response.getWriter().write(String.valueOf(check==true));//입력받은 인자 값을 지정된 Number 객체 형으로 변환하여 반환
	System.out.println(String.valueOf(check==true));
%>