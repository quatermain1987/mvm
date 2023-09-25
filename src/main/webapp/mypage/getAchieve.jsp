<%@ page language="java" contentType="application/json; charset=UTF-8"
	import="java.util.*,org.json.simple.JSONObject,org.json.simple.JSONArray,model.mypage.AchieveDAO,model.mypage.AchieveDTO,model.mypage.ReviewDAO,model.mypage.ReviewDTO"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//값을 받아옵니다
String userNoStr = request.getParameter("userNo");
int userNo = Integer.parseInt(userNoStr);

String achieveStr = request.getParameter("achieve");
int achieve = Integer.parseInt(achieveStr);

//json객체 형태로 저장하기위한 객체선언
JSONObject jsonObject = new JSONObject();

//sql 실행해서 값을 가져오기
//1.일단 도전과제 달성여부를 확인하기 편하게 따로 달성한 리스트를 저장함
AchieveDAO achieveDB = new AchieveDAO();
List<AchieveDTO> achieveList = achieveDB.getAchieve(userNo);
List<Integer> achieveListInt= new ArrayList<>();
for(AchieveDTO item : achieveList){
	achieveListInt.add(item.getAchieveNo());
}
System.out.println("도전과제 번호 전달받음"+achieve);

//2.도전과제를 달성하지 못했었더라면 조건확인후 도전과제 테이블에 insert
if (!achieveListInt.contains(achieve) && achieve==0) {
	ReviewDAO reviewDB = new ReviewDAO();
	int recommCount = reviewDB.getReviewRecommCount(userNo);

System.out.println("도전과제 값 가져옴"+recommCount);
	if (recommCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+recommCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보
	}
}
if (!achieveListInt.contains(achieve) && achieve==1) {
	ReviewDAO reviewDB = new ReviewDAO();
	int reviewCount = reviewDB.getReviewCount(userNo);
	if (reviewCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+reviewCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보 
	}
}/*
if (!achieveListInt.contains(achieve) && achieve==2) {
	ReviewDAO reviewDB = new ReviewDAO();
	int recommCount = reviewDB.getReviewRecommCount(userNo);
	if (recommCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+recommCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보
	}
}
if (!achieveListInt.contains(achieve) && achieve==3) {
	ReviewDAO reviewDB = new ReviewDAO();
	int recommCount = reviewDB.getReviewRecommCount(userNo);
	if (recommCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+recommCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보
	}
}
if (!achieveListInt.contains(achieve) && achieve==4) {
	ReviewDAO reviewDB = new ReviewDAO();
	int recommCount = reviewDB.getReviewRecommCount(userNo);
	if (recommCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+recommCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보
	}
}
if (!achieveListInt.contains(achieve) && achieve==5) {
	ReviewDAO reviewDB = new ReviewDAO();
	int recommCount = reviewDB.getReviewRecommCount(userNo);
	if (recommCount>0){//좋아요받은횟수 달성 조건
		System.out.println("도전과제 달성"+recommCount);
		achieveDB.insertAchieve(userNo, achieve);
		jsonObject.put("achieve", 1);//도전과제 팝업띄우기용 정보
	}
}*/
/* //이 페이지를 조회하는 myreview.jsp가 json형식으로 볼수있게 정하고
response.setContentType("application/json"); */
//jsonArray를 String형태로 바꿔서 값을 읽을수있게 변환
response.getWriter().write(jsonObject.toString());
%>
