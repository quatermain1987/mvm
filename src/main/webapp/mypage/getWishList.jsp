<%@ page language="java" contentType="application/json; charset=UTF-8"
    import="java.util.List,org.json.simple.JSONObject,org.json.simple.JSONArray,model.mypage.DetailDAO,model.mypage.WishDTO" pageEncoding="UTF-8"%>
<%
    String userNostr=request.getParameter("userNo");
   	int user_no=Integer.parseInt(userNostr);
    
    //int user_no=1;
    String startNumstr=request.getParameter("startNum");
    int startNum=Integer.parseInt(startNumstr);
    //String endNumstr=request.getParameter("endNum");
    //int endNum=Integer.parseInt(endNumstr);
    System.out.println("reviewGet.jsp의 userNo="+user_no);
    System.out.println("reviewGet.jsp의 startNum="+startNum);
    //System.out.println("reviewGet.jsp의 endNum="+endNum);
    
     	//DB연동
 		DetailDAO detaildao = new DetailDAO();
		List<WishDTO> wishList = detaildao.getWishList(user_no, startNum);

		JSONArray jsonArray = new JSONArray();

		if (wishList == null) {
	    		response.getWriter().write("0");
		} else {
	    for (int i = 0; i < wishList.size(); i++) {
	        WishDTO wish = wishList.get(i);
	
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("user_no", user_no);
	        jsonObject.put("mov_no", wish.getMov_no());
	        jsonArray.add(jsonObject);
	    }

    	response.getWriter().write(jsonArray.toString());
}
	
%>