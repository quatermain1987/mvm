<%@ page language="java" contentType="application/json; charset=UTF-8"
    import="java.util.List,org.json.simple.JSONObject,org.json.simple.JSONArray,model.mypage.ReviewDAO,model.mypage.ReviewDTO" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String userNostr=request.getParameter("userNo");
    int userNo=Integer.parseInt(userNostr);
    String startNumstr=request.getParameter("startNum");
    int startNum=Integer.parseInt(startNumstr);
    String endNumstr=request.getParameter("endNum");
    int endNum=Integer.parseInt(endNumstr);
    String query=request.getParameter("query");
    System.out.println("reviewGet.jsp의 userNo="+userNo);
    System.out.println("reviewGet.jsp의 startNum="+startNum);
    System.out.println("reviewGet.jsp의 endNum="+endNum);
    System.out.println("reviewGet.jsp의 query="+query);
    
    //DB연동
    ReviewDAO dbPro=new ReviewDAO();
    List<ReviewDTO> reviewList;
    
    if (query!="")//검색어가 있으면
    	reviewList=dbPro.getReview(userNo,startNum,endNum,query);
    else if (query=="" && endNum!=0)//검색어가 없으면
    	reviewList=dbPro.getReview(userNo,startNum,endNum);
    else //
    	reviewList=dbPro.getReview(userNo);
    
    //jsonObject를 저장할 jsonArray 선언
    JSONArray jsonArray= new JSONArray();
    //받은 리스트를 json형태로 저장
    for(int i=0; i<reviewList.size(); i++){
        ReviewDTO review=reviewList.get(i);

        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("reviewDate", String.valueOf(review.getReviewDate()));
        jsonObject.put("movGrade", review.getMovGrade());
        jsonObject.put("recommCount", review.getRecommCount());
        jsonObject.put("reviewContent", review.getReviewContent());
        jsonObject.put("movTitle", review.getMovTitle());
        jsonObject.put("userNickname", review.getUserNickname());
        jsonObject.put("userPic", review.getUserPic());
        jsonObject.put("review_no", review.getReview_no());
		
        jsonArray.add(jsonObject);
    }
    /* //이 페이지를 조회하는 myreview.jsp가 json형식으로 볼수있게 정하고
    response.setContentType("application/json"); */
    //jsonArray를 String형태로 바꿈
    response.getWriter().write(jsonArray.toString());
%>