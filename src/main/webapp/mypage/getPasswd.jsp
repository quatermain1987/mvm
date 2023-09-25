<%@ page language="java" contentType="application/json; charset=UTF-8"
	import="java.util.*,org.json.simple.JSONObject, model.mypage.UserDAO"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//값을 받아옵니다
String userNoStr = request.getParameter("userNo");
int userNo = Integer.parseInt(userNoStr);

//json객체 형태로 저장하기위한 객체선언
JSONObject jsonObject = new JSONObject();

//sql 실행해서 값을 가져오기
//1.일단 도전과제 달성여부를 확인하기 편하게 따로 달성한 리스트를 저장함
UserDAO userDB = new UserDAO();
String passwd = userDB.getPasswd(userNo);
System.out.println("비밀번호 값 가져옴"+passwd);
jsonObject.put("passwd", passwd);//도전과제 팝업띄우기용 정보
response.getWriter().write(jsonObject.toString());
%>
